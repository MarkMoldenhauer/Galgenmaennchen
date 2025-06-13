package galgenmaennchen.model;

import java.util.List;

/**
 * Diese Hilfsklasse stellt eine statische Liste mit gültigen Fallback-Wörtern bereit,
 * falls keine Verbindung zur Online-API möglich ist.
 * <p>
 * Die Wörter enthalten ausschließlich Großbuchstaben von A bis Z und keine Sonderzeichen.
 */
public class WortFallback {

    /**
     * Liste mit Fallback-Wörtern für den Offline-Betrieb.
     * Nur Wörter mit A-Z (ohne Umlaute, ß oder Sonderzeichen).
     */
    public static final List<String> WORTE = List.of(
        
        "COMPUTER", "SOFTWARE", "HARDWARE", "MONITOR", "TASTATUR", "MAUS", "NETZWERK", "DATENBANK", "PROGRAMM",
        "CODE", "JAVA", "PYTHON", "GITHUB", "BITBUCKET", "SERVER", "CLIENT", "ROUTER", "SWITCH", "PROZESSOR",
        "KERNEL", "SCRIPT", "BROWSER", "CHROME", "FIREFOX", "LINUX", "WINDOWS", "DEBUGGER", "EDITOR", "PLATTFORM",
        "APP", "LOGIN", "PASSWORT", "EINGABE", "AUSGABE", "UPLOAD", "DOWNLOAD", "CLOUD", "FIREWALL", "BACKUP",
        "SICHERHEIT", "VIRUS", "TROJANER", "PHISHING", "API", "FRONTEND", "BACKEND", "DATABASE", "TERMINAL", "KONSOLE",
        "BANANE", "SCHULE", "TISCH", "FENSTER", "KAFFEE", "ELEFANT", "KAMERA", "BUCH", "ZUG", "AUTO", "RAD", "WAGEN",
        "REISE", "HOTEL", "FLUGHAFEN", "KARTE", "KAROTTE", "SUPPE", "BUTTER", "BROT", "MILCH", "KAESE", "FLEISCH",
        "FISCH", "TELLER", "GLAS", "TASSE", "LAGER", "KISTE", "GARTEN", "PFLANZE", "BAUM", "WALD", "BERG", "FLUSS",
        "SEE", "INSEL", "STRAND", "MEER", "SONNE", "MOND", "STERN", "LUFT", "WIND", "REGEN", "SCHNEE", "STURM",
        "WOLKE", "BLUME", "VOGEL", "TIER", "HAUS", "ZIMMER", "STUHL", "SOFA", "LAMPE", "TREPPE", "KELLER", "TURM",
        "TAPETE", "UHR", "ZEIT", "TAG", "NACHT", "MORGEN", "ABEND", "WOCHE", "MONAT", "JAHR", "FEST", "FEIER",
        "PARTY", "TANZ", "LIED", "MUSIK", "GITARRE", "KLAVIER", "FILM", "ROMAN", "KUNST", "BILD", "FARBE", "PAPIER",
        "BLEISTIFT", "FEDER", "TEXT", "WORT", "SPRACHE", "STIMME", "KOPF", "HAND", "FUSS", "AUGE", "OHR", "MUND",
        "ZAHN", "NASE", "HERZ", "BLUT", "KNOCHEN", "KLEID", "HOSE", "JACKE", "SCHUH", "HEMD", "MANTEL", "SCHAL",
        "HUT", "KRAWATTE", "TASCHE", "RUCKSACK", "SCHIRM", "SPIEL", "KARTE", "WUERFEL", "BALL", "TOR", "TEAM",
        "SPORT", "LAUFEN", "SPRINGEN", "WERFEN", "RENNEN", "FAHREN", "FLIEGEN", "SCHWIMMEN", "TRINKEN", "ESSEN",
        "LESEN", "SCHREIBEN", "ZEICHNEN", "DENKEN", "TRAUM", "ZUKUNFT", "VERGANGENHEIT", "GEGENWART"
    );
}
