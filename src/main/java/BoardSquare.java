public class BoardSquare {
    /**
     * BoardSquare
     * CISC 181-050L Spring 2021
     * University of Delaware
     *
     * This class represents each square on the game board
     *
     * @author Weldin Dunn
     * @since Mar 24, 2021
     */

    private boolean isSpaceEmpty;
    private Piece piece;
    private String color;
    private boolean isJail; //Whether or not this square is a jail square

    public BoardSquare (String color) {
        this.color = color;
        isSpaceEmpty = true;
        isJail = false;
    }

    public Piece getPiece () {
        /**
         * Gives the piece on the square
         *
         * @return the piece on the square
         */
        return piece;
    }

    public boolean isEmpty () {
        /**
         * Tells whether or not the board square has a piece on it
         *
         * @return the boolean representing whether or not the board
         * square is empy
         */
        return isSpaceEmpty;
    }

    public String getSquareColor () {
        /**
         * Gives the color of the board square
         *
         * @return a string representing the color of the board square
         */
        return color;
    }

    public void setSquareColor (String color) {
        /**
         * Sets the board square color to a given value
         *
         * @param  color -- A string representing the square's color
         */

        this.color = color;
    }

    public void setPiece (Piece piece) {
        /**
         * Sets a given piece on the board square and changes the
         * the square from empty to occupied
         *
         * @param  piece -- A Piece representing the piece to be
         *                  put onto the board square
         */
        this.piece = piece;
        isSpaceEmpty = false;
    }

    public Piece removePiece () {
        /**
         * Removes the piece from the board square and gives the piece
         * removed
         *
         * @return the piece on the square that was removed
         */
        isSpaceEmpty = true;
        return piece;
    }

    public boolean getJail() {
        /**
         * Checks if the current board square is a jail space
         *
         * @return a boolean indicating the above (the value of isJail)
         */

        return isJail;
    }

    public void setJailSquare() {
        /**
         * Turns this space into a jail square
         */

        isJail = true;
    }

    @Override
    public String toString () {
        /**
         * Overrides the toString method by giving either the piece
         * and its color or a blank line
         *
         * @return a string representing the piece (or lack thereof)
         * on the board square
         */
        if (isSpaceEmpty) {
            if (isJail) { //If it's a jail square, present it as such
                return "-JAIL-";
            } else {
                return "------";
            }
        } else {
            return piece.toString();
        }
    }

}