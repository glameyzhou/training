package org.glamey.training.jvm.loader.executor;

import com.google.common.io.Files;

import java.io.File;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws Exception {
        File classFile = new File("/Users/yang.zhou/idea_project/training/algorithm/target/classes/org/glamey/training/algorithm/sort/BubbleSort.class");
        byte[] bytes = Files.toByteArray(classFile);


        for (int i = 0; i < 10; i++) {
            HotSwapClassLoader classLoader = new HotSwapClassLoader();
            Class clazz = classLoader.loadClass(bytes);

            Method mainMethod = clazz.getMethod("main", new Class[]{String[].class});
            Object invoke = mainMethod.invoke(null, new String[]{null});
            System.out.println(invoke);
        }

    }
}
