import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
/**
 * Created by camerongera on 11/10/16.
 */
public class TileDeckTest {

    TileDeck testTileDeck;

    /*
     *  Initialize testTileDeck to a new TileDeck
     *
     */
    @Before
    public void setUp() {
        testTileDeck = new TileDeck();
    }

    /*
     *  Test to see that testTileDeck is created and not null
     *
     */
    @Test
    public void testTileDeckCreation() {
        assertNotNull(testTileDeck);
    }

    /*
     *  Make sure that we get a next tile on the first move.
     *
     */
    @Test
    public void testGetTopTile() {
        assertNotNull(testTileDeck.getTopTile());
    }

    /*
     *  Test to see if the deck is empty after one move.
     *
     */
    @Test
    public void testIsEmptyAfterOne() {
        assertFalse(testTileDeck.isEmpty());
    }

    /*
     *  Test to see if the deck is empty after all moves.
     *
     */
    @Test
    public void testIsEmptyAfterAll() {
        TileDeck temp = new TileDeck();
        int size = temp.getTileDeckSize();
        System.out.println("Before: " + temp.getTileDeckSize());
        for(int i = 0; i < size; i++) {
            temp.getTiles().pop();
        }
        System.out.println("After: " + temp.getTileDeckSize());
        assertTrue(temp.isEmpty());
    }

    /*
     *  Test to check if the deck is shuffled and not equal every time.
     *
     */
    @Test
    public void testShufflingDeck() {
        TileDeck temp = new TileDeck();
        assertNotEquals(testTileDeck,temp);

    }

    /*
     *  Check to make sure that there are 24 types of tiles
     *
     */
    @Test
    public void sameNumberOfTileTypes() {
        assertEquals(testTileDeck.tileTypes.length, 24);
    }
}
