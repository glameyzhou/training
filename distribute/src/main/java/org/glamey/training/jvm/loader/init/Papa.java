package org.glamey.training.jvm.loader.init;

/**
 * @author yang.zhou 2019.11.28.10
 */
public class Papa extends Grandpa {
    public static final String PAPA_FINAL_CONSTANTS = "PAPA_FINAL_CONSTANTS";
    public static String PAPA_CONSTANTS = "PAPA_CONSTANTS";

    static {
        System.out.println("[Papa] [static block code] begin");
    }

    static {
        System.out.println("[Papa] [static block code] finish");
    }

    {
        System.out.println("[Papa] [block code] begin");
    }

    {
        System.out.println("[Papa] [block code] end");
    }

    public Papa() {
        System.out.println("[papa] [construct]");
    }

    public static void papaMethod() {
        System.out.println("[papa] [static method]");
    }
}
