package org.glamey.training.servlet.three;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author zhouyang.zhou.
 * @date 2017.12.03.16.
 */
@WebListener
public class AppContextListener implements ServletContextListener {
  @Override public void contextInitialized(ServletContextEvent sce) {

    ThreadPoolExecutor executor = new ThreadPoolExecutor(50,
                                                         100,
                                                         5000L,
                                                         TimeUnit.MILLISECONDS,
                                                         new ArrayBlockingQueue<Runnable>(500),
                                                         new ThreadFactory() {
                                                           @Override public Thread newThread(Runnable r) {
                                                             Thread thread = new Thread(r);
                                                             thread.setName("Request Processor");
                                                             return thread;
                                                           }
                                                         },
                                                         new RejectedExecutionHandler() {
                                                           @Override public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                                                             System.out.printf("thread was rejected...");
                                                           }
                                                         });

    sce.getServletContext().setAttribute(ServletConstants.EXECUTOR, executor);
  }

  @Override public void contextDestroyed(ServletContextEvent sce) {
    Object attribute = sce.getServletContext().getAttribute(ServletConstants.EXECUTOR);
    if(attribute == null) {
      return;
    }

    ThreadPoolExecutor executor = (ThreadPoolExecutor) attribute;
    if(!executor.isShutdown()) {
      executor.shutdownNow();
    }
  }
}
