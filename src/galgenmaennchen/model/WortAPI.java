package galgenmaennchen.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Random;

/**
 * Die Klasse {@code WortAPI} stellt eine Verbindung zu einer externen API her,
 * um ein zufälliges deutsches Wort zu beziehen. Falls dies fehlschlägt oder das
 * Wort ungültige Zeichen enthält, wird ein Wort aus {@link WortFallback} verwendet.
 */
public class WortAPI {

    /** URL der externen API zur Generierung deutscher Wörter */
    private static final String API_URL = "https://random-word-api.herokuapp.com/word?lang=de&number=1";

    private final Random random;

    /**
     * Konstruktor – übergibt den Zufallsgenerator für die Fallback-Auswahl.
     *
     * @param random Zufallsgenerator zur Auswahl eines Offline-Worts
     */
    public WortAPI(Random random) {
        this.random = random;
    }

    /**
     * Liefert ein zufälliges deutsches Wort in Großbuchstaben.
     * <p>
     * Ablauf:
     * <ol>
     *   <li>Versuche Abruf von API</li>
     *   <li>Filter: nur Buchstaben A-Z</li>
     *   <li>Fallback: {@link WortFallback} bei Fehlern</li>
     * </ol>
     *
     * @return Ein geeignetes Wort in Großbuchstaben
     */
    public String holeZufaelligesWort() {
        try {
            HttpURLConnection conn = (HttpURLConnection) URI.create(API_URL).toURL().openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);

            if (conn.getResponseCode() == 200) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    String json = in.readLine(); // z. B. ["apfel"]
                    String wort = json.replaceAll("[\\[\\]\"]", "").toUpperCase();

                    if (wort.matches("^[A-Z]+$")) {
                        return wort;
                    }
                }
            }
        } catch (Exception e) {
            // Fehler bei API-Aufruf – Ignorieren, Fallback verwenden
        }

        // Lokales Fallback-Wort aus WortFallback-Liste
        int index = random.nextInt(WortFallback.WORTE.size());
        return WortFallback.WORTE.get(index);
    }
}
