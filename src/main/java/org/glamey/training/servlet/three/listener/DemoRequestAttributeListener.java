package org.glamey.training.servlet.three.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * @author zhouyang.zhou.
 * @date 2017.12.03.15.
 */
@WebListener
public class DemoRequestAttributeListener implements ServletRequestAttributeListener {
  @Override public void attributeAdded(ServletRequestAttributeEvent srae) {
    System.out.printf("[request attributeAdded] %s --> %s\n", srae.getName(), srae.getValue());
  }

  @Override public void attributeRemoved(ServletRequestAttributeEvent srae) {
    System.out.printf("[request attributeRemoved] %s --> %s\n", srae.getName(), srae.getValue());
  }

  @Override public void attributeReplaced(ServletRequestAttributeEvent srae) {
    System.out.printf("[request attributeReplaced] %s --> %s\n", srae.getName(), srae.getValue());
  }
}
