public class PieceOP extends Piece
        implements Hostile, Friendly {
    /**
     * PieceOP
     * CISC 181-050L Spring 2021
     * University of Delaware
     *
     * This extension of piece represents an Optimus Prime
     * piece which can attack, befriend, and transform between
     * truck and bot form
     *
     * @author Weldin Dunn, Zachary England, Alex Irwin
     * @since May 4, 2021
     */

    private int numAttacked;
    private int numBefriended;

    public PieceOP (String symbol, String teamColor,int numAttacked, int numBefriended, boolean hidden, boolean original, boolean inJail, boolean isTransformed){
        super(symbol, teamColor, hidden, original, inJail, isTransformed);
        this.numBefriended = numBefriended;
        this.numAttacked = numAttacked;

    }

    public PieceOP ()  {
        this("OP", "NON", 0, 0, false, true, false, false);
    }

    public int getNumAttacked()    {
        /**
         * Gives the value of numAttacked (see below) to the caller
         *
         * @return numAttacked -- the integer representing the number of attacks made by this piece
         */
        return this.numAttacked;
    }

    public void setNumAttacked(int numAttacked)    {
        /**
         * Gives the number of piece this piece has attacked
         *
         * @param numAttacked -- an int representing the number of pieces this one has attacked
         */
        this.numAttacked = numAttacked;
    }

    public void setOriginal(boolean original) {
        /**
         * Makes this piece the original one
         *
         * @param original -- a boolean representing whether or not the piece is the original
         */
        this.original = original;
    }

    public void speak() {
        /**
         * Prints the phrase "I AM OPTIMUS PRIME"
         */
        System.out.println("I AM OPTIMUS PRIME");
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
        if (validPath(fromRow, fromCol, toRow, toCol) && isTransformed) {
            numAttacked++;
            System.out.println("One shall stand, one shall fall");
            return true;
        } else {
            return false;
        }
    }

    public int getNumBefriended(){
        /**
         * Gives the number of pieces befriended by this one
         *
         * @return an int representing the number of pieces befriended by this one
         */
        return numBefriended;
    }

    public void setNumBefriended(int numBefriended) {
        /**
         * Sets the number of pieces befriended by this one based on a given number
         *
         * @param  numBefriended -- an int representing the number of pieces befriended by this one
         */
        this.numBefriended = numBefriended;
    }

    public boolean befriend(int fromRow, int fromCol, int toRow, int toCol) {
        /**
         * Takes a current space on the board and a space to be moved to
         * and returns true if its path is valid, while increasing the number
         * of friends made
         *
         * @param  fromRow -- an int representing the first space's row
         * @param  fromCol -- an int representing the first space's column
         * @param  toRow -- an int representing the second space's row
         * @param  toCol -- an int representing the second space's column
         *
         * @return a boolean representing whether or not a piece was befriended
         */

        if (validPath(fromRow, fromCol, toRow, toCol) && isTransformed == false) {
            numBefriended++;
            System.out.println("More than meets the eye");
            return true;
        } else {
            return false;
        }
    }

    public boolean validPath(int firstSpaceRow, int firstSpaceCol,
                             int secondSpaceRow, int secondSpaceCol) {
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
        if (isTransformed == true) {
            //Can move one space forward/backward or side to side
            if ((Math.abs(firstSpaceCol - secondSpaceCol) == 1 && (firstSpaceRow - secondSpaceRow) == 0) ||
                    ((firstSpaceCol - secondSpaceCol) == 0 && Math.abs(firstSpaceRow - secondSpaceRow) == 1)) {
                return true;
            } else {
                return false;
            }
        } else {
            //Can move as many spaces as desired either forward/backward or side to side
            if ((Math.abs(firstSpaceCol - secondSpaceCol) > 0 && (firstSpaceRow - secondSpaceRow) == 0) ||
                    ((firstSpaceCol - secondSpaceCol) == 0 && Math.abs(firstSpaceRow - secondSpaceRow) > 0)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public PieceOP spawn() {
        /**
         * Makes a copy of this piece
         *
         * @return a PieceOP representing a copy of the original
         */
        PieceOP copyOP = new PieceOP("OP".toLowerCase(),
                this.teamColor,
                this.numAttacked,
                this.numBefriended,
                false,
                false,
                false,
                false);
        System.out.println("Autobots, roll out!");
        return copyOP;
    }
}
