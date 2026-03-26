package _2singleton.logger;

/**
 * Service métier simulant la gestion d'un compte bancaire.
 * Utilise un Logger pour tracer les opérations sans polluer la logique métier.
 */
public class Service {

    // On récupère l'unique instance du logger
    private final Logger logger = Logger.getInstance();
    private double solde;

    public Service(double soldeInitial) {
        this.solde = soldeInitial;
        logger.info("Service bancaire initialisé avec un solde de " + soldeInitial + " €");
    }

    public void deposer(double montant) {
        logger.debug("Tentative de dépôt : " + montant + " €");

        if (montant <= 0) {
            logger.warn("Dépôt refusé : le montant doit être positif (reçu " + montant + ")");
            return;
        }

        solde += montant;
        logger.info("Dépôt de " + montant + " € effectué. Nouveau solde : " + solde + " €");
    }

    public void retirer(double montant) {
        logger.debug("Tentative de retrait : " + montant + " €");

        if (montant <= 0) {
            logger.warn("Retrait refusé : le montant doit être positif (reçu " + montant + ")");
            return;
        }

        if (montant > solde) {
            logger.error("Retrait refusé : solde insuffisant (demandé=" + montant
                    + " €, disponible=" + solde + " €)");
            return;
        }

        solde -= montant;
        logger.info("Retrait de " + montant + " € effectué. Nouveau solde : " + solde + " €");
    }

    public double getSolde() {
        return solde;
    }
}
