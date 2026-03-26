package _3facade.excel;

import java.util.ArrayList;
import java.util.List;

/**
 * Modèle représentant un étudiant avec ses notes.
 */
public class Etudiant {

    private final String nom;
    private final String prenom;
    private final List<Note> notes = new ArrayList<>();

    public Etudiant(String nom, String prenom) {
        this.nom    = nom;
        this.prenom = prenom;
    }

    public void ajouterNote(String matiere, double valeur) {
        notes.add(new Note(matiere, valeur));
    }

    /** Retourne la valeur de la note pour une matière donnée, ou 0 si absente. */
    public double getNote(String matiere) {
        return notes.stream()
                .filter(n -> n.getMatiere().equalsIgnoreCase(matiere))
                .mapToDouble(Note::getValeur)
                .findFirst()
                .orElse(0.0);
    }

    /** Calcule la moyenne sur toutes les matières. */
    public double calculerMoyenne() {
        return notes.stream()
                .mapToDouble(Note::getValeur)
                .average()
                .orElse(0.0);
    }

    public String getNom()    { return nom;    }
    public String getPrenom() { return prenom; }
}

