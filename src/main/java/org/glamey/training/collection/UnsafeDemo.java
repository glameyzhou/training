package org.glamey.training.collection;

import java.util.concurrent.atomic.AtomicInteger;
import sun.misc.Unsafe;

/**
 * @author zhouyang.zhou. 2017.04.27.16.
 */
public class UnsafeDemo {
  private static final Unsafe unsafe = Unsafe.getUnsafe();
  private static final long valueOffset;

  static {
    try {
      valueOffset = unsafe.objectFieldOffset
          (AtomicInteger.class.getDeclaredField("value"));
    } catch (Exception ex) {
      throw new Error(ex);
    }
  }

  private volatile int value;

  public UnsafeDemo() {
  }

  public UnsafeDemo(int initialValue) {
    value = initialValue;
  }

  public final int get() {
    return value;
  }

  public final void set(int newValue) {
    value = newValue;
  }
}
