package com.uxiangtech.activitybox.engine;

import java.lang.reflect.InvocationTargetException;

public class CustomClassLoader extends ClassLoader {
  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {
    return super.loadClass(name, true);
  }

  //  @Override
//  public Class<?> loadClass(String name) throws ClassNotFoundException {
//    synchronized (getClassLoadingLock(name)) {
//      // 首先尝试查找已经加载的类
//      Class<?> c = findLoadedClass(name);
//      if (c == null) {
//        long t0 = System.nanoTime();
//        try {
//          // 如果没有加载，则使用默认的类加载器加载
//          c = loadClass(name, true);
//        } catch (ClassNotFoundException e) {
//          // 如果默认的类加载器也没有找到，则尝试自定义的加载逻辑
//          long t1 = System.nanoTime();
//          c = findClass(name);
//          // 这里可以执行静态块
//          if (c != null) {
//            resolveClass(c);
//          }
//          // 记录加载时间
//          sun.misc.PerfCounter.getFindClassTime().addTime(t1 - t0);
//          if (c == null) {
//            throw new ClassNotFoundException(name);
//          }
//        }
//      }
//      return c;
//    }
//  }
//
//  protected Class<?> findClass(String name) throws ClassNotFoundException {
//    try {
//      String path = name.replace('.', '/') + ".class";
//      InputStream is = getClass().getResourceAsStream(path);
//      if (is == null) {
//        throw new ClassNotFoundException(name);
//      }
//
//      ByteArrayOutputStream baos = new ByteArrayOutputStream();
//      byte[] bytes = new byte[1024];
//
//      while (true) {
//        int n = is.read(bytes);
//        if (n == -1) {
//          break;
//        }
//        baos.write(bytes, 0, n);
//      }
//
//      byte[] byteArray = baos.toByteArray();
//
//      return defineClass(name, byteArray, 0, byteArray.length);
//    } catch (IOException e) {
//      throw new ClassNotFoundException(name, e);
//    }
//  }

  public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    CustomClassLoader classLoader = new CustomClassLoader();
    Class<?> clazz = classLoader.loadClass("com.uxiangtech.activitybox.engine.StaticBlockTest");

    // 通过反射执行静态块
    Class.forName("com.uxiangtech.activitybox.engine.StaticBlockTest", false, classLoader);


  }
}
