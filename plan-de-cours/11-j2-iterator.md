## Bloc 2 — Pattern 5 : Iterator
**Durée : 2h00** · 09h15 – 11h15

### Le problème que ça résout
On a une structure de données complexe (arbre, graphe, liste chaînée…) et on veut pouvoir la **parcourir avec un simple `foreach`**, sans que le code client ait besoin de savoir comment elle est construite.

### Métaphore
> *« Un distributeur de bonbons. Tu tournes la manivelle et tu obtiens un bonbon. Tu n'as pas besoin de savoir comment les bonbons sont empilés à l'intérieur. Tu demandes juste "le suivant". »*

### Déroulé

#### 1. Poser le problème (15 min)
`src/iterator/arbre/Main.java` :
- on a un `Arbre` avec des enfants récursifs ;
- 2 manières de le parcourir :
  - **récursif** → `parcourirRecurssif()` : simple mais impossible à mettre dans un `foreach` ;
  - **itératif** → `parcourirIteratif()` : logique de parcours mélangée au code client.

**Question à poser :**
> *« Et si je veux parcourir ce même arbre en profondeur et en largeur ? Est-ce que le code client doit connaître la différence ? »*

#### 2. Rappel : comment `foreach` fonctionne en Java (10 min)
Expliquer au tableau :
```java
for (String x : maCollection) { ... }
```
→ Java appelle en coulisses :
1. `maCollection.iterator()` → retourne un `Iterator<String>` ;
2. `iterator.hasNext()` → est-ce qu'il reste des éléments ? ;
3. `iterator.next()` → donne l'élément suivant.

→ Donc pour qu'un objet soit parcourable avec `foreach`, il doit implémenter `Iterable<T>`.

#### 3. Montrer l'exemple des collections (20 min)
`src/iterator/collection/` :
- `Pair`, `Triplet` → structures simples ;
- `PairIterator`, `TripletIterator` → itérateurs dédiés ;
- `Tuple` → interface commune.

Montrer comment chaque structure implémente `Iterable` et retourne son propre `Iterator`.

#### 4. Dessiner le diagramme UML (10 min)
```
    Iterable<T>              Iterator<T>
    ┌──────────┐             ┌──────────┐
    │iterator()│             │hasNext() │
    └─────┬────┘             └─────┬────┘
          │                  ┌──────┴──────┐
          │                  │             │
       Arbre              ArbreIterator
   implements              implements
   Iterable<String>        Iterator<String>
```

#### 5. Exercice : rendre l'Arbre itérable 🌳 (45 min)
`src/iterator/arbre/`

**Consigne donnée aux étudiants :**
> Appliquer le design pattern Iterator sur la classe `Arbre` afin de pouvoir écrire :
> ```java
> for (String x : a1) {
>     System.out.println(x);
> }
> ```

**Ce qu'ils doivent faire :**
1. faire implémenter `Iterable<String>` à `Arbre` ;
2. créer une classe `ArbreIterator` qui implémente `Iterator<String>` ;
3. gérer le parcours (en largeur ou en profondeur, au choix).

#### 6. Correction (20 min)
Corriger au tableau, comparer les solutions, dessiner le diagramme final.

