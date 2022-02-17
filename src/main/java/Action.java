public abstract class Action {
    /**
     * Action
     * CISC 181-050L Spring 2021
     * University of Delaware
     *
     * This abstract class represents the action a player can make
     * with one of their pieces during their turn
     *
     * @author Weldin Dunn
     * @since Apr 16, 2021
     */

    protected GameS21 gameS21;
    protected int fromSquareRow;
    protected int fromSquareCol;
    protected int toSquareRow;
    protected int toSquareCol;

    public Action (GameS21 gameS21, int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol) {
        this.gameS21 = gameS21;
        this.fromSquareRow = fromSquareRow;
        this.fromSquareCol = fromSquareCol;
        this.toSquareRow = toSquareRow;
        this.toSquareCol = toSquareCol;
    }

    public boolean fromSquareValid() {
        /**
         * Says whether or not the square the player is moving their
         * piece from is valid. This will only be true if that square
         * is both in bounds and contains a piece from their team.
         *
         * @return a boolean representing whether or not the move is valid
         */
        if (gameS21.getGameBoard().inBounds(fromSquareRow, fromSquareCol) &&
                gameS21.getCurrentTeam().getTeamPieces().contains(
                        gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean toSquareValid(boolean shouldBeEmpty) {
        /**
         * Says whether or not the square the player is moving their piece
         * to is valid. This can only be true if the space is in bounds.
         * This will be true if the space should be empty and it is, or if
         * the space shouldn't be empty and contains a piece from the
         * opponent's team.
         *
         * @param  shouldBeEmpty -- A boolean representing whether or not
         *                       a given space should be empty
         *
         * @return a boolean representing whether or not the move is valid
         */
        if (gameS21.getGameBoard().inBounds(toSquareRow, toSquareCol)) {
            if (shouldBeEmpty && gameS21.getGameBoard().getSquares()[toSquareRow][toSquareCol].isEmpty()) {
                return true;
            } else if (!shouldBeEmpty && gameS21.getOpponentTeam().getTeamPieces().contains(
                    gameS21.getGameBoard().getSquares()[toSquareRow][toSquareCol].getPiece())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean validActionPath() {
        /**
         * Tells whether or not the given path is valid for the piece
         * on the fromSquare
         *
         * @return a boolean representing whether or not the path is valid.
         */
        if (gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().validPath(fromSquareRow, fromSquareCol, toSquareRow, toSquareCol)) {
            return true;
        } else {
            return false;
        }
    }

    abstract boolean validAction(); //Whether or not the player's move is valid
    abstract void performAction();
}
