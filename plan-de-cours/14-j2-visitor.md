## Bloc 6 — Pattern 8 : Visitor
**Durée : 45 min** · 15h00 – 15h45

### Le problème que ça résout
On a une collection d'objets de **types différents**, et on veut pouvoir leur appliquer **plusieurs traitements** sans modifier leurs classes à chaque fois.

### Métaphore
> *« Un guide touristique qui visite une ville. Il passe devant un musée, un restaurant, une mairie. Chaque lieu est différent, mais le guide sait quoi faire avec chacun : le touriste regarde les horaires du musée, le prix du restaurant, et ignore la mairie. Si demain un nouveau type de visiteur arrive (un inspecteur sanitaire), on crée un nouveau visitor sans toucher aux lieux. »*

### Déroulé

#### 1. Montrer le problème (10 min)
`src/visitor/Main.java` — version sans pattern :
```java
for (Lieu lieu : lieux) {
    if (lieu instanceof Restaurant) { ... }
    else if (lieu instanceof Mairie) { ... }
    else if (lieu instanceof Musee) { ... }
}
```

**Question :**
> *« Et si j'ajoute un type de lieu (`Cinema`) et un type de visiteur (`InspecteurSanitaire`) ? Combien de `if/else` je dois toucher ? »*

#### 2. Présenter le pattern (15 min)
- `Visitable` → interface avec `accept(Visitor v)` ;
- `Visitor` → interface avec une méthode `visit` par type de lieu ;
- `EmptyVisitor` → implémentation par défaut qui ne fait rien ;
- `TouristeVisitor` → surcharge uniquement `visitRestaurant()` et `visitMusee()`.

La version avec pattern :
```java
TouristeVisitor visitor = new TouristeVisitor();
for (Lieu lieu : lieux) {
    lieu.accept(visitor);
}
```

**Message clé :**
> *« Le lieu sait comment se faire visiter (`accept`). Le visiteur sait quoi faire avec chaque type de lieu. Personne n'a besoin de `instanceof`. »*

#### 3. Dessiner le diagramme UML (10 min)
```
   Visitable               Visitor
   ┌──────────┐            ┌──────────────────┐
   │accept(v) │            │visitMairie()      │
   └─────┬────┘            │visitRestaurant()  │
         │                 │visitMusee()       │
    ┌────┼────┐            └────────┬──────────┘
    │    │    │                     │
  Musee Rest. Mairie         TouristeVisitor
```

#### 4. Discussion (10 min)
- pattern plus abstrait et plus difficile ;
- utile quand la structure change rarement mais les traitements changent souvent ;
- pas nécessaire de savoir le recoder entièrement à ce stade : savoir le **lire et le reconnaître** suffit.

