# Design Patterns – Exercices à rendre

> **Date limite de rendu : dimanche 29 mars 2026, 23h59**
>
> **Adresse de rendu :** alexandre.petrillo@intervenant.igensia.com
>
> **Format attendu :** copies d'écran des **portions de code pertinentes** uniquement, insérées directement dans le corps du mail (pas de pièce jointe de code source).

---

## Exercice 1 – Iterator / Triplet

### Contexte

Le projet contient déjà deux structures de données qui implémentent le design pattern **Iterator** :

| Structure | Classe principale | Itérateur |
|---|---|---|
| Liste | `MonArrayList` | `MonArrayListIterator` |
| Arbre binaire | `MonArbre` | `MonArbreIterator` |

Ces deux structures implémentent toutes deux l'interface `Iterable` (`createIterator()`) et produisent un objet implémentant `Iterator` (`hasNext()` / `next()`), ce qui permet de les parcourir de manière uniforme via la méthode `parcourirStructure(Iterable)` du `Main`.

### Ce qui est demandé

Créer une nouvelle structure **`Triplet`** dans le package `_5iterator` (sous-package `triplet` conseillé) qui stocke exactement **trois valeurs** de type `String`, et qui respecte le design pattern Iterator de la même manière que les structures existantes.


**Copies d'écran attendues :**
- Le code de `Triplet` (avec `createIterator()`)
- Le code de `TripletIterator` (`hasNext()` et `next()`)
- La sortie console prouvant le bon fonctionnement

---

## Exercice 2 – Composite / Ingrédients d'une recette

### Contexte

Le projet contient un modèle de recettes dans le package `_4composite.recette` :

- `Ingredient` : un ingrédient avec un **prix unitaire** et une **unité**.
- `Recette` : une recette qui contient :
  - `Map<Ingredient, Double> ingredients` → une quantité par ingrédient direct.
  - `Map<Recette, Double> sousRecettes` → une quantité par sous-recette.
- `Main` : construit une pizza composée de plusieurs recettes imbriquées (pâte, sauce tomate, boulette…).

La méthode `calculerPrixRecette(Recette)` dans `Main` est à compléter.

---

### Partie a) – Approche naïve (sans modifier les classes existantes)

**Compléter** la méthode `calculerPrixRecette(Recette recette)` dans `Main.java` **sans toucher à `Ingredient.java` ni à `Recette.java`**.

La logique à implémenter :

```
prixTotal = Σ ( ingredient.getPrix() × quantité ) pour chaque ingrédient direct
          + Σ ( calculerPrixRecette(sousRecette) × quantité ) pour chaque sous-recette
```

La méthode est récursive : le prix d'une sous-recette est lui-même calculé par un appel récursif à `calculerPrixRecette`.

**Copies d'écran attendues :**
- Le corps complété de la méthode `calculerPrixRecette`
- La sortie console affichant le prix total de la pizza

---

### Partie b) – Refactoring avec le pattern Composite

Le problème de l'approche naïve : `Recette` et `Ingredient` sont deux types distincts, ce qui oblige à les traiter séparément avec deux boucles. Le pattern **Composite** résout cela en les faisant partager une **interface commune**.

**Modifications à apporter :**

1. Créer une interface (ou classe abstraite) `IComposantRecette` avec une méthode :
   ```java
   double calculerPrix(double quantite);
   ```
2. Faire implémenter `IComposantRecette` par `Ingredient` :
   - `calculerPrix(double quantite)` → retourne `this.prix * quantite`.
3. Faire implémenter `IComposantRecette` par `Recette` :
   - `calculerPrix(double quantite)` → calcule récursivement le prix de tous ses composants (ingrédients + sous-recettes) multiplié par `quantite`.
4. Simplifier `Recette` en remplaçant les deux maps séparées (`ingredients` et `sousRecettes`) par une seule :
   ```java
   Map<IComposantRecette, Double> composants = new HashMap<>();
   ```
5. Dans `Main`, ajouter le calcul avec le pattern :
   ```java
   double prixAvecPattern = pizza.calculerPrix(1);
   System.out.println("Le prix total (pattern Composite) de la pizza est " + prixAvecPattern);
   ```

**Copies d'écran attendues :**
- Le code de l'interface `IComposantRecette`
- La méthode `calculerPrix` dans `Ingredient`
- La méthode `calculerPrix` dans `Recette`
- La sortie console comparant les deux résultats (naïf et pattern) — ils doivent être identiques

