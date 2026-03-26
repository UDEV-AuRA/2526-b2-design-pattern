## Bloc 3 — Rappels POO
**Durée : 1h30** · 09h45 – 11h15

### Objectifs
- remettre tout le monde au même niveau sur les fondamentaux POO ;
- installer le vocabulaire qui sera utilisé pendant tout le module ;
- montrer que les design patterns s'appuient directement sur ces concepts.

### Comment présenter ce bloc
Ne pas faire un cours magistral exhaustif. Plutôt :
1. rappeler le concept ;
2. montrer un bout de code du projet ;
3. poser une question ;
4. passer au suivant.

---

### 3.1 Classes, objets et encapsulation
**Durée indicative : 20 min**

#### Ce que les étudiants doivent savoir
- une **classe** = un plan de fabrication ;
- un **objet** = une instance concrète de ce plan ;
- l'**encapsulation** = protéger les données internes (`private`) et exposer des méthodes contrôlées ;
- **encapsuler ≠ mettre des getters/setters partout** : le vrai enjeu, c'est que l'état interne ne puisse évoluer qu'à travers des **opérations métier** qui garantissent la cohérence.

#### Métaphore
> *« Une classe, c'est un moule à gâteau. L'objet, c'est le gâteau. Tu peux faire 10 gâteaux à partir du même moule, mais chacun a sa propre garniture. »*

#### Exemple 1 — getter / setter (rappel basique)
`src/_1poo/_03heritage/Personne.java` :
- attributs `private` (`nom`, `prenom`, `age`) ;
- accesseurs `getNom()`, `setNom()` ;
- faire remarquer : on ne touche jamais directement aux attributs depuis l'extérieur.

> C'est la version « minimum » de l'encapsulation. Mais attention : si on a un getter ET un setter pour chaque attribut, l'objet est presque aussi exposé que s'il était `public`.

#### Exemple 2 — état interne protégé par des méthodes métier ⭐
`src/_1poo/_01encapsulation/CompteBancaire.java` :
- l'attribut `solde` est `private` ;
- **il n'y a pas de `setSolde()`** → impossible d'écrire `compte.setSolde(1_000_000)` ;
- le solde ne peut évoluer que via `deposer(montant)` et `retirer(montant)` ;
- les règles métier sont **à l'intérieur** de la classe :
  - on ne peut pas déposer un montant négatif ;
  - on ne peut pas retirer plus que le solde.

```java
CompteBancaire compte = new CompteBancaire("Alice", 500);
compte.deposer(200);    // ✅ solde = 700
compte.retirer(150);    // ✅ solde = 550
compte.retirer(9999);   // ❌ IllegalStateException : solde insuffisant
// compte.setSolde(1_000_000); // ❌ n'existe pas !
```

`src/_1poo/_01encapsulation/Main.java` :
- montrer l'exécution pour bien illustrer les cas d'erreur.

**Message clé :**
> *« L'encapsulation, ce n'est pas juste mettre `private` + getter + setter. C'est **contrôler comment** l'état évolue. Un `CompteBancaire` sans `setSolde()`, c'est un compte qu'on ne peut pas trafiquer. Les méthodes `deposer()` et `retirer()` sont les seules portes d'entrée, et elles vérifient les règles. »*

#### Question à poser
> *« Si `solde` était `public`, ou s'il y avait un `setSolde()`, qu'est-ce qu'un développeur pressé pourrait faire dans un programme avec 200 classes ? »*
> → N'importe qui pourrait écrire `compte.solde = -500` ou `compte.setSolde(1_000_000)` et casser toute la logique métier.

#### Pont vers les patterns
> *« Dans les design patterns, on verra souvent des classes dont l'état interne est modifié uniquement par des méthodes bien définies. Par exemple, dans `Composite`, un `Dossier` expose `add(IFile)` et `calculerTaille()` mais ne vous laisse pas manipuler sa liste d'enfants directement. C'est le même principe. »*

---

### 3.2 Héritage
**Durée indicative : 20 min**

#### Ce que les étudiants doivent savoir
- une sous-classe **hérite** des attributs et méthodes de sa classe parente ;
- elle peut ajouter ses propres attributs / méthodes ;
- relation « est-un » : un `Professeur` **est une** `Personne` ;
- **l'héritage ne sert pas qu'à mutualiser des attributs** — c'est son usage le plus pauvre.

