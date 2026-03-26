## *Pause méridienne — 12h15 – 13h15*

---

## Bloc 6 — Pattern 2 : Factory Method
**Durée : 1h15** · 13h15 – 14h30

### Le problème que ça résout
Le code client doit créer des objets, mais **il ne devrait pas savoir quelle classe concrète** instancier. On veut pouvoir changer le type d'objet créé sans toucher au code qui l'utilise.

### Métaphore
> *« Imagine une pizzeria. Le client commande "une pizza", mais c'est le cuisinier qui décide comment la préparer. Le client ne rentre pas en cuisine pour choisir la pâte, la sauce, le four. Il délègue. Si demain le cuisinier change de recette, le client ne change rien à sa commande. »*

### Déroulé

#### 1. Montrer le problème (20 min)
`src/factorymethod/theme/mauvais/FenetreChat.java` :
- la fenêtre de chat doit créer des boutons ;
- elle vérifie le thème avec un `if/else` :
  ```java
  if (theme.equalsIgnoreCase("clair")) {
      boutonSend = new BoutonClair();
  } else {
      boutonSend = new BoutonSombre();
  }
  ```

**Questions à poser :**
> *« Si j'ajoute un thème "pastel", combien de `if` je dois modifier ? »*
> *« Et si en plus du bouton, j'ai un input et un historique dans le même `if/else` ? »*

#### 2. Présenter le pattern (15 min)
`src/factorymethod/theme/bon/FenetreChat.java` :
- la fenêtre est maintenant **abstraite** ;
- elle délègue la création via `creerBouton()` ;
- les sous-classes (`FenetreChatClaire`, `FenetreChatSombre`) décident de quoi créer.

**Message clé :**
> *« La classe parente sait QUOI faire avec le bouton, mais pas LEQUEL créer. Elle délègue cette décision aux sous-classes. »*

#### 3. Dessiner le diagramme UML (10 min)
```
   FenetreChat (abstract)
   ┌─────────────────────┐
   │ + initFenetre()     │
   │ + creerBouton()     │ ◁─── factory method
   └────────┬────────────┘
            │
   ┌────────┴─────────┐
   │                  │
FenetreChatClaire  FenetreChatSombre
  return new         return new
  BoutonClair()      BoutonSombre()
```

Bien insister sur les 2 hiérarchies :
- la hiérarchie des **créateurs** (les fenêtres) ;
- la hiérarchie des **produits** (les boutons).

#### 4. Deuxième exemple : la logistique (10 min)
`src/factorymethod/logistic/` :
- `Logistics` (abstract) → `planDelivery()` appelle `createTransport()` ;
- `TruckLogistics` → crée un `Truck` ;
- `ShipLogistics` → crée un `Ship`.

Même structure, domaine totalement différent → le pattern est générique.

#### 5. Mini exercice oral (10 min)
> *« Vous gérez une application de messagerie. Vous avez un `MessageSender` qui envoie des messages. Selon le type (email, SMS, push), le message est différent. Proposez à l'oral une structure de classes qui utilise Factory Method. »*

Corriger au tableau ensemble.

