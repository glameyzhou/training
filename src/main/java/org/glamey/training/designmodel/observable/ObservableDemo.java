package org.glamey.training.designmodel.observable;

import com.google.common.collect.Lists;
import com.sun.org.apache.regexp.internal.RE;
import java.util.List;

/**
 * @author zhouyang.zhou. 2017.08.29.16.
 */
public class ObservableDemo {

  public static void main(String[] args) {

    Broadcast broadcast = new Broadcast();
    Radio radio_1 = new Radio();
    Radio radio_2 = new Radio();
    Radio radio_3 = new Radio();

    broadcast.addObserver(radio_1);
    broadcast.addObserver(radio_2);
    broadcast.addObserver(radio_3);

    List<String> messages = Lists.newArrayList(
        "现在状态是1",
        "现在状态是2",
        "现在状态是3",
        "现在状态是4"
    );

    for (String message : messages) {
      System.out.println("--------------------------------------->");
      broadcast.sendMessage(message);
      System.out.printf("radio_1 --> %s\n", radio_1.getMessage());
      System.out.printf("radio_2 --> %s\n", radio_2.getMessage());
      System.out.printf("radio_3 --> %s\n", radio_3.getMessage());
    }
  }
}
