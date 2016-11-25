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
    * This test creates a tile and places it on the board. It then checks the board (SlotMap)
    * to see that this tile was in fact placed.
    */
    @Test
    public void testTilePlacement(){
        Tile tile = new Tile();
        MoveOption move = new MoveOption(72072, 0);
        board.placeTile(tile, move);
        SlotMap map = board.getMap();
        assertNotNull(map.get(move.location));
    }

    /* This test places a tile on the board. It then tests to see if a potentialMove list is returned
    * for a new tile in consideration
    */
    @Test
    public void testPotentialMoves(){
        Tile tile = new Tile();
        MoveOption move = new MoveOption(72072, 0);
        board.placeTile(tile, move);
        Tile tile2 = new Tile("ggggm0");
        assertNotNull(board.potentialMoves(tile2));
    }

    /*
    * This test places a tile on the board then tests to see if the openLocations list has indeed been
    * updated. The comparison is done by cloning the current state of openLocations and comparing it to
    * the state of openLocations after a tile is placed on the board
    * */
    @Test
    public void testAddNewOptions(){
        ArrayList<Integer> listBefore;
        ArrayList<Integer> listAfter;
        listBefore = (ArrayList<Integer>) board.getOpenLocations().clone();
        Tile tile = new Tile();
        MoveOption move = new MoveOption(72072, 0);
        board.placeTile(tile, move);
        listAfter = (ArrayList<Integer>) board.getOpenLocations().clone();
        assertTrue(!listBefore.equals(listAfter));
    }


    //Hold on testing meeple placement until implemented.
}
