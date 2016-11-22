import java.util.*;

public abstract class Region{

  private HashMap<Player,Integer> meeplesByPlayer;
  protected HashMap<Integer,Boolean> openPorts;

  public abstract void absorb(Region otherRegion);

  public void init(){
    openPorts = new HashMap<Integer, Boolean>();
    meeplesByPlayer = new HashMap<Player, Integer>();
  }

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

  public void addOpenPort(int port){
    openPorts.put(port,true);
  }

  public void closePort(int port){
    openPorts.remove(port);
  }

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
