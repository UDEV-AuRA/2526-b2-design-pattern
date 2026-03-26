package _1poo._04.interfac.notifier;

import _1poo._04.interfac.Notifier;
import _1poo._04.interfac.Salarie;

public class CourierService implements Notifier {
  private LaPoste laPoste;

  public void envoyerContratASigner(Salarie salarie) {
    laPoste.envoyerLettre(salarie.getAdressePostale(), "Viens ton signer ton contrat");
  }
}
