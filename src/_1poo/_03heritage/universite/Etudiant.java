package _1poo._03heritage.universite;

public class Etudiant extends Personne {
  private String promotion;

  public Etudiant(String nom, String prenom, String promotion) {
    super(nom, prenom);
    this.promotion = promotion;
  }

  @Override
  public void sePresenter() {
    super.sePresenter();
    System.out.println("et je suis étudiant en " + promotion);
  }


  public String getPromotion() {
    return promotion;
  }
}
