import java.util.*;

public class Road extends Region{
  protected HashMap<Integer, Boolean> openPorts;
  protected HashMap<Integer, Boolean> slotsContained;
  protected HashMap<Field,Boolean> adjacentFields;

  public Road(){
    super.init();
    slotsContained = new HashMap<Integer,Boolean>();
    adjacentFields = new HashMap<Field,Boolean>();
  }

  public void absorb(Region otherRegion){
    Road otherRoad = (Road) otherRegion;
    openPorts.putAll(otherRoad.openPorts);
    slotsContained.putAll(otherRoad.slotsContained);
  }

  public void addAdjacent(Region adjacentRegion){
    adjacentFields.put((Field) adjacentRegion, true);
  }

  public String toString(){
    String s = super.toString();
    Iterator<Field> it = adjacentFields.keySet().iterator();
    s += "adjacent fields: {";
    while(it.hasNext()){
      s += it.next();
    }

    return s + "}\n";
  }

}
