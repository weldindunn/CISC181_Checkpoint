import org.junit.Test;

import static org.junit.Assert.*;
import java.util.*;

public class ActionMoveTest {

    private GameS21 ourGame;
    private  Piece scoobyA;
    private Piece blueHenA;
    private Piece minerA;
    private  Piece scoobyB;
    private Piece blueHenB;
    private Piece minerB;
    int numRows = 4;
    int numCols = 4;

    private void createOurGame() {
        // Create 3 pieces for Team A
        scoobyA = new PieceScoobyDoo("SD","Red",0,
                0,false,true,true);
        minerA = new PieceUnderminer("UM","Red",1,
                false,false,true);
        blueHenA = new PieceBlueHen("BH","Red",0,
                0,false, minerA.original);
        // Create an array list to hold Team A's pieces
        ArrayList<Piece> piecesTeamA = new ArrayList<>();
        piecesTeamA.add(scoobyA);
        piecesTeamA.add(minerA);
        piecesTeamA.add(blueHenA);

        // Create 3 pieces for Team B
        scoobyB = new PieceScoobyDoo("SD","Blu",0,
                0,false,true,true);
        minerB = new PieceUnderminer("UM","Blu",1,
                false,false,true);
        blueHenB = new PieceBlueHen("BH","Blu",0,
                0,false, minerA.original);

        // Create an array list to hold Team B's pieces
        ArrayList<Piece> piecesTeamB = new ArrayList<>();
        piecesTeamB.add(scoobyB);
        piecesTeamB.add(minerB);
        piecesTeamB.add(blueHenB);

        // Create TeamA and TeamB objects and pass them the array lists of pieces
        Team teamA = new Team("Freshmen", "Red",piecesTeamA, 1000);
        Team teamB = new Team("Sophomores",  "Blu",piecesTeamB, 1000);


        // Create a game object

        ourGame = new GameS21(numRows, numCols, teamA, teamB);
        // remove all the pieces and put in specific locations for testing purposes
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (!ourGame.getGameBoard().getSquares()[i][j].isEmpty()) {
                    ourGame.getGameBoard().getSquares()[i][j].removePiece();
                }
            }
        }
    }

    @Test
    public void testActionMove() throws Exception{
        System.out.println("Testing ActionMove");

        createOurGame();
        ourGame.getGameBoard().getSquares()[0][0].setPiece(scoobyA);
        ourGame.getGameBoard().getSquares()[0][3].setPiece(scoobyB);
        ourGame.getGameBoard().getSquares()[3][0].setPiece(blueHenA);
        ourGame.getGameBoard().getSquares()[3][3].setPiece(blueHenB);
        System.out.println(ourGame.getGameBoard().toString());

        // Now that game is set up - lets test actions
        ActionMove move1 = new ActionMove(ourGame, 0, 1, 3, 3);
        assertFalse(move1.fromSquareValid());   // invalid because no Piece on this space
        assertFalse(move1.toSquareValid(true));  // invalid because space is not empty

        move1 = new ActionMove(ourGame, 0, 5, 9, 0);
        assertFalse(move1.fromSquareValid());   // invalid because out of bounds
        assertFalse(move1.toSquareValid(true));  // invalid because out of bounds

        move1 = new ActionMove(ourGame, 3, 0, 0, 1);
        assertTrue(move1.fromSquareValid());   // valid because Team A Piece on this space
        assertTrue(move1.toSquareValid(true));  // valid because space is empty and inbounds
        assertTrue(move1.validActionPath());  // valid path for a PieceBlueHen
        assertTrue((move1.validAction()));  // valid because all of the conditions above are true

        ActionMove move2 = new ActionMove(ourGame, 0, 3, 1, 2);
        assertFalse(move2.fromSquareValid());   // invalid because  Team B Piece on this space and its Team A's turn
        assertTrue(move2.toSquareValid(true));  // valid because space is empty and inbounds
        assertTrue(move2.validActionPath());  // valid path for a PieceScoobyDoo
        assertFalse((move2.validAction()));  // invalid because at least one of the conditions above is false

        move2 = new ActionMove(ourGame, 0, 0, -1, -1);
        assertTrue(move2.fromSquareValid());   // valid because Team A Piece on this space
        assertFalse(move2.toSquareValid(true));  // invalid because not in bounds
        assertTrue(move2.validActionPath());  // valid path for a PieceScoobyDoo
        assertFalse((move2.validAction()));  // invalid because at least one of the conditions above is false

        move2 = new ActionMove(ourGame, 3, 0, 0, 0);
        assertTrue(move2.fromSquareValid());   // valid because Team A Piece on this space
        assertFalse(move2.toSquareValid(true));  // invalid because space is not empty
        assertTrue(move2.validActionPath());  // valid path for a PieceBlueHen
        assertFalse((move2.validAction()));  // invalid because at least one of the conditions above is false

        move2 = new ActionMove(ourGame, 3, 0, 0, 3);
        assertTrue(move2.fromSquareValid());   // valid because Team A Piece on this space
        assertFalse(move2.toSquareValid(true));  // invalid because space is not empty
        assertTrue(move2.validActionPath());  // valid path for a PieceBlueHen
        assertFalse((move2.validAction()));  // invalid because at least one of the conditions above is false

        move2 = new ActionMove(ourGame, 0, 0, 1, 1);
        assertTrue(move2.fromSquareValid());   // valid because  Team A Piece on this space
        assertTrue(move2.toSquareValid(true));  // valid because space is empty and inbounds
        assertTrue(move2.validActionPath());  // valid path for a PieceScoobyDoo
        assertTrue((move2.validAction()));  // valid because all of the conditions above are true

        System.out.println("Action Move Complete");
    }

    @Test
    public void testActionAttack() throws Exception{

        System.out.println("Testing ActionAttack");
        createOurGame();
        ourGame.getGameBoard().getSquares()[0][0].setPiece(scoobyA);
        ourGame.getGameBoard().getSquares()[0][3].setPiece(scoobyB);
        ourGame.getGameBoard().getSquares()[2][1].setPiece(blueHenA);
        ourGame.getGameBoard().getSquares()[2][2].setPiece(blueHenB);
        ourGame.getGameBoard().getSquares()[1][1].setPiece(minerB);
        System.out.println(ourGame.getGameBoard().toString());

        // Testing ActionAttack
        ActionAttack attack1 = new ActionAttack(ourGame, 0, 1, 2, 3);
        assertFalse(attack1.fromSquareValid());   // invalid because no Piece on this space
        assertFalse(attack1.toSquareValid(false));  // invalid  space is  empty

        attack1 = new ActionAttack(ourGame, 0, 5, 9, 0);
        assertFalse(attack1.fromSquareValid());   // invalid because out of bounds
        assertFalse(attack1.toSquareValid(false));  // invalid because out of bounds

        attack1 = new ActionAttack(ourGame, 2, 1, 2, 2);
        assertTrue(attack1.fromSquareValid());   // valid because Team A Piece on this space
        assertTrue(attack1.toSquareValid(false));  // valid because space has Team B's piece
        assertTrue(attack1.validActionPath());  // valid path for a PieceBlueHen
        assertTrue((attack1.validAction()));  // valid because all of the conditions above are true and PieceBlueHen can attack

        ActionAttack attack2 = new ActionAttack(ourGame, 2, 2, 0, 3);
        assertFalse(attack2.fromSquareValid());   // invalid because Team B Piece on this space and its Team A's turn
        assertTrue(attack2.toSquareValid(false));  // valid because space has Team B's piece and its Team A's turn
        assertTrue(attack2.validActionPath());  // valid path for a PieceBlueHen
        assertFalse((attack2.validAction()));  // invalid because one of the above is invalid

        attack2 = new ActionAttack(ourGame, 2, 1, 0, 0);
        assertTrue(attack2.fromSquareValid());  // valid because Team A Piece on this space
        assertFalse(attack2.toSquareValid(false));  // invalid because space has Team A's piece and its Team A's turn
        assertTrue(attack2.validActionPath());  // valid path for a PieceBlueHen
        assertFalse((attack2.validAction()));  // invalid because one of the above is invalid

        attack2 = new ActionAttack(ourGame, 0, 0, 1, 1);
        assertTrue(attack2.fromSquareValid());   // valid because  Team A Piece on this space
        assertTrue(attack2.toSquareValid(false));  // valid because space has Team B's piece and its Team A's turn
        assertTrue(attack2.validActionPath());  // valid path for a PieceScoobyDoo
        assertFalse((attack2.validAction()));  // invalid because PieceScoobyDoo can't attack
    }

    @Test
    public void testActionBefriend() throws Exception{

        System.out.println("Testing ActionBefriend");
        createOurGame();
        ourGame.getGameBoard().getSquares()[0][0].setPiece(scoobyA);
        ourGame.getGameBoard().getSquares()[0][3].setPiece(scoobyB);
        ourGame.getGameBoard().getSquares()[2][1].setPiece(minerA);
        ourGame.getGameBoard().getSquares()[2][2].setPiece(blueHenB);
        ourGame.getGameBoard().getSquares()[1][1].setPiece(minerB);
        System.out.println(ourGame.getGameBoard().toString());

        // Testing ActionBefriend
        ActionBefriend befriend1 = new ActionBefriend(ourGame, 0, 1, 2, 3);
        assertFalse(befriend1.fromSquareValid());   // invalid because no Piece on this space
        assertFalse(befriend1.toSquareValid(false));  // invalid  space is  empty

        befriend1 = new ActionBefriend(ourGame, 0, 5, 9, 0);
        assertFalse(befriend1.fromSquareValid());   // invalid because out of bounds
        assertFalse(befriend1.toSquareValid(false));  // invalid because out of bounds

        befriend1 = new ActionBefriend(ourGame, 2, 1, 2, 2);
        assertTrue(befriend1.fromSquareValid());   // valid because  Team A Piece on this space
        assertTrue(befriend1.toSquareValid(false));  // valid because space has Team B's piece
        assertTrue(befriend1.validActionPath());  // valid path for a PieceUnderminer
        assertFalse((befriend1.validAction()));  // invalid because PieceUnderminer can't befriend

        ActionBefriend befriend2 = new ActionBefriend(ourGame, 2, 2, 0, 3);
        assertFalse(befriend2.fromSquareValid());   // invalid because Team B Piece on this space and its Team A's turn
        assertTrue(befriend2.toSquareValid(false));  // valid because space has Team B's piece and its Team A's turn
        assertTrue(befriend2.validActionPath());  // valid path for a PieceBlueHen
        assertFalse((befriend2.validAction()));  // invalid because one of the above is invalid

        befriend2 = new ActionBefriend(ourGame, 2, 1, 0, 0);
        assertTrue(befriend2.fromSquareValid());  // valid because  Team A Piece on this space
        assertFalse(befriend2.toSquareValid(false));  // invalid because space has Team A's piece and its Team A's turn
        assertTrue(befriend2.validActionPath());  // valid path for a PieceUnderminer
        assertFalse((befriend2.validAction()));  // invalid because one of the above is invalid

        befriend2 = new ActionBefriend(ourGame, 0, 0, 1, 1);
        assertTrue(befriend2.fromSquareValid());   // valid because  Team A Piece on this space
        assertTrue(befriend2.toSquareValid(false));  // valid because space has Team B's piece and its Team A's turn
        assertTrue(befriend2.validActionPath());  // valid path for a PieceScoobyDoo
        assertTrue((befriend2.validAction()));  // valid because PieceScoobyDoo can befriend
    }
}