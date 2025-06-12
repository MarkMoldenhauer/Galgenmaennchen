package galgenmaennchen.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Random;

/**
 * Die Klasse {@code WortAPI} stellt eine Verbindung zu einer externen API her, 
 * um ein zufälliges deutsches Wort zu beziehen. Falls dies fehlschlägt oder das
 * Wort ungültige Zeichen enthält (z. B. Umlaute oder Sonderzeichen), wird stattdessen
 * ein Wort aus einer vordefinierten Liste zurückgegeben.
 */
public class WortAPI {

    /** URL der externen API zur Generierung deutscher Wörter */
    private static final String API_URL = "https://random-word-api.herokuapp.com/word?lang=de&number=1";

    /** Lokale Fallback-Liste mit gültigen Wörtern (nur Großbuchstaben A-Z) */
    private static final String[] DEFAULT_WORTE = {
        "BANANE", "SCHULE", "TISCH", "COMPUTER", "FENSTER", "PROGRAMMIERUNG"
    };

    private final Random random;

    /**
     * Konstruktor – übergibt den Zufallsgenerator für die Fallback-Auswahl.
     *
     * @param random Instanz von {@code Random} zur Auswahl eines Fallback-Worts
     */
    public WortAPI(Random random) {
        this.random = random;
    }

    /**
     * Liefert ein zufälliges deutsches Wort in Großbuchstaben.
     * <p>
     * Der Ablauf ist wie folgt:
     * <ol>
     *   <li>Versuch, ein Wort von der API abzurufen</li>
     *   <li>Filter: Nur Wörter mit Buchstaben A–Z (ohne ß, Ä, Ö, Ü etc.)</li>
     *   <li>Falls die API nicht antwortet oder das Wort ungeeignet ist,
     *       wird ein lokales Fallback-Wort verwendet</li>
     * </ol>
     *
     * @return Ein gültiges deutsches Wort (nur Großbuchstaben A-Z)
     */
    public String holeZufaelligesWort() {
        try {
        	HttpURLConnection conn = (HttpURLConnection) URI.create(API_URL).toURL().openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);

            if (conn.getResponseCode() == 200) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    String json = in.readLine(); // Beispiel: ["apfel"]
                    String wort = json.replaceAll("[\\[\\]\"]", "").toUpperCase();

                    // Prüfen, ob das Wort ausschließlich aus Buchstaben A-Z besteht
                    if (wort.matches("^[A-Z]+$")) {
                        return wort;
                    }
                }
            }
        } catch (Exception e) {
            // Fehler bei Verbindung oder Verarbeitung – wird ignoriert
        }

        // Fallback-Wort aus lokaler Liste
        return DEFAULT_WORTE[random.nextInt(DEFAULT_WORTE.length)];
    }
}
