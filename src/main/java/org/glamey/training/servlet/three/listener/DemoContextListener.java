package org.glamey.training.servlet.three.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author zhouyang.zhou.
 * @date 2017.12.03.15.
 */
@WebListener
public class DemoContextListener implements ServletContextListener {
  @Override public void contextInitialized(ServletContextEvent sce) {
    System.out.printf("servlet context listener [started]\n");
  }

  @Override public void contextDestroyed(ServletContextEvent sce) {
    System.out.printf("servlet context listener [destroyed]\n");
  }
}
