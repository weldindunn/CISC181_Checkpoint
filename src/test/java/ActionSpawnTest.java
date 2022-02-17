import org.junit.Test;

import static org.junit.Assert.*;
import java.util.ArrayList;

public class ActionSpawnTest {

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
        Team teamA = new Team("Freshmen", "Red",piecesTeamA, 200);
        Team teamB = new Team("Sophomores",  "Blu",piecesTeamB, 200);


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
    public void testActionSpawn() throws Exception{

        System.out.println("Testing ActionSpawn");
        createOurGame();
        ourGame.getGameBoard().getSquares()[0][0].setPiece(scoobyA);
        ourGame.getGameBoard().getSquares()[0][3].setPiece(scoobyB);
        ourGame.getGameBoard().getSquares()[2][1].setPiece(minerA);
        minerA.setOriginal(false);
        ourGame.getGameBoard().getSquares()[2][2].setPiece(blueHenB);
        ourGame.getGameBoard().getSquares()[1][1].setPiece(minerB);
        System.out.println(ourGame.getGameBoard().toString());

        // Testing ActionSpawn
        ActionSpawn spawn1 = new ActionSpawn(ourGame, 0, 1, 2, 3);
        assertFalse(spawn1.fromSquareValid());   // invalid because no Piece on this space

        spawn1 = new ActionSpawn(ourGame, 0, 5, 9, 0);
        assertFalse(spawn1.fromSquareValid());   // invalid because out of bounds
        assertFalse(spawn1.toSquareValid(true));  // invalid because out of bounds

        spawn1 = new ActionSpawn(ourGame, 2, 1, 0, 1);
        assertTrue(spawn1.fromSquareValid());   // valid because  Team A Piece on this space
        assertTrue(spawn1.toSquareValid(true));  // valid because space is empty
        assertTrue(spawn1.validActionPath());  // valid path for a PieceUnderminer
        assertFalse((spawn1.validAction()));  // invalid because this PieceUnderminer is not an original

        ActionSpawn spawn2 = new ActionSpawn(ourGame, 2, 2, 0, 3);
        assertFalse(spawn2.fromSquareValid());   // invalid because Team B Piece on this space and its Team A's turn
        assertFalse(spawn2.toSquareValid(true));  // valid because space is not empty
        assertTrue(spawn2.validActionPath());  // valid path for a PieceBlueHen
        assertFalse((spawn2.validAction()));  // invalid because one of the above is invalid

        minerA.setOriginal(true);
        spawn2 = new ActionSpawn(ourGame, 2, 1, 0, 0);
        assertTrue(spawn2.fromSquareValid());  // valid because  Team A Piece on this space
        assertFalse(spawn2.toSquareValid(true));  // invalid because space is not empty
        assertTrue(spawn2.validActionPath());  // valid path for a PieceUnderminer
        assertFalse((spawn2.validAction()));  // invalid because one of the above is invalid

        spawn2 = new ActionSpawn(ourGame, 2, 1, 0, 1);
        assertTrue(spawn2.fromSquareValid());  // valid because Team A Piece on this space
        assertTrue(spawn2.toSquareValid(true));  // valid because space is empty
        assertTrue(spawn2.validActionPath());  // valid path for a PieceUnderminer
        assertTrue(spawn2.validAction());  // valid because all above are valid
        spawn2.performAction();
        // spawned piece should not be an original
        assertFalse(ourGame.getBoardSquares()[0][1].getPiece().isOriginal());
        System.out.println(ourGame.getGameBoard().toString());

    }

}