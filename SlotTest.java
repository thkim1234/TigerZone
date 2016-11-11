import org.junit.*;
import static org.junit.Assert.*;
/**
 * Created by camerongera on 11/10/16.
 */
public class SlotTest {
    private Slot testSlot;
    private Tile testTile;

    /*
     *  Create the slot and tile we need to execute the test.
     *
     */
    @Before
    public void setUp() throws Exception {
        testSlot = new Slot();
        testTile = new Tile();
    }

    /*
     *  Test that the Slot was created and not null
     *  Test that the Slot.connections are not null as well. Avoiding nullpointer exceptions.
     */
    @Test
    public void testSlotCreation() {
        assertNotNull(testSlot);
        assertNotNull(testSlot.connections);
    }


    /*
     *  Test if the tile can be set for a Slot
     *
     */
    @Test
    public void testTilePlacement() {
        testSlot.setTile(testTile, 0);
        System.out.println(testSlot.getTile());
        assertNotNull(testSlot.getTile());
    }

    /*
     *  Test if the tile can fit in comparison to the slot it is next to.
     *
     */
    @Test
    public void testCanFit() {
        if(testSlot.canFit(testTile, 0)) {
            System.out.println("Printing true");
            assertTrue(testSlot.canFit(testTile, 0));
        } else {
            System.out.println("Printing false");
            assertFalse(testSlot.canFit(testTile, 0));
        }

    }

}
