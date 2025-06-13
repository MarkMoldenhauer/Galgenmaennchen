package galgenmaennchen;

import java.util.Random;
import java.util.Scanner;

import galgenmaennchen.controller.SpielController;
import galgenmaennchen.model.ComputerRaten;
import galgenmaennchen.model.SpielLogik;
import galgenmaennchen.model.SpielModus;
import galgenmaennchen.model.SpielRunde;
import galgenmaennchen.model.WortAPI;
import galgenmaennchen.view.SpielAnzeige;
import galgenmaennchen.view.Text;

/**
 * Startpunkt des Galgenmännchen-Spiels.
 * Verantwortlich für: Initialisierung, Moduswahl, Steuerung und Spielrunden.
 * 
 * @author M. Moldenhauer
 */
public class Galgenmaennchen {

    private final Random random;
    private final SpielModus modus;
    private final SpielAnzeige anzeige;

    public Galgenmaennchen(SpielModus modus, Scanner scanner, Random random) {
        this.modus = modus;
        this.random = random;
        this.anzeige = new SpielAnzeige(scanner);
        starteSpiel(scanner);
    }

    private void starteSpiel(Scanner scanner) {
        switch (modus) {
            case COMPUTER, ZWEISPIELER -> starteNormalesSpiel();
            case COMPUTER_RATEN -> starteComputerRatenSpiel(scanner);
            case BEENDEN -> anzeige.zeigeNachricht(Text.TSCHUESS);
        }
    }

    /**
     * Führt Modus 1 & 2 aus.
     */
    private void starteNormalesSpiel() {
        boolean nochmal;
        do {
            String wort = ermittleWort();
            anzeige.zeigeWortZumTesten(wort);
            boolean gewonnen = new SpielRunde(anzeige, wort).spielen();
            anzeige.zeigeNachricht(gewonnen ? Text.SIEG + wort : Text.NIEDERLAGE + wort);
            nochmal = frageNachNeustart();
        } while (nochmal);
        anzeige.zeigeNachricht(Text.TSCHUESS);
    }

    /**
     * Führt Modus 3 aus: Computer rät das Wort.
     */
    private void starteComputerRatenSpiel(Scanner scanner) {
        boolean nochmal;
        do {
            anzeige.frage(Text.GEHEIMWORT_EINGABE);
            String wort = anzeige.leseEingabe();
            while (!SpielLogik.istEingabeGueltig(wort)) {
                anzeige.zeigeNachricht(Text.GEHEIMWORT_UNGUELTIG);
                anzeige.frage(Text.GEHEIMWORT_WIEDERHOLUNG);
                wort = anzeige.leseEingabe();
            }
            for (int i = 0; i < 50; i++) System.out.println(); // "Konsole löschen"

            ComputerRaten spiel = new ComputerRaten(wort, anzeige, scanner);
            spiel.spiele();

            anzeige.frage(Text.NOCHMAL);
            nochmal = anzeige.leseEingabe().equals("1");
        } while (nochmal);
        anzeige.zeigeNachricht(Text.TSCHUESS);
    }

    private String ermittleWort() {
        if (modus == SpielModus.COMPUTER) {
            return new WortAPI(random).holeZufaelligesWort();
        }
        return spielrundeLeseGeheimwort();
    }

    private String spielrundeLeseGeheimwort() {
        anzeige.frage(Text.GEHEIMWORT_EINGABE);
        String wort = anzeige.leseEingabe();
        while (!SpielLogik.istEingabeGueltig(wort)) {
            anzeige.zeigeNachricht(Text.GEHEIMWORT_UNGUELTIG);
            anzeige.frage(Text.GEHEIMWORT_WIEDERHOLUNG);
            wort = anzeige.leseEingabe();
        }
        for (int i = 0; i < 50; i++) System.out.println();
        return wort;
    }

    private boolean frageNachNeustart() {
        anzeige.frage(Text.NOCHMAL);
        return anzeige.leseEingabe().equals("1");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        SpielModus modus = SpielController.ermittleModus(scanner);
        if (modus != null) {
            new Galgenmaennchen(modus, scanner, random);
        } else {
            System.out.println(Text.TSCHUESS);
        }

        scanner.close();
    }
}
