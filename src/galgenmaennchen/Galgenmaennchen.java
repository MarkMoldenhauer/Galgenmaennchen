package galgenmaennchen;
import java.util.*;

public class Galgenmaennchen {

    /**
     * Wortliste
     */
    private static final String[] WORTLISTE = {
        "Banane", "Schule", "Tisch", "Computer", "Programmierung", "Fenster"
    };

    /**
     *  Anzahl erlaubter Fehlversuche (Leben)
     */
    private static final int MAX_LEBEN = 10;

    /**
     *  Galgen (10 Leben)
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

    /**
     *  Konstanten
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean nochmal = true;

        while (nochmal) {
            /**
             *  Initialisierung
             *  Der Computer wählt ein zufälliges Wort aus der Liste.
             *  Der Spieler erhält 10 Leben. Wort ist noch vollständig verdeckt.
        	 */
            
            String wort = WORTLISTE[random.nextInt(WORTLISTE.length)].toUpperCase();
            System.out.println(wort); // Zum Testen sichtbar
            char[] loesung = wort.toCharArray();
            char[] anzeige = new char[loesung.length];
            Arrays.fill(anzeige, '_');

            int leben = MAX_LEBEN;
            Set<Character> bereitsGeraten = new HashSet<>();

            /** Hauptrunde
            * Der Spieler gibt Buchstaben oder Wort ein
            * Richtig geratene Buchstaben werden aufgedeckt
            * Falsche Eingaben kosten ein Leben
            * Das Galgenbild wird aktualisiert
            */
            boolean gewonnen = false;
            while (leben > 0 && !gewonnen) {
                System.out.print(TEXT_BEGRUESSUNG);
                for (char c : anzeige) {
                    System.out.print(c + " ");
                }
                System.out.println();

                System.out.println(TEXT_FEHLER + (MAX_LEBEN - leben) + "/" + MAX_LEBEN);
                System.out.println(TEXT_BEREITS_GERATEN + bereitsGeraten);
                int galgenIndex = Math.min(GALGEN.length - 1, MAX_LEBEN - leben);
                System.out.println("\n" + GALGEN[galgenIndex]);

                /** Eingabeaufforderung
                * Spieler gibt Buchstaben/Wort ein.
                */
                System.out.print(TEXT_EINGABE);
                String eingabe = scanner.nextLine().toUpperCase().trim();

                if (eingabe.length() == 0 || (!eingabe.chars().allMatch(Character::isLetter))) {
                    System.out.println(TEXT_EINGABE_FEHLER);
                    continue;
                }

                if (eingabe.length() == 1) {
                    char buchstabe = eingabe.charAt(0);

                    if (bereitsGeraten.contains(buchstabe)) {
                        System.out.println(TEXT_BEREITS_VERSUCHT);
                        continue;
                    }

                    bereitsGeraten.add(buchstabe);

                    /**
                     *  Überprüfung der Eingabe
                     */
                    boolean treffer = false;
                    for (int i = 0; i < loesung.length; i++) {
                        if (loesung[i] == buchstabe) {
                            anzeige[i] = buchstabe;
                            treffer = true;
                        }
                    }

                    if (!treffer) {
                        leben--;
                        System.out.println(TEXT_LEBEN_VERLOREN);
                    } else {
                        System.out.println(TEXT_RICHTIG);
                    }
                } else {
                    /**
                     *  Überprüfung der Eingabe (Wortvergleich)
                     */
                    if (eingabe.equals(wort)) {
                        gewonnen = true;
                        anzeige = wort.toCharArray();
                        break;
                    } else {
                        leben--;
                        System.out.println(TEXT_LEBEN_VERLOREN);
                    }
                }

                /**
                 *  Spielzustand prüfen: Wurde das Wort vollständig erraten?
                 */
                gewonnen = Arrays.equals(loesung, anzeige);
            }

         /**
          *  Spielende – Anzeige von Sieg oder Niederlage
          */
            int galgenIndex = Math.min(GALGEN.length - 1, MAX_LEBEN - leben);
            System.out.println("\n" + GALGEN[galgenIndex]);

            if (gewonnen) {
                System.out.println(TEXT_SIEG + wort);
            } else {
                System.out.println(TEXT_NIEDERLAGE + wort);
            }

            /**
             *  Abfrage, ob eine neue Runde gestartet werden soll
             */
            System.out.print(TEXT_NOCHMAL);
            String antwort = scanner.nextLine();
            nochmal = antwort.equals("1");
        }

        System.out.println(TEXT_TSCHUESS);
        scanner.close();
    }
}
