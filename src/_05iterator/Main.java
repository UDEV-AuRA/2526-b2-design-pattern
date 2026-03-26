package _05iterator;

import _05iterator.arbre.MonArbre;
import _05iterator.list.MonArrayList;
import _05iterator.list.MonArrayListIterator;

import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    MonArrayList monArrayList = new MonArrayList();
    monArrayList.add("A");
    monArrayList.add("B");
    monArrayList.add("C");

    System.out.println(monArrayList.size());
    System.out.println(monArrayList.get(1));

    for (int i = 0; i < monArrayList.size(); i++) {
      System.out.println(monArrayList.get(i));
    }

    System.out.println("-----------------------");

    var a = new MonArbre("A");
    var b = new MonArbre("B");
    var c = new MonArbre("C");
    var d = new MonArbre("D");

    a.setFilsGauche(b);
    a.setFilsDroit(c);
    c.setFilsGauche(d);

    List<MonArbre> arbresAParcourir = new ArrayList<>();
    arbresAParcourir.add(a);
    for (int i = 0; i < arbresAParcourir.size(); i++) {
      MonArbre arbre = arbresAParcourir.get(i);
      System.out.println(arbre.getValeur());
      if (arbre.getFilsGauche() != null) {
        arbresAParcourir.add(arbre.getFilsGauche());
      }
      if (arbre.getFilsDroit() != null) {
        arbresAParcourir.add(arbre.getFilsDroit());
      }
    }

    //
    System.out.println("utilisation du design pattern sur MonArrrayList");

    MonArrayListIterator monArrayListIterator = monArrayList.createIterator();
    while (monArrayListIterator.hasNext()) {
      String valeur = monArrayListIterator.next();
      System.out.println(valeur);
    }

    //
    System.out.println("utilisation du design pattern sur MonArbre");
    parcourirStructure(a);
    System.out.println("utilisation du design pattern sur La liste");
    parcourirStructure(monArrayList);
  }

  private static void parcourirStructure(Iterable iterable) {
    Iterator iterator = iterable.createIterator();
    while (iterator.hasNext()) {
      String valeur = iterator.next();
      System.out.println(valeur);
    }
  }
}
