package org.glamey.training.jvm.loader.executor;

public class HotSwapClassLoader extends ClassLoader {

    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadClass(byte[] bytes) {
        return defineClass(null, bytes, 0, bytes.length);
    }
}
