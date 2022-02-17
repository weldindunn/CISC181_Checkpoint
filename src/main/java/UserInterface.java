import java.util.*;

public class UserInterface {
    /**
     * UserInterface
     * CISC 181-050L Spring 2021
     * University of Delaware
     *
     * This class
     *
     * @author Weldin Dunn
     * @since Apr 17, 2021
     */

    private GameS21 gameS21;
    private char lastAction = 'R'; //The last action this piece performed, used for transformation

    public UserInterface (GameS21 gameS21) {
        this.gameS21 = gameS21;
    }

    private char getValidActionType() {
        System.out.println("Enter a character representing a move option:");
        System.out.println("M (move): Moves piece from one square to another");
        System.out.println("S (spawn): Selects piece on one square and makes a copy\nof it on another selected square");
        System.out.println("A (attack): Moves piece from one square to another by\nattacking the piece on the destination square.\nThe attacked piece is removed from the game.");
        System.out.println("B (befriended): Selects a piece from the opponent's team and\nadds it to the 'befriender's' team.");

        //Presents how transformation works
        System.out.println("T (transform): Transforms a piece from its normal state to its \nMECHA state. Can only be done every other turn!");

        boolean entered = false;
        char move = ' ';
        Scanner scanner = new Scanner(System.in);
        while (!entered) {
            move = scanner.next().charAt(0);
            if (move == 'M' || move == 'S' || move == 'A' || move == 'B' || move == 'T') {
                entered = true;
            }
        }
        return move;
    }

    private void nextPlayersAction() {

        for (int i = 0; i < gameS21.getCurrentTeam().getTeamPieces().size(); i++) {

            //Checks all pieces to see if they should still be in jail
            if (gameS21.getNumTurn() - 3 >= gameS21.getCurrentTeam().getTeamPieces().get(i).getJailTurn()) {
                gameS21.getCurrentTeam().getTeamPieces().get(i).setJailStatus(false);
            }

            //Checks all pieces to see if they should still be cooling
            if (gameS21.getNumTurn() - 2 >= gameS21.getCurrentTeam().getTeamPieces().get(i).getTransformTurn()) {
                gameS21.getCurrentTeam().getTeamPieces().get(i).setCooling(false);
            }
        }

        boolean takingTurn = true;
        while (takingTurn) {

            //Takes the action type and fromSquare from the user
            char actionType = getValidActionType();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the indexes of the tile FROM which your " + actionType);
            System.out.println("will happen in the format \"row:column\"");
            System.out.println("(Does not apply to Transformation, entry will not matter)");
            String fromSquare = scanner.next();
            int fromSquareRow = fromSquare.charAt(0) - 48;
            int fromSquareCol = fromSquare.charAt(2) - 48;

            //Takes the toSquare from the user
            System.out.println("Enter the indexes of the tile TO which your " + actionType);
            System.out.println("will happen in the format \"row:column\"");
            System.out.println("(Does not apply to Transformation, entry will not matter)");
            String toSquare = scanner.next();
            int toSquareRow = toSquare.charAt(0) - 48;
            int toSquareCol = toSquare.charAt(2) - 48;

            //Sets the action to the given type
            Action action;
            if (actionType == 'M') {
                action = new ActionMove(gameS21, fromSquareRow, fromSquareCol, toSquareRow, toSquareCol);
                System.out.println(gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().toString() + " tried to move to " + toSquareRow + ":" + toSquareCol);
            } else if (actionType == 'S') {
                System.out.println(gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().toString() + " tried to spawn a clone on " + toSquareRow + ":" + toSquareCol + "!");
                action = new ActionSpawn(gameS21, fromSquareRow, fromSquareCol, toSquareRow, toSquareCol);
            } else if (actionType == 'A') {
                System.out.println(gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().toString() + " tried to attack " + gameS21.getGameBoard().getSquares()[toSquareRow][toSquareCol].getPiece().toString() + " on " + toSquareRow + ":" + toSquareCol + "!");
                action = new ActionAttack(gameS21, fromSquareRow, fromSquareCol, toSquareRow, toSquareCol);
            } else if (actionType == 'B') {
                System.out.println(gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().toString() + " tried to befriend " + gameS21.getGameBoard().getSquares()[toSquareRow][toSquareCol].getPiece().toString() + " on " + toSquareRow + ":" + toSquareCol + "!");
                action = new ActionBefriend(gameS21, fromSquareRow, fromSquareCol, toSquareRow, toSquareCol);
            } else if (actionType == 'T') {

                //Transformation action!
                System.out.println(gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().toString() + " tried to transform!");
                action = new ActionTransform(gameS21, fromSquareRow, fromSquareCol, toSquareRow, toSquareCol);
            } else {
                System.out.println("Please enter a valid move type, they are case sensitive!");
                continue;
            }

            //Says whether or not the move is valid, for TESTING PURPOSES
            /*
            System.out.println("\n");
            System.out.println("fromSquare valid: " + action.fromSquareValid());
            if (actionType == 'M' || actionType == 'S') {
                System.out.println("toSquare valid: " + action.toSquareValid(true));
            } else {
                System.out.println("toSquare valid: " + action.toSquareValid(false));
            }
            System.out.println("Action Path valid: " + action.validActionPath() + "\n");
            */

            //System.out.println(Math.abs(fromSquareRow - toSquareRow) + ", " + Math.abs(fromSquareCol - toSquareCol));

            //If the action is valid, perform it, otherwise start over.
            if (action.validAction()) {
                action.performAction();
                if (actionType != 'T') {
                    takingTurn = false;

                    //If the last action was transformation, set it as cooling
                    if (lastAction == 'T') {
                        gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().setCooling(true);
                    }
                    System.out.println("and it worked!");

                    //If the piece lands on a jail square, say so
                    if (gameS21.getGameBoard().getSquares()[toSquareRow][toSquareCol].getJail()) {
                        System.out.println("But that piece landed on a jail square!");
                    }
                } else {
                    System.out.println("and it worked!");
                    System.out.println("Make your next move!");

                    //If the piece transformed, update its 'transform turn'
                    gameS21.getGameBoard().getSquares()[fromSquareRow][fromSquareCol].getPiece().setTransformTurn(gameS21.getNumTurn());
                }

                lastAction = actionType;
            } else {
                System.out.println("and it failed...");
                System.out.println("Please make sure you correctly formatted your input!");
            }
        }
    }

