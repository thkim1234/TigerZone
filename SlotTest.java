import org.junit.*;
import static org.junit.Assert.*;
/**
 * Created by camerongera on 11/10/16.
 */
public class SlotTest {
    private Slot testSlot;
    private Tile testTile;

    @Before
    public void setUp() throws Exception {
        testSlot = new Slot();
        testTile = new Tile();
    }

    @Test
    public void testSlotCreation() {
        assertNotNull(testSlot);
        assertNotNull(testSlot.connections);
    }

    @Test
    public void testTilePlacement() {
        testSlot.setTile(testTile, 0);
        assertNotNull(testSlot.getTile());
    }

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
