import org.junit.Test;

import static org.junit.Assert.*;
import java.lang.reflect.*;

public class GamePieceTests {

    @Test
    public void test_Piece() {
        // Piece has a constructor
        // but we should not be able to create a Piece
        System.out.println("Testing Piece Class");
        try {
            Class<Piece> clazz = Piece.class;
            Constructor<Piece> ctor = clazz.getDeclaredConstructor(String.class, String.class,
                    boolean.class, boolean.class);
            try {
                ctor.newInstance("BHen", "Red");
                fail("Should not be able to create an instance of Piece");
            } catch (Exception e) {
                // should happen
            }

            System.out.println("Testing Piece Constructor passes");

            // speak should not be implemented in the Piece class
            Method m = clazz.getDeclaredMethod("speak", new Class[]{});
            assertEquals(void.class, m.getReturnType());
            assertTrue(Modifier.isAbstract(m.getModifiers()));

            // validPath should not be implemented in the Piece class
            m = clazz.getDeclaredMethod("validPath", int.class,int.class,int.class,int.class);
            assertEquals(boolean.class, m.getReturnType());
            assertTrue(Modifier.isAbstract(m.getModifiers()));

            // spawn should not be implemented in the Piece class
            m = clazz.getDeclaredMethod("spawn", new Class[]{});
            assertEquals(Piece.class, m.getReturnType());
            assertTrue(Modifier.isAbstract(m.getModifiers()));

        } catch (Exception e) {
            // not supposed to happen
            e.printStackTrace();
            fail(e.getMessage());
        }
    }


    @Test
    public void test_ScoobyDoo() {
        System.out.println("Testing ScoobyDoo Class");
        PieceScoobyDoo scooby = new PieceScoobyDoo("SD", "Blu",0,0,false,true,true);
        assertTrue(scooby instanceof Piece);

        try {
            // speak should be implemented
            Method m = PieceScoobyDoo.class.getMethod("speak", new Class[]{});
            assertEquals(void.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

            // validPath should be implemented
            m = PieceScoobyDoo.class.getDeclaredMethod("validPath", int.class,int.class,int.class,int.class);
            assertEquals(boolean.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

        } catch (Exception e) {
            // not supposed to happen
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void test_Underminer() {
        System.out.println("Testing PieceUnderminer Class");
        PieceUnderminer miner = new PieceUnderminer("UM", "GRN", 10,
                false,true, true);
        assertTrue(miner instanceof Piece);

        try {
            // speak should be implemented
            Method m = PieceUnderminer.class.getMethod("speak", new Class[]{});
            assertEquals(void.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

            // validPath should be implemented
            m = PieceUnderminer.class.getDeclaredMethod("validPath", int.class,int.class,int.class,int.class);
            assertEquals(boolean.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

            // getNumAttacked should be implemented
            m = PieceUnderminer.class.getDeclaredMethod("getNumAttacked", new Class[]{});
            assertEquals(int.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

            // setNumAttacked should be implemented
            m = PieceUnderminer.class.getDeclaredMethod("setNumAttacked", int.class);
            assertEquals(void.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

            // spawn should be implemented
            m = PieceUnderminer.class.getDeclaredMethod("spawn", new Class[]{});
            assertEquals(PieceUnderminer.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));


        } catch (Exception e) {
            // not supposed to happen
            e.printStackTrace();
            fail(e.getMessage());
        }
    }



    @Test
    public void test_BlueHen() {
        System.out.println("Testing Blue Hen Class");
        PieceBlueHen youdee = new PieceBlueHen("BH", "Red", 5, 2,true, true);
        assertTrue(youdee instanceof Piece);

        try {
            // speak should be implemented
            Method m = PieceBlueHen.class.getMethod("speak", new Class[]{});
            assertEquals(void.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

            // validPath should be implemented
            m = PieceUnderminer.class.getDeclaredMethod("validPath", int.class,int.class,int.class,int.class);
            assertEquals(boolean.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

            // spawn should be implemented
            m = PieceBlueHen.class.getDeclaredMethod("spawn", new Class[]{});
            assertEquals(PieceBlueHen.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

            // getNumAttacked should be implemented
            m = PieceBlueHen.class.getDeclaredMethod("getNumAttacked", new Class[]{});
            assertEquals(int.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

            // getBefriended should be implemented
            m = PieceBlueHen.class.getDeclaredMethod("getNumBefriended", new Class[]{});
            assertEquals(int.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

            // setNumAttacked should be implemented
            m = PieceBlueHen.class.getDeclaredMethod("setNumAttacked", int.class);
            assertEquals(void.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));
            // setNumBefriended should be implemented
            m = PieceBlueHen.class.getDeclaredMethod("setNumBefriended", int.class);
            assertEquals(void.class, m.getReturnType());
            assertFalse(Modifier.isAbstract(m.getModifiers()));

        } catch (Exception e) {
            // not supposed to happen
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}