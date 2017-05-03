package org.glamey.training.io.bio;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhouyang.zhou  2017/1/1.22.
 */
public class ClientDemo {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static void main(String[] args)
          throws IOException, InterruptedException, ClassNotFoundException, IllegalAccessException, InstantiationException, IntrospectionException,
          InvocationTargetException {

    Class<?> clazz = Class.forName("org.glamey.myidea.util.io.bio.ClientDemo");
    ClientDemo object = (ClientDemo) clazz.newInstance();
    PropertyDescriptor propertyDescriptor = new PropertyDescriptor("name", clazz);
    Method method = propertyDescriptor.getWriteMethod();
    Object voidString = method.invoke(object, "名字");
    System.out.println(voidString);
    System.out.println(object.getName());


    /*int index = 1;
    String data;
    while (true) {
      Socket socket = new Socket("127.0.0.1", 2345);
      data = String.format("大家好,现在是[%d]->%s", index, DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
      OutputStream outputStream = socket.getOutputStream();
      outputStream.write(new String(data).getBytes(Charsets.UTF_8.name()));
      outputStream.flush();
      outputStream.close();
      socket.close();
      System.out.println(String.format("sending--->%s", data));
      index++;
      TimeUnit.SECONDS.sleep(1);
    }*/
  }
}
