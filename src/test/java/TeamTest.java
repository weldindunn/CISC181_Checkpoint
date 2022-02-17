import org.junit.Test;

import static org.junit.Assert.*;
import java.util.ArrayList;

public class TeamTest {

    @Test
    public void getTeamName() throws Exception {

        System.out.println("Testing Team class");

        Piece scoobyA = new PieceScoobyDoo("SD","Red",0,
                0,false,true,true);
        Piece minerA = new PieceUnderminer("UM","Red",1,
                false,false,true);
        Piece blueHenA = new PieceBlueHen("BH","Red",0,0,false, minerA.original);
        ArrayList<Piece> piecesTeamA = new ArrayList<>();
        piecesTeamA.add(scoobyA);
        piecesTeamA.add(minerA);
        piecesTeamA.add(blueHenA);

        Piece scoobyB = new PieceScoobyDoo("SD","Blu",0,
                0,false,true,true);
        Piece minerB = new PieceUnderminer("UM","Blu",1,
                false,false,true);
        Piece blueHenB = new PieceBlueHen("BH","Blu",0,0,false, minerA.original);
        ArrayList<Piece> piecesTeamB = new ArrayList<>();
        piecesTeamB.add(scoobyB);
        piecesTeamB.add(minerB);
        piecesTeamB.add(blueHenB);

        Team teamA = new Team("CISC", "Red", piecesTeamA, 1000);
        Team teamB = new Team("181",  "Blu", piecesTeamB, 1000);

        // check teamA
        assertEquals("CISC",teamA.getTeamName());
        assertEquals("Red",teamA.getTeamColor());
        assertEquals(3,teamA.getTeamPieces().size());

        assertTrue(teamA.getTeamPieces().contains(scoobyA));
        assertTrue(teamA.getTeamPieces().contains(blueHenA));
        assertTrue(teamA.getTeamPieces().contains(minerA));

        assertFalse(teamA.getTeamPieces().contains(scoobyB));
        assertFalse(teamA.getTeamPieces().contains(blueHenB));
        assertFalse(teamA.getTeamPieces().contains(minerB));


        // check teamB
        assertEquals("181",teamB.getTeamName());
        assertEquals("Blu",teamB.getTeamColor());
        assertEquals(3,teamB.getTeamPieces().size());

        assertTrue(teamB.getTeamPieces().contains(scoobyB));
        assertTrue(teamB.getTeamPieces().contains(blueHenB));
        assertTrue(teamB.getTeamPieces().contains(minerB));

        assertFalse(teamB.getTeamPieces().contains(scoobyA));
        assertFalse(teamB.getTeamPieces().contains(blueHenA));
        assertFalse(teamB.getTeamPieces().contains(minerA));

        // remove a Piece from teamA and put on Team B
        teamA.removePieceFromTeam(scoobyA);
        teamB.addPieceToTeam(scoobyA);

        // check team B pieces
        assertEquals(4,teamB.getTeamPieces().size());
        assertTrue(teamB.getTeamPieces().contains(scoobyB));
        assertTrue(teamB.getTeamPieces().contains(blueHenB));
        assertTrue(teamB.getTeamPieces().contains(minerB));
        assertTrue(teamB.getTeamPieces().contains(scoobyA));

        assertFalse(teamB.getTeamPieces().contains(blueHenA));
        assertFalse(teamB.getTeamPieces().contains(minerA));


        //check that scoobyA color was changed
        assertEquals(teamB.getTeamColor(),scoobyA.getTeamColor());

        // check team A pieces
        assertEquals(2,teamA.getTeamPieces().size());

        assertTrue(teamA.getTeamPieces().contains(blueHenA));
        assertTrue(teamA.getTeamPieces().contains(minerA));

        assertFalse(teamA.getTeamPieces().contains(scoobyA));
        assertFalse(teamA.getTeamPieces().contains(scoobyB));
        assertFalse(teamA.getTeamPieces().contains(blueHenB));
        assertFalse(teamA.getTeamPieces().contains(minerB));

        System.out.println(teamA.toString());
        System.out.println(teamB.toString());
    }
}