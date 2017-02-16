package org.glamey.training.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zhouyang.zhou. 2016.12.26.14.
 */
public class CustomerInvocationHandler implements InvocationHandler {
  private Object target;

  public CustomerInvocationHandler(Object target) {
    this.target = target;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println(String.format("start invoke %s-->%s", target.getClass().getName(), method.getName()));
    Object invoke = method.invoke(target, args);
    System.out.println(String.format("end invoke %s-->%s", target.getClass().getName(), method.getName()));
    return invoke;
  }
}
