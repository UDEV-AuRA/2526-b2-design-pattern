package _1poo._04.interfac.notifier;

import _1poo._04.interfac.Notifier;
import _1poo._04.interfac.Salarie;

public class MailService implements Notifier {
  private Smtp smtp;

  public void envoyerContratASigner(Salarie salarie) {
    smtp.envoyerEmail(salarie.getEmail(), "Viens ton signer ton contrat");
  }
}
