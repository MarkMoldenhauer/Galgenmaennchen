package galgenmaennchen.model;

import java.util.*;
import galgenmaennchen.view.SpielAnzeige;
import galgenmaennchen.view.Text;

/**
 * Spiellogik für den Modus, in dem der Computer das vom Spieler ausgedachte Wort errät.
 */
public class ComputerRaten {

    private final String geheimwort;
    private final SpielAnzeige anzeige;
    private final Scanner scanner;
    private final Set<Character> geraten = new HashSet<>();
    private final int maxLeben = Galgen.getMaxLeben();

    private final List<Character> buchstabenPool = new ArrayList<>(List.of(
        'E','N','I','S','R','A','T','D','H','U','L','C','G','M','O','B','W','F','K','Z',
        'V','P','J','Y','X','Q'
    ));

    public ComputerRaten(String geheimwort, SpielAnzeige anzeige, Scanner scanner) {
        this.geheimwort = geheimwort.toUpperCase();
        this.anzeige = anzeige;
        this.scanner = scanner;
    }

    /**
     * Führt den Spielablauf für den Computer-Raten-Modus aus.
     */
    public void spiele() {
        int leben = maxLeben;
        char[] loesung = geheimwort.toCharArray();
        char[] anzeigeArr = SpielLogik.initialisiereAnzeige(loesung);

        while (leben > 0 && !Arrays.equals(loesung, anzeigeArr)) {
            anzeige.zeigeSpielstand(anzeigeArr, leben, geraten, maxLeben);

            char tipp = waehleBuchstabe();
            geraten.add(tipp);

            System.out.println(Text.COMPUTER_RATE_FRAGE + tipp);
            warteAufEnter();

            boolean treffer = false;
            for (int i = 0; i < loesung.length; i++) {
                if (loesung[i] == tipp) {
                    anzeigeArr[i] = tipp;
                    treffer = true;
                }
            }

            if (treffer) {
                System.out.println(Text.RICHTIG);
            } else {
                leben--;
                System.out.println(Text.COMPUTER_FEHLER);
            }
        }

        anzeige.zeigeSpielstand(anzeigeArr, leben, geraten, maxLeben);
        System.out.println(Arrays.equals(loesung, anzeigeArr)
            ? Text.SIEG + geheimwort
            : Text.NIEDERLAGE + geheimwort);
    }

    /**
     * Wählt einen zufälligen, noch nicht geratenen Buchstaben.
     */
    private char waehleBuchstabe() {
        Collections.shuffle(buchstabenPool);
        for (char buchstabe : buchstabenPool) {
            if (!geraten.contains(buchstabe)) {
                return buchstabe;
            }
        }
        return '?';
    }

    /**
     * Wartet auf Enter-Eingabe vom Spieler.
     */
    private void warteAufEnter() {
        System.out.print(Text.COMPUTER_NEXT);
        scanner.nextLine();
    }
}
