import java.util.*;

public class City extends Region{
  protected HashMap<Field,Boolean> adjacentFields;
  protected HashMap<Integer, Boolean> slotsContained;
  protected HashMap<Character, Boolean> animals;
  int crocodiles;

  public City(){
    super.init();
    adjacentFields = new HashMap<Field, Boolean>();
    slotsContained = new HashMap<Integer,Boolean>();
    animals = new HashMap<Character, Boolean>();
    crocodiles = 0;
  }

  public void addCrocodile(){
    crocodiles ++;
  }

  public void absorb(Region otherRegion){
    super.absorb(otherRegion);
    City otherCity = (City) otherRegion;
    slotsContained.putAll(otherCity.slotsContained);
    adjacentFields.putAll(otherCity.adjacentFields);
  }

  public void addAnimal(char c){ animals.put(c, true); }

  public void closePort(int port){
    super.closePort(port);
    if(openPorts.isEmpty()){
      notifyComplete();
    }
  }

  private void notifyComplete(){
    Set<Field> adjFields = adjacentFields.keySet();
    for(Field field: adjFields){
      field.addCompleteCity(this);
    }
  }

  public int totalScore(){
    int uniqueLiveAnimals = animals.size()-crocodiles;
    if(uniqueLiveAnimals < 0){
      uniqueLiveAnimals = 0;
    }
    int factor = (super.openPorts.size() == 0) ? 2 : 1;
    return factor*slotsContained.size()+(1+uniqueLiveAnimals);
  }

  public void addAdjacent(Region adjacentRegion){
    adjacentFields.put((Field) adjacentRegion, true);
  }

  public String toString(){
    String s = super.toString();
    Iterator<Field> it = adjacentFields.keySet().iterator();
    s += "  adjacent fields: ";
    while(it.hasNext()){
      s += "  "+ it.next();
    }

    return s + "\n";
  }



}
