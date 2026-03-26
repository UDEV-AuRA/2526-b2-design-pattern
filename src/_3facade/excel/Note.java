package _3facade.excel;

/**
 * Modèle représentant la note d'un étudiant dans une matière.
 */
public class Note {

    private final String matiere;
    private final double valeur;

    public Note(String matiere, double valeur) {
        if (valeur < 0 || valeur > 20) {
            throw new IllegalArgumentException("Une note doit être comprise entre 0 et 20.");
        }
        this.matiere = matiere;
        this.valeur  = valeur;
    }

    public String getMatiere() { return matiere; }
    public double getValeur()  { return valeur;  }
}