#### ⚠️ Idée reçue à déconstruire
Beaucoup d'étudiants pensent que l'héritage sert à « ne pas réécrire `nom` et `prenom` dans chaque classe ». C'est vrai, mais c'est la partie la moins intéressante.

**Les 3 vrais intérêts de l'héritage :**

| Intérêt | Ce que ça veut dire | Exemple |
|---|---|---|
| 1. **Partager du comportement** (méthodes) | La classe parente fournit des méthodes métier que les enfants réutilisent ou **redéfinissent** (`@Override`) | `Animal.faireCrier()` redéfini par `Chat` et `Chien` |
| 2. **Établir un contrat** (typage) | On peut manipuler tous les enfants via le type parent → c'est la base du **polymorphisme** | `List<Personne>` qui contient des `Professeur` et des `Etudiant` |
| 3. **Spécialiser** un comportement | Un enfant peut enrichir ou modifier le comportement du parent tout en gardant le reste | Un `Professeur` a la même `toString()` qu'une `Personne` mais ajoute sa `specialite` |

> *Si vous n'utilisez l'héritage que pour mutualiser des attributs, une simple composition fait la même chose en plus souple.*

#### Métaphore
> *« L'héritage, c'est comme un arbre généalogique. Tu hérites des traits de tes parents, mais tu as aussi tes propres caractéristiques. »*
>
> Mais attention à la deuxième partie de la métaphore :
> *« Tu n'hérites pas juste de la couleur des yeux (= attributs). Tu hérites aussi du savoir-faire familial : la recette de cuisine de ta grand-mère, la manière de conduire de ton père (= méthodes). Et toi, tu peux adapter la recette à ton goût (= `@Override`). »*

#### Code à montrer — exemple existant (attributs partagés)
- `src/_1poo/_03heritage/Personne.java` → parent avec `nom`, `prenom`, `age` ;
- `src/_1poo/_03heritage/Professeur.java` → ajoute `specialite` ;
- `src/_1poo/_03heritage/Etudiant.java` → hérite sans rien ajouter ;
- `src/_1poo/_03heritage/Main.java` → `List<Personne>` qui contient des profs et des étudiants.

**Faire remarquer :**
> *« Ici `Etudiant` est une coquille vide. Il hérite de `Personne` juste pour ses attributs. C'est un usage faible de l'héritage. Est-ce qu'on a vraiment besoin d'une sous-classe pour ça ? »*

#### Contre-exemple oral — quand l'héritage prend tout son sens
Proposer à l'oral une version améliorée pour montrer la différence :
```java
public class Personne {
    // ... attributs ...

    public String sePresenter() {
        return "Bonjour, je suis " + prenom + " " + nom;
    }
}

public class Professeur extends Personne {
    private String specialite;

    @Override
    public String sePresenter() {
        // Réutilise le comportement du parent + le spécialise
        return super.sePresenter() + ", professeur en " + specialite;
    }

    public void donnerCours() {
        System.out.println("Je fais cours de " + specialite);
    }
}

public class Etudiant extends Personne {
    private String numEtudiant;

    @Override
    public String sePresenter() {
        return super.sePresenter() + ", étudiant n°" + numEtudiant;
    }
}
```

**Ce que cet exemple montre en plus :**
- `sePresenter()` est un **comportement** partagé, pas un attribut ;
- chaque enfant le **redéfinit** (`@Override`) pour le spécialiser ;
- `donnerCours()` est une méthode qui n'a de sens que pour `Professeur` — c'est la **spécialisation** ;
- on peut appeler `personne.sePresenter()` sur n'importe quel élément d'une `List<Personne>` sans connaître le type réel — c'est le **polymorphisme** (transition vers 3.3).

#### Message clé
> *« Si votre héritage ne sert qu'à partager des champs `private` avec des getters/setters, vous n'avez pas besoin d'héritage. L'héritage prend tout son sens quand les sous-classes **redéfinissent un comportement** ou quand on veut traiter les enfants de manière **uniforme** via le type parent. C'est exactement ce qu'on fera dans les design patterns : `Factory Method`, `Visitor`, `Composite`… tous reposent sur cette idée. »*

#### Point de vigilance
Faire remarquer dans `Main.java` le problème du `instanceof` :
```java
if (personne instanceof Professeur) { ... }
```
→ c'est exactement ce genre de code que les patterns aideront à éviter.

