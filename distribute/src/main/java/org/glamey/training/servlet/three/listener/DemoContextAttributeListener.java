package org.glamey.training.servlet.three.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * @author zhouyang.zhou.
 * @date 2017.12.03.15.
 */
@WebListener
public class DemoContextAttributeListener implements ServletContextAttributeListener {

  @Override public void attributeAdded(ServletContextAttributeEvent event) {
    System.out.printf("[Context attributeAdded] %s --> %s\n", event.getName(), event.getValue());
  }

  @Override public void attributeRemoved(ServletContextAttributeEvent event) {
    System.out.printf("[Context attributeRemoved] %s --> %s\n", event.getName(), event.getValue());
  }

  @Override public void attributeReplaced(ServletContextAttributeEvent event) {
    System.out.printf("[Context attributeReplaced] %s --> %s\n", event.getName(), event.getValue());
  }
}
