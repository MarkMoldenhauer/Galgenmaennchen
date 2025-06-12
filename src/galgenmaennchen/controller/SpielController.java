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
     * Fragt den Benutzer nach dem gewünschten Spielmodus (Computer oder Zwei-Spieler).
     * Gibt den entsprechenden {@link SpielModus} zurück.
     * 
     * @param scanner Der Scanner zur Benutzereingabe (z. B. System.in)
     * @return {@code SpielModus.COMPUTER} oder {@code SpielModus.ZWEISPIELER}, je nach Benutzereingabe
     */
    public static SpielModus ermittleModus(Scanner scanner) {
        SpielAnzeige anzeige = new SpielAnzeige(scanner);
        anzeige.zeigeBegruessung();

        while (true) {
            anzeige.frage(Text.MODUS_WAHL);
            String eingabe = anzeige.leseEingabe();

            if (eingabe.equals("1")) {
                return SpielModus.COMPUTER;
            } else if (eingabe.equals("2")) {
                return SpielModus.ZWEISPIELER;
            } else {
                anzeige.zeigeNachricht(Text.MODUS_UNGUELTIG);
            }
        }
    }
}
