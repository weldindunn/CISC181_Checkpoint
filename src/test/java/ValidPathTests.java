import org.junit.Test;

import static org.junit.Assert.*;

public class ValidPathTests {

    @Test
    public void validPath() throws Exception {
        System.out.println("Testing  Blue Hen validPath");

        // place piece on the GameBoard
        Piece youdee = new PieceBlueHen("BH","Red",0,
                1,false,true);
        // this piece can fly so should be able to go anywhere on board
        assertTrue(youdee.validPath(35,1,5,67));
        assertTrue(youdee.validPath(5,1,10,0));
        assertTrue(youdee.validPath(8,1,4,20));

        // this piece can't fly so should only be able to move
        // left or right any number of spaces but not up or down
        youdee = new PieceBlueHen("BH","Red",10,
                1,false,true);

        // this piece can move left or right any number of spaces but not up or down
        assertTrue(youdee.validPath(15,1,15,67));
        assertTrue(youdee.validPath(4,1,4,0));
        assertTrue(youdee.validPath(8,16,8,10));
        assertFalse(youdee.validPath(15,1,16,56));
        assertFalse(youdee.validPath(5,10,6,0));
        assertFalse(youdee.validPath(7,10,6,0));

        System.out.println("Testing Underminer validPath");
        Piece miner = new PieceUnderminer("UM","Gre",1,
                false,false,true);
        // this piece is not underground and can dig so should be able to go anywhere on board
        assertTrue(miner.validPath(15,1,75,34));
        assertTrue(miner.validPath(5,2,1,0));
        assertTrue(miner.validPath(8,1,4,20));

        miner = new PieceUnderminer("UM","Gre",1,
                true,false,true);
        // this piece is underground so can't go anywhere
        assertFalse(miner.validPath(15,1,75,34));
        assertFalse(miner.validPath(5,2,1,0));
        assertFalse(miner.validPath(8,1,4,20));

        miner = new PieceUnderminer("UM","Gre",11,
                false,false,true);
        // this piece is not underground and can't dig so
        // Can move one space up or one space down the board and not left or right

        assertTrue(miner.validPath(35,2,34,2));
        assertTrue(miner.validPath(5,9,6,9));
        assertFalse(miner.validPath(35,1,33,1));
        assertFalse(miner.validPath(5,10,7,10));
        assertFalse(miner.validPath(34,1,33,2));
        assertFalse(miner.validPath(6,20,7,10));

        System.out.println("Testing Scooby Doo validPath");

        PieceScoobyDoo scooby = new PieceScoobyDoo("SD","Blu",0,
                0,false,true,true);
        // this piece is not entangled so can
        //  move one diagonally
        scooby = new PieceScoobyDoo("SD","Blu",0,
                0,false,true,true);
        assertFalse(scooby.validPath(15,1,13,3));
        assertTrue(scooby.validPath(4,11,5,12));
        assertTrue(scooby.validPath(8,16,7,15));
        assertTrue(scooby.validPath(6,12,7,11));
        assertTrue(scooby.validPath(6,12,5,13));

        assertFalse(scooby.validPath(4,10,5,12));
        assertFalse(scooby.validPath(8,16,7,14));
        assertFalse(scooby.validPath(8,16,6,15));

        // this piece is entangled and has no scooby snacks so no valid paths
        scooby = new PieceScoobyDoo("SD","Blu",0,
                0,false,false,true);
        assertFalse(scooby.validPath(4,11,5,12));
        assertFalse(scooby.validPath(8,16,7,15));
        assertFalse(scooby.validPath(6,12,7,11));
        assertFalse(scooby.validPath(6,12,5,13));
        assertFalse(scooby.validPath(4,11,6,13));
        assertFalse(scooby.validPath(8,16,6,14));
        assertFalse(scooby.validPath(5,12,7,10));
        assertFalse(scooby.validPath(7,12,5,14));


        scooby = new PieceScoobyDoo("SD","Blu",0,
                10,false,false,true);
        // this piece is entangled and has scooby snacks so eats one and can
        //  move two spaces diagonally

        assertTrue(scooby.validPath(4,11,6,13));
        assertEquals(9,scooby.getNumScoobySnacks());
        scooby.entangle();
        assertTrue(scooby.validPath(8,16,6,14));
        assertEquals(8,scooby.getNumScoobySnacks());
        scooby.entangle();
        assertTrue(scooby.validPath(5,12,7,10));
        assertEquals(7,scooby.getNumScoobySnacks());
        scooby.entangle();
        assertTrue(scooby.validPath(7,12,5,14));
        assertEquals(6,scooby.getNumScoobySnacks());
        scooby.entangle();

        assertFalse(scooby.validPath(4,11,5,12));
        assertEquals(5,scooby.getNumScoobySnacks());
        scooby.entangle();
        assertFalse(scooby.validPath(8,16,7,15));
        assertEquals(4,scooby.getNumScoobySnacks());
        scooby.entangle();
        assertFalse(scooby.validPath(6,12,7,11));
        assertEquals(3,scooby.getNumScoobySnacks());
        scooby.entangle();
        assertFalse(scooby.validPath(6,12,5,13));
        assertEquals(2,scooby.getNumScoobySnacks());

    }
}