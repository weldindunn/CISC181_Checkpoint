import org.junit.Test;

import static org.junit.Assert.*;

public class GameBoardTests {

    public static String boardString(int numRows, int numColumns){
        StringBuilder boardString = new StringBuilder();
        boardString.append("Col :     ");

        for(int col = 0; col < numColumns; col++){
            boardString.append(col + "       ");
        }
        boardString.append("\n");
        for(int row = 0; row < numRows; row++){
            boardString.append("Row : " + row + "   ");
            for(int col = 0; col < numColumns; col++){
                boardString.append("------" + "  ");
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }

    @Test
    public void testingBoard() throws Exception {
        System.out.println("Testing Board Class");

        int numRows = 6;
        int numCols = 10;
        GameBoard testGameBoard = new GameBoard(numRows,numCols);
        // print the GameBoard all spaces should be empty
        System.out.println(testGameBoard.toString());
        assertEquals(boardString(numRows,numCols),testGameBoard.toString());

        assertEquals(numRows,testGameBoard.getNumRows());
        assertEquals(numCols,testGameBoard.getNumColumns());

        assertTrue(testGameBoard.inBounds(0,0));
        assertTrue(testGameBoard.inBounds(numRows-1, numCols-1));
        assertFalse(testGameBoard.inBounds(numRows, numCols));
        assertFalse(testGameBoard.inBounds(-1,-1));

        // place some pieces on the GameBoard
        Piece scooby = new PieceScoobyDoo("SD","Blu",0,
                0,false,true,true);
        testGameBoard.getSquares()[0][0].setPiece(scooby);
        Piece miner = new PieceUnderminer("UM","Gre",1,
                false,false,true);
        testGameBoard.getSquares()[numRows-1][numCols-1].setPiece(miner);
        Piece youdee = new PieceBlueHen("BH","Red",3,
                9,false,true);
        testGameBoard.getSquares()[2][1].setPiece(youdee);
        System.out.println(testGameBoard.toString());

        // testing random empty space method
        BoardSquare emptySpace = testGameBoard.findRandomEmptySpace();
        assertTrue(emptySpace.isEmpty());
        assertNull(emptySpace.getPiece());
        Piece scrappy = new PieceScoobyDoo("BS","Gre",0,
                0,false,true,true);
        emptySpace.setPiece(scrappy);
        System.out.println(testGameBoard.toString());

        //spawn a Piece and place on the GameBoard
        Piece scoobyCopy = scooby.spawn();
        testGameBoard.getSquares()[numRows-3][numCols-3].setPiece(scoobyCopy);
        System.out.println(testGameBoard.toString());
    }

}