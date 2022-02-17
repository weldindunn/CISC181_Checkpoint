public abstract class Piece {
    /**
     * Piece
     * CISC 181-050L Spring 2021
     * University of Delaware
     *
     * This abstract superclass holds the
     * shared properties of each type of piece
     *
     * @author Weldin Dunn
     * @since Mar 23, 2021
     */

    protected String symbol;
    protected String teamColor;
    protected boolean hidden;
    protected boolean original;
    protected boolean inJail; //Whether or not this piece is in the jail square
    protected boolean isTransformed; //Whether or not the piece is in transformed mode
    protected boolean isCooling; //Whether or not the piece is cooling down from transformation
    protected boolean jailStatus; //Whether or not the piece is in jail
    protected int inJailTurn; //The turn this piece went to jail
    protected int transformTurn; //The turn this piece transformed

    public Piece (String symbol, String teamColor, boolean hidden, boolean original, boolean inJail, boolean isTransformed) {
        this.symbol = symbol;
        this.teamColor = teamColor;
        this.hidden = hidden;
        this.original = original;
        this.inJail = inJail;
        this.isTransformed = isTransformed;
        inJailTurn = -2;
        transformTurn = -1;
        isCooling = false;
        jailStatus = false;
    }

    public String getSymbol() {
        /**
         * Gives the symbol of the piece
         *
         * @return A string representing the symbol of the piece
         */
        return symbol;
    }

    public String getTeamColor() {
        /**
         * Gives the color of the piece's team
         *
         * @return A string representing the color of the piece's team
         */
        return teamColor;
    }

    public boolean isHidden() {
        /**
         * Gives the hidden status of the piece
         *
         * @return a boolean representing the hiding status of the piece
         */
        return hidden;
    }

    public boolean isOriginal() {
        /**
         * Gives status of whether or not the piece is the original
         *
         * @return a boolean representing whether or not the piece is the original
         */
        return original;
    }

    public boolean canSpawn() {
        /**
         * Tells whether or not the piece can spawn a new one or not
         *
         * @return a boolean representing whether or not the piece is the original
         */
        return original;
    }

    public void setSymbol(String symbol) {
        /**
         * Sets the symbol based on a given one
         *
         * @param  symbol -- a string representing the symbol of the piece
         */
        this.symbol = symbol;
    }

    public void setTeamColor(String teamColor) {
        /**
         * Sets the color of the team based on a given one
         *
         * @param  teamColor -- a string representing the team color of the piece
         */
        this.teamColor = teamColor;
    }

    public void setHidden(boolean hidden) {
        /**
         * Sets the piece to be hidden
         *
         * @param hidden -- a boolean representing whether or not the piece is hidden
         */
        this.hidden = hidden;
    }

    public boolean canTransform() {
        /**
         * Says whether or not the piece can transform
         *
         * @return a boolean value representing whether or not the piece can transform
         */

        if (isTransformed == false && isCooling == false) {
            return true;
        } else {
            return false;
        }
    }

    public void transform() {
        /**
         * Changes the character's transformation status to
         * true
         */
        isTransformed = true;
    }

    public void setCooling(boolean coolStatus) {
        /**
         * Sets the character as cooling so it cannot transform
         *
         * @param  coolStatus -- A boolean representing whether or not this piece is cooling
         */
        this.isCooling = coolStatus;
    }

    public boolean isInJail() {
        /**
         * Checks whether or not this piece is in jail
         *
         * @return a boolean representing the above (the value of jailStatus)
         */

        return jailStatus;
    }

    public void setJailStatus(boolean jailStatus) {
        /**
         * Sets the player in or out of jail
         *
         * @param  jailStatus -- A boolean representing whether or not the piece is being put in jail
         */

        this.jailStatus = jailStatus;
    }

    public int getJailTurn () {
        /**
         * Gives the turn on which this piece went to jail
         *
         * @return an int representing the turn on which this piece went to jail.
         */

        return inJailTurn;
    }

    public void setJailTurn (int jailTurn) {
        /**
         * Sets the inJailTurn to a given value
         *
         * @param  jailTurn -- The turn on which this piece was sent to jail
         */

        inJailTurn = jailTurn;
    }

    public boolean getTransformStatus() {
        /**
         * Says whether or not this piece is transformed
         *
         * @return a boolean representing whether or not this piece is transformed
         */

        return isTransformed;
    }

    public int getTransformTurn() {
        /**
         * Gives the turn on which this piece last transformed
         *
         * @return an int representing the above value (transformTurn)
         */

        return transformTurn;
    }

    public void setTransformTurn(int transformTurn) {
        /**
         * Sets the transform turn to the current turn
         *
         * @param  transformTurn -- An int representing the current turn
         */

        this.transformTurn = transformTurn;
    }

    public abstract void speak ();
    //Makes the piece print something

    public abstract boolean validPath (int firstSpaceRow, int firstSpaceCol, int secondSpaceRow, int secondSpaceCol);
    //Tells whether or not the piece can move in a given path

    public abstract Piece spawn ();
    //Spawns a clone of the piece

    @Override
    public String toString() {
        /**
         * Overrides the toString method to return the piece's team color and symbol
         *
         * @return a string representing the team color and symbol of the piece
         */

        //Presents if piece is in jail or cooling, for testing purposes
        if (isInJail()) {
            return teamColor + "J" + symbol;
        } else if (isCooling) {
            return teamColor + "C" + symbol;
        } else if (hidden) {
            return teamColor.charAt(0) + symbol + "hid";
        } else {
            return teamColor + " " + symbol;
        }
    }
}
