import java.util.*;

public class City extends Region{
  protected HashMap<Field,Boolean> adjacentFields;
  protected HashMap<Integer, Boolean> slotsContained;

  public City(){
    adjacentFields = new HashMap<Field, Boolean>();
    openPorts = new HashMap<Integer,Boolean>();
    slotsContained = new HashMap<Integer,Boolean>();
  }

  public void absorb(Region otherRegion){
    City otherCity = (City) otherRegion;
    openPorts.putAll(otherCity.openPorts);
    slotsContained.putAll(otherCity.slotsContained);
    adjacentFields.putAll(otherCity.adjacentFields);
  }

  public void closePort(int port){
    openPorts.remove(port);
    if(openPorts.isEmpty()){
      notifyComplete();
    }
  }

  private void notifyComplete(){
    Set<Field> adjFields = adjacentFields.keySet();
    for(Field field: adjFields){
      field.addCompleteCity();
    }
  }

  public void addAdjacent(Region adjacentRegion){
    adjacentFields.put((Field) adjacentRegion, true);
  }



}
