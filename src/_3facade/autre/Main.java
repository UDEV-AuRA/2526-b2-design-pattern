package _3facade.autre;

public class Main {
  public static void main(String[] args) {
    VraieService vraieService = new VraieService();
    Adapter adapter = new Adapter(vraieService);
    Client client = new Client(adapter);


  }
}
