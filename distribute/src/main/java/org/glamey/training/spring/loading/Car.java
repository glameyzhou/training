package org.glamey.training.spring.loading;

/**
 * @author zhouyang.zhou. 2017.08.30.16.
 */
public class Car {

  private String name;
  private String color;

  public Car() {
    System.out.println("car default constructor");
  }

  public Car(String name, String color) {
    this.name = name;
    this.color = color;
    System.out.println("car all args constructor");
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  @Override public String toString() {
    return "Car{" +
        "name='" + name + '\'' +
        ", color='" + color + '\'' +
        '}';
  }
}
