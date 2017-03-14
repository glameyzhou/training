package org.glamey.training.jvm.oom;

import java.lang.reflect.Method;
import org.glamey.training.mybatis.domain.UserEntity;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * 测试perm区溢出
 *
 * @author zhouyang.zhou. 2017.02.17.15.
 */
public class PermSizeOOMDemo {

  public static void main(String[] args) {
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
      System.out.println(object == null ? null : object.getClass());
    }
  }
}
