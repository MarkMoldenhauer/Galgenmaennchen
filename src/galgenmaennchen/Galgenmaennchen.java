package galgenmaennchen;

import java.util.*;

/**
 * @Autor M. Moldenhauer
 * 
 * Ein Spiel, bei dem Spieler durch das Erraten von Buchstaben ein verborgenes Wort aufdecken müssen, 
 * bevor ein Strichmännchen vollständig am Galgen hängt.
 */

public class Galgenmaennchen {

    /*
     * Wortliste für den Spielmodus gegen den Computer
     */
    private static final String[] WORTLISTE = {
        "Banane", "Schule", "Tisch", "Computer", "Programmierung", "Fenster"
    };

    /*
     * Maximale Anzahl an Leben (Fehlversuche)
     */
    private static final int MAX_LEBEN = 10;

    /*
     * Grafische Darstellung des Galgens je nach Anzahl der Fehler
     */
    private static final String[] GALGEN = {
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

    /*
     * Konstanten für Benutzeroberfläche
     */
    private static final String TEXT_BEGRUESSUNG = "\nAktueller Stand: ";
    private static final String TEXT_FEHLER = "Fehlversuche: ";
    private static final String TEXT_BEREITS_GERATEN = "Bisher geraten: ";
    private static final String TEXT_EINGABE = "Gib einen Buchstaben oder das ganze Wort ein: ";
    private static final String TEXT_EINGABE_FEHLER = "Bitte gib einen gültigen Buchstaben oder ein ganzes Wort ein!";
    private static final String TEXT_BEREITS_VERSUCHT = "Diesen Buchstaben hast du bereits versucht.";
    private static final String TEXT_LEBEN_VERLOREN = "Leider falsch. Du verlierst ein Leben.";
    private static final String TEXT_RICHTIG = "Richtig!";
    private static final String TEXT_SIEG = "\nHerzlichen Glückwunsch! Du hast das Wort erraten: ";
    private static final String TEXT_NIEDERLAGE = "\nDu hast leider verloren. Das Wort war: ";
    private static final String TEXT_NOCHMAL = "Noch mal spielen? Wenn ja, tippe 1 (und Enter), sonst egal was (und Enter).\n";
    private static final String TEXT_TSCHUESS = "Tschüss!";

    /*
     * Konstanten für Moduswahl
     */
    private static final String MODUS_BEGRUESSUNG = "Willkommen bei Galgenmännchen.";
    private static final String MODUS_FRAGE = "Bitte Modus wählen: 1 = Gegen Computer, 2 = Zwei-Spieler-Modus";
    private static final String MODUS_UNGUELTIG = "Ungültige Eingabe. Bitte nur 1 oder 2.";
    private static final String MODUS_WAHL = "Deine Wahl: ";

    /*
     * Konstanten für Zwei-Spieler-Modus
     */
    private static final String TEXT_GEHEIMWORT = "Spieler 1, gib ein geheimes Wort ein: ";
    private static final String TEXT_GEHEIMWORT_UNGUELTIG = "Ungültige Eingabe. Bitte nur Buchstaben.";
    private static final String TEXT_GEHEIMWORT_WIEDERHOLUNG = "Erneut eingeben: ";

    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();
    private int modus;

    /*
     * Konstruktor mit Auswahl des Spielmodus
     */
    public Galgenmaennchen(int modus) {
        this.modus = modus;
        starteSpiel();
    }

    /*
     * Steuert Ablauf des Spiels (inkl. Wiederholung)
     */
    private void starteSpiel() {
        boolean nochmal;
        do {
            String wort = ermittleWort();
            zeigeWortZumTesten(wort); // optional sichtbar für Tests
            spielrunde(wort);
            nochmal = frageNachNeustart();
        } while (nochmal);

        System.out.println(TEXT_TSCHUESS);
        scanner.close();
    }

    /*
     * Führt Spielrunde mit einem gegebenen Wort durch
     */
    private void spielrunde(String wort) {
        char[] loesung = wort.toCharArray();
        char[] anzeige = initialisiereAnzeige(loesung);
        int leben = MAX_LEBEN;
        Set<Character> geraten = new HashSet<>();

        boolean gewonnen = false;
        while (leben > 0 && !gewonnen) {
            zeigeSpielstand(anzeige, leben, geraten);
            boolean korrekt = verarbeiteSpielzug(wort, loesung, anzeige, geraten);

            if (!korrekt && !Arrays.equals(loesung, anzeige)) {
                leben--;
            }

            gewonnen = Arrays.equals(loesung, anzeige);
        }

        zeigeSpielende(gewonnen, wort, leben);
    }

    /*
     * Verarbeitet Eingabe des Spielers (Buchstabe oder Wort)
     */
    private boolean verarbeiteSpielzug(String wort, char[] loesung, char[] anzeige, Set<Character> geraten) {
        System.out.print(TEXT_EINGABE);
        String eingabe = leseEingabe();

        if (!istEingabeGueltig(eingabe)) {
            gibFehlermeldung(TEXT_EINGABE_FEHLER);
            return false;
        }

        if (eingabe.length() == 1) {
            return pruefeEinzelbuchstabe(eingabe.charAt(0), loesung, anzeige, geraten);
        } else {
            return pruefeWorteingabe(eingabe, wort, anzeige);
        }
    }

    /*
     * Ermittelt Wort je nach Spielmodus
     */
    private String ermittleWort() {
        return (modus == 1)
            ? WORTLISTE[random.nextInt(WORTLISTE.length)].toUpperCase()
            : benutzereingabeWort();
    }

    /*
     * Fragt, ob Spieler neue Runde starten möchte
     */
    private boolean frageNachNeustart() {
        System.out.print(TEXT_NOCHMAL);
        String antwort = leseEingabe();
        return antwort.equals("1");
    }

    /*
     * Prüft Buchstaben und aktualisiert Anzeige bzw. Leben
     */
    private boolean pruefeEinzelbuchstabe(char buchstabe, char[] loesung, char[] anzeige, Set<Character> geraten) {
        if (geraten.contains(buchstabe)) {
            gibFehlermeldung(TEXT_BEREITS_VERSUCHT);
            return false;
        }

        geraten.add(buchstabe);

        if (!pruefeBuchstabe(buchstabe, loesung, anzeige)) {
            gibFehlermeldung(TEXT_LEBEN_VERLOREN);
            return false;
        }

        System.out.println(TEXT_RICHTIG);
        return false;
    }

    /*
     * Prüft komplette Worteingabe
     */
    private boolean pruefeWorteingabe(String eingabe, String wort, char[] anzeige) {
        if (eingabe.equals(wort)) {
            for (int i = 0; i < wort.length(); i++) {
                anzeige[i] = wort.charAt(i);
            }
            return true;
        } else {
            gibFehlermeldung(TEXT_LEBEN_VERLOREN);
            return false;
        }
    }

    /*
     * Gibt Fehlermeldung aus
     */
    private void gibFehlermeldung(String meldung) {
        System.out.println(meldung);
    }

    /*
     * Gibt Sieg-/Niederlage-Meldung und finalen Galgen aus
     */
    private void zeigeSpielende(boolean gewonnen, String wort, int leben) {
        zeigeGalgen(leben);
        if (gewonnen) {
            System.out.println(TEXT_SIEG + wort);
        } else {
            System.out.println(TEXT_NIEDERLAGE + wort);
        }
    }

    /*
     * Zeigt aktuellen Spielstand an
     */
    private void zeigeSpielstand(char[] anzeige, int leben, Set<Character> geraten) {
        System.out.print(TEXT_BEGRUESSUNG);
        for (char c : anzeige) System.out.print(c + " ");
        System.out.println();
        System.out.println(TEXT_FEHLER + (MAX_LEBEN - leben) + "/" + MAX_LEBEN);
        System.out.println(TEXT_BEREITS_GERATEN + geraten);
        zeigeGalgen(leben);
    }

    /*
     * Zeigt Galgen je nach verbliebenem Leben
     */
    private void zeigeGalgen(int leben) {
        int galgenIndex = Math.min(GALGEN.length - 1, MAX_LEBEN - leben);
        System.out.println("\n" + GALGEN[galgenIndex]);
    }

    /*
     * Prüft, ob Buchstabe in der Lösung enthalten ist
     */
    private boolean pruefeBuchstabe(char buchstabe, char[] loesung, char[] anzeige) {
        boolean treffer = false;
        for (int i = 0; i < loesung.length; i++) {
            if (loesung[i] == buchstabe) {
                anzeige[i] = buchstabe;
                treffer = true;
            }
        }
        return treffer;
    }

    /*
     * Erzeugt die Anfangsanzeige (z. B. "____")
     */
    private char[] initialisiereAnzeige(char[] loesung) {
        char[] anzeige = new char[loesung.length];
        Arrays.fill(anzeige, '_');
        return anzeige;
    }

    /*
     * Prüft, ob Eingabe gültig ist (nur Buchstaben erlaubt)
     */
    private boolean istEingabeGueltig(String eingabe) {
        return !eingabe.isEmpty() && eingabe.chars().allMatch(Character::isLetter);
    }

    /*
     * Liest eine Eingabe ein, trimmt und wandelt in Großbuchstaben um
     */
    private String leseEingabe() {
        return scanner.nextLine().toUpperCase().trim();
    }

    /*
     * Gibt das Wort sichtbar aus (nur für Tests)
     */
    private void zeigeWortZumTesten(String wort) {
        System.out.println(wort);
    }

    /*
     * Liest im Zwei-Spieler-Modus das geheime Wort ein
     */
    private String benutzereingabeWort() {
        System.out.print(TEXT_GEHEIMWORT);
        String wort = leseEingabe();
        while (!istEingabeGueltig(wort)) {
            System.out.println(TEXT_GEHEIMWORT_UNGUELTIG);
            System.out.print(TEXT_GEHEIMWORT_WIEDERHOLUNG);
            wort = leseEingabe();
        }
        for (int i = 0; i < 50; i++) System.out.println();
        return wort;
    }

    /*
     * Fragt den Spielmodus ab (1 = gegen PC, 2 = gegen Mitspieler)
     */
    private static int ermittleModus(Scanner scanner) {
        int modus = 0;
        System.out.println(MODUS_BEGRUESSUNG);
        System.out.println(MODUS_FRAGE);

        while (true) {
            System.out.print(MODUS_WAHL);
            String eingabe = scanner.nextLine().trim();

            try {
                modus = Integer.parseInt(eingabe);
                if (modus == 1 || modus == 2) {
                    return modus;
                } else {
                    System.out.println(MODUS_UNGUELTIG);
                }
            } catch (NumberFormatException e) {
                System.out.println(MODUS_UNGUELTIG);
            }
        }
    }

    /*
     * Programmstart
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int modus = ermittleModus(scanner);
        new Galgenmaennchen(modus);
        scanner.close();
    }
}
