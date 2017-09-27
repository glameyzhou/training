package org.glamey.training.good_habit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * 如果序列化对象中没有添加serialVersionUID的话，编译器会自动添加一个唯一编码。
 *
 * 1、没有添加UID，后续如果新增属性后，进行反序列化的时候，首先会自动对比数据流中的UID与对象中的UID是否一致，如果不一致，报错。
 * 2、添加UID
 * 2.1 新增属性之后，没有手动修改对应的UID版本号，反序列的时候，针对新增的属性，对应的值为NULL。
 * 2.2 新增属性后，手动升级对应的UID版本号，反序列化的时候，客户机没有来得及升级版本的话，导致反序列化失败。
 *
 *
 * 针对静态属性是否参与序列化
 * @author zhouyang.zhou. 2017.09.22.17.
 */
public class SerializableDemo {

  public static void main(String[] args) {
    Person person = new Person();
    person.setName("zhouyang");

    SerializableUtil.serializable(person);
    Person person_2 = (Person) SerializableUtil.deserializable();
    System.out.println(person_2.getName());
    System.out.println(person_2.getAge());

    boolean nullInstanceOfString = (String) null instanceof String;
    System.out.println(nullInstanceOfString);
  }



  public static class Person implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private Integer age;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Integer getAge() {
      return age;
    }

    public void setAge(Integer age) {
      this.age = age;
    }
  }

  static class SerializableUtil {
    final static File file = new File("/Users/zhouyangzhou/work/temp.bin");

    public static void serializable(Serializable object) {

      try {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(object);
        outputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    public static Object deserializable() {
      try {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
        Object object = inputStream.readObject();
        inputStream.close();
        return object;
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      return null;
    }
  }
}
