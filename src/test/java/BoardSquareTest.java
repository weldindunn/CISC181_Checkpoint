import org.junit.Test;

import static org.junit.Assert.*;

public class BoardSquareTest{

    @Test
    public void testingBoardSpace() throws Exception {
        System.out.println("Testing Board Space Class");

        BoardSquare testSquare11 = new BoardSquare("Black");
        BoardSquare testSquare12 = new BoardSquare("White");

        // getColor
        assertEquals("Black",testSquare11.getSquareColor());
        assertEquals("White",testSquare12.getSquareColor());

        // isEmpty
        assertTrue(testSquare11.isEmpty());
        assertTrue(testSquare12.isEmpty());

        //toString
        assertEquals(("------"),testSquare11.toString());
        assertEquals(("------"),testSquare12.toString());

        //getPiece - none
        assertNull(testSquare11.getPiece());
        assertNull(testSquare12.getPiece());

        //setPiece
        // place some pieces on the GameBoard
        Piece scooby = new PieceScoobyDoo("SD","Blu",0,
                0,false,true,true);
        Piece miner = new PieceUnderminer("UM","Gre",1,
                false,false,true);
        testSquare11.setPiece(scooby);
        testSquare12.setPiece(miner);

        //toString
        assertEquals(("Blu SD"),testSquare11.toString());
        assertEquals(("Gre UM"),testSquare12.toString());

        //getPiece - returns Piece but doesn't remove it from space
        assertSame(scooby,testSquare11.getPiece());
        assertSame(miner,testSquare12.getPiece());

        //isEmpty
        assertFalse(testSquare11.isEmpty());
        assertFalse(testSquare12.isEmpty());

        // removePiece - returns Piece and removes it from space
        assertSame(scooby,testSquare11.removePiece());
        assertSame(miner,testSquare12.removePiece());

        //isEmpty
        assertTrue(testSquare11.isEmpty());
        assertTrue(testSquare12.isEmpty());

        //toString
        assertEquals(("------"),testSquare11.toString());
        assertEquals(("------"),testSquare12.toString());
    }
}