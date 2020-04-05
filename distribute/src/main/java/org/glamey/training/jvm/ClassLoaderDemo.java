package org.glamey.training.jvm;

/**
 * @author zhouyang.zhou. 2016.12.26.15.
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoaderDemo.class.getClassLoader();
        System.out.println(classLoader);  // system class loader
        System.out.println(classLoader.getParent()); // extension class loader
        System.out.println(classLoader.getParent().getParent()); //bootstrap class loader 使用C实现，获取不到
    }
}
