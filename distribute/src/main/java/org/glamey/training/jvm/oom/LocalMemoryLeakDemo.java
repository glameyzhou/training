package org.glamey.training.jvm.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 本地内存溢出情况，通过UNSafe直接在本地内存分配
 *
 * -XX:MaxDirectMemorySize=10m，如果不设定此值，将与-Xmx保持一致
 *
 * @author zhouyang.zhou. 2017.09.14.09.
 */
public class LocalMemoryLeakDemo {
  private static final int _1MB = 1024 * 1024;

  private void allocateFromLocalMemory() {
    try {
      Field field = Unsafe.class.getDeclaredFields()[0];
      field.setAccessible(true);
      Unsafe o = (Unsafe) field.get(null);
      while (true) {
        o.allocateMemory(_1MB);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    LocalMemoryLeakDemo demo = new LocalMemoryLeakDemo();
    demo.allocateFromLocalMemory();
  }
}
