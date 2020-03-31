package org.glamey.agent;

import java.util.concurrent.TimeUnit;

public class GreetingMain {

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            System.out.println(new Dog().greeting());
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }
}
