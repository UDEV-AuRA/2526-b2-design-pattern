## Bloc 5 — Pattern 7 : Observer
**Durée : 45 min** · 14h15 – 15h00

### Le problème que ça résout
Quand un objet change d'état, **plusieurs autres objets** doivent en être informés automatiquement, sans que le premier ne connaisse les détails des autres.

### Métaphore
> *« Un groupe WhatsApp. Quand quelqu'un envoie un message, tous les membres du groupe le reçoivent. On peut rejoindre ou quitter le groupe à tout moment, et l'envoyeur ne sait même pas qui est dans le groupe. »*

### Déroulé

#### 1. Poser le problème (10 min)
Un `Magasin` reçoit un nouveau modèle de téléphone. Plusieurs acteurs veulent être prévenus :
- un `Client` qui attend le produit ;
- un `Reparateur` qui doit se former ;
- le `SiteAppleCom` qui doit mettre à jour sa page.

Sans pattern : le magasin devrait connaître chaque acteur individuellement.

#### 2. Montrer le code (15 min)
`src/observator/stock/` :
- `Subscriber` → interface avec `update()` ;
- `Magasin` → liste de `Subscriber`, méthode `subscribe()`, méthode `recevoirNoueauModele()` qui appelle `update()` sur chaque abonné ;
- `Client`, `Reparateur` → implémentent `Subscriber` ;
- `Main.java` → on abonne les acteurs, on déclenche l'événement.

**Message clé :**
> *« Le Magasin ne sait pas ce que font les abonnés quand ils reçoivent la notification. Il sait juste qu'ils ont une méthode `update()`. C'est le découplage. »*

#### 3. Dessiner le diagramme UML (10 min)
```
    Subscriber (interface)
    ┌──────────┐
    │update()  │
    └─────┬────┘
          │
   ┌──────┼──────┐
   │      │      │
 Client  Répar. SiteApple

    Magasin
    ┌─────────────────────────────┐
    │ - subscribers: List         │
    │ + subscribe()               │
    │ + recevoirNouveauModele()   │
    └─────────────────────────────┘
```

#### 4. Question ouverte (10 min)
> *« Donnez-moi 3 exemples concrets de la vie de développeur où vous utiliseriez Observer. »*
>
> Réponses attendues : événements d'interface graphique (bouton cliqué), notifications push, websockets, systèmes de messagerie, etc.

