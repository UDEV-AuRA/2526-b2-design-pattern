package _1poo._03heritage.universite;

public class Professeur extends Personne {
  private String specialite;

  public Professeur(String nom, String prenom, String specialite) {
    super(nom, prenom);
    this.specialite = specialite;
  }

  @Override
  public void sePresenter() {
    super.sePresenter();
    System.out.println("et j'enseigne les " + specialite);
  }

  public String getSpecialite() {
    return specialite;
  }
}
