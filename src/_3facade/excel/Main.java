package _3facade.excel;

import java.io.IOException;
import java.util.List;

/**
 * ╔═══════════════════════════════════════════════════════════════════════╗
 * ║          DÉMONSTRATION DU PATTERN FACADE — Export Excel              ║
 * ╠═══════════════════════════════════════════════════════════════════════╣
 * ║                                                                       ║
 * ║  PROBLÈME sans Facade :                                               ║
 * ║  Le code métier doit connaître et gérer toute l'API Apache POI :     ║
 * ║     XSSFWorkbook, XSSFSheet, XSSFRow, XSSFCell,                      ║
 * ║     CellStyle, Font, DataFormat, FillPatternType,                     ║
 * ║     HorizontalAlignment, IndexedColors, FileOutputStream…            ║
 * ║  → Code métier pollué par des détails techniques.                    ║
 * ║  → Difficile à lire, à tester et à maintenir.                        ║
 * ║                                                                       ║
 * ║  SOLUTION avec Facade :                                               ║
 * ║  ExportBulletinsService parle uniquement le langage de l'école :     ║
 * ║     creerFeuille(), ajouterTitre(), ajouterEntetes(), ajouterLigne() ║
 * ║  → Aucune import Apache POI dans le service métier.                  ║
 * ║  → Code lisible, testable, découplé de la bibliothèque technique.    ║
 * ╚═══════════════════════════════════════════════════════════════════════╝
 */
public class Main {

    public static void main(String[] args) throws IOException {

        // ─── Données métier ────────────────────────────────────────────
        List<String> matieres = List.of("Maths", "Anglais", "Physique", "Algo", "POO");

        List<Etudiant> etudiants = List.of(
                creerEtudiant("Dupont",   "Alice",   17.5, 14.0, 16.0, 18.0, 15.5),
                creerEtudiant("Martin",   "Bob",     12.0, 18.5, 10.5, 11.0, 14.0),
                creerEtudiant("Bernard",  "Claire",  15.0, 13.0, 19.0, 16.5, 17.0),
                creerEtudiant("Leclerc",  "David",    9.5, 11.0,  8.0, 13.0, 10.5),
                creerEtudiant("Moreau",   "Emma",    20.0, 16.0, 18.5, 19.0, 20.0)
        );

        // ─── Utilisation de la Facade via le service métier ─────────────
        //
        //  Comparez avec ce que vous feriez sans Facade :
        //
        //    XSSFWorkbook wb = new XSSFWorkbook();
        //    XSSFSheet sheet = wb.createSheet("Bulletins");
        //    CellStyle style = wb.createCellStyle();
        //    Font font = wb.createFont();
        //    font.setBold(true);
        //    font.setColor(IndexedColors.WHITE.getIndex());
        //    style.setFont(font);
        //    style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        //    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //    ...  (50+ lignes techniques supplémentaires)
        //
        //  AVEC la Facade, le code métier reste propre et lisible :

        ExcelFacadePoi    facade  = new ExcelFacadePoi();
        ExportBulletinsService service = new ExportBulletinsService(facade);

        service.exporterBulletins(etudiants, matieres, "bulletins_2026.xlsx");
    }

    // ── Méthode utilitaire ────────────────────────────────────────────
    private static Etudiant creerEtudiant(String nom, String prenom,
                                          double maths, double anglais,
                                          double physique, double algo, double poo) {
        Etudiant e = new Etudiant(nom, prenom);
        e.ajouterNote("Maths",    maths);
        e.ajouterNote("Anglais",  anglais);
        e.ajouterNote("Physique", physique);
        e.ajouterNote("Algo",     algo);
        e.ajouterNote("POO",      poo);
        return e;
    }
}

