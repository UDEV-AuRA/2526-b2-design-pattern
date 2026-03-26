## Bloc 3 — Pattern 6 : Decorator (partie 1 — cours)
**Durée : 1h00** · 11h15 – 12h15

### Le problème que ça résout
On veut **ajouter des comportements** à un objet **sans modifier sa classe** et **sans créer une sous-classe pour chaque combinaison**.

### Métaphore
> *« Un café. Tu commences avec un café simple. Tu veux ajouter du lait ? On enveloppe le café dans "avec lait". Du sucre en plus ? On enveloppe encore. Chaque ajout est une couche, mais au final, tu bois toujours un café. »*

### Déroulé

#### 1. Montrer le problème (15 min)
`src/decorator/datasource/Demo.java` :
- on veut sauvegarder des données ;
- on veut pouvoir compresser ET chiffrer ;
- dans la version sans pattern, on doit appeler manuellement chaque étape :
  ```java
  String compressed = compressor.compress(data);
  String encrypted = encryptor.encode(compressed);
  dataSource.writeData(encrypted);
  ```
- et pour la lecture, il faut faire l'inverse dans le bon ordre.

**Question à poser :**
> *« Si je veux ajouter un "logger" qui écrit un log à chaque lecture/écriture, combien d'endroits je dois modifier ? »*

#### 2. Présenter le pattern (15 min)
Principe : chaque décorateur **enveloppe** l'objet décoré et **respecte la même interface**.

`src/decorator/notifier/` :
- `Notifier` → interface avec `notifier(int utilisateurId)` ;
- `EmptyNotifier` → implémentation de base ;
- `AbstractNotifierDecorator` → contient un `Notifier` et délègue ;
- `SlackNotifierDecorator`, `FacebookNotifierDecorator` → ajoutent chacune un comportement avant/après la délégation.

**Message clé :**
> *« Le décorateur a la même interface que l'objet décoré. Le code client ne voit aucune différence. C'est transparent. »*

#### 3. Dessiner le diagramme UML (10 min)
```
       Notifier (interface)
       ┌────────────────┐
       │notifier(id)    │
       └───────┬────────┘
               │
     ┌─────────┴──────────┐
     │                    │
 EmptyNotifier    AbstractNotifierDecorator
                  ┌─────────────────────┐
                  │ - notifier: Notifier │
                  └────────┬────────────┘
                           │
                ┌──────────┴──────────┐
                │                     │
          SlackDecorator      FacebookDecorator
```

#### 4. Montrer l'empilement (20 min)
Dessiner au tableau comment les décorateurs s'emboîtent :
```
FacebookDecorator
  └─ SlackDecorator
       └─ EmptyNotifier

Appel de notifier(42) :
  1. FacebookDecorator.notifier(42)  → envoie sur Facebook, puis délègue ↓
  2. SlackDecorator.notifier(42)     → envoie sur Slack, puis délègue ↓
  3. EmptyNotifier.notifier(42)      → ne fait rien (ou envoie un email de base)
```

---

## *Pause méridienne — 12h15 – 13h15*

---

## Bloc 4 — Pattern 6 : Decorator (partie 2 — exercice)
**Durée : 1h00** · 13h15 – 14h15

### Exercice : transformer la DataSource 💾
`src/decorator/datasource/`

**Consigne donnée aux étudiants :**
> Le fichier `DemoAvecDecorator.java` contient un TODO. L'objectif est de refactorer le code de `Demo.java` en utilisant le pattern Decorator, pour pouvoir écrire :
> ```java
> DataSource source = new EncryptorDecorator(
>     new CompressorDecorator(
>         new FileDataSource("output.txt")
>     )
> );
> source.writeData(data);
> String result = source.readData();
> ```

**Ce qu'ils doivent faire :**
1. créer un `DataSourceDecorator` abstrait qui implémente `DataSource` et contient un `DataSource` ;
2. créer `CompressorDecorator` qui compresse avant d'écrire et décompresse après lecture ;
3. créer `EncryptorDecorator` qui chiffre avant d'écrire et déchiffre après lecture ;
4. vérifier que l'empilement fonctionne.

**Durée indicative :** 40 min de travail + 20 min de correction.

