package org.glamey.training.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * java.lang.OutOfMemoryError: GC overhead limit exceeded -Xms=20m -Xmx=20m -XX:NewSize=10m -XX:PermSize=10m
 *
 * @author zhouyang.zhou. 2017.09.14.09.
 */
public class HeapOOMDemo {

  private int count = 1;

  private void allocate() {
    /*while (true) {
      byte[] bytes = new byte[10240];
      System.out.printf("count=%d, bytes.length=%d\n", count++, bytes.length);
      //count=3664000, bytes.length=10240
      //try {
      //  TimeUnit.MILLISECONDS.sleep(100);
      //} catch (InterruptedException e) {
      //  e.printStackTrace();
      //}
    }*/

    List<OOMObject> list = new ArrayList<>();
    while (true) {
      list.add(new OOMObject());
      System.out.println(count);
      count++;
    }
  }

  public static void main(String[] args) {
    HeapOOMDemo demo = new HeapOOMDemo();
    demo.allocate();
  }
}