#### Question à poser
> *« Dans le `Main.java`, on fait un `instanceof` pour accéder à `getSpecialite()`. Si on avait une méthode `sePresenter()` redéfinie dans chaque sous-classe, est-ce qu'on aurait encore besoin de ce `instanceof` ? »*
> → Non. Le polymorphisme ferait le travail tout seul. C'est ça, le vrai pouvoir de l'héritage.

---

### 3.3 Polymorphisme
**Durée indicative : 15 min**

#### Ce que les étudiants doivent savoir
- on peut manipuler un objet via sa **référence parente** ;
- le comportement réel dépend du **type concret** de l'objet ;
- c'est le moteur de la plupart des design patterns.

#### Métaphore
> *« Imagine une télécommande universelle. Tu appuies sur "allumer", et le comportement dépend de l'appareil branché : une télé s'allume, une radio aussi, mais chacun fait ça à sa manière. »*

#### Code à montrer
- `src/_1poo/_04polymorphisme/Animal.java` → classe abstraite avec `faireCrier()` ;
- `src/_1poo/_04polymorphisme/Chat.java` → implémente `faireCrier()` à sa manière ;
- `src/_1poo/_04polymorphisme/Chien.java` → idem.

#### Schéma au tableau
```
       Animal (abstract)
       ┌──────────┐
       │faireCrier│
       └────┬─────┘
            │
     ┌──────┴──────┐
     │             │
   Chat          Chien
  "Miaou"       "Wouf"
```

#### Question à poser
> *« Si je rajoute un `Canard`, est-ce que je dois modifier la classe `Animal` ? »*
> → Non, il suffit de créer une nouvelle sous-classe. C'est exactement l'idée des patterns.

---

### 3.4 Abstraction et interfaces
**Durée indicative : 15 min**

#### Ce que les étudiants doivent savoir
- une **classe abstraite** = un plan incomplet, à compléter par les sous-classes ;
- une **interface** = un contrat pur, sans implémentation ;
- « programmer contre une interface » = dépendre du contrat, pas de l'implémentation.

#### Différence entre les deux
Résumer au tableau :

| | Classe abstraite | Interface |
|---|---|---|
| Peut avoir du code ? | Oui | Non (avant Java 8) |
| Héritage multiple ? | Non | Oui |
| Quand l'utiliser ? | Quand on a du code commun à partager | Quand on veut juste un contrat |

#### Code à montrer
- `src/_1poo/_05abstraction/Animal.java` → **interface** avec `faireCrier()` ;
- `src/_1poo/_03impl/notif/Notifier.java` → interface de notification ;
- `src/_1poo/_03impl/notif/NotifierMail.java` et `NotifierSms.java` → 2 implémentations.

#### Pont vers les patterns
> *« Quand on parlera de `Decorator`, `Observer`, `Factory Method`… vous verrez qu'on programme toujours contre une interface. C'est ce qui rend le code souple. »*

---

### 3.5 Composition
**Durée indicative : 10 min**

