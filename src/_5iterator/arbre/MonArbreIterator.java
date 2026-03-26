package _5iterator.arbre;

import _5iterator.Iterator;

import java.util.ArrayList;
import java.util.List;

public class MonArbreIterator implements Iterator {
  private final MonArbre monArbre;

  private List<MonArbre> arbresAParcourir = new ArrayList<>();
  private int index = 0;

  public MonArbreIterator(MonArbre monArbre) {
    this.monArbre = monArbre;
    this.arbresAParcourir.add(monArbre);
  }

  public boolean hasNext() {
    return index < arbresAParcourir.size();
  }

  public String next() {
    MonArbre arbre = arbresAParcourir.get(index);
    if (arbre.getFilsGauche() != null) {
      arbresAParcourir.add(arbre.getFilsGauche());
    }
    if (arbre.getFilsDroit() != null) {
      arbresAParcourir.add(arbre.getFilsDroit());
    }
    index++;
    return arbre.getValeur();
  }
}
