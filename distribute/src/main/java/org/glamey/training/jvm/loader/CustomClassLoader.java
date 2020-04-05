package org.glamey.training.jvm.loader;

import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义classloader
 * <p>
 * 判断两个类是否相同，必须看是否为同一个classLoader
 *
 * @author zhouyang.zhou. 2017.01.04.17.
 */
public class CustomClassLoader {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = new ClassLoader() {
            /**
             * 不提倡重写loadClass,而应该使用findClass。只有当父类加载器失败的时候，会走findClass()
             *
             * {@link ClassLoader#loadClass(String)}
             * 重写loadClass 破坏了双亲委派
             *
             *
             * {@link ClassLoader#findClass(String)}
             * 重新findClass 不会破坏双亲委派
             * @param name
             * @return
             * @throws ClassNotFoundException
             */
            @Override
            public Class findClass(String name) throws ClassNotFoundException {
                String filename = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream inputStream = getClass().getResourceAsStream(filename);
                if (inputStream == null) {
                    return super.loadClass(name);
                }


                try {
                    byte[] buffer = new byte[inputStream.available()];
                    inputStream.read(buffer);
                    return super.defineClass(name, buffer, 0, buffer.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        Object obj = classLoader.loadClass("org.glamey.training.jvm.loader.CustomClassLoader").newInstance();
        System.out.println(obj);

        System.out.println(obj instanceof CustomClassLoader);


        //classloader
        ClassLoader cLoader = CustomClassLoader.class.getClassLoader();
        /**
         * sun.misc.Launcher$AppClassLoader@18b4aac2 app classloader
         *
         * 内容是有ClassLoader.getSystemClassLoader()返回
         *
         * 系统类加载器，负责加载用户类路径下classpath上指定的所有类库
         */
        System.out.println(cLoader);
        /**
         * sun.misc.Launcher$ExtClassLoader@7229724f ext classloader
         * java\lib\ext中包含的内容
         */
        System.out.println(cLoader.getParent());
        System.out.println(cLoader.getParent().getParent()); // 类启动加载器 bootstrap classloader --> null

    }
}
