package _4composite.fichiers;

public class Main {
  public static void main(String[] args) {

    var f1 = new Fichier("f1.txt", 10);
    var f2 = new Fichier("f2.txt", 30);
    var d1 = new Dossier();
    //d1.getFichiers().add(f1);
    //d1.getFichiers().add(f2);


    var f3 = new Fichier("f3.txt", 50);
    var d3 = new Dossier();
    //d3.getFichiers().add(f3);

   //d1.getSousDossiers().add(d3);

    System.out.println(f1.getTaille());
    //System.out.println(d3.calculerTaille());
  }
}
