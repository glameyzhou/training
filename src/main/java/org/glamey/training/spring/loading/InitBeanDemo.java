package org.glamey.training.spring.loading;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author zhouyang.zhou. 2017.08.31.10.
 */
public class InitBeanDemo implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {

  private String name;
  private String address;

  private Car car;

  private BeanFactory beanFactory;
  private String beanName;

  @Override public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    System.out.println("BeanFactoryAware set beanFactory");
    this.beanFactory = beanFactory;
  }

  @Override public void setBeanName(String name) {
    System.out.println("BeanNameAware set beanName");
    this.beanName = name;
  }

  @Override public void destroy() throws Exception {
    System.out.println("DisposableBean destroy bean");
  }

  @Override public void afterPropertiesSet() throws Exception {
    System.out.println("InitialingBean afterPropertiesSet");
  }

  public void initMethod() {
    System.out.println("<bean init-method=\"initMethod\">");
  }

  public void destroyMethod() {
    System.out.println("<bean destroy-method=\"destroyMethod\">");
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    System.out.println("set property name");
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    System.out.println("set property address");
    this.address = address;
  }

  public void setCar(Car car) {
    System.out.println("set property ref bean car");
    this.car = car;
  }

  public Car getCar() {
    return car;
  }

  public BeanFactory getBeanFactory() {
    return beanFactory;
  }

  public String getBeanName() {
    return beanName;
  }
}
