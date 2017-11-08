package org.glamey.training.designmodel.observable;

import com.google.common.collect.Lists;
import com.sun.org.apache.regexp.internal.RE;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhouyang.zhou. 2017.08.29.16.
 */
@Slf4j
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
      log.info("--------------------------------------->");
      broadcast.sendMessage(message);
      log.info("radio_1 --> {}", radio_1.getMessage());
      log.info("radio_2 --> {}", radio_2.getMessage());
      log.info("radio_3 --> {}", radio_3.getMessage());
    }
  }
}
