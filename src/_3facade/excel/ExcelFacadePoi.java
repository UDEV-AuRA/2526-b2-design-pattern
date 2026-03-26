package _3facade.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ╔══════════════════════════════════════════════════════════════════╗
 * ║                    PATTERN : FACADE                             ║
 * ╠══════════════════════════════════════════════════════════════════╣
 * ║ Cette classe est la FACADE.                                     ║
 * ║                                                                  ║
 * ║ Elle masque entièrement la complexité d'Apache POI :            ║
 * ║   • XSSFWorkbook, XSSFSheet, XSSFRow, XSSFCell                 ║
 * ║   • CellStyle, Font, DataFormat, FillPatternType…              ║
 * ║   • Gestion des flux I/O (FileOutputStream)                    ║
 * ║                                                                  ║
 * ║ Le client (ExportBulletinsService) n'a AUCUNE dépendance POI.  ║
 * ║ Il appelle uniquement des méthodes au vocabulaire métier.       ║
 * ╚══════════════════════════════════════════════════════════════════╝
 */
public class ExcelFacadePoi {

    // ── Sous-système Apache POI (caché derrière la Facade) ─────────────
    private final XSSFWorkbook workbook;
    private XSSFSheet feuilleActive;
    private int ligneActuelle;

    // Styles réutilisables
    private final CellStyle styleEntete;
    private final CellStyle styleTexte;
    private final CellStyle styleNote;
    private final CellStyle styleMoyenne;
    private final CellStyle styleTitre;

    // ── Constructeur : initialise le classeur et les styles ────────────
    public ExcelFacadePoi() {
        this.workbook = new XSSFWorkbook();
        this.styleEntete  = creerStyleEntete();
        this.styleTexte   = creerStyleTexte();
        this.styleNote    = creerStyleNote();
        this.styleMoyenne = creerStyleMoyenne();
        this.styleTitre   = creerStyleTitre();
    }

    // ═══════════════════════════════════════════════════════════════════
    //  API PUBLIQUE — c'est tout ce que le client voit
    // ═══════════════════════════════════════════════════════════════════

    /** Crée un nouvel onglet dans le classeur. */
    public void creerFeuille(String nom) {
        this.feuilleActive = workbook.createSheet(nom);
        this.ligneActuelle = 0;
    }

    /** Ajoute une ligne de titre fusionnée sur {@code nbColonnes} colonnes. */
    public void ajouterTitre(String titre, int nbColonnes) {
        Row row = feuilleActive.createRow(ligneActuelle++);
        row.setHeightInPoints(30);
        Cell cell = row.createCell(0);
        cell.setCellValue(titre);
        cell.setCellStyle(styleTitre);
        feuilleActive.addMergedRegion(new CellRangeAddress(
                ligneActuelle - 1, ligneActuelle - 1, 0, nbColonnes - 1));
    }

    /** Ajoute une ligne d'en-têtes de colonnes. */
    public void ajouterEntetes(String... colonnes) {
        Row row = feuilleActive.createRow(ligneActuelle++);
        row.setHeightInPoints(20);
        for (int i = 0; i < colonnes.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(colonnes[i]);
            cell.setCellStyle(styleEntete);
            feuilleActive.setColumnWidth(i, 22 * 256);   // largeur fixe
        }
    }

    /**
     * Ajoute une ligne de données.
     * Les colonnes texte, les notes (Double) et les moyennes sont
     * automatiquement mis en forme.
     *
     * @param estLigneMoyenne  true = style "moyenne" (fond coloré)
     * @param valeurs          valeurs de chaque cellule
     */
    public void ajouterLigne(boolean estLigneMoyenne, Object... valeurs) {
        Row row = feuilleActive.createRow(ligneActuelle++);
        for (int i = 0; i < valeurs.length; i++) {
            Cell cell = row.createCell(i);
            Object val = valeurs[i];

            if (val instanceof Double d) {
                cell.setCellValue(d);
                cell.setCellStyle(estLigneMoyenne ? styleMoyenne : styleNote);
            } else if (val instanceof Integer n) {
                cell.setCellValue(n);
                cell.setCellStyle(styleTexte);
            } else {
                cell.setCellValue(val != null ? val.toString() : "");
                cell.setCellStyle(styleTexte);
            }
        }
    }

    /** Sérialise le classeur dans le fichier indiqué et libère les ressources. */
    public void sauvegarder(String cheminFichier) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(cheminFichier)) {
            workbook.write(fos);
        }
        workbook.close();
    }

    // ═══════════════════════════════════════════════════════════════════
    //  MÉTHODES PRIVÉES — complexité POI totalement encapsulée
    // ═══════════════════════════════════════════════════════════════════

    private CellStyle creerStyleTitre() {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        font.setColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    private CellStyle creerStyleEntete() {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        appliquerBordures(style);
        return style;
    }

    private CellStyle creerStyleTexte() {
        CellStyle style = workbook.createCellStyle();
        appliquerBordures(style);
        return style;
    }

    private CellStyle creerStyleNote() {
        CellStyle style = workbook.createCellStyle();
        DataFormat fmt = workbook.createDataFormat();
        style.setDataFormat(fmt.getFormat("0.00"));
        style.setAlignment(HorizontalAlignment.CENTER);
        appliquerBordures(style);
        return style;
    }

    private CellStyle creerStyleMoyenne() {
        CellStyle style = workbook.createCellStyle();
        DataFormat fmt = workbook.createDataFormat();
        style.setDataFormat(fmt.getFormat("0.00"));
        style.setAlignment(HorizontalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        appliquerBordures(style);
        return style;
    }

    private void appliquerBordures(CellStyle style) {
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
    }
}
