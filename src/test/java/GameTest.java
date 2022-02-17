import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void gameTests() throws Exception {

        System.out.println("Testing Game");

        // Create 3 pieces for team A
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

        //Game ourGame = new Game(10, 10, teamA, teamB);
        //System.out.println(ourGame.toString());
    }
}