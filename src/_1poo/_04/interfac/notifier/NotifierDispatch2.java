package _1poo._04.interfac.notifier;

import _1poo._04.interfac.Notifier;
import _1poo._04.interfac.Salarie;

import java.util.List;

public class NotifierDispatch2 {
  private List<Notifier> notifiers;

  public void notifier(Salarie salarie) {
    ///for (Notifier notifier : notifiers) {
    ///  if (salarie.getPreferenceNotification().equals(notifier.getType())) {
    ///    notifier.envoyerContratASigner(salarie);
    ///  }
    ///}
  }
}
