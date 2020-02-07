package org.glamey.training.vavr;

import io.vavr.API;
import io.vavr.CheckedFunction0;
import io.vavr.Tuple;
import io.vavr.Tuple3;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import static io.vavr.API.$;
import static io.vavr.API.Case;

/**
 * @author yang.zhou 2020.01.22.17
 */
@Slf4j
public class Demo {

    public static void main(String[] args) {
        Tuple3<String, String, Integer> tuple3 = Tuple.of("a", "b", 1);
        System.out.println(tuple3._1);

        Tuple3<String, String, Integer> tuple31 = tuple3.map1(String::toUpperCase);
        System.out.println(tuple31._1);


        String input = "a";
        String output = API.Match(input).of(
                Case($("g"), () -> "NULL"),
                Case($("a"), "bad"),
                Case($(), "unknown")
        );
        System.out.println(output);

        Try<String> strings = Try.of((CheckedFunction0<String>) () -> {
            int ret = 1 / 0;
            return String.valueOf(ret);
        }).onFailure(throwable -> log.error("throwable->", throwable));

        boolean success = strings.isSuccess();
        if (success) {
            System.out.println("success -> " + strings.get());
        } else {
            System.out.println("failure");
        }


    }
}
