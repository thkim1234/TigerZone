import java.util.*;

public class Road extends Region{
  protected HashMap<Integer, Boolean> openPorts;
  protected HashMap<Integer, Boolean> slotsContained;

  public Road(){
    openPorts = new HashMap<Integer,Boolean>();
    slotsContained = new HashMap<Integer,Boolean>();
  }

  public void addPort(int port){
    openPorts.put(port, true);
  }

  public void absorb(Region otherRegion){
    Road otherRoad = (Road) otherRegion;
    openPorts.putAll(otherRoad.openPorts);
    slotsContained.putAll(otherRoad.slotsContained);
  }

  public void closePort(int port){
    openPorts.remove(port);
  }



}