#### Ce que les étudiants doivent savoir
- un objet peut **contenir** d'autres objets ;
- relation « a-un » (vs « est-un » pour l'héritage) ;
- souvent préférable à l'héritage pour la flexibilité.

#### Métaphore
> *« Une voiture n'est pas un moteur, mais elle a un moteur. Si le moteur casse, on le remplace sans jeter la voiture. »*

#### Code à montrer
- `src/_1poo/_05composition/Voiture.java` → contient un attribut `private Moteur moteur` ;
- `src/_1poo/_05composition/Moteur.java` → classe indépendante.

#### Lien avec les patterns
La composition est le mécanisme central de `Composite` et `Decorator`.

---

### 3.6 Le mot-clé `static`
**Durée indicative : 5 min**

#### Ce que les étudiants doivent savoir
- un membre `static` appartient à la **classe**, pas à une instance ;
- on y accède via `NomClasse.methode()`, pas via un objet ;
- utile pour les utilitaires, les constantes, et… `Singleton`.

#### Code à montrer
- `src/_1poo/_06static/Exemple.java` → `methodDynamique()` vs `methodStatic()` ;
- `src/_1poo/_06static/Main.java` → appel des deux.

---

### 3.7 SOLID — les principes essentiels
**Durée indicative : 20 min**

#### Introduction (2 min)
Présenter SOLID au tableau :

| Lettre | Principe | En une phrase |
|:---:|---|---|
| **S** | Single Responsibility | Une classe = une seule raison de changer |
| **O** | Open / Closed | Ouvert à l'extension, fermé à la modification |
| **L** | Liskov Substitution | Un enfant doit pouvoir remplacer son parent sans casser le code |
| **I** | Interface Segregation | Pas d'interface « fourre-tout » |
| **D** | Dependency Inversion | Dépendre d'abstractions, pas d'implémentations |

> *« SOLID, ce sont 5 boussoles. Elles ne vous disent pas exactement quoi coder, mais elles vous disent si vous partez dans la mauvaise direction. »*

**Cadrage :** on ne va pas passer 1h sur SOLID. On va voir les **3 principes les plus utiles** pour comprendre les design patterns (S, O, D), et on en **survolera 2 autres** (L, I) pour la culture.

---

#### S — Single Responsibility Principle (SRP)
**Durée indicative : 4 min**

##### Définition
> *« Une classe ne doit avoir qu'**une seule raison de changer**. »*

Autrement dit : chaque classe a **un seul job**.

##### Métaphore
> *« Dans un restaurant, le cuisinier cuisine, le serveur sert, le comptable fait les comptes. Si le cuisinier fait aussi la compta, le jour où la loi fiscale change, il faut modifier… le cuisinier. Ça n'a aucun sens. »*

##### Exemple oral — pas besoin de code
Poser au tableau une classe `Utilisateur` qui fait trop de choses :
```java
public class Utilisateur {
    public void seConnecter() { ... }
    public void envoyerEmail() { ... }
    public void genererPDF() { ... }
    public void sauvegarderEnBDD() { ... }
}
```

**Question :**
> *« Combien de raisons cette classe a-t-elle de changer ? »*
> → 4 : si la logique d'authentification change, si le format d'email change, si le format PDF change, si la BDD change. C'est 4 classes, pas 1.

##### Message clé
> *« Si vous décrivez votre classe et que vous utilisez le mot "ET", c'est qu'elle fait trop de choses. `Utilisateur` gère la connexion ET les emails ET le PDF ET la BDD. »*

##### Lien avec les patterns
> *« SRP explique pourquoi dans `Observer`, l'objet observé ne gère pas lui-même les notifications de chaque type d'abonné. Et pourquoi dans `Decorator`, chaque comportement (compression, chiffrement) est dans sa propre classe. »*

---

#### O — Open/Closed Principle (OCP)
**Durée indicative : 5 min**

##### Définition
> *« Une classe doit être **ouverte à l'extension** (on peut ajouter du comportement) mais **fermée à la modification** (on ne touche pas au code existant). »*

##### Métaphore
> *« Une multiprise. Tu veux brancher un nouvel appareil ? Tu ajoutes une prise. Tu ne démontes pas la multiprise pour re-souder les fils à l'intérieur. »*

##### Code à montrer — version mauvaise ❌
`src/_1poo/_13solid/_2openclose/mauvais/ProcessusPaiement.java` :
```java
public void payer(String moyenPaiement) {
    if (moyenPaiement.equals("cb")) {
        //..
    } else if (moyenPaiement.equals("paypal")) {
        //..
    }
}
```

**Faire remarquer :**
- chaque nouveau moyen de paiement = modifier cette classe ;
- risque de casser un moyen existant en modifiant le `if/else` ;
- cette classe grandit indéfiniment.

##### Code à montrer — version bonne ✅
`src/_1poo/_13solid/_2openclose/bon/PaiementCB.java` et `PaiementPayPal.java` :
- chaque moyen de paiement est sa propre classe ;
- pour ajouter Apple Pay, on **crée** `PaiementApplePay.java` → on **n'ouvre jamais** `ProcessusPaiement`.

**Compléter à l'oral** (l'interface manque dans le code, la présenter au tableau) :
```java
public interface MoyenPaiement {
    void payer();
}

public class PaiementCB implements MoyenPaiement { ... }
public class PaiementPayPal implements MoyenPaiement { ... }
// Ajouter Apple Pay = créer une nouvelle classe, point.
```

##### Questions à poser
> *« Si je veux ajouter Apple Pay dans la version mauvaise, combien de fichiers je modifie ? »* → 1, mais je risque de tout casser.
>
> *« Et dans la version bonne ? »* → 0 fichier modifié, 1 fichier créé. Aucun risque de régression.

##### Schéma au tableau
```
      MoyenPaiement (interface)
      ┌────────┐
      │payer() │
      └───┬────┘
          │
   ┌──────┼──────────┐
   │      │          │
 CB    PayPal    ApplePay ← AJOUTÉ sans toucher au reste
```

