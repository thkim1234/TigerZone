package gameplay;

import board.Board;
import region.Region;
import tile.Tile;
import tile.TileDeck;

import java.util.Collection;
import java.util.HashMap;
import java.util.Stack;

public abstract class Player {

  protected Stack<Tiger> availableTigers;
  private Stack<Tiger> placedTigers;
  private int score;

  public void init(){
    placedTigers = new Stack<Tiger>();
    availableTigers = new Stack<Tiger>();
    score = 0;
    for(int i = 0; i<7; i++){
      availableTigers.add(new Tiger(this));
    }
  }

  //public abstract MoveOption chooseMove(Tile t, Board b);

  public abstract TigerOption chooseMove(Tile t, Board b, TileDeck tiles);

  public abstract int chooseTigerPlacement();

  public void addScore(int score){
    this.score += score;
  }

  public int getScore(){
    return this.score;
  }

  public Tiger giveTiger(){
    Tiger givenTiger;
    if(!availableTigers.isEmpty())
      givenTiger = availableTigers.pop();
    else
      givenTiger = new Tiger(this);

    placedTigers.add(givenTiger);
    return givenTiger;
  }

  public void reclaimTigers(Collection<Tiger> tigers){
    placedTigers.removeAll(tigers);
    availableTigers.addAll(tigers);
  }

  public HashMap<Region, Boolean> relevantRegions(){
    HashMap<Region, Boolean> relevantRegions = new HashMap<Region, Boolean>();
    for(Tiger tiger : placedTigers){
      relevantRegions.put(tiger.getRegion(), true);
    }
    return relevantRegions;
  }

  public String toString(){
    return "player- tigers left: "+availableTigers.size()+" score: "+Integer.toString(score);
  }

  public TigerOption passMethod(){
    TigerOption pass = null;
    return pass;
    }


}
