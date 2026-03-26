## Bloc 7 — Synthèse finale
**Durée : 45 min** · 15h45 – 16h30

### Activité 1 — Carte mentale des patterns (15 min)
Construire ensemble au tableau :

```
                    Design Patterns
                         │
          ┌──────────────┼──────────────┐
          │              │              │
       Création       Structure     Comportement
       │              │              │
    Singleton      Composite      Iterator
    Factory M.     Decorator      Observer
    Abstract F.                   Visitor
```

### Activité 2 — « Quel pattern choisir ? » (15 min)
Poser des situations, les étudiants répondent :

| Situation | Pattern attendu |
|---|---|
| *« Je veux une seule connexion BDD dans toute l'appli. »* | `Singleton` |
| *« Je veux ajouter du logging et du caching à mes requêtes HTTP sans le modifier. »* | `Decorator` |
| *« J'ai un système de fichiers avec des dossiers et des fichiers, et je veux calculer la taille totale. »* | `Composite` |
| *« Je veux que mes composants d'interface soient tous du même thème. »* | `Abstract Factory` |
| *« Je veux être prévenu quand un produit est de nouveau en stock. »* | `Observer` |
| *« Je veux parcourir un arbre avec un `foreach`. »* | `Iterator` |
| *« Je veux appliquer différents traitements à une liste d'objets de types variés sans `instanceof`. »* | `Visitor` |

### Activité 3 — Ressources pour aller plus loin (5 min)
- [refactoring.guru](https://refactoring.guru/fr/design-patterns) → fiches illustrées de tous les patterns ;
- les patterns non vus mais intéressants à explorer seul : `Adapter`, `Facade`, `Strategy`, `Command` (mentionnés dans le `README.md`) ;
- rappeler que les patterns viennent avec l'expérience : on ne force pas un pattern dans du code qui n'en a pas besoin.

### Mot de fin (10 min)
> *« Vous ne devez pas connaître 23 patterns par cœur. Vous devez savoir reconnaître un problème de design, savoir qu'une solution existe, et savoir où la trouver. C'est ça qui vous rendra meilleurs. »*

