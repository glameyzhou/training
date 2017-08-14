package org.glamey.training.jvm.oom;

import java.lang.reflect.Method;
import org.glamey.training.db.mybatis.domain.UserEntity;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * 测试perm区溢出
 *
 * -Xms100m -Xmx200m -XX:NewSize=50m -XX:PermSize=50m
 *
 * @author zhouyang.zhou. 2017.02.17.15.
 */
public class PermSizeOOMDemo {

  public static void main(String[] args) {
    int count = 0;
    while (true) {
      Enhancer enhancer = new Enhancer();
      enhancer.setSuperclass(UserEntity.class);
      enhancer.setUseCache(false);
      enhancer.setCallback(new MethodInterceptor() {
        @Override public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
          return methodProxy.invoke(o, objects);
        }
      });
      Object object = enhancer.create();
      System.out.printf("%d-->%s\n", count++, (object == null ? null : object.getClass()));
    }
  }
}
