package org.glamey.training.designmodel.spi;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.ServiceLoader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhouyang.zhou  2017/2/2.21.
 */
public class Main {
    public static void main(String[] args) {
        ServiceLoader<SpiOperation> spiOperations = ServiceLoader.load(SpiOperation.class);
        /*Iterator<SpiOperation> iterator = spiOperations.iterator();
        while (iterator.hasNext()) {
            SpiOperation spiOperation = iterator.next();
            int result = spiOperation.operate(10, 5);
            System.out.println(String.format("%s ---> %d", spiOperation.getClass().getName(), result));
        }*/

        ExecutorService executorService = Executors.newWorkStealingPool();

        String join = String.join("+", "_", "a", "+");
        System.out.println(join);
        byte[] encode = Base64.getEncoder().encode(join.getBytes(Charset.forName("UTF-8")));
        System.out.println(new String(encode));


        byte[] source = Base64.getDecoder().decode(encode);
        System.out.println(new String(source));


        spiOperations.iterator().forEachRemaining(spiOperation -> {
            int result = spiOperation.operate(10, 5);
            System.out.println(String.format("%s --> %s", spiOperation.getDesc(), result));
        });


        // register mysql driver
    /*try {

      Class.forName("com.mysql.jdbc.Driver"); // 在装载com.mysql.jdbc.Driver的时候，会执行static方法快，将jdbc驱动放在list中，供后续的getConnnection使用
      DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/qreaper?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&autoReconnect=true&allowMultiQueries=true",
              "root","root");
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }*/
    }
}
