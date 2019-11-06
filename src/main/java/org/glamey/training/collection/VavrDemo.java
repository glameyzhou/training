package org.glamey.training.collection;

import io.vavr.Lazy;
import io.vavr.collection.List;

import java.util.UUID;

import static io.vavr.API.*;

/**
 * @author yang.zhou 2019.11.06.14
 */
public class VavrDemo {
    private static final int[] array = {3, 5, 7, 2, 6, 1};


    public static void main(String[] args) {
        lazy();
        match(Type.SUCCESS);


    }

    public static void lazy() {
        //多次拿到的结果都一样
        Lazy<UUID> lazy = Lazy.of(UUID::randomUUID);
        for (int i = 0; i < 10; i++) {
            System.out.println(lazy.get().toString());
        }
    }

    public static void list(int[] array) {
        java.util.List<int[]> ints = List.of(array).toJavaList();

    }


    public static void match(Type type) {
        String output = Match(type).of(
                Case($(Type.SUCCESS), Type.SUCCESS.name()),
                Case($(Type.FAILURE), Type.FAILURE.name()),
                Case($(Type.ERROR), Type.ERROR.name()),
                Case($(), "any thing"));
        System.out.println(output);
    }


    enum Type {
        SUCCESS, FAILURE, ERROR;
    }
}

