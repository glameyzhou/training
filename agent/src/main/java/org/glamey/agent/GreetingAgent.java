package org.glamey.agent;

import java.lang.instrument.Instrumentation;

public class GreetingAgent {

    public static void premain(String options, Instrumentation instrumentation) {
        if (options != null) {
            System.out.println("Greeting premain invoked, options is " + options);
        } else {
            System.out.println("Greeting premain invoked, no options");
        }
        instrumentation.addTransformer(new DogFileTransformer());

    }
}
