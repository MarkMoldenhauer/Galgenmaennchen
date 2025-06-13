package galgenmaennchen.view;

import java.util.Scanner;
import java.util.Set;

import galgenmaennchen.model.Galgen;
import galgenmaennchen.model.SpielLogik;

/**
 * Die Klasse {@code SpielAnzeige} kapselt alle Ein- und Ausgaben
 * des Spiels Galgenmännchen. Sie ist für die Benutzerkommunikation verantwortlich.
 */
public class SpielAnzeige {

    private final Scanner scanner;

    /**
     * Konstruktor – initialisiert die Eingabequelle.
     *
     * @param scanner {@code Scanner}-Instanz zur Benutzereingabe
     */
    public SpielAnzeige(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Gibt Begrüßung und Moduserklärung auf der Konsole aus.
     */
    public void zeigeBegruessung() {
        System.out.println(Text.MODUS_BEGRUESSUNG);
        System.out.println(Text.MODUS_FRAGE);
    }

    /**
     * Gibt das geheime Wort aus – dient Test- und Debug-Zwecken.
     *
     * @param wort Das zu erratende Wort
     */
    public void zeigeWortZumTesten(String wort) {
        System.out.println(wort);
    }

    /**
     * Zeigt den aktuellen Spielstand:
     * - den Fortschritt beim Erraten des Worts
     * - die Anzahl verlorener Leben
     * - bereits geratene Buchstaben
     * - sowie den Galgenzustand als ASCII-Zeichnung
     *
     * @param anzeige     Anzeige-Array mit erratenen Zeichen
     * @param leben       Noch verbleibende Leben
     * @param geraten     Menge aller bereits geratenen Buchstaben
     * @param maxLeben    Maximale Anzahl Leben (zur Anzeige)
     */
    public void zeigeSpielstand(char[] anzeige, int leben, Set<Character> geraten, int maxLeben) {
        System.out.print(Text.BEGRUESSUNG);
        for (char c : anzeige) System.out.print(c + " ");
        System.out.println();
        System.out.println(Text.FEHLER + (maxLeben - leben) + "/" + maxLeben);
        System.out.println(Text.BEREITS_GERATEN + geraten);
        zeigeGalgen(Galgen.getStufe(maxLeben - leben));
    }

    /**
     * Gibt die ASCII-Grafik des aktuellen Galgen-Zustands aus.
     *
     * @param galgenStufe Der Galgen-String entsprechend der Fehleranzahl
     */
    public void zeigeGalgen(String galgenStufe) {
        System.out.println("\n" + galgenStufe);
    }

    /**
     * Gibt eine Nachricht mit Zeilenumbruch aus.
     *
     * @param nachricht Nachricht an den Spieler
     */
    public void zeigeNachricht(String nachricht) {
        System.out.println(nachricht);
    }

    /**
     * Gibt eine Eingabeaufforderung ohne Zeilenumbruch aus.
     *
     * @param frageText Die Frage bzw. der Hinweis
     */
    public void frage(String frageText) {
        System.out.print(frageText);
    }

    /**
     * Liest die Benutzereingabe ein, wandelt sie in Großbuchstaben um
     * und entfernt Leerzeichen am Anfang/Ende.
     *
     * @return Die bereinigte Eingabe
     */
    public String leseEingabe() {
        return scanner.nextLine().toUpperCase().trim();
    }

    /**
     * Gibt die Endmeldung der Runde aus.
     *
     * @param gewonnen true, wenn das Spiel gewonnen wurde
     * @param wort     Das gesuchte Wort
     */
    public void zeigeSpielende(boolean gewonnen, String wort) {
        if (gewonnen) {
            zeigeNachricht(Text.SIEG + wort);
        } else {
            zeigeNachricht(Text.NIEDERLAGE + wort);
        }
    }

    /**
     * Fragt das geheime Wort im Zwei-Spieler-Modus ab.
     * Führt eine Gültigkeitsprüfung durch und „versteckt“ das Wort
     * anschließend mit Leerzeilen.
     *
     * @return Das eingegebene und geprüfte Wort
     */
    public String liesGeheimesWort() {
        frage(Text.GEHEIMWORT_EINGABE);
        String wort = leseEingabe();
        while (!SpielLogik.istEingabeGueltig(wort)) {
            zeigeNachricht(Text.GEHEIMWORT_UNGUELTIG);
            frage(Text.GEHEIMWORT_WIEDERHOLUNG);
            wort = leseEingabe();
        }
        for (int i = 0; i < 50; i++) System.out.println(); // „versteckt“ das Wort
        return wort;
    }

    /**
     * Zeigt ein Menü nach einer Runde an, in dem der Spieler wählen kann,
     * ob er weiterspielen, zurück ins Hauptmenü oder das Spiel beenden möchte.
     *
     * @return "1" = nochmal spielen, "2" = zurück ins Hauptmenü, "3" = Spiel beenden
     */
    public String frageRundenMenue() {
        String eingabe;
        do {
            System.out.println(Text.RUNDE_MENUE);
            frage(Text.RUNDE_MENUE_AUSWAHL);
            eingabe = leseEingabe();
            if (!eingabe.equals("1") && !eingabe.equals("2") && !eingabe.equals("3")) {
                zeigeNachricht(Text.RUNDE_MENUE_UNGUELTIG);
            }
        } while (!eingabe.equals("1") && !eingabe.equals("2") && !eingabe.equals("3"));

        return eingabe;
    }
}
