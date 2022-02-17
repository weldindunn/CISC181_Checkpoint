import java.util.*;

public abstract class Game {
    /**
     * Game
     * CISC 181-050L Spring 2021
     * University of Delaware
     *
     * This abstract class controls the game itself
     *
     * @author Weldin Dunn
     * @since Mar 30, 2021
     */

    protected GameBoard board;
    protected Team player1;
    protected Team player2;
    protected String turn;
    protected int numTurn; //The turn it is, starting with 1;

    public Game (int numRows, int numCols, Team player1Team, Team player2Team) {
        player1 = player1Team;
        player2 = player2Team;
        turn = player1.getTeamName();
        numTurn = 1;
        initializeGameBoard(numRows, numCols);
    }

    private void initializeGameBoard (int numRows, int numCols) {
        /**
         * Sets up a new game board and adds each team's pieces to it
         * on random spaces
         *
         * @param  numRows -- an int representing the number of rows on the board
         * @param  numcols -- an int representing the number of columns on the board
         */
        board = new GameBoard(numRows, numCols);
        for (int i = 0; i < player1.getTeamPieces().size(); i++) {
            board.findRandomEmptySpace().setPiece(player1.getTeamPieces().get(i));
        }
        for (int i = 0; i < player2.getTeamPieces().size(); i++) {
            board.findRandomEmptySpace().setPiece(player2.getTeamPieces().get(i));
        }
    }

    public GameBoard getGameBoard() {
        /**
         * Gives the GameBoard of this class
         *
         * @return board -- the GameBoard of this class
         */
        return board;
    }

    public Team getCurrentTeam () {
        /**
         * Gives the team with the whose turn it is
         *
         * @return a Team whose turn it is
         */
        if (player1.getTeamName() == turn) {
            return player1;
        } else {
            return player2;
        }
    }

    public Team getOpponentTeam () {
        /**
         * Gives the team with the whose turn it isn't
         *
         * @return a Team whose turn it isn't
         */
        if (player1.getTeamName() == turn) {
            return player2;
        } else {
            return player1;
        }
    }

    public boolean isTurn (Team team) {
        /**
         * Tells whether or not it is a given team's turn
         *
         * @return a boolean representing whether or not it is a given team's turn
         */
        if (team.getTeamName() == turn) {
            return true;
        } else {
            return false;
        }
    }

    public BoardSquare[][] getBoardSquares() {
        /**
         * Gives the game board's matrix of board squares
         *
         * @return the BoardSquare matrix of this class
         */
        return board.getSquares();
    }

    public void changeTurn () {
        /**
         * Changes the turn from one team to the other
         */
        turn = getOpponentTeam().getTeamName();
        numTurn++; //Add another turn to the count
    }

    public int getNumTurn () {
        /**
         * Gives the current turn number
         *
         * @return an int representing the above (numTurn)
         */

        return numTurn;
    }

    @Override
    public String toString() {
        /**
         * Gives a string made of various information
         *
         * @return a string comprised of the game board, each team's pieces, and whose turn it is
         */
        StringBuilder retString = new StringBuilder();
        retString.append("Game Board:\n")
                .append(String.join("", Collections.nCopies(10 + board.getNumColumns()*8, "*")))
                .append("\n" + getGameBoard().toString())
                .append(String.join("", Collections.nCopies(10 + board.getNumColumns()*8, "*")))
                .append("\n" + getCurrentTeam().toString() + "\n")
                .append(String.join("", Collections.nCopies(10 + board.getNumColumns()*8, "*")))
                .append("\n" + getOpponentTeam().toString() + "\n")
                .append(String.join("", Collections.nCopies(10 + board.getNumColumns()*8, "*")))
                .append("\nIt is Team " + getCurrentTeam().getTeamName() + "'s turn\n");
        return retString.toString();
    }

    abstract boolean isAWinner();
    abstract Team getWinner();
    abstract boolean isGameEnded();
}