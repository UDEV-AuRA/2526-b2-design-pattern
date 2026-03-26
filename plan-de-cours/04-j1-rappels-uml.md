## Bloc 4 — Rappels UML : lire et dessiner les design patterns
**Durée : 30 min** · 11h15 – 11h45

### Objectifs
- savoir lire un diagramme de classes UML ;
- savoir dessiner un schéma simple au tableau ou sur papier ;
- avoir le vocabulaire pour lire les diagrammes de [refactoring.guru](https://refactoring.guru).

### Ce qu'il faut présenter (et pas plus)

#### La classe en UML
```
┌──────────────────┐
│    NomClasse      │
├──────────────────┤
│ - attribut: Type  │   (- = private)
│ + attribut: Type  │   (+ = public)
├──────────────────┤
│ + methode(): Type │
│ - methode(): Type │
└──────────────────┘
```

#### Les relations essentielles

| Relation | Symbole UML | Signification | Exemple du projet |
|---|---|---|---|
| **Héritage** | ligne pleine + triangle creux ▷ | « est un » | `Professeur` ▷ `Personne` |
| **Implémentation** | ligne **pointillée** + triangle creux ▹ | « respecte le contrat d'une interface » | `NotifierMail` ▹ `Notifier` |
| **Association** | ligne pleine + flèche simple → | « possède (attribut) » | `Voiture` → `Moteur` (attribut `private Moteur moteur`) |
| **Composition** | losange plein ◆→ | « possède, ne vit pas sans » | `Voiture` ◆→ `Moteur` |
| **Agrégation** | losange vide ◇→ | « contient, peut vivre sans » | `Dossier` ◇→ `Fichier` |
| **Dépendance** | ligne **pointillée** + flèche simple ⇢ | « utilise temporairement » | `Main` ⇢ `Logger` |

> **Astuce pour distinguer Implémentation et Dépendance :** les deux sont en pointillés, mais la pointe est différente :
> - **Triangle creux** ▹ = implémentation (`implements`) ;
> - **Flèche simple** ⇢ = dépendance (utilisation passagère).

#### Association vs Dépendance — explication concrète

C'est la distinction la plus floue pour les étudiants. La clarifier au tableau :

**Association** = la classe a un **attribut** de l'autre type → elle le connaît **en permanence**.
```java
public class Voiture {
    private Moteur moteur; // ← ATTRIBUT : lien permanent, Voiture connaît Moteur tout le temps
}
```

**Dépendance** = la classe **n'a PAS d'attribut** de l'autre type, mais le manipule dans une **variable locale**, un **paramètre de méthode** ou un **type de retour** → lien **temporaire**, le temps d'un appel.
```java
public class Main {
    // Pas d'attribut Logger !
    public static void main(String[] args) {
        Logger logger = Logger.getInstance(); // ← variable locale : lien temporaire
        logger.log("hello");
        // Après cette méthode, Main n'a plus aucun lien avec Logger
    }
}
```

**Question à poser :**
> *« `Main` dépend-il de `Logger` ? Oui : si `Logger` change de signature, `Main` casse. Mais est-ce que `Main` possède un `Logger` ? Non : il l'utilise juste le temps d'une méthode. C'est ça, une dépendance. »*

#### Exercice rapide (10 min)
Demander aux étudiants de dessiner sur papier le diagramme UML de `_03heritage/` :
- `Personne` (parent)
- `Professeur`, `Etudiant`, `PersonnelAdministratif` (enfants)

Puis corriger ensemble au tableau.

#### Ressource à donner
Indiquer que les diagrammes de chaque pattern sont disponibles sur : https://refactoring.guru/fr/design-patterns

