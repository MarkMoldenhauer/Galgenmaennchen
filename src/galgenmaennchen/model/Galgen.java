package galgenmaennchen.model;

/**
 * Die Klasse {@code Galgen} stellt die ASCII-Darstellung des Galgens bereit
 * und verwaltet die verschiedenen Stufen, abhängig von der Anzahl an Fehlern des Spielers.
 */
public class Galgen {

    // ASCII-Stufen des Galgens je nach Fehleranzahl
    private static final String[] STUFEN = {
        "",
        "\n\n\n\n\n____",
        "\n |\n |\n |\n |\n_|___",
        " ______\n |\n |\n |\n |\n_|___",
        " ______\n |    |\n |\n |\n |\n_|___",
        " ______\n |    |\n |    O\n |\n |\n_|___",
        " ______\n |    |\n |    O\n |    |\n |\n_|___",
        " ______\n |    |\n |    O\n |   /|\n |\n_|___",
        " ______\n |    |\n |    O\n |   /|\\\n |\n_|___",
        " ______\n |    |\n |    O\n |   /|\\\n |   /\n_|___",
        " ______\n |    |\n |    O\n |   /|\\\n |   / \\\n_|___\n RIP 💀"
    };

    /**
     * Gibt die ASCII-Darstellung des aktuellen Galgenzustands zurück,
     * basierend auf der Anzahl der Fehler.
     *
     * @param fehler Die Anzahl der bisherigen Fehlversuche
     * @return Die entsprechende Galgen-Stufe als ASCII-String
     */
    public static String getStufe(int fehler) {
        int index = Math.min(fehler, STUFEN.length - 1);
        return STUFEN[index];
    }

    /**
     * Gibt die maximale Anzahl an Leben (Fehlversuchen) zurück, 
     * bevor das Spiel verloren ist.
     *
     * @return Anzahl der maximal erlaubten Fehler
     */
    public static int getMaxLeben() {
        return STUFEN.length - 1;
    }
}
