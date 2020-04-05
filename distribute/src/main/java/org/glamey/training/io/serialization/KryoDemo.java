package org.glamey.training.io.serialization;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.IdentityMap;
import com.google.common.base.Stopwatch;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouyang.zhou  2017/1/7.20.
 */
public class KryoDemo {
  public static void main(String[] args) throws FileNotFoundException {
    for (int j = 0; j < 10; j++) {
      Stopwatch stopwatch = Stopwatch.createStarted();
      for (int i = 0; i < 100; i++) {
        UserEntity entity = new UserEntity();
        entity.setId(i);
        entity.setEmail("zhouyang.zhou@qunar.com");
        entity.setPassword("zhouyang.zhou@qunar.com");
        entity.setNickName("周杨");

        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(false);
        kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
        Output output = new Output(new FileOutputStream(new File("/tmp/serialization/userEntity_" + i)));
        kryo.writeObject(output, entity);
        output.close();

        Input input = new Input(new FileInputStream(new File("/tmp/serialization/userEntity_" + i)));
        kryo.readObject(input, UserEntity.class);
        input.close();

        if (i == 99) {
          IdentityMap originalToCopyMap = kryo.getOriginalToCopyMap();
          if (originalToCopyMap != null) {
            IdentityMap.Entries entries = originalToCopyMap.entries();
            if (entries != null) {
              Iterator iterator = entries.iterator();
              while (iterator.hasNext()) {
                Object next = iterator.next();
                Object value = originalToCopyMap.get(next);
                System.out.println(String.format("%s-->%s", next, value));
              }
            }
          }
        }
      }
      System.out.println("serialization elapsed " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");
    }
  }
}
