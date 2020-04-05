package org.glamey.training.designmodel.observable;

/**
 * @author zhouyang.zhou. 2017.08.29.15.
 */
public class Radio implements Observer<Broadcast> {

  private String message;

  /*@Override public void update(Observable o, Object arg) {
    if(o instanceof Broadcast) {
      Broadcast broadcast = (Broadcast) o;
      String message = broadcast.getMessage();
      this.message = message;
      System.out.println(message);
    }
  }*/

  public String getMessage() {
    return this.message;
  }

  @Override public void change(Broadcast broadcast) {
    this.message = broadcast.getMessage();
  }
}