## Bloc 7 — Pattern 3 : Abstract Factory
**Durée : 30 min** · 14h30 – 15h00

### Le problème que ça résout
On doit créer des **familles d'objets cohérents entre eux**. Par exemple : un thème clair doit produire un bouton clair, un input clair, un historique clair. On ne veut pas mélanger.

### Métaphore
> *« C'est comme un catalogue de meubles IKEA. Tu choisis la collection "MALM" et tu sais que le lit, la commode et la table de nuit vont ensemble. Tu ne mélanges pas MALM et KALLAX. »*

### Déroulé

#### 1. Différence avec Factory Method (10 min)
| Factory Method | Abstract Factory |
|---|---|
| une seule méthode de création | plusieurs méthodes de création |
| un seul type de produit | une famille de produits |
| sous-classes du créateur | sous-classes de la fabrique |

#### 2. Montrer le code (10 min)
- `src/abstractfactory/widget/bon/theme/mauvais/` → tout mélangé avec des `if/else` ;
- `src/abstractfactory/widget/bon/theme/bon/` → `ComponentAbstractFactory` avec `ComponentClaireConcreteFactory` et `ComponentSombreConcreteFactory`.

#### 3. Dessiner le diagramme UML (10 min)
```
    ComponentAbstractFactory (interface)
    ┌──────────────────────┐
    │ + creerBouton()      │
    │ + creerInput()       │
    │ + creerHistory()     │
    └────────┬─────────────┘
             │
    ┌────────┴──────────┐
    │                   │
  Claire              Sombre
  Factory             Factory
```

**Message clé :**
> *« Factory Method crée UN produit. Abstract Factory crée UNE FAMILLE de produits cohérents. »*

