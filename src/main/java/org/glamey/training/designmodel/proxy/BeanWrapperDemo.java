package org.glamey.training.designmodel.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author zhouyang.zhou. 2016.12.27.18.
 */
public class BeanWrapperDemo {

  public static void main(String[] args)
          throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

    //通过java.lang.reflect方式设置一个值
    setBeanValueByReflect();
    setBeanValueByBeanWrapper();
  }

  private static void setBeanValueByBeanWrapper() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    Class<?> clazz = Class.forName("org.glamey.training.designmodel.proxy.HelloWordImpl");
    HelloWordImpl target = (HelloWordImpl) clazz.newInstance();
    BeanWrapper beanWrapper = new BeanWrapperImpl(target);
    beanWrapper.setPropertyValue("message", "hello");
    System.out.println(target.getMessage());
    System.out.println(String.format("wrapperClass=%s, wrapperInstance=%s", beanWrapper.getWrappedClass(), beanWrapper.getWrappedInstance()));
  }

  private static void setBeanValueByReflect()
          throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    Class<?> clazz = Class.forName("org.glamey.training.designmodel.proxy.HelloWordImpl");
    HelloWordImpl target = (HelloWordImpl) clazz.newInstance();
    Method setMessageMethod = clazz.getMethod("setMessage", new Class[] {String.class});
    setMessageMethod.invoke(target, "Hello");
    String message = target.getMessage();
    System.out.println(message);
    "".intern();
  }
}
