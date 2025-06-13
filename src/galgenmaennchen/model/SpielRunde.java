package galgenmaennchen.model;

import java.util.*;

import galgenmaennchen.view.SpielAnzeige;
import galgenmaennchen.view.Text;

/**
 * Diese Klasse repräsentiert eine Spielrunde im Galgenmännchen-Spiel.
 * Sie steuert Anzeige, Eingabe, Lebensverwaltung und die Spielzuglogik.
 */
public class SpielRunde {

    private final SpielAnzeige anzeige;
    private final String wort;

    /**
     * Konstruktor – setzt Anzeige-Instanz und zu erratendes Wort.
     * 
     * @param anzeige Anzeigeinstanz zur Interaktion mit dem Spieler
     * @param wort    Das zu erratende Wort
     */
    public SpielRunde(SpielAnzeige anzeige, String wort) {
        this.anzeige = anzeige;
        this.wort = wort;
    }

    /**
     * Führt die Spielrunde aus und gibt zurück, ob der Spieler gewonnen hat.
     * 
     * @return true, wenn das Wort vollständig erraten wurde
     */
    public boolean spielen() {
        char[] loesung = wort.toCharArray();
        char[] anzeigeArr = SpielLogik.initialisiereAnzeige(loesung);
        int leben = Galgen.getMaxLeben();
        Set<Character> geraten = new HashSet<>();

        while (leben > 0 && !Arrays.equals(loesung, anzeigeArr)) {
            anzeige.zeigeSpielstand(anzeigeArr, leben, geraten, Galgen.getMaxLeben());
            boolean korrekt = verarbeiteSpielzug(loesung, anzeigeArr, geraten);

            if (!korrekt) {
                leben--; // Leben nur bei falschem Versuch abziehen
            }
        }

        // Nach dem letzten Versuch (z. B. bei Niederlage) – finaler Stand
        anzeige.zeigeSpielstand(anzeigeArr, leben, geraten, Galgen.getMaxLeben());

        return Arrays.equals(loesung, anzeigeArr);
    }

    /**
     * Nimmt eine Eingabe vom Spieler entgegen und verarbeitet diese.
     * 
     * @param loesung     Das zu erratende Wort als Zeichenarray
     * @param anzeigeArr  Der aktuelle Anzeigezustand mit Buchstaben und Unterstrichen
     * @param geraten     Bereits geratene Buchstaben
     * @return true, wenn die Eingabe korrekt war oder ignoriert wurde
     */
    private boolean verarbeiteSpielzug(char[] loesung, char[] anzeigeArr, Set<Character> geraten) {
        anzeige.frage(Text.EINGABE);
        String eingabe = anzeige.leseEingabe();

        if (!SpielLogik.istEingabeGueltig(eingabe)) {
            anzeige.zeigeNachricht(Text.EINGABE_FEHLER);
            return true; // Kein Leben abziehen bei ungültiger Eingabe
        }

        if (eingabe.length() == 1) {
            return pruefeEinzelbuchstabe(eingabe.charAt(0), loesung, anzeigeArr, geraten);
        } else {
            return pruefeWorteingabe(eingabe, wort, anzeigeArr);
        }
    }

    /**
     * Prüft einen einzelnen geratenen Buchstaben.
     * 
     * @param buchstabe   Der eingegebene Buchstabe
     * @param loesung     Die Lösung als Zeichenarray
     * @param anzeigeArr  Die aktuelle Anzeige
     * @param geraten     Set mit bereits geratenen Buchstaben
     * @return true, wenn der Buchstabe korrekt ist oder bereits geraten wurde
     */
    private boolean pruefeEinzelbuchstabe(char buchstabe, char[] loesung, char[] anzeigeArr, Set<Character> geraten) {
        if (geraten.contains(buchstabe)) {
            anzeige.zeigeNachricht(Text.BEREITS_VERSUCHT);
            return true; // Kein Leben abziehen!
        }

        geraten.add(buchstabe);

        if (SpielLogik.pruefeBuchstabe(buchstabe, loesung, anzeigeArr)) {
            anzeige.zeigeNachricht(Text.RICHTIG);
            return true;
        } else {
            anzeige.zeigeNachricht(Text.LEBEN_VERLOREN);
            return false;
        }
    }

    /**
     * Prüft, ob ein vollständiges Wort korrekt eingegeben wurde.
     * 
     * @param eingabe     Das eingegebene Wort
     * @param wort        Das tatsächliche Lösungswort
     * @param anzeigeArr  Die aktuelle Anzeige
     * @return true, wenn das Wort korrekt war
     */
    private boolean pruefeWorteingabe(String eingabe, String wort, char[] anzeigeArr) {
        if (eingabe.equalsIgnoreCase(wort)) {
            for (int i = 0; i < wort.length(); i++) {
                anzeigeArr[i] = wort.charAt(i);
            }
            return true;
        } else {
            anzeige.zeigeNachricht(Text.LEBEN_VERLOREN);
            return false;
        }
    }
}
