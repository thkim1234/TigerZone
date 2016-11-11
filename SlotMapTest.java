import org.junit.*;
import static org.junit.Assert.*;
/**
 * Created by camerongera on 11/10/16.
 */
public class SlotMapTest {
    private SlotMap testSlotMap;

    @Before
    public void setUp() {
        testSlotMap = new SlotMap();
    }

    @Test
    public void testSlotMapCreated() {
        assertNotNull(testSlotMap);
    }

    @Test
    public void testAdjKeyFunc() {
        assertEquals(testSlotMap.getAdjKey(0,0),1000);
        assertEquals(testSlotMap.getAdjKey(0,1),1);
        assertEquals(testSlotMap.getAdjKey(0,2),-1000);
        assertEquals(testSlotMap.getAdjKey(0,3),-1);
    }


}
