package _2singleton.logger;

public class Main {

    public static void main(String[] args) {

        // ============================================================
        // PROBLÈME : sans Singleton, chaque classe a sa propre instance
        // ============================================================
        System.out.println("===== SANS Singleton =====");

        // Imaginons que Service et Main créent chacun leur propre Logger
        // Logger loggerDuMain = new Logger();  // <- ne compile PAS : constructeur privé
        // C'est exactement pour ça que le constructeur est privé !
        // Si on pouvait écrire "new Logger()" :

        //   Logger loggerDuMain    = new Logger();
        //   Logger loggerDuService = new Logger();  // 2 objets distincts en mémoire
        //
        //   loggerDuMain.setNiveauMinimum(Niveau.WARN); // je veux taire les DEBUG/INFO
        //
        //   service.deposer(100); // Service utilise loggerDuService -> DEBUG et INFO
        //                         // s'affichent quand même !
        //                         // Le changement de niveau n'a eu aucun effet sur lui.

        // ============================================================
        // SOLUTION : avec Singleton, tout le monde partage la même instance
        // ============================================================
        System.out.println("===== AVEC Singleton =====");

        Logger l1 = Logger.getInstance();
        Logger l2 = Logger.getInstance();
        System.out.println("l1 == l2 ? " + (l1 == l2)); // true : c'est le même objet

        Service service = new Service(1000.0);

        System.out.println("\n-- Niveau DEBUG (tout afficher) --");
        service.deposer(250.0);
        service.retirer(100.0);

        // On change le niveau UNE SEULE FOIS depuis n'importe où dans l'appli
        // -> ça s'applique immédiatement partout, y compris dans Service
        System.out.println("\n-- On passe en niveau WARN (on cache DEBUG et INFO) --");
        Logger.getInstance().setNiveauMinimum(Logger.Niveau.WARN);

        service.deposer(50.0);   // DEBUG et INFO ne s'affichent plus
        service.retirer(-10.0);  // WARN s'affiche encore
        service.retirer(9999.0); // ERROR s'affiche encore
    }
}
