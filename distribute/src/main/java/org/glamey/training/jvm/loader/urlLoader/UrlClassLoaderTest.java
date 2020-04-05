package org.glamey.training.jvm.loader.urlLoader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yang.zhou 2020.02.04.10
 */
public class UrlClassLoaderTest {

    public static void printClassLoader() {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("SystemClassLoader ==> " + systemClassLoader);
        ClassLoader classLoader = UrlClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);
        do {
            classLoader = classLoader.getParent();
            System.out.println(classLoader);
        } while (classLoader != null);
    }


    public static void process() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String className = "java.lang.String";
        Class<?> clazz = Class.forName(className);
        Constructor<?> constructor = clazz.getConstructor(new Class[]{String.class});
        constructor.setAccessible(true);
        Object object = constructor.newInstance("Hello World");
        Method lengthMethod = clazz.getDeclaredMethod("length");
        Object length = lengthMethod.invoke(object, null);
        System.out.println(length);
        System.out.println(clazz);
        System.out.println(clazz.getClassLoader());

    }



    public static void main(String[] args) throws Exception {
        printClassLoader();
        process();
    }


}
