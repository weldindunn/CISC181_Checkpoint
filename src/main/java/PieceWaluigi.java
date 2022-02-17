public class PieceWaluigi extends Piece
        implements Hostile, Friendly {
    /**
     * PieceWaluigi
     * CISC 181-050L Spring 2021
     * University of Delaware
     *
     * This extension of piece represents a Waluigi piece which
     * can attack, befriend, and transform between regular and mecha-form
     *
     * @author Weldin Dunn, Zachary England, Alex Irwin
     * @since May 4, 2021
     */

    private int numAttacked;
    private int numBefriended;
    private int tacos;

    public PieceWaluigi (String symbol, String teamColor,int numAttacked, int numBefriended, boolean hidden, boolean original, boolean inJail, boolean isTransformed, int tacos){
        super(symbol, teamColor, hidden, original, inJail, isTransformed);
        this.numBefriended = numBefriended;
        this.numAttacked = numAttacked;
        this.tacos = tacos;

    }

    public PieceWaluigi ()  {
        this("WA", "NON", 0, 0, false, true, false, false, 0);
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
         * Prints the phrase "WAAAAAAAA"
         */
        System.out.println("WAAAAAAAA");
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
            System.out.println("IMA THA WINNNA!");
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

    public int getTacos() {
        /**
         * Gives the number of tacos Waluigi has
         *
         * @return an int representing the above (tacos)
         */

        return tacos;
    }

    public void addTacos (int tacos) {
        /**
         * Adds a number of tacos to his taco collection
         *
         * @param  tacos -- an int representing the tacos to be added
         */

        this.tacos+=tacos;
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
            System.out.println("YEAAAAH");
            return true;
        } else {
            return false;
        }
    }

    public boolean validPath(int firstSpaceRow, int firstSpaceCol,
                             int secondSpaceRow, int secondSpaceCol) {
        /**
         * Takes a current space on the board and a space to be moved to
         * and says whether or not that move can be made. He can move in
         * the L pattern, like a Knight in chess
         *
         * @param  firstSpaceRow -- an int representing the first space's row
         * @param  firstSpaceCol -- an int representing the first space's column
         * @param  secondSpaceRow -- an int representing the second space's row
         * @param  secondSpaceCol -- an int representing the second space's column
         *
         * @return a boolean representing whether or not the path given is valid
         */
        if (((Math.abs(firstSpaceCol - secondSpaceCol) == 2) && (Math.abs(firstSpaceRow - secondSpaceRow) == 1)) ||
                ((Math.abs(firstSpaceCol - secondSpaceCol) == 1) && (Math.abs(firstSpaceRow - secondSpaceRow) == 2))) {
            return true;
        } else {
            return false;
        }
    }

    public PieceWaluigi spawn() {
        /**
         * Makes a copy of this piece
         *
         * @return a PieceWaluigi representing a copy of the original
         */
        PieceWaluigi copyWA = new PieceWaluigi("WA".toLowerCase(),
                this.teamColor,
                this.numAttacked,
                this.numBefriended,
                false,
                false,
                false,
                false,
                0);
        System.out.println("WALUIGI TIIIIME!");
        return copyWA;
    }
}
