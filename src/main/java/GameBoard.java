public class GameBoard {
    /**
     * GameBoard
     * CISC 181-050L Spring 2021
     * University of Delaware
     *
     * This class represents the game board itself
     *
     * @author Weldin Dunn
     * @since Mar 24, 2021
     */

    private int numRows;
    private int numCols;
    private BoardSquare[][] squares;

    public GameBoard (int numRows, int numCols) {
        squares = new BoardSquare[numRows][numCols];
        this.numRows = numRows;
        this.numCols = numCols;
        setUpEmptyBoard();
    }

    public int getNumRows () {
        /**
         * Gets the number of rows on the game board
         *
         * @return an int representing the number of rows on the game board
         */
        return numRows;
    }

    public int getNumColumns () {
        /**
         * Gets the number of columns on the game board
         *
         * @return an int representing the number of columns on the game board
         */
        return numCols;
    }

    public BoardSquare[][] getSquares () {
        /**
         * Gets the two dimensional array of board squares
         *
         * @return a two dimensional array of BoardSquares representing the game board
         */
        return squares;
    }

    public boolean inBounds (int rowIndex, int colIndex) {
        /**
         * Tells whether or not a given address is in bounds on the game board
         *
         * @param  rowIndex -- an int representing the index of the row
         * @param  colIndex -- an int representing the index of the column
         *
         * @return a boolean representing whether or not the address is in bounds
         */
        if (rowIndex < numRows && colIndex < numCols && rowIndex > -1 && colIndex > -1) {
            return true;
        } else {
            return false;
        }
    }

    public void setUpEmptyBoard () {
        /**
         * Creates the BoardSquares at each address in squares
         */
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j+=2) {
                squares[i][j] = new BoardSquare ("Red");
            }
            for (int j = 1; j < numCols; j+=2) {
                squares[i][j] = new BoardSquare ("Yellow");
            }

            //Square is a prison square
            squares[i][0].setSquareColor("Gray");
        }

        //Sets a square
        findRandomEmptySpace().setJailSquare();
    }

    public BoardSquare findRandomEmptySpace () {
        /**
         * Finds a random empty board square
         *
         * @return a BoardSquare representing a randomly selected empty board square
         */
        int rand1 = (int)(numRows * Math.random());
        int rand2 = (int)(numRows * Math.random());
        BoardSquare square = squares[rand1][rand2];

        while (squares[rand1][rand2].isEmpty() == false) {
            System.out.println("" + rand1);
            rand1 = (int)(numRows * Math.random());
            rand2 = (int)(numCols * Math.random());
            if (squares[rand1][rand2].isEmpty() == true) {
                break;
            }
        }
        return squares[rand1][rand2];
    }

    @Override
    /**
     * Overrides the toString method in order to return a visual representation
     * of the board
     *
     * @return a string representing the board itself with all pieces
     */
    public String toString(){
        StringBuilder boardString = new StringBuilder();
        boardString.append("Col :     ");
        for(int col = 0; col < squares[0].length; col++){
            boardString.append("" + col + "       ");
        }
        boardString.append("\n");
        for(int row = 0; row < squares.length; row++){
            boardString.append("Row : " + row + "   ");
            for(int col = 0; col < squares[row].length; col++){
                boardString.append(squares[row][col].toString() + "  ");
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }
}
