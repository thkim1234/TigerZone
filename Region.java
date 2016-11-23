
import java.util.*;

public abstract class Region{

  protected HashMap<Player,Integer> meeplesByPlayer;
  protected HashMap<Integer,Boolean> openPorts;

  //merge another region into this one
  public void absorb(Region otherRegion){
    meeplesByPlayer.putAll(otherRegion.meeplesByPlayer);
    openPorts.putAll(otherRegion.openPorts);
    //most regions will add to this, merging other attributes also
  }

  public void addOpenPort(int port){ openPorts.put(port,true); }

  public void closePort(int port){
    openPorts.remove(port);
  }

  public void init(){
    openPorts = new HashMap<Integer, Boolean>();
    meeplesByPlayer = new HashMap<Player, Integer>();
  }

  public void score(){

    //find the player with the most meeples in the area
    int maxOfMeeplesByPlayer = 0;
    ArrayList<Player> playersWithMax = new ArrayList<Player>();
    int currentMeeples;

    //iterate through the players, finding which players
    //have the max num meeples in the region
    for(Player player: meeplesByPlayer.keySet()){
      currentMeeples = meeplesByPlayer.get(player);
      if(currentMeeples == maxOfMeeplesByPlayer){
        playersWithMax.add(player);
      } else if(currentMeeples > maxOfMeeplesByPlayer){
        playersWithMax.clear();
        playersWithMax.add(player);
        maxOfMeeplesByPlayer = currentMeeples;
      }
    }

    //divide the total score by how many players share the region
    //template method!!!
    int scoreToAdd = totalScore()/playersWithMax.size();

    //add the score to all the players who share the region
    for(Player owner: playersWithMax){
      owner.addScore(scoreToAdd);
    }
  };

  //given the specified owner of the meeple, note that that player has added
  //a meeple to this region
  public void addMeeple(Player owner){
    if(!meeplesByPlayer.containsKey(owner)){
      meeplesByPlayer.put(owner,1);
    } else {
      meeplesByPlayer.put(owner,meeplesByPlayer.get(owner)+1);
    }
  }

  public void addAdjacent(Region adjacentRegion){
    //by default, do nothing
    //only implemented for region types where this is needed
  }

  public void setSlot(int center){
    //by default do nothing, I added this for the monastery
  }

  //for template method - yippee!
  protected abstract int totalScore();

  public String toString(){
    Iterator<Integer> it = openPorts.keySet().iterator();
    String s = "open ports: ";
    int current;
    while(it.hasNext()){
      current = it.next();
      s += "[ "+(current/(100000)-72)+", "+((current%100000)/100-72)+" -- "+(current%100)+"]";
    }
    return s + "  ";
          // "cities: \n" + cities.toString()+"\n"
          // "roads: \n" + roads.toString()+"\n"
  }

}
