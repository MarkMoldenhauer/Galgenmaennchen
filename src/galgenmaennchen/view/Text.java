package galgenmaennchen.view;

/**
 * Enthält alle textuellen Ausgaben des Spiels als Konstanten.
 * Diese zentrale Sammlung erleichtert Wartung und Änderung.
 */
public class Text {

    /**
     *  Allgemeine Texte
     */
    public static final String BEGRUESSUNG = "\nAktueller Stand: ";
    public static final String FEHLER = "Fehlversuche: ";
    public static final String BEREITS_GERATEN = "Bisher geraten: ";
    public static final String EINGABE = "Gib einen Buchstaben oder das ganze Wort ein: ";
    public static final String EINGABE_FEHLER = "Bitte gib einen gültigen Buchstaben oder ein ganzes Wort ein!";
    public static final String BEREITS_VERSUCHT = "Diesen Buchstaben hast du bereits versucht.";
    public static final String LEBEN_VERLOREN = "Leider falsch. Du verlierst ein Leben.";
    public static final String RICHTIG = "Richtig!";
    public static final String SIEG = "\nHerzlichen Glückwunsch! Das Wort war: ";
    public static final String NIEDERLAGE = "\nDas war leider nichts. Das Wort war: ";
    public static final String TSCHUESS = "Tschüss! Bis zum nächsten Mal :-)";

    /**
     * Menü nach einer Spielrunde
     */
    public static final String RUNDE_MENUE = 
        "\nWas möchtest du als Nächstes tun?\n" +
        "1 = Noch eine Runde\n" +
        "2 = Zurück zum Hauptmenü\n" +
        "3 = Spiel beenden\n";

    public static final String RUNDE_MENUE_AUSWAHL = "Deine Auswahl: ";
    public static final String RUNDE_MENUE_UNGUELTIG = "Ungültige Auswahl. Bitte 1, 2 oder 3 eingeben.";

    /**
     *  Moduswahl
     */
    public static final String MODUS_BEGRUESSUNG = "Willkommen bei Galgenmännchen.";
    public static final String MODUS_FRAGE = 
        "Bitte Modus wählen:\n" +
        "1 = Gegen Computer\n" +
        "2 = Zwei-Spieler-Modus\n" +
        "3 = Computer rät dein Wort\n" +
        "4 = Spiel beenden";
    public static final String MODUS_UNGUELTIG = "Ungültige Eingabe. Bitte nur 1, 2, 3 oder 4.";
    public static final String MODUS_WAHL = "Deine Wahl: ";

    /**
     *  Zwei-Spieler-Modus
     */
    public static final String GEHEIMWORT_EINGABE = "Spieler 1, gib ein geheimes Wort ein: ";
    public static final String GEHEIMWORT_UNGUELTIG = "Ungültige Eingabe. Bitte nur Buchstaben.";
    public static final String GEHEIMWORT_WIEDERHOLUNG = "Erneut eingeben: ";

    /**
     *  ComputerRaten-Modus
     */
    public static final String COMPUTER_RATE_FRAGE = "Computer rät: ";
    public static final String COMPUTER_TREFFER = "Treffer!";
    public static final String COMPUTER_FEHLER = "Leider falsch. Der Computer verliert ein Leben.";
    public static final String COMPUTER_NEXT = "Drücke Enter für den nächsten Versuch...";
}
