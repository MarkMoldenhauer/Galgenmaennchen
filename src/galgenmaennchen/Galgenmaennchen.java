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
 * Nach jeder Spielrunde wird ein Menü angeboten:
 * <ul>
 *     <li>1 – Noch einmal spielen</li>
 *     <li>2 – Zurück ins Hauptmenü</li>
 *     <li>3 – Beenden</li>
 * </ul>
 * 
 * @author MarkMoldenhauer
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

    /**
     * Steuert den Spielablauf basierend auf dem gewählten Modus.
     *
     * @param scanner Scanner für Benutzereingaben
     */
    private void starteSpiel(Scanner scanner) {
        switch (modus) {
            case COMPUTER, ZWEISPIELER -> starteNormalesSpiel();
            case COMPUTER_RATEN -> starteComputerRatenSpiel(scanner);
            case BEENDEN -> anzeige.zeigeNachricht(Text.TSCHUESS);
        }
    }

    /**
     * Führt Modus 1 & 2 aus – Spieler rät das Wort.
     */
    private void starteNormalesSpiel() {
        while (true) {
            String wort = ermittleWort();
            anzeige.zeigeWortZumTesten(wort);
            boolean gewonnen = new SpielRunde(anzeige, wort).spielen();
            anzeige.zeigeSpielende(gewonnen, wort);

            String wahl = anzeige.frageRundenMenue();
            if (wahl.equals("1")) {
                continue; // nochmal spielen
            } else if (wahl.equals("2")) {
                SpielModus neuerModus = SpielController.ermittleModus(new Scanner(System.in));
                new Galgenmaennchen(neuerModus, new Scanner(System.in), random);
                return;
            } else {
                anzeige.zeigeNachricht(Text.TSCHUESS);
                return;
            }
        }
    }

    /**
     * Führt Modus 3 aus – Computer rät das Spielerwort.
     *
     * @param scanner Eingabequelle für das Wort
     */
    private void starteComputerRatenSpiel(Scanner scanner) {
        while (true) {
            String wort = anzeige.liesGeheimesWort();
            ComputerRaten spiel = new ComputerRaten(wort, anzeige, scanner);
            spiel.spiele();

            String wahl = anzeige.frageRundenMenue();
            if (wahl.equals("1")) {
                continue; // nochmal spielen
            } else if (wahl.equals("2")) {
                SpielModus neuerModus = SpielController.ermittleModus(scanner);
                new Galgenmaennchen(neuerModus, scanner, random);
                return;
            } else {
                anzeige.zeigeNachricht(Text.TSCHUESS);
                return;
            }
        }
    }

    /**
     * Ermittelt das zu ratende Wort, je nach Spielmodus.
     *
     * @return gültiges Wort in Großbuchstaben
     */
    private String ermittleWort() {
        if (modus == SpielModus.COMPUTER) {
            return new WortAPI(random).holeZufaelligesWort();
        }
        return anzeige.liesGeheimesWort();
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
        if (modus != null) {
            new Galgenmaennchen(modus, scanner, random);
        } else {
            System.out.println(Text.TSCHUESS);
        }

        scanner.close();
    }
}
