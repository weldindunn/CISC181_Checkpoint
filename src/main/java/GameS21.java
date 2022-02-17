public class GameS21 extends Game {
    /**
     * GameS21
     * CISC 181-050L Spring 2021
     * University of Delaware
     *
     * This class controls the game itself and checks the conditions
     * of winning/ending the game
     *
     * @author Weldin Dunn
     * @since Apr 13, 2021
     */
    public GameS21 (int numRows, int numCols, Team player1, Team player2) {
        super(numRows, numCols, player1, player2);
    }

    public boolean isGameEnded () {
        /**
         * Checks whether or not one of the players it out of pieces
         * to know whether or not the game is over
         *
         * @return a boolean representing whether or not the game is over
         */
        if (player1.getDebt() <= 0 || player2.getDebt() <= 0) { //If either player paid off their debt, the game is over
            return true;
        } else {
            return false;
        }

    }

    public boolean isAWinner () {
        /**
         * Checks whether or not one of the players has paid off their debt
         *
         * @return a boolean representing whether or not a player has paid off their debt
         */
        if (getWinner() != null) {
            return true;
        } else {
            return false;
        }
    }

    public Team getWinner() {
        /**
         * Gives the caller the game's winner based on their debt
         *
         * @return the Team which has paid off their debt
         */
        if (isAWinner() == false) {
            return null;
        } else if (player1.getDebt() <= 0) { //If player1 paid off their debt, they are the winner
            return player1;
        } else { //Otherwise it's player2
            return player2;
        }
    }
}
