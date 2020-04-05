package org.glamey.training.servlet.three.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * @author zhouyang.zhou.
 * @date 2017.12.03.15.
 */
@WebListener
public class DemoRequestListener implements ServletRequestListener {

  @Override public void requestInitialized(ServletRequestEvent sre) {
    System.out.printf("servlet Request listener [started]\n");
  }

  @Override public void requestDestroyed(ServletRequestEvent sre) {
    System.out.printf("servlet Request listener [destroyed]\n");
  }
}
