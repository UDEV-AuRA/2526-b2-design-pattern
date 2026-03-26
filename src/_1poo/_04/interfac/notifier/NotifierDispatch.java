package _1poo._04.interfac.notifier;

import _1poo._04.interfac.Salarie;

public class NotifierDispatch {
  private MailService mailService = new MailService();
  private CourierService courierService = new CourierService();

  public void notifier(Salarie salarie) {
    if (salarie.getPreferenceNotification().equals("MAIL")) {
      mailService.envoyerContratASigner(salarie);
    } else if (salarie.getPreferenceNotification().equals("COURIER")) {
      courierService.envoyerContratASigner(salarie);
    }
  }
}
