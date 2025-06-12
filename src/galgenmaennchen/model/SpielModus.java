package galgenmaennchen.model;

/**
 * Der {@code SpielModus} gibt an, ob das Spiel gegen den Computer
 * oder gegen einen zweiten Spieler gespielt wird.
 */
public enum SpielModus {

    /**
     * Der Computer wählt ein zufälliges Wort, das erraten werden muss.
     */
    COMPUTER,

    /**
     * Ein Mitspieler denkt sich ein Wort aus, das erraten werden muss.
     */
    ZWEISPIELER
}
