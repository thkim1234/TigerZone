package region;

import java.util.*;

public class GameTrail extends Region{
  protected HashMap<Integer, Boolean> slotsContained;
  protected HashMap<Jungle,Boolean> adjacentJungles;
  protected ArrayList<Character> animals;
  protected int crocodiles;

  public GameTrail(){
    super.init();
    slotsContained = new HashMap<Integer,Boolean>();
    adjacentJungles = new HashMap<Jungle,Boolean>();
  }

  //absorb another region
  public void absorb(Region otherRegion){
    super.absorb(otherRegion);
    GameTrail otherGameTrail = (GameTrail) otherRegion;
    slotsContained.putAll(otherGameTrail.slotsContained);
  }

  public void addCrocodile(){
    crocodiles ++;
  }


  //remove the specified port from the list of complete ports
  public void closePort(int port){
    super.closePort(port);
    if(super.openPorts.isEmpty()){
      super.score();
      //notifyComplete(); commented this out because we don't care about completed trails anmore
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
  //tell all the adjacent jungles that this lake completed
  private void notifyComplete(){
    Set<Jungle> adjJungles = adjacentJungles.keySet();
    for(Jungle jungle : adjJungles){
      jungle.addCompleteTrail(this);
    }
  }

  //add an adjacent jungle (for later notification)
  public void addAdjacent(Region adjacentRegion){
    adjacentJungles.put((Jungle) adjacentRegion, true);
  }

  public String toString(){
    String s = super.toString();
    Iterator<Jungle> it = adjacentJungles.keySet().iterator();
    s += " adjacent jungles: {";
    while(it.hasNext()){
      s += it.next()+",";
    }

    return s + "}\n";
  }

}
