package _1poo._03heritage.universite;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    Professeur professeurRogue = new Professeur("Rogue", "Severus", "potions");
    Etudiant etudiantHarry = new Etudiant("Potter", "Harry", "Griffondor");
    Etudiant etudiantDrago = new Etudiant("Malefoy", "Drago", "Serpentard");

    // professeurRogue.sePresenter();
    // etudiantHarry.sePresenter();
    // etudiantDrago.sePresenter();

    //table ronde
    List<Personne> list = new ArrayList<>();
    list.add(professeurRogue);
    list.add(etudiantHarry);
    list.add(etudiantDrago);

    //a éviter!
    for (Personne personne : list) {
      System.out.println("bonjour je suis " + personne.getPrenom());
      if (personne instanceof Professeur professeur) {
        System.out.println("et j'enseigne les " + professeur.getSpecialite());
      } else if (personne instanceof Etudiant etudiant) {
        System.out.println("et je suis dans la promo " + etudiant.getPromotion());
      }
    }

    for (Personne personne : list) {
      personne.sePresenter();
    }


    Personne p = etudiantHarry;
    p.sePresenter();
    p = professeurRogue;
    p.sePresenter();

  }

}
