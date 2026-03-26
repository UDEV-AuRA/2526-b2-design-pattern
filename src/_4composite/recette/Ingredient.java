package _4composite.recette;

public class Ingredient {
    private double prix;
    private String unite;

    public Ingredient(double prix, String unite) {
        this.prix = prix;
        this.unite = unite;
    }

    public double getPrix() {
        return prix;
    }
}