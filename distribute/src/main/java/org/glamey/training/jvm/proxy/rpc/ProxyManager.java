package org.glamey.training.jvm.proxy.rpc;

import jline.UnixTerminal;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ProxyManager<T> {
    private Class<T> clazz;

    public ProxyManager(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T getInstance() {
        return (T) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{clazz},
                new ProxyInvocation());
    }

    private class ProxyInvocation implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            RpcContext rpcContext = new RpcContext();
            rpcContext.setMethodName(method.getName());
            Map<String, Object> map = new HashMap<>();
            if (method.getParameterTypes().length > 0) {
                Object arg = args[0];
                Class<?> parameterType = method.getParameterTypes()[0];
                for (Field field : parameterType.getDeclaredFields()) {
                    field.setAccessible(true);
                    map.put(field.getName(), field.get(arg));
                }
            }
            return RpcInvoker.invoke(rpcContext);
        }
    }
}
