import org.junit.*;
import static org.junit.Assert.*;
/**
 * Created by camerongera on 11/13/16.
 */
public class MoveOptionTest {

    MoveOption newMove = new MoveOption(72072,0);

    @Test
    public void moveOptionLocation() {
        assertEquals(newMove.location, 72072);
    }

    @Test
    public void moveOptionRotation() {
        assertEquals(newMove.rotation, 0);
    }
}