    public void playOurGame () {
        boolean running = true;
        while(running) {
            nextPlayersAction();
            System.out.println(gameS21);

            if (!gameS21.isGameEnded()) {
                System.out.println("Next turn!");
            } else {
                System.out.println("The game has ended. ");
                if (gameS21.isAWinner()) {
                    System.out.println("" + gameS21.getWinner().getTeamName() + " won!");
                }
                running = false;
            }
        }
    }

    public static void main(String[] args){
        // Create 3 pieces for Team A
        Piece scoobyA = new PieceScoobyDoo("SD", "Red",0,
                0,false,true,true, false, false);
        Piece minerA = new PieceUnderminer("UM", "Red",0,
                false,false,true,false,false);
        Piece blueHenA = new PieceBlueHen("BH", "Red",0,
                0,false, minerA.original,false,false);

        //Creates waluigi and OP
        Piece waluigiA = new PieceWaluigi("WA", "Red", 0,
                0, false, true, false, false, 0);
        Piece primeA = new PieceOP("OP", "Red", 0,
                0, false, true, false, false);

        // Create an array list to hold Team A's pieces
        ArrayList<Piece> piecesTeamA = new ArrayList<>();
        piecesTeamA.add(scoobyA);
        piecesTeamA.add(minerA);
        piecesTeamA.add(blueHenA);
        piecesTeamA.add(waluigiA); //Adds Waluigi
        piecesTeamA.add(primeA); //Adds OP

        // Create 3 pieces for Team B
        Piece scoobyB = new PieceScoobyDoo("SD", "Blu",0,
                0,false,true,true, false, false);
        Piece minerB = new PieceUnderminer("UM", "Blu",0,
                false,false,true, false, false);
        Piece blueHenB = new PieceBlueHen("BH", "Blu",0,
                0,false, minerA.original, false, false);

        //Creates waluigi and OP
        Piece waluigiB = new PieceWaluigi("WA", "Blu", 0,
                0, false, true, false, false, 0);
        Piece primeB = new PieceOP("OP", "Blu", 0,
                0, false, true, false, false);

        // Create an array list to hold Team B's pieces
        ArrayList<Piece> piecesTeamB = new ArrayList<>();
        piecesTeamB.add(scoobyB);
        piecesTeamB.add(minerB);
        piecesTeamB.add(blueHenB);
        piecesTeamB.add(waluigiB); //Adds waluigi
        piecesTeamB.add(primeB); //Adds OP

        // Create TeamA and TeamB objects and pass them the array lists of pieces
        //Each team begins with debt
        Team teamA = new Team("Freshmen", "Red",piecesTeamA, 600);
        Team teamB = new Team("Sophomores", "Blu",piecesTeamB, 600);

        // Create an instance of the game
        GameS21 ourGame = new GameS21(10, 10, teamA, teamB);
        System.out.println(ourGame.toString());

        // Create PlayGame object and play the game
        UserInterface ui = new UserInterface(ourGame);
        ui.playOurGame();
    }
}
