import java.util.*;

public class TileDeck {

  private Stack<Tile> tiles;

  public TileDeck(){
    //sets tiles with given requirements
  }

  public Tile getTopTile(){
    return tiles.top();
  }

  public void shuffle(){
    //TODO
  }


  public boolean isEmpty(){
    return tiles.size() == 0;
  }


}
