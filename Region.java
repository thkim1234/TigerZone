import java.util.*;

public abstract class Region{

  private HashMap<Player,Integer> meeplesByPlayer;
  private HashMap<Integer,Boolean> openPorts;

  public abstract void absorb(Region otherRegion);

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

}
