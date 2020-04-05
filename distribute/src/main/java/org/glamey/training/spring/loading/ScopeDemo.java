package org.glamey.training.spring.loading;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author zhouyang.zhou. 2017.08.30.16.
 */
public class ScopeDemo implements InitializingBean, DisposableBean {

  private String message;
  public void process() {
    System.out.println("bean process..." + message);
  }

  @Override public void destroy() throws Exception {
    System.out.println("bean destroy...");
  }

  @Override public void afterPropertiesSet() throws Exception {
    System.out.println("bean init...");
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
