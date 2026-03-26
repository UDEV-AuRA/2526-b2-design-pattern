package _3facade.autre;

public class Adapter implements Service {

  private VraieService service;

  public Adapter(VraieService service) {
    this.service = service;
  }

  @Override
  public void faireTraitement() {
    service.enregistrer("donnée");
  }

}
