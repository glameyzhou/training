package org.glamey.training.jvm.oom;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * -Xms64m -Xmx64m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/yang.zhou/Documents/work/heap_dump/dump.hprof
 *
 * @author yang.zhou 2019.11.22.15
 */
public class DumpHeapOnOOMError {


    public static void main(String[] args) {
        heap();
    }


    public static void heap() {
        long star = System.currentTimeMillis();
        Set<HeapDemo> hashSet = new HashSet<>(10000000);
        for (int i = 0; i < 10000000; i++) {
            hashSet.add(new HeapDemo(1, String.valueOf(1)));
        }
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - star));
    }


    @AllArgsConstructor
    @Data
    public static class HeapDemo {
        private Integer integer;
        private String code;
    }
}
