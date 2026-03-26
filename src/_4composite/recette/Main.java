package _4composite.recette;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Ingredient tomate = new Ingredient(0.3, "piece");
        Ingredient oignon = new Ingredient(0.1, "piece");
        Ingredient huile = new Ingredient(10, "litre");
        Ingredient farine = new Ingredient(2, "kg");
        Ingredient sel = new Ingredient(0.5, "kg");
        Ingredient eau = new Ingredient(0.1, "litre");
        Ingredient mozzarella = new Ingredient(3, "piece");
        Ingredient boeuf = new Ingredient(20, "kg");

        Recette boulette = new Recette();
        boulette.add(0.1, boeuf);
        boulette.add(0.01, huile);

        Recette sauceTomate = new Recette();
        sauceTomate.add(3, tomate);
        sauceTomate.add(1, oignon);
        sauceTomate.add(0.1, huile);
        sauceTomate.addSousRecette(1, boulette);

        Recette pate = new Recette();
        pate.add(0.250, farine);
        pate.add(0.001, sel);
        pate.add(0.250, eau);
        pate.add(0.010, huile);

        Recette pizza = new Recette();
        pizza.addSousRecette(1, sauceTomate);
        pizza.addSousRecette(1, pate);
        pizza.add(2, mozzarella);

        // 1. Calculer le prix d'une recette de manière naïve = sans le pattern Composite
        double prixTotal = calculerPrixRecette(pizza);
        System.out.println("Le prix total de la pizza est " + prixTotal);

        // 2. Calculer le prix d'une recette en utilisant le pattern
        // TODO
    }

    // Méthode qui calcule le prix d'une recette
    // TODO a compléter !
    private static double calculerPrixRecette(Recette recette) {
        double prixTotal = 0;

        // Prix des ingredients
        Set<Ingredient> tousSesIngredientsDirects = recette.getIngredients().keySet();
        for (Ingredient ingredient : tousSesIngredientsDirects) {
            Double quantite = recette.getIngredients().get(ingredient);
            // TODO
        }

        // Prix des sous recettes
        Set<Recette> tousSesSousRecettesDirects = recette.getSousRecettes().keySet();
        for (Recette sousRecette : tousSesSousRecettesDirects) {
            Double quantite = recette.getSousRecettes().get(sousRecette);
            double prixUnitaireDeLaSousRecette = calculerPrixRecette(sousRecette);
        }

        //...
        return prixTotal;
    }
}