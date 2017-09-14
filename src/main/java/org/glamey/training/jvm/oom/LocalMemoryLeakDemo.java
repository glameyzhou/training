package org.glamey.training.jvm.oom;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/**
 * 本地内存溢出情况，通过UNSafe直接在本地内存分配
 *
 * -XX:MaxDirectMemorySize=10m
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