##### Lien avec les patterns
> *« OCP est le cœur de presque tous les design patterns. `Factory Method` : on ajoute une sous-classe au lieu de modifier le créateur. `Composite` : on ajoute un nouveau type de nœud. `Decorator` : on ajoute un décorateur sans toucher aux existants. `Observer` : on ajoute un abonné sans modifier le sujet. Les patterns sont des machines à respecter Open/Closed. »*

---

#### L — Liskov Substitution Principle (LSP)
**Durée indicative : 3 min** (survol)

##### Définition
> *« Si `B` hérite de `A`, on doit pouvoir utiliser un `B` partout où on attend un `A` sans que le programme se comporte bizarrement. »*

##### Métaphore
> *« Si tu remplaces un vrai billet de 20€ par un faux parfait, la machine à café doit quand même te servir un café. Si la machine se bloque, c'est que le faux billet ne respecte pas le "contrat" du vrai billet. »*

##### Code à montrer — version mauvaise ❌
`src/_1poo/_13solid/_3liskovsubstitution/mauvais/` :
```java
public class Chien {
    public void aboyer() {
        System.out.println("aboyer");
    }
}
public class ChienIncontinent extends Chien {
    public void aboyer() {
        System.out.println("aboyer");
        System.out.println("fais pipi"); // 💥 Effet de bord inattendu !
    }
}
```

**Faire remarquer :**
> *« Quelqu'un qui appelle `chien.aboyer()` s'attend à ce que le chien aboie. Pas à ce qu'il fasse pipi en plus. Si on remplace un `Chien` par un `ChienIncontinent`, le comportement change de manière surprenante. Le contrat est violé. »*

##### Code à montrer — version bonne ✅
`src/_1poo/_13solid/_3liskovsubstitution/bon/` :
```java
public class Chien {
    public void aboyer() {
        System.out.println("aboyer");
    }
    public void etreContent() {
        aboyer();
    }
}
public class ChienIncontinent extends Chien {
    public void etreContent() {  // ← on redéfinit le bon comportement
        aboyer();
        System.out.println("fais pipi");
    }
}
```

→ `aboyer()` fait toujours la même chose quel que soit le type de chien. Le comportement spécifique est dans `etreContent()`, une méthode qui **autorise** la variation.

##### Message clé
> *« Hériter ne veut pas dire "faire n'importe quoi". Un enfant doit respecter les promesses du parent. Sinon, le polymorphisme devient un piège. »*

---

#### I — Interface Segregation Principle (ISP)
**Durée indicative : 2 min** (survol rapide)

##### Définition
> *« Aucun code client ne devrait être forcé de dépendre de méthodes qu'il n'utilise pas. »*

##### Métaphore
> *« Une télécommande universelle avec 200 boutons dont tu utilises 5. Mieux vaut avoir une petite télécommande avec juste les boutons utiles. »*

##### Exemple oral rapide
```java
// ❌ Interface trop grosse
public interface Animal {
    void marcher();
    void nager();
    void voler();
}
// Un poisson qui implémente voler() ? Absurde.

// ✅ Interfaces séparées
public interface Marcheur { void marcher(); }
public interface Nageur   { void nager(); }
public interface Volant   { void voler(); }
// Un canard implémente les 3, un poisson implémente seulement Nageur.
```

##### Message clé
> *« On verra ce principe à l'œuvre dans les patterns : les interfaces comme `Iterable`, `Visitable`, `DataSource` sont toutes petites et ciblées. Pas d'interface "God Object". »*

---

#### D — Dependency Inversion Principle (DIP)
**Durée indicative : 5 min**

##### Définition
> *« Les modules de haut niveau ne doivent pas dépendre des modules de bas niveau. Les deux doivent dépendre d'**abstractions**. »*

