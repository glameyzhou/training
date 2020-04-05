package org.glamey.training.jvm.proxy.bytebuddy;

import com.google.common.collect.Lists;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

import java.util.List;

/**
 * @author yang.zhou 2020.01.14.23
 */
public class ToStringByteBuddy {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class<?> loaded = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello Byte Buddy"))
                .make()
                .load(ToStringByteBuddy.class.getClassLoader())
                .getLoaded();


        Object object = loaded.newInstance();
        String toString = object.toString();
        System.out.println(object.getClass().getCanonicalName() + "  " + toString);


        List<String> list = Lists.newArrayList("a", "b");

        System.out.println(list.toString());
    }
}
