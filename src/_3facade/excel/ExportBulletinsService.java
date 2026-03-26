package _3facade.excel;

import java.io.IOException;
import java.util.List;

/**
 * ╔══════════════════════════════════════════════════════════════════╗
 * ║               CLIENT de la Facade (service métier)              ║
 * ╠══════════════════════════════════════════════════════════════════╣
 * ║ Ce service connaît UNIQUEMENT le vocabulaire de l'école :       ║
 * ║   Etudiant, Note, matière, moyenne…                             ║
 * ║                                                                  ║
 * ║ Il ne contient PAS UNE SEULE ligne d'API Apache POI.            ║
 * ║ Toute la complexité est déléguée à ExcelFacadePoi.              ║
 * ╚══════════════════════════════════════════════════════════════════╝
 */
public class ExportBulletinsService {

    /** La Facade injectée (injection par constructeur → testable). */
    private final ExcelFacadePoi excelFacade;

    public ExportBulletinsService(ExcelFacadePoi excelFacade) {
        this.excelFacade = excelFacade;
    }

    /**
     * Génère un fichier Excel contenant le bulletin scolaire de chaque étudiant.
     *
     * @param etudiants    liste des étudiants à exporter
     * @param matieres     liste des matières (colonnes)
     * @param cheminFichier chemin du fichier .xlsx à créer
     */
    public void exporterBulletins(List<Etudiant> etudiants,
                                  List<String>  matieres,
                                  String        cheminFichier) throws IOException {

        int nbColonnes = 2 + matieres.size() + 1; // Nom + Prénom + matières + Moyenne

        // 1. Créer la feuille
        excelFacade.creerFeuille("Bulletins scolaires");

        // 2. Ligne de titre
        excelFacade.ajouterTitre("Bulletins scolaires — Promotion 2025/2026", nbColonnes);

        // 3. En-têtes
        String[] entetes = construireEntetes(matieres);
        excelFacade.ajouterEntetes(entetes);

        // 4. Une ligne par étudiant
        for (Etudiant etudiant : etudiants) {
            Object[] valeurs = construireValeurs(etudiant, matieres);
            excelFacade.ajouterLigne(false, valeurs);
        }

        // 5. Ligne de moyennes de promotion
        Object[] moyennesPromo = calculerMoyennesPromotion(etudiants, matieres);
        excelFacade.ajouterLigne(true, moyennesPromo);

        // 6. Sauvegarder
        excelFacade.sauvegarder(cheminFichier);
        System.out.println("✅ Bulletins exportés → " + cheminFichier);
    }

    // ── helpers privés ────────────────────────────────────────────────

    private String[] construireEntetes(List<String> matieres) {
        String[] entetes = new String[2 + matieres.size() + 1];
        entetes[0] = "Nom";
        entetes[1] = "Prénom";
        for (int i = 0; i < matieres.size(); i++) {
            entetes[2 + i] = matieres.get(i);
        }
        entetes[entetes.length - 1] = "Moyenne";
        return entetes;
    }

    private Object[] construireValeurs(Etudiant etudiant, List<String> matieres) {
        Object[] valeurs = new Object[2 + matieres.size() + 1];
        valeurs[0] = etudiant.getNom();
        valeurs[1] = etudiant.getPrenom();
        for (int i = 0; i < matieres.size(); i++) {
            valeurs[2 + i] = etudiant.getNote(matieres.get(i));
        }
        valeurs[valeurs.length - 1] = etudiant.calculerMoyenne();
        return valeurs;
    }

    private Object[] calculerMoyennesPromotion(List<Etudiant> etudiants, List<String> matieres) {
        Object[] ligne = new Object[2 + matieres.size() + 1];
        ligne[0] = "Moyenne promotion";
        ligne[1] = "";
        for (int i = 0; i < matieres.size(); i++) {
            final String matiere = matieres.get(i);
            double moyenneMatiere = etudiants.stream()
                    .mapToDouble(e -> e.getNote(matiere))
                    .average()
                    .orElse(0.0);
            ligne[2 + i] = moyenneMatiere;
        }
        double moyenneGenerale = etudiants.stream()
                .mapToDouble(Etudiant::calculerMoyenne)
                .average()
                .orElse(0.0);
        ligne[ligne.length - 1] = moyenneGenerale;
        return ligne;
    }
}

