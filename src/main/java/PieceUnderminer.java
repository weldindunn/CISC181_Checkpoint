public class PieceUnderminer extends Piece
                             implements Hostile {
    /**
     * PieceUnderminer
     * CISC 181-050L Spring 2021
     * University of Delaware
     *
     * This extension of piece represents
     * an Underminer piece (woo woo)
     *
     * @author Weldin Dunn
     * @since Mar 23, 2021
     */

    private int numAttacked;
    private boolean underGround;
    private boolean digs;

    final public int MAX_NUM_ATTACKS = 4;

    public PieceUnderminer (String symbol, String teamColor, int numAttacked, boolean underGround, boolean hidden, boolean original, boolean inJail, boolean isTransformed){
        super(symbol, teamColor, hidden, original, inJail, isTransformed);
        this.numAttacked = numAttacked;
        this.underGround = underGround;
        updateDigs();
    }

    public PieceUnderminer () {
        this("M", "NON", 0, false, false, true, false, false);
    }

    public int getNumAttacked() {
        /**
         * Gives the value of numAttacked (see below) to the caller
         *
         * @return numAttacked -- the integer representing the number of attacks made by this piece
         */
        return numAttacked;
    }

    public boolean isUnderGround() {
        /**
         * VVVVVV
         *
         * @return a boolean representing whether or not the piece is underground
         */
        return underGround;
    }

    public boolean canDig () {
        /**
         * VVVVVV
         *
         * @return a boolean representing whether or not the piece can dig
         */
        return digs;
    }

    public void setOriginal(boolean original) {
        /**
         * Makes this piece the original one
         *
         * @param original -- a boolean representing whether or not the piece is the original
         */
        this.original = original;
    }

    public void setNumAttacked(int numAttacked) {
        /**
         * Gives the number of piece this piece has attacked
         *
         * @param numAttacked -- an int representing the number of pieces this one has attacked
         */
        this.numAttacked = numAttacked;
        updateDigs();
    }

    public void setUnderGround (boolean underGround) {
        /**
         * Makes the piece go underground
         *
         * @param  underGround -- a boolean representing whether or not the piece is underground
         */
        this.underGround = underGround;
    }

    private void updateDigs () {
        /**
         * Updates whether or not the piece can dig based on the number of attacks
         * it has made
         */
        if (numAttacked < MAX_NUM_ATTACKS) {
            digs = true;
        } else {
            digs = false;
        }
    }

    public void speak () {
        /**
         * Prints the words "Behold the Underminer!"
         */
        System.out.println("Behold the Underminer!");
    }

    public boolean validPath (int firstSpaceRow, int firstSpaceCol, int secondSpaceRow, int secondSpaceCol) {
        /**
         * Takes a current space on the board and a space to be moved to
         * and says whether or not that move can be made
         *
         * @param  firstSpaceRow -- an int representing the first space's row
         * @param  firstSpaceCol -- an int representing the first space's column
         * @param  secondSpaceRow -- an int representing the second space's row
         * @param  secondSpaceCol -- an int representing the second space's column
         *
         * @return a boolean representing whether or not the path given is valid
         */
        if (underGround) {
            //can move anywhere
            return false;
        } else if (digs) {
            return true;
        } else {
            //can move one space up or down, but not side to side
            if (Math.abs(firstSpaceRow - secondSpaceRow) == 1 && (firstSpaceCol - secondSpaceCol) == 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean attack(int fromRow, int fromCol, int toRow, int toCol){
        /**
         * Takes a current space on the board and a space to be moved to
         * and returns true if its path is valid, while increasing the number
         * of attacks made
         *
         * @param  fromRow -- an int representing the first space's row
         * @param  fromCol -- an int representing the first space's column
         * @param  toRow -- an int representing the second space's row
         * @param  toCol -- an int representing the second space's column
         *
         * @return a boolean representing whether or not an attack was made
         */
        if (validPath(fromRow, fromCol, toRow, toCol)) {
            numAttacked++;
            speak();
            System.out.println("GET OUTTA HEEEERE!");
            return true;
        } else {
            return false;
        }
    }

    public PieceUnderminer spawn () {
        /**
         * Makes a copy of this piece
         *
         * @return a PieceUnderminer representing a copy of the original
         */
        PieceUnderminer copyMiner = new PieceUnderminer("um", this.teamColor,
                                                        this.numAttacked, this.underGround,
                                                        false, false, false, false);
        return copyMiner;
    }
}
