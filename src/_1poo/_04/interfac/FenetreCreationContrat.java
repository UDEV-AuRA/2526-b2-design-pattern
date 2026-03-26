package _1poo._04.interfac;

public class FenetreCreationContrat {

  private Notifier notifier;

  FenetreCreationContrat(Notifier notifier) {
    this.notifier = notifier;
  }

  public void creerContrat(Salarie futurSalarie) {
    //fait d'autre trucs pas utile

    notifier.envoyerContratASigner(futurSalarie);
  }

  public void afficher() {
    //pas utile pour l'exmeple
  }


}
