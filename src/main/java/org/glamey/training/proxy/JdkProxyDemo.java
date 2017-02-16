package org.glamey.training.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * @author zhouyang.zhou. 2016.12.26.14.
 */
public class JdkProxyDemo {
  public static void main(String[] args) throws Exception {
    proxy_1();

    System.out.println("------------------------------------>");
    proxy_2();

    System.out.println("------------------------------------->");
  }

  private static void proxy_2() {
    HelloWord helloWord =
            (HelloWord) Proxy.newProxyInstance(JdkProxyDemo.class.getClassLoader(), new Class<?>[] {HelloWord.class},
                    new CustomerInvocationHandler(new HelloWordImpl()));
    helloWord.sayHello("glamey");
  }

  private static void proxy_1() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    final Class<?> proxyClass = Proxy.getProxyClass(JdkProxyDemo.class.getClassLoader(), HelloWord.class);
    final Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
    final InvocationHandler invocationHandler = new CustomerInvocationHandler(new HelloWordImpl());
    HelloWord helloWord = (HelloWord) constructor.newInstance(invocationHandler);
    helloWord.sayHello("glamey");
  }
}
