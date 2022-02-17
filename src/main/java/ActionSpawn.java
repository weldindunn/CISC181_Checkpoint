public class ActionSpawn extends Action {
    /**
     * ActionSpawn
     * CISC 181-050L Spring 2021
     * University of Delaware
     *
     * This class represents a type of action where a player
     * spawns a clone on the 'toSquare'
     *
     * @author Weldin Dunn
     * @since Apr 16, 2021
     */

    public ActionSpawn (GameS21 gameS21, int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol) {
        super(gameS21, fromSquareRow, fromSquareCol, toSquareRow, toSquareCol);
    }

    public boolean validAction() {
        /**
         * Checks whether or not the piece on the fromSquare can
         * spawn a piece on the toSquare
         *
         * @return a boolean representing whether or not the action is valid
         */
        if (fromSquareValid() && toSquareValid(true) && validActionPath() && gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().canSpawn() && !gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().isInJail()) {
            //System.out.println("fromSquareValid: " + fromSquareValid());
            //System.out.println("toSquareValid: " + toSquareValid(true));
            //System.out.println("validActionPath: " + validActionPath());
            //System.out.println("canSpawn: " + gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().canSpawn());
            return true;
        } else {
            //System.out.println("fromSquareValid: " + fromSquareValid());
            //System.out.println("toSquareValid: " + toSquareValid(true));
            //System.out.println("validActionPath: " + validActionPath());
            //System.out.println("canSpawn: " + gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().canSpawn());
            return false;
        }
    }

    public void performAction() {
        /**
         * Spawns a piece from the fromSquare, adds it to the team, places it
         * on the toSquare, and then changes the turn.
         */
        Piece spawn = gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().spawn();
        gameS21.getCurrentTeam().addPieceToTeam(spawn);
        gameS21.getGameBoard().getSquares()[toSquareRow][toSquareCol].setPiece(spawn);
        gameS21.changeTurn();
    }
}
