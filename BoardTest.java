import org.junit.*;
import java.util.*;
import java.util.ArrayList;
import static org.junit.Assert.*;


/**
 * Created by michael on 11/10/16.
 */
public class BoardTest {
    Board board;
    @Before
    public void setUp() throws Exception { board = new Board(); }

    /*
    * This test verifies the creation of a board object and validates
    * its fields. It also checks that the origin contains a slot
    */
    @Test
    public void testBoardConstructor(){
        Slot slot = new Slot();
        ArrayList<Integer> openLocations = board.getOpenLocations();
        Map<Integer, Slot> map = board.getMap();
        int origin = openLocations.get(0);
        assertEquals(origin, 72072);
        assertTrue(board.getSlot(72072) != null);
    }


    /*
    * Need some assitance with how we should write a test that tests possible tile placements.
    * */
    @Test
    public void testTilePlacement(){

    }

    /* This appears to already be verified with the can fit function should we just reuse that here?*/
    @Test
    public void testPotentialMoves(){

    }

    /*
    * Two things: 1 this method is private, how should we go about testing it. Two, how many possible locations
    * should we test to justify a robust test
    * */
    @Test
    public void testAddNewOptions(){

    }

    //The same applies wih the newSlot function. Perhaps nest the test classes. Not sure though


    //Hold on testing meeple placement until implemented.
}
