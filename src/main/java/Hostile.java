public interface Hostile {
    /**
     * Hostile
     * CISC 181-050L Spring 2021
     * University of Delaware
     *
     * This interface is implemented into pieces that can attack
     *
     * @author Weldin Dunn
     * @since Mar 30, 2021
     */

    boolean attack (int attackerRowIndex, int attackerColIndex,
                    int defenderRowIndex, int defenderColIndex);
    //Sets up the attack method for hostile pieces
}
