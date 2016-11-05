import java.util.*;

public class TileDeck {

  private Stack<Tile> tiles;
  //representation: the 4 edges, the center, and the shield placement
  //g = grass
  //c = city
  //r = road
  //m = monastary
  //s = stoplight
  //0 = no shield
  //1 = center shield
  //2 = corner shield     I don't think there is a corner shield only shields in cities
  public static String[] tileTypes = {"ggrgm0", "ggggm0", "ccccc1", "rcrgr0", "cgggg0", "gcgcc1", "cgcgc0", "gcgcg0", "gccgg0", "crrgg0", "rcgrg0", "rcrrr0", "cggcg1", "cggcg0", "crrcg1", "crrcg0", "ccgcc1", "ccgcc0", "ccrcc1", "ccrcc0", "rgrgr0", "ggrrg0", "grrrs0", "rrrrs0"};
  public static int[] numType = {2,4,1,4,5,2,1,3,2,3,3,3,2,3,2,3,1,3,2,1,8,9,4,1};

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
    Tile[] deck;
    deck = new Tile[72];
    for(int i = 0; i < tiles.size; i++){
      deck[i] = tiles.pop(); //pops all tiles off of the deck stack and puts in deck array
    }
    Random rand = new Random();
    for(int i = 0; i<deck.size; i++){
      int randInt = rand.nextInt(72);
      Tile temp = deck[i];
      deck[i] = deck[randInt];
      deck[randInt] = temp;
    }

    for(int i = 0; i<deck.size; i++){
      tiles.push(deck[i]);
    }
  }


  public boolean isEmpty(){
    return tiles.size() == 0;
  }



}
