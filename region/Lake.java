package region;

import java.util.*;

public class Lake extends Region{
  protected HashMap<Character, Boolean> animals;
  int crocodiles;

  public Lake(){
    super.init();
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
    animals.putAll(otherLake.animals);
  }

  public void addAnimal(char c){ animals.put(c, true); }

  public int totalScore(){
    int uniqueLiveAnimals = animals.size()-crocodiles;
    if(uniqueLiveAnimals < 0){
      uniqueLiveAnimals = 0;
    }
    int factor = (super.openPorts.size() == 0) ? 2 : 1;
    return factor*super.slotsContained.size()*(1+uniqueLiveAnimals);
  }


  public String toString(){
    return "L " + super.toString()+"\n";
  }

  public boolean complete(){
    return super.openPorts.size() == 0;
  }



}