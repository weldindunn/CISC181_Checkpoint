public class PieceBlueHen extends Piece
             implements Hostile, Friendly {
    /**
     * PieceBlueHen
     * CISC 181-050L Spring 2021
     * University of Delaware
     *
     * This extension of piece represents
     * a Blue Hen piece
     *
     * @author Weldin Dunn
     * @since Mar 23, 2021
     */

    private int numAttacked;
    private int numBefriended;
    private boolean flies;

    final public int MAX_NUM_ATTACKED = 3;

    public PieceBlueHen (String symbol, String teamColor,int numAttacked, int numBefriended, boolean hidden, boolean original, boolean inJail, boolean isTransformed){
        super(symbol, teamColor, hidden, original, inJail, isTransformed);
        this.numBefriended = numBefriended;
        this.numAttacked = numAttacked;
        updateFly();
    }

    public PieceBlueHen ()  {
        this("BH","NON", 0,0,false,true, false, false);
    }

    public int getNumAttacked()    {
        /**
         * Gives the value of numAttacked (see below) to the caller
         *
         * @return numAttacked -- the integer representing the number of attacks made by this piece
         */
        return this.numAttacked;
    }

    public boolean canFly()    {
        /**
         * Gives the value of flies (see below) to the caller
         *
         * @return flies -- the boolean representing whether or not the piece can fly
         */
        return this.flies;
    }

    public void setNumAttacked(int numAttacked)    {
        /**
         * Gives the number of piece this piece has attacked
         *
         * @param numAttacked -- an int representing the number of pieces this one has attacked
         */
        this.numAttacked = numAttacked;
        updateFly();
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
         * Prints the words "Go UD!"
         */
        System.out.println("Go UD!");
    }

    private void updateFly() {
        /**
         * Says whether or not this piece can fly based on the number of pieces
         * it has attacked and befriended
         */
        if (this.numAttacked < MAX_NUM_ATTACKED){
            this.flies = true;
        }
        else if (this.numBefriended < 1){
            this.flies = true;
        }
        else {
            this.flies = false;
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
        if (flies == true) {
            return true;
        } else {
            //can move side to side but not up or down
            if (Math.abs(firstSpaceCol - secondSpaceCol) > 0 && (firstSpaceRow - secondSpaceRow) == 0) {
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
            System.out.println("GET OUTTA HEEEERE!");
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

        if (validPath(fromRow, fromCol, toRow, toCol)) {
            numBefriended++;
            System.out.println("Welcome to the club");
            return true;
        } else {
            return false;
        }
    }

    public PieceBlueHen spawn() {
        /**
         * Makes a copy of this piece
         *
         * @return a PieceBlueHen representing a copy of the original
         */
        PieceBlueHen copyHen = new PieceBlueHen("BH".toLowerCase(),
                this.teamColor,
                this.numAttacked,
                this.numBefriended,
                false,
                false,
                false,
                false);
        return copyHen;
    }
}