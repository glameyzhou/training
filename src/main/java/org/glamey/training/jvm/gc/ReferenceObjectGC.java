package org.glamey.training.jvm.gc;

/**
 * 引用计数器GC
 *
 *  -Xmx10M -Xms10M -XX:+PrintGCDetails
 *
 *  -XX:+UseConcMarkSweepGC
 *
 *
 *  [Full GC (System.gc()) [PSYoungGen: 496K->0K(2560K)] [ParOldGen: 3336K->3625K(7168K)] 3832K->3625K(9728K), [Metaspace: 2996K->2996K(1056768K)], 0.0039711 secs] [Times: user=0.03 sys=0.01, real=0.00 secs]
 *
 *
 *  ParOldGen: 3336K->3625K(7168K)  fullGC之后，内存大小没法发生变化，说明对象互相引用并没有被回收。
 *
 * @author yang.zhou 2019.11.15.15
 */
public class ReferenceObjectGC {

    public Object instance = null;

    private static final int _1M = 1024 * 1024;

    public static void testGC() {
        byte[] buffer = new byte[_1M * 3]; //主要为了方便GC日志查看

        ReferenceObjectGC objA = new ReferenceObjectGC();
        ReferenceObjectGC objB = new ReferenceObjectGC();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        System.gc();
    }

    public static void main(String[] args) {
        testGC();
    }
    
}
