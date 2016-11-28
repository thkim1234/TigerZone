package region;

import gameplay.Player;
import gameplay.Tiger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public abstract class Region{

  private int id;

  protected HashMap<Player,ArrayList<Tiger>> tigersByPlayer;
  protected HashMap<Integer,Boolean> openPorts;
  protected HashMap<Integer,Boolean> slotsContained;

  //merge another region into this one
  public void absorb(Region otherRegion){
    tigersByPlayer.putAll(otherRegion.tigersByPlayer);
    openPorts.putAll(otherRegion.openPorts);
    slotsContained.putAll(otherRegion.slotsContained);
    //most regions will add to this, merging other attributes also
  }

  public void addSlot(int slot){
    slotsContained.put(slot, true);
  }

  public void addOpenPort(int port){ openPorts.put(port,true); }

  public void closePort(int port){
    openPorts.remove(port);
  }

  public void init(){
    id = RegionManager.getId();
    openPorts = new HashMap<Integer, Boolean>();
    tigersByPlayer = new HashMap<Player, ArrayList<Tiger>>();
    slotsContained = new HashMap<Integer, Boolean>();
  }

  public void addAnimal(char animal){
    //by default, do nothing
  }

  public void score(){

    //if there are no tigers, we don't care
    if(tigersByPlayer.size() == 0){
      return;
    }

    //find the player with the most tigers in the area
    int maxOfTigersByPlayer = 0;
    ArrayList<Player> playersWithMax = new ArrayList<Player>();
    int currentTigers;

    //iterate through the players, finding which players
    //have the max num tigers in the region
    for(Player player: tigersByPlayer.keySet()){
      currentTigers = tigersByPlayer.get(player).size();
      if(currentTigers == maxOfTigersByPlayer){
        playersWithMax.add(player);
      } else if(currentTigers > maxOfTigersByPlayer){
        playersWithMax.clear();
        playersWithMax.add(player);
        maxOfTigersByPlayer = currentTigers;
      }

      //return the tigers to the player
      player.reclaimTigers(tigersByPlayer.get(player));
    }

    //divide the total score by how many players share the region
    //template method!!!
    int scoreToAdd = totalScore()/playersWithMax.size();

    //add the score to all the players who share the region
    for(Player owner: playersWithMax){
      owner.addScore(scoreToAdd);
    }

    tigersByPlayer.clear();
  }

  public int getCurrentScore(){
    return totalScore();
  }

  //given the specified owner of the tiger, note that that player has added
  //a tiger to this region
  public void placeTiger(Tiger tiger){
    if(!tigersByPlayer.containsKey(tiger.owner)){
      ArrayList<Tiger> tigers = new ArrayList<Tiger>();
      tigers.add(tiger);
      tigersByPlayer.put(tiger.owner,tigers);
    } else {
      tigersByPlayer.get(tiger.owner).add(tiger);
    }
  }

  //given the specified owner of the tiger, note that that player has added
  //a tiger to this region
  public void removeTiger(Tiger tiger){
    if(!tigersByPlayer.containsKey(tiger.owner)){
      return;
    } else {
      tigersByPlayer.get(tiger.owner).remove(tiger);
    }
  }

  public abstract boolean complete();


  public void addAdjacent(RegionContainer adjacentRegion){
    //by default, do nothing
    //only implemented in jungle
  }

  //for template method - yippee!
  protected abstract int totalScore();

  public int getId() { return id; }

  public String toString(){
    Iterator<Integer> it = slotsContained.keySet().iterator();

    String s = Integer.toString(id)+" on slots: ";
    int current;
    while(it.hasNext()){
      current = it.next();
      s += "("+(current/1000-77)+","+(current%1000-77)+")";
    }
    return s;
  }

}