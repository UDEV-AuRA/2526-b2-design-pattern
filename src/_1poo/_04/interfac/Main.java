package _1poo._04.interfac;

import _1poo._04.interfac.notifier.CourierService;
import _1poo._04.interfac.notifier.MailService;

public class Main {
  static String param;

  public static void main(String[] args) {
    Notifier notifier = param.equals("MAIL") ? new MailService() : new CourierService();

    FenetreCreationContrat fenetreCreationContrat
      = new FenetreCreationContrat(notifier);

    fenetreCreationContrat.afficher();
  }
}

