##### Métaphore
> *« Une prise électrique. Tu ne soudes pas ton chargeur directement dans le mur. Tu passes par une prise standard (= l'abstraction). Si tu changes de chargeur, le mur ne bouge pas. Si tu changes la source d'énergie, le chargeur ne bouge pas. »*

##### Code à montrer — version mauvaise ❌
`src/_1poo/_13solid/_5dependencyinversion/mauvais/FenetrePrincipale.java` :
```java
public class FenetrePrincipale {
    private ServiceEnregistementSql serviceEnregistementSql = new ServiceEnregistementSql();
    private ServiceEnregistementFichier serviceEnregistementFichier = new ServiceEnregistementFichier();
    private String modeEnregistrement = "sql";

    public void clickBoutonEnregistrer() {
        if (Objects.equals(modeEnregistrement, "sql")) {
            serviceEnregistementSql.enregistrer();
        } else {
            serviceEnregistementFichier.enregistrer();
        }
    }
}
```

**Faire remarquer :**
- la fenêtre **connaît** les 2 implémentations concrètes par leur nom ;
- elle les instancie elle-même avec `new` ;
- elle contient un `if/else` pour choisir ;
- ajouter un 3e mode = modifier la fenêtre (viole aussi OCP !).

##### Schéma au tableau — version mauvaise
```
FenetrePrincipale
    │
    ├──→ ServiceEnregistementSql        (dépendance directe)
    └──→ ServiceEnregistementFichier    (dépendance directe)
```
→ Le module de haut niveau (la fenêtre) dépend des modules de bas niveau (les services concrets).

##### Code à montrer — version bonne ✅
`src/_1poo/_13solid/_5dependencyinversion/bon/` :

**1. L'interface (l'abstraction)**
`ServiceEnregistement.java` :
```java
public interface ServiceEnregistement {
    void enregistrer();
}
```

**2. Les implémentations**
`ServiceEnregistementSql.java` et `ServiceEnregistementFichier.java` implémentent `ServiceEnregistement`.

**3. La fenêtre — ne connaît que l'interface**
`FenetrePrincipale.java` :
```java
public class FenetrePrincipale {
    private ServiceEnregistement serviceEnregistement;

    public FenetrePrincipale(ServiceEnregistement serviceEnregistement) {
        this.serviceEnregistement = serviceEnregistement;  // injection par constructeur
    }

    public void clickBoutonEnregistrer() {
        serviceEnregistement.enregistrer();  // aucun if/else !
    }
}
```

**4. Le choix se fait au point d'entrée**
`Main.java` :
```java
ServiceEnregistement service;
if (args[0] == "sql") {
    service = new ServiceEnregistementSql();
} else {
    service = new ServiceEnregistementFichier();
}
FenetrePrincipale fenetre = new FenetrePrincipale(service);
```

##### Schéma au tableau — version bonne
```
FenetrePrincipale                    ServiceEnregistement (interface)
    │                                        ▲
    └──→ ServiceEnregistement ◁──────────────┤
                                             │
                              ┌──────────────┼──────────────┐
                              │                             │
                   ServiceEnregistementSql    ServiceEnregistementFichier
```
→ Tout le monde dépend de l'abstraction. Plus aucune flèche directe entre haut et bas niveau.

##### Questions à poser
> *« Dans la version bonne, si je veux ajouter un `ServiceEnregistementCloud`, est-ce que je modifie `FenetrePrincipale` ? »*
> → Non. Je crée une nouvelle classe, et je la passe au constructeur dans le `Main`.
>
> *« Comment s'appelle la technique qui consiste à passer la dépendance au constructeur ? »*
> → **L'injection de dépendance.** C'est la conséquence pratique de DIP.

##### Lien avec les patterns
> *« DIP est le principe que vous retrouverez **partout** dans les design patterns :
> - `Factory Method` → le créateur dépend d'une interface `Transport`, pas de `Truck` directement ;
> - `Decorator` → le décorateur dépend de l'interface `DataSource`, pas de `FileDataSource` ;
> - `Observer` → le sujet dépend de l'interface `Subscriber`, pas de `Client` ou `Reparateur` ;
> - `Composite` → le dossier dépend de `IFile`, pas de `Fichier` ou `Dossier`.
>
> Les patterns ne font que **systématiser** l'inversion de dépendance. C'est pour ça qu'on en parle maintenant. »*

---

#### Récapitulatif SOLID au tableau (1 min)

| Principe | Question à se poser |
|---|---|
| **S**RP | *« Ma classe fait-elle plus d'un job ? »* |
| **O**CP | *« Si j'ajoute une fonctionnalité, est-ce que je modifie du code existant ? »* |
| **L**SP | *« Si je remplace un parent par son enfant, est-ce que ça casse quelque chose ? »* |
| **I**SP | *« Mes interfaces ont-elles des méthodes que certains implémenteurs n'utilisent pas ? »* |
| **D**IP | *« Ma classe dépend-elle d'une implémentation concrète ou d'une abstraction ? »* |

> *« Gardez ces 5 questions en tête pendant les 2 jours. Chaque pattern qu'on verra est une réponse concrète à au moins l'une d'entre elles. »*

