package org.glamey.training.jvm.oom;

import java.lang.reflect.Method;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * Perm OOM：方法区主要存放类信息、常量信息等。 我们可以通过动态生成代理类的方式。
 *
 * -Xms100m -Xmx100m -XX:NewSize=10m -XX:PermSize=10m
 *
 * @author zhouyang.zhou. 2017.09.14.09.
 */
public class PermOOMDemo {

  public void generateDynamicClass() {
    int count = 1;
    while (true) {
      Enhancer enhancer = new Enhancer();
      enhancer.setUseCache(false);
      enhancer.setSuperclass(OOMObject.class);
      enhancer.setCallback(new MethodInterceptor() {
        @Override public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
          return methodProxy.invoke(o, objects);
        }
      });
      OOMObject object = (OOMObject) enhancer.create();
      System.out.printf("count=%d, object=%s\n", count, object != null ? object.getClass() : null);
      //org.glamey.training.jvm.oom.OOMObject$$EnhancerByCgLIB$$xxxx
      ++count;
    }
  }

  public static void main(String[] args) {
    PermOOMDemo demo = new PermOOMDemo();
    demo.generateDynamicClass();
  }
}
