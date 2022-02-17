public class ActionMove extends Action{
    /**
     * ActionMove
     * CISC 181-050L Spring 2021
     * University of Delaware
     *
     * This class represents a type of action where a player
     * moves their piece from one square to another
     *
     * @author Weldin Dunn
     * @since Apr 16, 2021
     */

    public ActionMove (GameS21 gameS21, int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol) {
        super(gameS21, fromSquareRow, fromSquareCol, toSquareRow, toSquareCol);
    }

    public boolean validAction() {
        /**
         * Checks whether or not the piece on the fromSquare
         * can move in the given path
         *
         * @return a boolean representing whether or not the action is valid
         */
        if (fromSquareValid() && toSquareValid(true) && validActionPath() && !gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().isInJail()) {
            return true;
        } else {
            //System.out.println(gameS21.getNumTurn() - 3);
            return false;
        }
    }

    public void performAction() {
        /**
         * Removes the piece from the fromSquare, moves it to the twoSquare,
         * and changes the turn.
         */
        Piece piece = gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].removePiece();

        if (gameS21.getGameBoard().getSquares()[toSquareRow][toSquareCol].getJail()) {
            //Send the piece to jail
            for (int i = 0; i < gameS21.getGameBoard().getNumRows(); i++) {
                if (gameS21.getGameBoard().getSquares()[i][0].isEmpty()) {
                    gameS21.getGameBoard().getSquares()[i][0].setPiece(piece); //Sends piece to jail
                    gameS21.getGameBoard().getSquares()[i][0].getPiece().setJailTurn(gameS21.getNumTurn()); //Sets that piece's jail turn
                    gameS21.getGameBoard().getSquares()[i][0].getPiece().setJailStatus(true);
                    break;
                }
            }
        } else {
            //Move the piece to the toSquare
            gameS21.getGameBoard().getSquares()[toSquareRow][toSquareCol].setPiece(piece);
        }

        gameS21.changeTurn();
    }
}
