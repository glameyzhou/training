package org.glamey.training.spring.loading;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhouyang.zhou. 2017.08.30.16.
 */
public class LoadingDemo {

  public static void main(String[] args) {

    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("/spring/applicationContext-loading.xml");
    //Car car = (Car) applicationContext.getBean("car");
    //System.out.println(car);

    ScopeDemo scopeDemo = (ScopeDemo) applicationContext.getBean("scopeDemo");
    scopeDemo.process();

    applicationContext.destroy();
  }
}
