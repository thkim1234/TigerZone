import static org.junit.Assert.*;

import org.junit.Test;

public class HumanPlayerTest {
	private HumanPlayer player1 = new HumanPlayer();
	private Tile tile = new Tile();
	private Board board = new Board();
	private TileDeck tiles = new TileDeck();
	
	//The test failed. 
	@Test
	public void testChooseMove() {
		tile = tiles.getTopTile();
		assertEquals(tile, board);
	}

}
