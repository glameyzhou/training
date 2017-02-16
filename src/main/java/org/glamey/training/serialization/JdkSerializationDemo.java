package org.glamey.training.serialization;

import com.google.common.base.Stopwatch;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouyang.zhou  2017/1/7.20.
 */
public class JdkSerializationDemo {
  public static void main(String[] args) throws IOException, ClassNotFoundException {
    for (int j = 0; j < 10; j++) {
      Stopwatch stopwatch = Stopwatch.createStarted();
      for (int i = 0; i < 100; i++) {
        UserEntity entity = new UserEntity();
        entity.setId(i);
        entity.setEmail("zhouyang.zhou@qunar.com");
        entity.setPassword("zhouyang.zhou@qunar.com");
        entity.setNickName("周杨");

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("/tmp/serialization/userEntity_" + i)));
        objectOutputStream.writeObject(entity);
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("/tmp/serialization/userEntity_" + i)));
        objectInputStream.readObject();
        objectInputStream.close();
      }
      System.out.println("serialization elapsed " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");
    }
  }
}
