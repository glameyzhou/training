package org.glamey.training.designmodel;

import org.glamey.training.designmodel.singleton.SingletonV1;
import org.glamey.training.designmodel.singleton.SingletonV2;
import org.glamey.training.designmodel.singleton.SingletonV3;
import org.glamey.training.designmodel.singleton.SingletonV4;
import org.glamey.training.designmodel.singleton.SingletonV5;
import org.junit.Test;

/**
 * @author zhouyang.zhou. 2017.01.10.16.
 */
public class SingletonTest {

  @Test
  public void getInstance() throws Exception {
    for (int i = 0; i < 100; i++) {
      new Thread(new Runnable() {
        public void run() {
          System.out.println(SingletonV1.getInstance());
          System.out.println(SingletonV2.getInstance());
          System.out.println(SingletonV3.getInstance());
          System.out.println(SingletonV4.getInstance());
          System.out.println(SingletonV5.INSTANCE.getInstance());
          System.out.println("-----------------------");
        }
      }).start();
    }
  }
}