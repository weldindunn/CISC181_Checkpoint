public class PieceScoobyDoo extends Piece
                            implements Friendly {
    /**
     * PieceScoobyDoo
     * CISC 181-050L Spring 2021
     * University of Delaware
     *
     * This extension of piece represents
     * a Scooby Doo piece
     *
     * @author Weldin Dunn
     * @since Mar 23, 2021
     */

    private int numScoobySnacks;
    private int numBefriended;
    private boolean canMove;

    public PieceScoobyDoo(String symbol, String teamColor, int numBefriended,
                          int numScoobySnacks, boolean hidden, boolean canMove, boolean original, boolean inJail, boolean isTransformed) {

        super(symbol, teamColor, hidden, original, inJail, isTransformed);
        this.numScoobySnacks = numScoobySnacks;
        this.numBefriended = numBefriended;
        this.canMove = canMove;
    }

    public PieceScoobyDoo() {
        this("S","NON", 0,0,
                false,true,true, false, false);
    }

    public int getNumScoobySnacks() {
        /**
         * Gives the number of scooby snacks in storage
         *
         * @return an int representing the number of scooby snacks in storage
         */
        return numScoobySnacks;
    }

    public boolean isEntangled() {
        /**
         * Tells whether or not the piece can move
         *
         * @return a boolean representing whether or not the piece can move
         */
        return !canMove;
    }

    public void entangle() {
        /**
         * Makes it so the piece cannot move
         */
        this.canMove = false;
    }

    public int getNumBefriended(){
        /**
         * Gives the number of pieces befriended by this one
         *
         * @return an int representing the number of pieces befriended by this one
         */
        return numBefriended;
    }

    public void setNumBefriended (int numBefriended)    {
        /**
         * Sets the number of pieces befriended by this one based on a given number
         *
         * @param  numBefriended -- an int representing the number of pieces befriended by this one
         */
        this.numBefriended = numBefriended;
    }

    public boolean befriend(int fromRow, int fromCol, int toRow, int toCol){
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
            speak();
            System.out.println("Ree-hee-hee!");
            return true;
        } else {
            return false;
        }
    }

    public void speak() {
        /**
         * Prints the words "Scooby-Dooby-Doo!"
         */
        System.out.println("Scooby-Dooby-Doo!");
    }

    private void eatScoobySnack() {
        /**
         * "eats a scooby snack" by removing one from storage and allowing
         * the piece to move
         */
        if (this.numScoobySnacks > 0){
            this.numScoobySnacks -= 1;
            this.canMove = true;
        }
    }

    public void collectScoobySnacks (int numSnacks) {
        /**
         * Takes in a number of scooby snacks and adds them to the current
         * number of scooby snacks, and then eats a scooby snack by calling
         * the eatScoobySnack method
         *
         * @param  numSnacks -- an int representing the number of scooby snacks
         *                      to be eaten
         */
        if (numSnacks >= 0) {
            this.numScoobySnacks += numSnacks;
            if (isEntangled()) {
                eatScoobySnack();
            }
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
        if (canMove == true && Math.abs(firstSpaceRow - secondSpaceRow) == 1 && Math.abs(firstSpaceCol - secondSpaceCol) == 1) {
            return true;
        } else if (canMove == false && numScoobySnacks > 0) {
            eatScoobySnack();
            if (Math.abs(firstSpaceRow - secondSpaceRow) >= 2 && Math.abs(firstSpaceCol - secondSpaceCol) >= 2) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public PieceScoobyDoo spawn(){
        /**
         * Makes a copy of this piece
         *
         * @return a PieceScoobyDoo representing a copy of the original
         */
        PieceScoobyDoo scrappy = new PieceScoobyDoo(
                "SD".toLowerCase(),
                this.teamColor,
                this.numBefriended,
                this.numScoobySnacks,
                false,
                true,
                false,
                false,
                false);
        return scrappy;
    }
}