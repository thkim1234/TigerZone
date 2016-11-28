package tile;

import java.util.*;
import java.util.regex.PatternSyntaxException;

public class TileDeck {

  private Stack<Tile> tiles;
  //representation: the 4 edges, the center, and the shield placement
  //g = grass
  //c = lake
  //r = trail
  //m = monastary
  //s = stoplight
  //0 = no shield
  //1 = center shield
  //2 = corner shield     I don't think there is a corner shield only shields in lakes
  //public static String[] tileTypes = {"ggrgm0", "ggggm0", "ccccc1", "rcrgr0", "cgggg0", "gcgcc1", "cgcgc0", "gcgcg0", "gccgg0", "crrgg0", "rcgrg0", "rcrrr0", "cggcg1", "cggcg0", "crrcg1", "crrcg0", "ccgcc1", "ccgcc0", "ccrcc1", "ccrcc0", "rgrgr0", "ggrrg0", "grrrs0", "rrrrs0"};
  //public static int[] numType = {2,4,1,3,5,2,1,3,2,3,3,3,2,3,2,3,1,3,2,1,8,9,4,1};
  //public static String[] tileTypes = {"ggggg0", "ggggm0", "ggrgm0", "rrrrr0", "rgrgr0", "rggrg0", "rgrrr0", "ccccc0", "gcccc0", "ccggg0", "gcgcc0", "cgcgg0", "cgggg0", "gccgg0", "rcgrg0", "rcgrg2", "gcrrg0", "gcrrg3", "rcrgr0", "rcrgr1", "rcccc0", "rcrrr0", "rcrrr2", "rccrg0", "rccrg3", "cgrgr0", "cgrgr1"};
  //public static int[] numType = {1,4,2,1,8,9,4,1,4,5,3,3,5,2,1,2,1,2,1,2,3,1,2,3,2,1,2};
  public static String[] tileTypes = {"JJJJ-", "JJJJX", "JJTJX", "TTTT-", "TJTJ-", "TJJT-", "TJTT-", "LLLL-", "JLLL-", "LLJJ-", "JLJL-", "LJLJ-", "LJJJ-", "JLLJ-", "TLJT-", "TLJTP", "JLTT-", "JLTTB", "TLTJ-", "TLTJD", "TLLL-", "TLTT-", "TLTTP", "TLLT-", "TLLTB", "LJTJ-", "LJTJD","TLLLC"};
  public static int[] numType = {1,4,2,1,8,9,4,1,4,5,3,3,5,2,1,2,1,2,1,2,3,1,2,3,2,1,2,2};

  public TileDeck(){
    tiles = new Stack<Tile>();
    for(int i = 0; i<tileTypes.length; i++){
      for(int j = 0; j<numType[i]; j++){
        tiles.push(new Tile(tileTypes[i]));
      }
    }

    this.shuffle();
    //tiles.push(new tile.Tile(tileTypes[3]));
  }
  //Accepts a string of tileTypes delimited by spaces and returns a TileDeck created from it
  public TileDeck(String tileString){
      tiles = new Stack<Tile>();
      try {
//          System.out.println("IN TILEDECK CONSTRUCTOR" + tileString);
          String[] splitArray = tileString.split("\\s+");
          for(int i = 0; i < splitArray.length; i++){
              if(splitArray[i].length() == 5)
                tiles.push(new Tile(splitArray[i]));
          }
      } catch (PatternSyntaxException ex) {
          System.out.println("Could not split tileDeck String into array");
      }
      tiles.push(new Tile("TLTJ-"));
  }


  public Tile getTopTile(){
    return tiles.pop();
  }

  public void shuffle(){
    Tile[] deck;
    deck = new Tile[tiles.size()];
    Stack<Tile> temp1 = tiles;
    int j = 0;
    while(!temp1.empty()){
        deck[j] = temp1.peek();
        temp1.pop(); //pops all tiles off of the deck stack and puts in deck array
        j++;
    }
    Random rand = new Random();
    for(int i = 1; i<deck.length; i++){
        int randInt = rand.nextInt(deck.length);
        Tile temp = deck[i];
        deck[i] = deck[randInt];
        deck[randInt] = temp;
    }

    for(int i = 0; i<deck.length; i++){
      tiles.push(deck[i]);
    }
  }

  public int getTileDeckSize() {
      return tiles.size();
  }

  public Stack<Tile> getTiles() {
      return tiles;
  }


  public boolean isEmpty(){
    return tiles.size() == 0;
  }
  public String toString() {
      Stack<Tile> temp = this.tiles;
      String tileDeck = "";
      while(!temp.empty()){
          tileDeck += temp.peek() + "\n";
          temp.pop();
      }
      return tileDeck;
  }



}
