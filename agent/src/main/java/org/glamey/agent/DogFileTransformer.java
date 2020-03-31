package org.glamey.agent;

import lombok.SneakyThrows;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class DogFileTransformer implements ClassFileTransformer {

    /**
     * return null 表示没有任何拦截转换
     *
     * @param loader
     * @param className
     * @param classBeingRedefined
     * @param protectionDomain
     * @param classfileBuffer
     * @return
     * @throws IllegalClassFormatException
     */
    @SneakyThrows
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("DogFileTransformer is coming...");
        if ("org/glamey/agent/Dog".equals(className)) {
            System.out.println("DogFileTransformer is invoked");
        }
        return null;
    }
}
