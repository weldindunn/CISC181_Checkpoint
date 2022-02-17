public class ActionTransform extends Action {
    /**
     * ActionTransform
     * CISC 181-050L Spring 2021
     * University of Delaware
     *
     * This class represents a type of action where a player
     * transforms from one form to another
     *
     * @author Weldin Dunn, Zachary England, Alex Irwin
     * @since May 4, 2021
     */

    public ActionTransform (GameS21 gameS21, int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol) {
        super(gameS21, fromSquareRow, fromSquareCol, toSquareRow, toSquareCol);
    }

    public boolean validAction() {
        /**
         * Checks whether or not the piece can transform
         *
         * @return a boolean representing whether or not the action is valid
         */
        if (gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().canTransform() && !gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().isInJail()) {
            return true;
        } else {
            return false;
        }
    }

    public void performAction() {
        /**
         * Transforms the piece from its normal mode to its super mode,
         * does not change turn.
         */
        gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().transform();
    }
}