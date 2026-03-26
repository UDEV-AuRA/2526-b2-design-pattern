## Bloc 2 — Introduction aux design patterns : le pourquoi
**Durée : 30 min** · 09h15 – 09h45

### Objectifs
- comprendre ce qu'est un design pattern ;
- comprendre pourquoi ça existe ;
- savoir les situer dans l'histoire du développement logiciel.

### Contenu à présenter

#### Qu'est-ce qu'un design pattern ?
- une **solution éprouvée** à un problème de conception **récurrent** ;
- pas une bibliothèque, pas un framework, pas du code copié-collé ;
- plutôt un **schéma de pensée** réutilisable.

#### Métaphore à utiliser en cours
> *« Un design pattern, c'est comme un plan d'architecte pour une maison. Tu ne recopies pas la maison du voisin, mais tu réutilises le même plan de base (cuisine à côté du salon, salle de bain à l'étage) parce qu'on sait que ça marche bien. »*

#### Pourquoi les design patterns existent
- le code grandit → il devient difficile à maintenir ;
- on se retrouve souvent face aux **mêmes types de problèmes** ;
- les développeurs expérimentés ont identifié des **solutions communes** ;
- le Gang of Four (GoF) les a formalisés en 1994 dans *Design Patterns: Elements of Reusable Object-Oriented Software*.

#### Les 3 familles de patterns
Présenter au tableau :

| Famille | Rôle | Exemples du module |
|---|---|---|
| **Création** | comment créer des objets | `Singleton`, `Factory Method`, `Abstract Factory` |
| **Structure** | comment organiser les objets entre eux | `Composite`, `Decorator` |
| **Comportement** | comment les objets communiquent | `Iterator`, `Observer`, `Visitor` |

#### Ce qu'un pattern n'est PAS
- une règle absolue ;
- une solution à appliquer partout ;
- un moyen de rendre le code « plus beau » sans raison.

#### Question à poser aux étudiants
> *« Est-ce que vous avez déjà eu du code qui marchait mais qui devenait ingérable quand il fallait ajouter une fonctionnalité ? »*

