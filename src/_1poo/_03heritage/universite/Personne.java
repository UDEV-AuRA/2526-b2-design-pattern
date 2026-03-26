package _1poo._03heritage.universite;

public class Personne {
  protected String nom;
  protected String prenom;

  public Personne(String nom, String prenom) {
    this.nom = nom;
    this.prenom = prenom;
  }

  public void sePresenter() {
    System.out.println("Bonjour, je m'appelle " + prenom);
  }


  public String getNom() {
    return nom;
  }

  public String getPrenom() {
    return prenom;
  }
}
