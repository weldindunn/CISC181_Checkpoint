import java.util.Locale;

public class ActionBefriend extends Action {
    /**
     * ActionBefriend
     * CISC 181-050L Spring 2021
     * University of Delaware
     *
     * This class represents a type of action where a player
     * befriends another piece of the opponent team
     *
     * @author Weldin Dunn
     * @since Apr 17, 2021
     */

    public ActionBefriend (GameS21 gameS21, int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol) {
        super(gameS21, fromSquareRow, fromSquareCol, toSquareRow, toSquareCol);
    }

    public boolean validAction() {
        /**
         * Checks whether or not the piece on the fromSquare can
         * befriend a piece from the other team on the toSquare
         *
         * @return a boolean representing whether or not the action is valid
         */
        if (fromSquareValid() && toSquareValid(false) && validActionPath() && Friendly.class.isAssignableFrom(gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().getClass()) && !gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().isInJail()) {
            return true;
        } else {
            return false;
        }
    }

    public void performAction() {
        /**
         * Removes the befriended piece from its team, adds it
         * to the befriender's team, then changes the turn.
         */
        if (befriend()) {
            //Removes piece from its team
            Piece friended = gameS21.getGameBoard().getSquares()[toSquareRow][toSquareCol].getPiece();
            gameS21.getOpponentTeam().removePieceFromTeam(friended);
            gameS21.getCurrentTeam().addPieceToTeam(friended);

            //If the friended piece is scooby doo
            if (friended.getSymbol() == "SD") {
                //If the befriender is a blue hen
                if (gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().getSymbol() == "BH") {
                    //Entangle
                    System.out.println("Entangled before?: " + ((PieceScoobyDoo)(friended)).isEntangled());
                    ((PieceScoobyDoo) friended).entangle();
                    System.out.println("Entangled after?: " + ((PieceScoobyDoo)(friended)).isEntangled());
                }

                //If it's WA or Optimus Prime
                if (gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().getSymbol() == "WA" || gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().getSymbol() == "OP") {
                    //Add a number of scooby snacks 1-3
                    System.out.println("Scooby snacks before: " + ((PieceScoobyDoo)(friended)).getNumScoobySnacks());
                    ((PieceScoobyDoo) friended).collectScoobySnacks((int)(3 * Math.random()) + 1);
                    System.out.println("Scooby snacks after: " + ((PieceScoobyDoo)(friended)).getNumScoobySnacks());
                }
            }

            //If the befriender is scooby doo
            if (gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().getSymbol() == "SD") {
                //Collect a scooby snack for this fella
                System.out.println("Scooby snacks before: " + ((PieceScoobyDoo)(gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece())).getNumScoobySnacks());
                ((PieceScoobyDoo)(gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece())).collectScoobySnacks(1);
                System.out.println("Scooby snacks after: " + ((PieceScoobyDoo)(gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece())).getNumScoobySnacks());
            }

            /*
            //If the befriender is Waluigi
            if (gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().getSymbol().toLowerCase() == "wa") {
                //Add a taco
                ((PieceWaluigi)(gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece())).addTacos(1);
            }
            */

            //When a piece is befriended, debt is subtracted
            gameS21.getCurrentTeam().subtractDebt(200);

            gameS21.changeTurn();
        }
    }

    private boolean befriend() {
        /**
         * Checks if the piece on the fromSquare can befriend by checking
         * if its piece type is an instance of the Friendly interface
         *
         * @return a boolean representing whether or not the piece can befriend
         */
        Piece friend = gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece();
        if (friend instanceof Friendly) {
            return true;
        } else {
            return false;
        }
    }
}
