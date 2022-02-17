import java.util.Locale;

public class ActionAttack extends Action {
    /**
     * ActionAttack
     * CISC 181-050L Spring 2021
     * University of Delaware
     *
     * This class represents a type of action where a player
     * attacks a piece from another team and then moves to
     * that square
     *
     * @author Weldin Dunn
     * @since Apr 16, 2021
     */

    public ActionAttack (GameS21 gameS21, int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol) {
        super(gameS21, fromSquareRow, fromSquareCol, toSquareRow, toSquareCol);
    }

    public boolean validAction() {
        /**
         * Checks whether or not the piece on the fromSquare can
         * attack a piece from the other team on the toSquare
         *
         * @return a boolean representing whether or not the action is valid
         */
        if (fromSquareValid() && toSquareValid(false) && validActionPath() && Hostile.class.isAssignableFrom(gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().getClass()) && !gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().isInJail()) {
            if ((gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().getSymbol() == "WA" || gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().getSymbol() == "OP") && gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().getTransformStatus()) {
                //If you are WA or OP and transformed you can attack
                return true;
            } else if (gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().getSymbol() == "UM" || gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().getSymbol() == "BH") {
                //If you are BH or UM, you can attack;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void performAction() {
        /**
         * Removes the attacked piece from the board and its team,
         * then moves the attacker to the toSquare
         */
        if (attack()) {
            //Removes piece from board and team
            Piece attacked = gameS21.getGameBoard().getSquares()[toSquareRow][toSquareCol].getPiece();
            gameS21.getGameBoard().getSquares()[toSquareRow][toSquareCol].removePiece();
            gameS21.getOpponentTeam().removePieceFromTeam(attacked);

            //Moves attacker
            Piece attacker = gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].removePiece();
            gameS21.getGameBoard().getSquares()[toSquareRow][toSquareCol].setPiece(attacker);

            //If the attacked piece is an underminer and the attacker is a blue hen
            if (attacked.getSymbol() == "UM" && attacker.getSymbol() == "BH") {
                //Hide the hen!
                attacker.setHidden(true);
            }

            //When a piece is attacked, debt is subtracted
            gameS21.getCurrentTeam().subtractDebt(200);

            //If the attacker is Waluigi
            if (attacker.getSymbol().toLowerCase() == "wa") {
                //If the piece has tacos, ask if they want another move
                if (((PieceWaluigi)(attacker)).getTacos() > 0) {
                    System.out.println("Make another move? (yes/no)");
                } else {
                    gameS21.changeTurn(); //Otherwise change the turn
                }
            } else {
                gameS21.changeTurn();
            }
        }
    }

    private boolean attack() {
        /**
         * Checks if the piece on the fromSquare can attack by checking
         * if its piece type is an instance of the Hostile interface
         *
         * @return a boolean representing whether or not the piece can attack
         */
        Piece attacker = gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece();
        if (attacker instanceof Hostile) {
            return true;
        } else {
            return false;
        }
    }
}
