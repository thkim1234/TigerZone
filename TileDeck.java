import java.util.*;

public class TileDeck {

  private Stack<Tile> tiles;

  public TileDeck(){
    tiles = new Stack<Tile>();
    for(int i = 0; i<tileTypes.length; i++){
      for(int j = 0; j<numType[i]; j++){
        tiles.push(new Tile(tileTypes[i]));
      }
    }
  }


  public Tile getTopTile(){
    return tiles.pop();
  }

  public void shuffle(){
    //TODO
  }


  public boolean isEmpty(){
    return tiles.size() == 0;
  }

  //representation: the 4 edges, the center, and the shield placement
  //g = grass
  //c = city
  //r = road
  //0 = no shield
  //1 = center shield
  //2 = corner shield
  public static String[] tileTypes = {"ggggm0","cgggg0","cgcgc0","cgcgc1"};
  public static int[] numType = {4,5,1,2};

}
