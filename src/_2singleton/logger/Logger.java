package _2singleton.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

  // --- Singleton ---
  private static Logger instance;

  public static Logger getInstance() {
    if (instance == null) {
      instance = new Logger();
    }
    return instance;
  }

  private Logger() {
  }
  // --- fin Singleton ---


  // Les niveaux sont ordonnés : DEBUG < INFO < WARN < ERROR
  public enum Niveau {DEBUG, INFO, WARN, ERROR}

  private static final DateTimeFormatter FORMATTER =
    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


  // Par défaut on affiche tout à partir de DEBUG
  private Niveau niveauMinimum = Niveau.DEBUG;

  public void setNiveauMinimum(Niveau niveau) {
    this.niveauMinimum = niveau;
    log("INFO ", "Niveau de log changé à : " + niveau);
  }

  private void log(String label, String message) {
    System.out.println(String.format("[%s] [%s] %s",
      LocalDateTime.now().format(FORMATTER),
      label,
      message));
  }

  private void logSiActif(Niveau niveau, String label, String message) {
    if (niveau.ordinal() >= niveauMinimum.ordinal()) {
      log(label, message);
    }
  }

  public void debug(String message) {
    logSiActif(Niveau.DEBUG, "DEBUG", message);
  }

  public void info(String message) {
    logSiActif(Niveau.INFO, "INFO ", message);
  }

  public void warn(String message) {
    logSiActif(Niveau.WARN, "WARN ", message);
  }

  public void error(String message) {
    logSiActif(Niveau.ERROR, "ERROR", message);
  }
}
