package _5iterator.list;

import _5iterator.Iterator;

public class MonArrayListIterator implements Iterator {
  private final MonArrayList monArrayList;
  private int index = 0;

  public MonArrayListIterator(MonArrayList monArrayList) {
    this.monArrayList = monArrayList;
  }

  public boolean hasNext() {
    return index < monArrayList.size();
  }

  public String next() {
    String valeur = monArrayList.get(index);
    index = index + 1;
    return valeur;
  }
}
