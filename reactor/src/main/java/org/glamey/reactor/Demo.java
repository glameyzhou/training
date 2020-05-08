package org.glamey.reactor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo {

    public static void main(String[] args) {
        System.out.println("This is the first file for reactor.");

        java.text.SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());
        System.out.println(date);
    }
}
