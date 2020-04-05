package org.glamey.training.designmodel.iterator;

/**
 * @author zhouyang.zhou. 2017.10.22.20.
 */
public class Repository implements Container<String> {

  public String[] names = {"john", "james", "jack", "tom"};

  @Override public Iterator<String> iterator() {
    return new NameIterator();
  }

  private class NameIterator implements Iterator<String> {

    int index;

    @Override public boolean hasNext() {
      return index < names.length;
    }

    @Override public String next() {
      if(this.hasNext()) {
        return names[index++];
      }
      return null;
    }
  }
}
