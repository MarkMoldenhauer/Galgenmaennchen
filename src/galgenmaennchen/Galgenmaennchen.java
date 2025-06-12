package galgenmaennchen;

import java.util.Random;
import java.util.Scanner;

import galgenmaennchen.controller.SpielController;
import galgenmaennchen.model.SpielLogik;
import galgenmaennchen.model.SpielModus;
import galgenmaennchen.model.SpielRunde;
import galgenmaennchen.model.WortAPI;
import galgenmaennchen.view.SpielAnzeige;
import galgenmaennchen.view.Text;

/**
 * Startpunkt des Galgenmännchen-Spiels.
 * <p>
 * Verantwortlich für:
 * <ul>
 *   <li>Initialisierung der Spielkomponenten</li>
 *   <li>Abfrage des Spielmodus</li>
 *   <li>Steuerung des Spielablaufs</li>
 *   <li>Verwaltung der Spielrunden</li>
 * </ul>
 * 
 * @author M. Moldenhauer
 */
public class Galgenmaennchen {

    private final Random random;             // Zufallsgenerator zur Wortauswahl
    private final SpielModus modus;          // Vom Spieler gewählter Spielmodus (COMPUTER oder ZWEISPIELER)
    private final SpielAnzeige anzeige;      // Zuständig für alle Ein- und Ausgaben im Spiel

    /**
     * Konstruktor für das Spiel.
     *
     * @param modus   Spielmodus (COMPUTER oder ZWEISPIELER)
     * @param scanner Eingabequelle für Benutzereingaben
     * @param random  Zufallsgenerator zur Wortausswahl
     */
    public Galgenmaennchen(SpielModus modus, Scanner scanner, Random random) {
        this.modus = modus;
        this.random = random;
        this.anzeige = new SpielAnzeige(scanner);
        starteSpiel();
    }

    /**
     * Steuert den Spielablauf inklusive Wiederholungen.
     */
    private void starteSpiel() {
        boolean nochmal;
        do {
            String wort = ermittleWort();
            anzeige.zeigeWortZumTesten(wort); // nur zu Testzwecken sichtbar
            boolean gewonnen = new SpielRunde(anzeige, wort).spielen();
            anzeige.zeigeNachricht(gewonnen ? Text.SIEG + wort : Text.NIEDERLAGE + wort);
            nochmal = frageNachNeustart();
        } while (nochmal);

        anzeige.zeigeNachricht(Text.TSCHUESS);
    }

    /**
     * Ermittelt das Wort für die Spielrunde – je nach Spielmodus.
     *
     * @return gültiges Wort in Großbuchstaben
     */
    private String ermittleWort() {
        if (modus == SpielModus.COMPUTER) {
            return new WortAPI(random).holeZufaelligesWort();
        }
        return spielrundeLeseGeheimwort();
    }

    /**
     * Liest im Zwei-Spieler-Modus das geheime Wort vom Mitspieler ein.
     *
     * @return gültiges Wort in Großbuchstaben
     */
    private String spielrundeLeseGeheimwort() {
        anzeige.frage(Text.GEHEIMWORT_EINGABE);
        String wort = anzeige.leseEingabe();
        while (!SpielLogik.istEingabeGueltig(wort)) {
            anzeige.zeigeNachricht(Text.GEHEIMWORT_UNGUELTIG);
            anzeige.frage(Text.GEHEIMWORT_WIEDERHOLUNG);
            wort = anzeige.leseEingabe();
        }
        for (int i = 0; i < 50; i++) System.out.println(); // Konsole „löschen“
        return wort;
    }

    /**
     * Fragt den Spieler, ob eine weitere Runde gestartet werden soll.
     *
     * @return true, wenn erneut gespielt werden soll
     */
    private boolean frageNachNeustart() {
        anzeige.frage(Text.NOCHMAL);
        return anzeige.leseEingabe().equals("1");
    }

    /**
     * Einstiegspunkt des Programms.
     *
     * @param args nicht verwendet
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        SpielModus modus = SpielController.ermittleModus(scanner);
        new Galgenmaennchen(modus, scanner, random);

        scanner.close();
    }
}
