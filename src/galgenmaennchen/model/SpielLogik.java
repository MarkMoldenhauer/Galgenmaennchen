package galgenmaennchen.model;

import java.util.Arrays;

/**
 * Die Klasse {@code SpielLogik} enthält statische Hilfsmethoden zur Spiellogik des Galgenmännchen-Spiels.
 * Sie unterstützt die Buchstabenprüfung, Anzeigeinitialisierung sowie Eingabevalidierung.
 */
public class SpielLogik {

    /**
     * Überprüft, ob der eingegebene Buchstabe im Lösungswort enthalten ist,
     * und aktualisiert die Anzeige entsprechend.
     *
     * @param buchstabe Der vom Spieler eingegebene Buchstabe
     * @param loesung   Das zu erratende Wort als Zeichenarray
     * @param anzeige   Das aktuelle Anzeigearray mit erratenen Buchstaben und Unterstrichen
     * @return {@code true}, wenn der Buchstabe im Lösungswort vorkommt, sonst {@code false}
     */
    public static boolean pruefeBuchstabe(char buchstabe, char[] loesung, char[] anzeige) {
        boolean treffer = false;
        for (int i = 0; i < loesung.length; i++) {
            if (loesung[i] == buchstabe) {
                anzeige[i] = buchstabe;
                treffer = true;
            }
        }
        return treffer;
    }

    /**
     * Erzeugt ein neues Anzeige-Array mit Unterstrichen als Platzhalter
     * für die Buchstaben des zu erratenden Wortes.
     *
     * @param loesung Das Lösungswort als Zeichenarray
     * @return Ein neues Anzeigearray mit Unterstrichen
     */
    public static char[] initialisiereAnzeige(char[] loesung) {
        char[] anzeige = new char[loesung.length];
        Arrays.fill(anzeige, '_');
        return anzeige;
    }

    /**
     * Überprüft, ob die Eingabe ausschließlich aus Buchstaben besteht.
     *
     * @param eingabe Die Texteingabe des Spielers
     * @return {@code true}, wenn die Eingabe gültig ist, sonst {@code false}
     */
    public static boolean istEingabeGueltig(String eingabe) {
        return !eingabe.isEmpty() && eingabe.chars().allMatch(Character::isLetter);
    }
}
