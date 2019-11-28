package org.glamey.training.jvm.loader.init;

/**
 * @author yang.zhou 2019.11.28.10
 */
public class Grandpa {
    public static final String GRANDPA_FINAL_CONSTANTS = "GRANDPA_FINAL_CONSTANTS";
    public static String GRANDPA_CONSTANTS = "GRANDPA_CONSTANTS";

    static {
        System.out.println("[grandpa] [static block code] begin");
    }

    static {
        System.out.println("[grandpa] [static block code] finish");
    }

    {
        System.out.println("[grandpa] [block code] begin");
    }

    {
        System.out.println("[grandpa] [block code] end");
    }

    public Grandpa() {
        System.out.println("[grandpa] [construct]");
    }

    public static void grandpaMethod() {
        System.out.println("[grandpa] [static method]");
    }
}
