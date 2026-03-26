## Bloc 8 — Pattern 4 : Composite
**Durée : 1h30** · 15h00 – 16h30

### Le problème que ça résout
On veut traiter **un objet simple** et **un groupe d'objets** exactement de la **même manière**. Par exemple : calculer la taille d'un fichier ou d'un dossier entier de la même façon.

### Métaphore
> *« Une boîte de Lego peut contenir des pièces individuelles ou d'autres boîtes. Mais pour calculer le poids total, tu additionnes tout pareil : pièce par pièce ou boîte par boîte. »*

### Déroulé

#### 1. Montrer le problème (20 min)
`src/composite/fichier/mauvais/Dossier.java` :
- 2 listes séparées : `List<Fichier>` et `List<Dossier>` ;
- 2 méthodes `add()` surchargées ;
- `calculerTaille()` doit parcourir les 2 listes différemment.

**Question à poser :**
> *« Et si je veux ajouter un `Raccourci` ? Combien de listes je dois ajouter ? Combien de boucles dans `calculerTaille()` ? »*

#### 2. Présenter le pattern (15 min)
`src/composite/fichier/bon/IFile.java` :
- une **interface commune** : `calculerTaille()` ;
- `Fichier` implémente `IFile` → feuille ;
- `Dossier` implémente `IFile` → contient une `List<IFile>` → composite.

`src/composite/fichier/bon/Dossier.java` :
- une seule liste `List<IFile>` ;
- une seule méthode `add(IFile)` ;
- `calculerTaille()` itère uniformément.

**Message clé :**
> *« Grâce à l'interface commune, le Dossier ne sait pas s'il contient des fichiers ou d'autres dossiers. Il s'en fiche. Il les traite tous pareil. »*

#### 3. Dessiner le diagramme UML (10 min)
```
        IFile (interface)
        ┌────────────────┐
        │calculerTaille()│
        └───────┬────────┘
                │
       ┌────────┴────────┐
       │                 │
    Fichier           Dossier
   (feuille)        (composite)
                    List<IFile>
```

#### 4. Exercice : la recette de pizza 🍕 (45 min)
`src/composite/recette/`

**Contexte donné aux étudiants :**
On a des `Ingredient` (avec un prix unitaire) et des `Recette` (qui contiennent des ingrédients et des sous-recettes). On veut calculer le prix total d'une pizza.

**Étape 1 (20 min) — Calcul naïf :**
Compléter `calculerPrixRecette()` dans `Main.java` sans modifier les classes.
→ Ils vont devoir parcourir `getIngredients()` et `getSousRecettes()` séparément. C'est lourd.

**Étape 2 (25 min) — Appliquer Composite :**
Refactorer pour que `Ingredient` et `Recette` implémentent une interface commune avec `calculerPrix()`.
→ Le calcul devient récursif et élégant.

#### 5. Correction (15 min)
Corriger ensemble au tableau, montrer le diagramme UML de la solution.

