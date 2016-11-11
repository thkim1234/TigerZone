import org.junit.*;
import static org.junit.Assert.*;
/**
 * Created by camerongera on 11/10/16.
 */
public class SlotTest {
    private Slot testSlot;

    @Before
    public void setUp() throws Exception { testSlot = new Slot(); }

    @Test
    public void testSlotCreation() {
        assertNotNull(testSlot);

    }


}
