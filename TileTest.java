import java.util.*;
import org.junit.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by michael on 11/10/16.
 */
public class TileTest {
    Tile tile;
    public static String[] tileTypes = {"ggrgm0", "ggggm0", "ccccc1", "rcrgr0", "cgggg0", "gcgcc1", "cgcgc0", "gcgcg0", "gccgg0", "crrgg0", "rcgrg0", "rcrrr0", "cggcg1", "cggcg0", "crrcg1", "crrcg0", "ccgcc1", "ccgcc0", "ccrcc1", "ccrcc0", "rgrgr0", "ggrrg0", "grrrs0", "rrrrs0"};

    @Before
    public void setUp() throws Exception { }

    @Test
    public void testEmptyTileConstructor(){
        tile = new Tile();
        assertEquals('g', tile.getCenterType());
        assertEquals('0', tile.getShieldLoc());
        char[] sideTypes = tile.getSideTypes();
        for(int i=0; i<Slot.NUM_SIDES; i++){
            assertEquals(sideTypes[i], 'g');
        }
    }

    @Test
    public void testTypeCodeTileConstructor(){
        String typeCode;
        for(int i = 0; i < tileTypes.length; i++){
            typeCode = tileTypes[i];
            tile = new Tile(typeCode);
            for(int j = 0; j < Slot.NUM_SIDES; j++){
                assertEquals(typeCode.charAt(j), tile.getSide(j));
            }
            assertEquals(typeCode.charAt(Slot.NUM_SIDES), tile.getCenterType());
            assertEquals(typeCode.charAt(Slot.NUM_SIDES + 1), tile.getShieldLoc());
        }
    }

    @Test
    public void testTileToString(){
        String returnString;
        for(int i = 0; i < tileTypes.length; i++){
            tile = new Tile(tileTypes[i]);
            returnString = tile.toString();
            assertEquals(tileTypes[i].toString(), returnString);
        }
    }



}
