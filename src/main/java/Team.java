import java.util.*;

public class Team {
    /**
     * Team
     * CISC 181-050L Spring 2021
     * University of Delaware
     *
     * This class represents each player's team
     *
     * @author Weldin Dunn
     * @since Mar 30, 2021
     */

    private String teamName;
    private String teamColor;
    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    private int debt; //How much debt each team has

    public Team (String teamName, String teamColor, ArrayList<Piece> pieces, int debt) {
        this.teamName = teamName;
        this.teamColor = teamColor;
        this.pieces = pieces;
        this.debt = debt;
    }

    public String getTeamName() {
        /**
         * Gives the value of teamName (see below) to the caller
         *
         * @return teamName -- the string representing the team name
         */
        return teamName;
    }

    public String getTeamColor() {
        /**
         * Gives the value of teamColor (see below) to the caller
         *
         * @return teamColor -- the string representing the team color
         */
        return teamColor;
    }

    public ArrayList<Piece> getTeamPieces() {
        /**
         * Gives 'pieces' (see below) to the caller
         *
         * @return pieces -- the ArrayList of pieces on this team
         */
        return pieces;
    }

    public int getDebt () {
        /**
         * Gives the amount of team debt (see below) to the caller
         *
         * @return debt -- the amount of remaining debt the team holds
         */
        return debt;
    }

    public void removePieceFromTeam(Piece piece) {
        /**
         * Removes a given piece from the team
         *
         * @param piece -- a Piece to be removed from the player's pieces
         */
        pieces.remove(piece);
    }

    public void addPieceToTeam(Piece piece) {
        /**
         * Adds a given piece to the team and sets its color
         * as the team color
         *
         * @param piece -- a Piece to be added to the player's pieces
         */
        piece.setTeamColor(teamColor);
        pieces.add(piece);
    }

    public void subtractDebt(int payment) {
        /**
         * Called when a player takes another team's piece
         * through befriending or attacking and subtracts
         * a given payment from the total debt
         *
         * @param payment -- an int representing the payment made to the debt
         */
        debt -= payment;
    }

    @Override
    public String toString () {
        /**
         * Gives the team name, color, and pieces
         *
         * @return a String representing the team name, color, and pieces
         */
        String string;
        string = "Team " + teamName + " : " + teamColor;
        string += "\nPieces : ";
        for (Piece piece : pieces){
            string += piece.toString() + " ";
        }
        string += "\nDebt : " + debt; //Adds the debt to the team string
        return string;
    }
}
