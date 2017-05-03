package org.glamey.training.jvm.loader;

/**
 * @author zhouyang.zhou. 2017.01.04.17.
 */
public class CustomClassLoader extends ClassLoader {

  public CustomClassLoader(ClassLoader parent) {
    super(parent);
  }

  public Class loadClass(String name) {
    return null;
  }
}
