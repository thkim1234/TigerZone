import org.junit.*;
import static org.junit.Assert.*;
/**
 * Created by camerongera on 11/13/16.
 */
public class GameTest {

    private HumanPlayer player1 = new HumanPlayer();
    private HumanPlayer player2 = new HumanPlayer();
    Game game = new Game(player1,player2);

    @Test
    public void gameConstructor() {
        assertNotNull(game.getBoard());
        assertNotNull(game.getTiles());
        assertEquals(2,game.getPlayers().size());
    }

}
