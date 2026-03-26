package _5iterator.arbre;

import _5iterator.Iterable;

public class MonArbre implements Iterable {
  private MonArbre filsGauche;
  private MonArbre filsDroit;
  private String valeur;

  public MonArbre(String valeur) {
    this.valeur = valeur;
    this.filsGauche = filsGauche;
    this.filsDroit = filsDroit;
  }

  public String getValeur() {
    return valeur;
  }

  public MonArbre getFilsGauche() {
    return filsGauche;
  }

  public void setFilsGauche(MonArbre filsGauche) {
    this.filsGauche = filsGauche;
  }

  public MonArbre getFilsDroit() {
    return filsDroit;
  }

  public void setFilsDroit(MonArbre filsDroit) {
    this.filsDroit = filsDroit;
  }

  public MonArbreIterator createIterator() {
    return new MonArbreIterator(this);
  }
}
