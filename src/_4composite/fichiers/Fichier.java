package _4composite.fichiers;

public class Fichier implements IFile {
  private String nom;
  private int taille;

  public Fichier(String nom, int taille) {
    this.nom = nom;
    this.taille = taille;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public int getTaille() {
    return taille;
  }

  public void setTaille(int taille) {
    this.taille = taille;
  }
}
