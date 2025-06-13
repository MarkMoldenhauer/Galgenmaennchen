package galgenmaennchen.controller;

import java.util.Scanner;

import galgenmaennchen.model.SpielModus;
import galgenmaennchen.view.SpielAnzeige;
import galgenmaennchen.view.Text;

/**
 * Der SpielController ist für die Steuerung der Spielinitialisierung verantwortlich.
 * Er fragt zu Beginn den gewünschten Spielmodus vom Benutzer ab.
 */
public class SpielController {

    /**
     * Fragt den Benutzer nach dem gewünschten Spielmodus.
     * Gibt den entsprechenden {@link SpielModus} zurück oder {@code null}, wenn das Spiel beendet werden soll.
     *
     * @param scanner Der Scanner zur Benutzereingabe (z. B. System.in)
     * @return {@link SpielModus} entsprechend der Eingabe oder {@code null} zum Beenden
     */
    public static SpielModus ermittleModus(Scanner scanner) {
        SpielAnzeige anzeige = new SpielAnzeige(scanner);
        anzeige.zeigeBegruessung();

        while (true) {
            anzeige.frage(Text.MODUS_WAHL);
            String eingabe = anzeige.leseEingabe();

            switch (eingabe) {
                case "1":
                    return SpielModus.COMPUTER;
                case "2":
                    return SpielModus.ZWEISPIELER;
                case "3":
                    return SpielModus.COMPUTER_RATEN;
                case "4":
                    return null;
                default:
                    anzeige.zeigeNachricht(Text.MODUS_UNGUELTIG);
            }
        }
    }
}
