package org.glamey.training.spring.loading;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * default constructor --> beanPostProcessor.before --> InitialingBean --> init-method --> beanPostProcessor.after --> disposableBean --> destroy-method
 *
 * @author zhouyang.zhou. 2017.08.31.10.
 */
public class BeanInitTest {
  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext-initBeanDemo.xml");

    //init
    InitBeanDemo initBeanDemo = (InitBeanDemo) context.getBean("initBeanDemo");
    System.out.println(initBeanDemo.getName());
    System.out.println(initBeanDemo.getAddress());
    System.out.println(initBeanDemo.getBeanFactory());
    System.out.println(initBeanDemo.getBeanName());
    System.out.println(initBeanDemo.getCar());

    //beanPostProcessor
    //context.getBean("beanPostProcessorDemo");

    //
    context.destroy();
  }
}
