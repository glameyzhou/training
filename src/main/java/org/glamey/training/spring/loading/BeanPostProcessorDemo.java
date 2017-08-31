package org.glamey.training.spring.loading;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author zhouyang.zhou. 2017.08.31.10.
 */
public class BeanPostProcessorDemo implements BeanPostProcessor {

  public BeanPostProcessorDemo() {
    super();
    System.out.println("BeanPostProcessDemo constructor");
  }

  @Override public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("BeanPostProcessDemo postProcessBeforeInitialization");
    return bean;
  }

  @Override public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("BeanPostProcessDemo postProcessAfterInitialization");
    return bean;
  }
}
