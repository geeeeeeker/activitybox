package com.uxiangtech.activitybox.engine.support.classloader;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.Problem;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.uxiangtech.activitybox.engine.modules.playways.ActPlayway;
import com.uxiangtech.activitybox.engine.modules.playways.StdPlaywayProxy;
import com.uxiangtech.activitybox.engine.support.compiler.ClassMeta;
import com.uxiangtech.activitybox.engine.support.compiler.CodeCompiler;
import com.uxiangtech.activitybox.engine.support.compiler.CodeCompilerFactory;
import com.uxiangtech.activitybox.engine.support.parser.CodeParsedResult;
import com.uxiangtech.activitybox.engine.support.parser.CodeParser;
import com.uxiangtech.activitybox.sdk.playways.StdPlayway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class JavaBasedPlaywayFactory implements PlaywayFactory {

  private static final Logger LOGGER =
    LoggerFactory.getLogger(JavaBasedPlaywayFactory.class);

  private final CodeParser parser;

  private final CodeCompiler compiler;

  private final PlaywayClassLoader playwayClassLoader;

  public JavaBasedPlaywayFactory(final ClassLoader parent) {
    this.parser = new JavaCodeParser();
    final JavacCodeCompilerFactory javacCodeCompilerFactory = new JavacCodeCompilerFactory();
    this.compiler = javacCodeCompilerFactory.getCompiler();
    this.playwayClassLoader = new PlaywayClassLoader(parent);
  }

  @Override
  public Map<String, StdPlayway> newInstances(final String code) {

    final Map<String, StdPlayway> playwayInstanceMap = new HashMap<>();

    // 1. 源码静态解析

    final CodeParsedResult codeParsedResult = this.parser.parse(code);

    // 2. 源码编译生成字节码
    this.compiler.compile(codeParsedResult);

    // 3. 加载字节码，并创建玩法实例
    for (ClassMeta classMeta : codeParsedResult.getClassMetas()) {

      try {

        final Class<?> clazz =
          this.playwayClassLoader.loadClass(classMeta.getFullQualifiedName());

        // 新建实例
        final Object instance = clazz.newInstance();

        // 注解优先，未发现注解则使用接口约定的玩法ID，若都没有则忽略
        String playwayId = null;
        final ActPlayway actPlaywayAnno =
          clazz.getAnnotation(ActPlayway.class);
        if (null != actPlaywayAnno) {
          playwayId = actPlaywayAnno.id();
          final String playwayName = actPlaywayAnno.name();
          StdPlaywayProxy stdPlaywayProxy = new StdPlaywayProxy(playwayId, playwayName, instance);
          playwayInstanceMap.put(playwayId, stdPlaywayProxy);
        } else {
          if (instance instanceof StdPlayway) {
            final StdPlayway stdPlayway = (StdPlayway) instance;
            playwayId = stdPlayway.getId();
            if (null != playwayId) {
              playwayInstanceMap.put(playwayId, stdPlayway);
            }
          }
        }

      } catch (final ClassNotFoundException | InstantiationException | IllegalAccessException e) {
        throw new RuntimeException(e);
      }

    }

    return playwayInstanceMap;
  }

  /**
   * 玩法类加载器
   */
  private final class PlaywayClassLoader extends ClassLoader {

    private Map<String, byte[]> classfileBytesCache;

    private ConcurrentMap<String, Class<?>> classCache = new ConcurrentHashMap<>();

    public PlaywayClassLoader(final ClassLoader parent) {
      super(parent);
    }

    /**
     * 自定义类检索方式，使用classfile字节信息加载Class
     *
     * @param name 完全限定名（FullQualifiedName），或称为binary name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

      // 缓存类，避免重复加载
      Class<?> clazz = this.classCache.get(name);
      if (null != clazz) {
        return clazz;
      }

      // 根据完全限定名（binary name）获取字节码文件二进制数据
      final byte[] classfileBytes = this.classfileBytesCache.get(name);
      if (null != classfileBytes) {
        clazz = this.defineClass(name, classfileBytes, 0, classfileBytes.length);
        this.classCache.put(name, clazz);
        return clazz;
      }

      // 直接抛出 ClassNotFoundExcetpion
      return super.findClass(name);
    }

    public Map<String, byte[]> getClassfileBytesCache() {
      if (null == this.classfileBytesCache) {
        return new HashMap<>();
      } else {
        return this.classfileBytesCache;
      }
    }

    public void setClassfileBytesCache(Map<String, byte[]> classfileBytesCache) {
      this.classfileBytesCache = classfileBytesCache;
    }
  }

  /**
   * 代码元数据解析器
   */
  private final class JavaCodeParser implements CodeParser {

    private final JavaParser javaParser = new JavaParser();

    /**
     * 解析Java源代码，可能包含多个类
     *
     * @param code
     * @return
     */
    public CodeParsedResult parse(final String code) {
      final ParseResult<CompilationUnit> parsedResult = this.javaParser.parse(code);
      if (!parsedResult.isSuccessful()) {
        final List<Problem> problems = parsedResult.getProblems();
        final StringBuilder builder = new StringBuilder();
        for (final Problem problem : problems) {
          builder.append(problem.toString()).append("\r\n");
        }
        final String problemsStr = builder.toString();
        throw new RuntimeException(problemsStr);
      }

      final CompilationUnit compilationUnit = parsedResult.getResult().get();

      String packageName = null;
      final Optional<PackageDeclaration> packageDeclaration = compilationUnit.getPackageDeclaration();
      if (packageDeclaration.isPresent()) {
        packageName = packageDeclaration.get().getNameAsString();
      }

      final List<ClassMeta> javaClassMetas = new ArrayList<>();

      final NodeList<TypeDeclaration<?>> typeDeclarations = compilationUnit.getTypes();
      for (TypeDeclaration<?> typeDeclaration : typeDeclarations) {
        final String simpleName = typeDeclaration.getName().asString();

        String fullQualifiedName = null;
        if (null == packageName) {
          fullQualifiedName = simpleName;
        } else {
          fullQualifiedName = packageName + "." + simpleName;
        }

        final ClassMeta javaClassMeta = new ClassMeta();
        javaClassMeta.setPackageName(packageName);
        javaClassMeta.setSimpleName(simpleName);
        javaClassMeta.setFullQualifiedName(fullQualifiedName);
        javaClassMeta.setCode(code);

        javaClassMetas.add(javaClassMeta);
      }

      return new CodeParsedResult(code, javaClassMetas);
    }
  }

  /**
   * Javac代码编译器工厂
   */
  public class JavacCodeCompilerFactory implements CodeCompilerFactory {
    @Override
    public CodeCompiler getCompiler() {
      return new JavacCodeCompiler();
    }
  }

  /**
   * Javac代码编译器
   */
  private class JavacCodeCompiler implements CodeCompiler {

    private final JavaCompiler javac = ToolProvider.getSystemJavaCompiler();

    @Override
    public void compile(final CodeParsedResult codeParsedResult) {

      // 编译诊断监听器，用于获取编译错误信息
      final FormattedDiagnosticCollector diagnosticCollector = new FormattedDiagnosticCollector();

      // 编译文件管理器，用于获取编译后的字节码
      final StandardJavaFileManager standardFileManager =
        this.javac.getStandardFileManager(diagnosticCollector, null, null);
      final ClassfileBytesJavaFileManager classfileBytesJavaFileManager = new ClassfileBytesJavaFileManager(standardFileManager);

      // 编译字节码的Java文件集合  注意：文本代码中可能包含多个类，因此需要使用集合
      final List<JavaFileObject> javaFileObjects = new ArrayList<>();
      for (ClassMeta classMeta : codeParsedResult.getClassMetas()) {
        final JavaFileObject javaFileObject = new InMemStrJavaFileObject(classMeta.getSimpleName(), codeParsedResult.getCode());
        javaFileObjects.add(javaFileObject);
      }

      // 根据编译单元，构建编译任务
      final JavaCompiler.CompilationTask compilationTask =
        javac.getTask(null /* 错误信息输出至 system.err */, classfileBytesJavaFileManager, diagnosticCollector, null, null, javaFileObjects);

      // 执行编译任务
      final Boolean isTaskCalledOk = compilationTask.call();

      if (!isTaskCalledOk) {

        // 构建编译失败诊断信息
        final String formatErrMsg = diagnosticCollector.formatMessage();

        throw new RuntimeException(formatErrMsg);

      }

      // 编译成功后，获取字节码文件字节数组
      Map<String, byte[]> classfileBytesCache = classfileBytesJavaFileManager.getClassfileBytesCache();
      JavaBasedPlaywayFactory.this.playwayClassLoader.setClassfileBytesCache(classfileBytesCache);
    }
  }

  /**
   * 内存字符串形式Java源代码文件对象
   */
  private final class InMemStrJavaFileObject extends SimpleJavaFileObject {

    private final String simpleClassName;
    private final String code;

    public InMemStrJavaFileObject(final String simpleClassName, final String code) {
      super(URI.create("string:///" + simpleClassName.replaceAll("\\.", "/") + Kind.SOURCE.extension), Kind.SOURCE);
      this.simpleClassName = simpleClassName;
      this.code = code;
    }

    public String getSimpleClassName() {
      return simpleClassName;
    }

    @Override
    public CharSequence getCharContent(final boolean ignoreEncodingErrors) {
      return code;
    }

  }


  /**
   * 字节码文件对象 JavaFileObject
   */
  private final class ClassfileBytesJavaFileObject extends SimpleJavaFileObject {

    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

    /**
     * 字节码使用完全限定名定位URI
     *
     * @param fullQualifiedName
     * @param kind
     */
    public ClassfileBytesJavaFileObject(final String fullQualifiedName, final Kind kind) {
      super(URI.create("string:///" + fullQualifiedName.replaceAll("\\.", "/") + Kind.SOURCE.extension), kind);
    }

    /**
     * 用于保存编译后的字节码信息，以二进制格式保存
     *
     * @return
     * @throws IOException
     */
    @Override
    public OutputStream openOutputStream() throws IOException {
      return this.baos;
    }

    /**
     * 获取编译后的字节码字节数组
     *
     * @return
     */
    public byte[] getClassFileBytes() {
      return this.baos.toByteArray();
    }
  }

  /**
   * 字节码文件管理器，用于获取编译后的字节码
   */
  private final class ClassfileBytesJavaFileManager extends ForwardingJavaFileManager<StandardJavaFileManager> {

    private final Map<String, ClassfileBytesJavaFileObject> classfileJavaFileObjectCache = new HashMap<>();

    public ClassfileBytesJavaFileManager(StandardJavaFileManager standardJavaFileManager) {
      super(standardJavaFileManager);
    }

    /**
     * 保存编译后的class文件对象，编辑过程中会回调该方法
     *
     * @param location  a location
     * @param className the name of a class
     * @param kind      the kind of file, must be one of {@link
     *                  JavaFileObject.Kind#SOURCE SOURCE} or {@link
     *                  JavaFileObject.Kind#CLASS CLASS}
     * @param sibling   a file object to be used as hint for placement;
     *                  might be {@code null}
     * @return
     */
    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) {

      final String fullQualifiedName = className;

      final ClassfileBytesJavaFileObject javaFileObject = new ClassfileBytesJavaFileObject(fullQualifiedName, kind);

      // 注意：此处必须缓存成ClassfileBytesJavaFileObject，因为此时openOutputStream方法还未被打开，字节码文件数据尚未写入输出流
      //
      // package com.sun.tools.javac.jvm;
      // public class ClassWriter extends ClassFile {
      // public JavaFileObject writeClass(ClassSymbol c)
      //        throws IOException, PoolOverflow, StringOverflow
      //    {
      //        JavaFileObject outFile
      //            = fileManager.getJavaFileForOutput(CLASS_OUTPUT,
      //                                               c.flatname.toString(),
      //                                               JavaFileObject.Kind.CLASS,
      //                                               c.sourcefile);
      //        OutputStream out = outFile.openOutputStream();
      //        try {
      //            writeClassFile(out, c);
      //            if (verbose)
      //                log.printVerbose("wrote.file", outFile);
      //            out.close();
      //            out = null;
      //        } finally {
      //            if (out != null) {
      //                // if we are propagating an exception, delete the file
      //                out.close();
      //                outFile.delete();
      //                outFile = null;
      //            }
      //        }
      //        return outFile; // may be null if write failed
      //    }
      // }
      this.classfileJavaFileObjectCache.put(fullQualifiedName, javaFileObject);

      return javaFileObject;
    }

    /**
     * 获取编译后的字节码文件二进制对象，用于类加载器运行时加载字节码信息
     *
     * @return
     */
    public Map<String, byte[]> getClassfileBytesCache() {
      final Map<String, byte[]> classfileBytesCache = new HashMap<>();
      this.classfileJavaFileObjectCache.forEach((key, value) -> {
        classfileBytesCache.put(key, value.getClassFileBytes());
      });
      return classfileBytesCache;
    }
  }


  private final class FormattedDiagnosticCollector implements DiagnosticListener<JavaFileObject> {
    private final List<Diagnostic<? extends JavaFileObject>> diagnostics = new ArrayList<>();

    @Override
    public void report(Diagnostic<? extends JavaFileObject> diagnostic) {
      this.diagnostics.add(diagnostic);
    }

    public String formatMessage() {
      StringBuilder builder = new StringBuilder();
      for (Diagnostic diagnostic : this.diagnostics) {
        // TODO toString() 如何实现？
        builder.append(diagnostic.toString()).append("\r\n");
      }
      return builder.toString();
    }
  }
}
