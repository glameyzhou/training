package org.glamey.training.jvm.loader.init;

/**
 * @author yang.zhou 2019.11.28.10
 */
public class Son extends Papa{
    public static final String SON_FINAL_CONSTANTS = "SON_FINAL_CONSTANTS";
    public static String SON_CONSTANTS = "SON_CONSTANTS";

    static {
        System.out.println("[son] [static block code] begin");
    }

    static {
        System.out.println("[son] [static block code] finish");
    }

    {
        System.out.println("[son] [block code] begin");
    }

    {
        System.out.println("[son] [block code] end");
    }

    public Son() {
        System.out.println("[son] [construct]");
    }

    public static void sonMethod() {
        System.out.println("[son] [static method]");
    }
}
