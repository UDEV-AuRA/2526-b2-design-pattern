## Bloc 5 — Pattern 1 : Singleton
**Durée : 30 min** · 11h45 – 12h15

### Le problème que ça résout
Parfois, on veut **une et une seule instance** d'une classe dans tout le programme. Exemples : un logger, une connexion à la base de données, un fichier de configuration.

### Métaphore
> *« L'Univers. Il n'y en a qu'un. On ne peut pas en fabriquer un deuxième. Quand n'importe qui "demande l'Univers", il obtient toujours le même. Ce n'est pas une convention — c'est physiquement impossible d'en créer un autre. »*

### Point clé à marteler : ce n'est PAS une convention

Beaucoup d'étudiants pensent qu'un Singleton c'est « on décide de ne créer qu'une seule instance ». Non.

> **Un Singleton, c'est une classe qui rend structurellement IMPOSSIBLE la création d'une deuxième instance.**

C'est le **constructeur `private`** qui garantit ça. Pas un commentaire, pas un accord entre développeurs. Le **compilateur** refuse `new Logger()` depuis l'extérieur.

```java
Logger logger = new Logger(); // ❌ ERREUR DE COMPILATION : le constructeur est private
Logger logger = Logger.getLogger(); // ✅ seul moyen d'obtenir l'instance
```

**Analogie à poser :**
> *« C'est comme une porte blindée avec une seule clé, pas un panneau "merci de ne pas entrer". Le panneau, c'est une convention. La porte blindée, c'est le constructeur `private`. »*

### Déroulé

#### 1. Poser le problème (5 min)
- que se passe-t-il si on crée 5 `Logger` différents ?
- les messages de log arrivent dans le désordre, chacun écrit dans son propre fichier, etc.
- chaque partie du code a « son » logger → aucune centralisation.

**Question :**
> *« Si chaque classe crée son propre `new Logger()`, combien de loggers aurez-vous dans un projet de 200 classes ? »*
> → Potentiellement 200. Et aucun moyen de garantir qu'ils écrivent tous au même endroit.

#### 2. Montrer le code (10 min)
`src/singleton/bon/Logger.java` — **décortiquer ligne par ligne :**

```java
public class Logger {
    private Logger() { }                          // 1️⃣ Constructeur PRIVATE
    private static Logger logger = new Logger();  // 2️⃣ La seule instance, créée par la classe elle-même
    public static Logger getLogger() {            // 3️⃣ Point d'accès unique
        return logger;
    }
}
```

**Expliquer chaque ligne :**
1. **`private Logger()`** — personne à l'extérieur ne peut écrire `new Logger()`. C'est la **porte blindée**. Le compilateur empêche toute tentative.
2. **`private static Logger logger = new Logger()`** — la classe se crée elle-même sa propre instance. Elle seule a le droit (elle est « à l'intérieur de la porte »).
3. **`public static Logger getLogger()`** — le seul moyen d'obtenir le logger. On passe obligatoirement par cette porte.

`src/singleton/bon/Client.java` :
```java
Logger logger1 = Logger.getLogger();
Logger logger2 = Logger.getLogger();
// logger1 == logger2 → true ! C'est le MÊME objet en mémoire.
```

**Démontrer en live :**
```java
System.out.println(logger1 == logger2); // true
System.out.println(logger1);            // singleton.bon.Logger@15db9742
System.out.println(logger2);            // singleton.bon.Logger@15db9742 ← même adresse !
```

#### 3. Dessiner le diagramme UML (5 min)
```
┌──────────────────────────┐
│        Logger             │
├──────────────────────────┤
│ - logger: Logger (static) │
├──────────────────────────┤
│ - Logger()                │  ← private !
│ + getLogger(): Logger     │  ← seul point d'accès
└──────────────────────────┘
```

Faire remarquer : le `-` devant `Logger()` signifie `private` en UML. C'est la clé du pattern.

#### 4. Récapitulatif des 3 mécanismes (2 min)
Résumer au tableau les **3 verrous** qui font le Singleton :

| Mécanisme | Ce que ça empêche |
|---|---|
| Constructeur `private` | Empêche quiconque d'écrire `new Logger()` |
| Attribut `static` | Stocke l'unique instance au niveau de la **classe** (pas d'un objet) |
| Méthode `static` publique | Fournit l'unique point d'accès → tout le monde obtient le même objet |

> *« Enlevez n'importe lequel de ces 3 verrous, et le Singleton ne tient plus. »*

#### 5. Discussion : limites et abus (3 min)
- rend le code difficile à tester (état global partagé) ;
- crée un couplage fort (tout dépend du Singleton) ;
- à utiliser avec parcimonie — un Singleton par projet, c'est souvent suffisant.

> *« Un Singleton, c'est comme un médicament : à bonne dose ça soigne, en overdose ça tue le code. »*

#### 6. Questions flash (5 min)
> *« Donnez-moi un exemple de la vraie vie où il y a une seule instance de quelque chose ET où c'est physiquement impossible d'en avoir deux. »*
> → L'Univers, le noyau d'un système d'exploitation, le fichier `/etc/hostname` d'une machine…
>
> *« Si je veux tester mon code avec un faux Logger, comment je fais si `Logger` est un Singleton ? »*
> → C'est exactement le problème. On ne peut pas facilement le remplacer. C'est pour ça qu'on l'utilise avec parcimonie.

