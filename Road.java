import java.util.*;

public class Road extends Region{
  protected HashMap<Integer, Boolean> openPorts;
  protected HashMap<Integer, Boolean> slotsContained;
  protected HashMap<Field,Boolean> adjacentFields;
  protected ArrayList<Character> animals;
  protected int crocodiles;

  public Road(){
    super.init();
    slotsContained = new HashMap<Integer,Boolean>();
    adjacentFields = new HashMap<Field,Boolean>();
  }

  //absorb another region
  public void absorb(Region otherRegion){
    super.absorb(otherRegion);
    Road otherRoad = (Road) otherRegion;
    slotsContained.putAll(otherRoad.slotsContained);
  }

  public void addCrocodile(){
    crocodiles ++;
  }


  //remove the specified port from the list of complete ports
  public void closePort(int port){
    super.closePort(port);
    if(openPorts.isEmpty()){
      super.score();
      //notifyComplete(); commented this out because we don't care about completed roads anmore
    }
  }

  public void addAnimal(char c){
    animals.add(c);
  }

  public int totalScore(){
    int liveAnimals = animals.size()-crocodiles;
    if(liveAnimals < 0){
      liveAnimals = 0;
    }
    return slotsContained.size()+(1+liveAnimals);
  }

  //observer pattern!
  //tell all the adjacent fields that this city completed
  private void notifyComplete(){
    Set<Field> adjFields = adjacentFields.keySet();
    for(Field field: adjFields){
      field.addCompleteRoad(this);
    }
  }

  //add an adjacent field (for later notification)
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
