package org.glamey.training.jvm.proxy.javassist;

import javassist.*;
import org.glamey.training.jvm.proxy.Processor;

/**
 * 通过javassist动态修改相关的class中的方法
 *
 * [将source进行编译，生成新的字节码] {@link CtNewMethod#make(String, CtClass)}
 * <p>
 * 例子：为{@link Processor#process()}方法增加耗时输出。
 *      流程：老method改名字，新增一个method,名字与老method一致，方法内调用老方法即可。
 *
 * @author yang.zhou 2020.01.07.18
 */
public class JavassistProxy {


    public static void main(String[] args) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        String className = "org.glamey.training.jvm.proxy.Processor";
        String methodName = "process";

        //ClassPool中获取指定class的相关实例
        CtClass clazz = ClassPool.getDefault().get(className);
        CtMethod method = clazz.getDeclaredMethod(methodName);

        String newMethodName = methodName + "$Origin";
        method.setName(newMethodName);

        String newMethodSource = "public void process() {" +
                "long startTime = System.currentTimeMillis();" +
                newMethodName + "();" +
                "System.out.println(\"耗时:\"+(System.currentTimeMillis() - startTime)+\"ms\");" +
                "}";
        CtMethod newMethod = CtMethod.make(newMethodSource, clazz);
        clazz.addMethod(newMethod);

        Processor processor = (Processor) clazz.toClass().newInstance();
        processor.process();
    }
}
