package region;

import java.util.*;

public class Lake extends Region{
  protected HashMap<Jungle,Boolean> adjacentJungles;
  protected HashMap<Integer, Boolean> slotsContained;
  protected HashMap<Character, Boolean> animals;
  int crocodiles;

  public Lake(){
    super.init();
    adjacentJungles = new HashMap<Jungle, Boolean>();
    slotsContained = new HashMap<Integer,Boolean>();
    animals = new HashMap<Character, Boolean>();
    crocodiles = 0;
  }

  public void addCrocodile(){
    crocodiles ++;
  }

  public void absorb(Region otherRegion){
    super.absorb(otherRegion);
    Lake otherLake = (Lake) otherRegion;
    slotsContained.putAll(otherLake.slotsContained);
    adjacentJungles.putAll(otherLake.adjacentJungles);
  }

  public void addAnimal(char c){ animals.put(c, true); }

  public void closePort(int port){
    super.closePort(port);
    if(openPorts.isEmpty()){
      notifyComplete();
    }
  }

  private void notifyComplete(){
    Set<Jungle> adjJungles = adjacentJungles.keySet();
    for(Jungle jungle : adjJungles){
      jungle.addCompleteLake(this);
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
