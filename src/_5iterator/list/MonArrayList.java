package _5iterator.list;

import _5iterator.Iterable;

public class MonArrayList implements Iterable {

  private String[] array = new String[10];
  private int size = 0;

  public void add(String valeur) {
    // TODO gérer le cas ou la size dépasse la length de l'array
    // il faut reconstruire un array plus grand.
    array[size] = valeur;
    size = size + 1;
  }

  public String get(int index) {
    return array[index];
  }

  public int size() {
    return size;
  }

  public MonArrayListIterator createIterator() {
    return new MonArrayListIterator(this);
  }
}
