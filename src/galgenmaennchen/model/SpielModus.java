package galgenmaennchen.model;

/**
 * Der {@code SpielModus} legt fest, wie das Spiel gespielt wird:
 * <ul>
 *     <li>{@code COMPUTER} – Der Spieler errät ein Wort, das der Computer auswählt.</li>
 *     <li>{@code ZWEISPIELER} – Ein Mitspieler denkt sich ein Wort aus, das erraten wird.</li>
 *     <li>{@code COMPUTERRATEN} – Der Computer versucht, ein vom Spieler ausgedachtes Wort zu erraten.</li>
 *     <li>{@code BEENDEN} – Das Spiel wird beendet.</li>
 * </ul>
 */
public enum SpielModus {

    /**
     * Der Spieler errät ein zufälliges Wort vom Computer.
     */
    COMPUTER,

    /**
     * Ein Mitspieler denkt sich ein Wort aus, das erraten werden muss.
     */
    ZWEISPIELER,

    /**
     * Der Computer versucht, das Wort des Spielers zu erraten.
     */
    COMPUTER_RATEN,

    /**
     * Beendet das Spiel.
     */
    BEENDEN
}
