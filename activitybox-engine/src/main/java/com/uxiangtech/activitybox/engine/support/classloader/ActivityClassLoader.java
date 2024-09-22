package com.uxiangtech.activitybox.engine.support.classloader;

/**
 * 活动的类加载器，实现不同活动间共用相同玩法代码时的类隔离，避免类定义冲突
 */
public class ActivityClassLoader extends ClassLoader {
}
