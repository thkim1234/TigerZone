package region;

import java.util.*;

public class GameTrail extends Region{
  protected HashMap<Jungle,Boolean> adjacentJungles;
  protected ArrayList<Character> animals;
  protected int crocodiles;

  public GameTrail(){
    super.init();
    adjacentJungles = new HashMap<Jungle,Boolean>();
    animals = new ArrayList<Character>();

  }

  //absorb another region
  public void absorb(Region otherRegion){
    super.absorb(otherRegion);
    GameTrail otherGameTrail = (GameTrail) otherRegion;
    animals.addAll(otherGameTrail.animals);
  }

  public void addCrocodile(){
    crocodiles ++;
  }

  public void addAnimal(char c){
    animals.add(c);
  }

  public int totalScore(){
    int liveAnimals = animals.size()-crocodiles;
    if(liveAnimals < 0){
      liveAnimals = 0;
    }
    return super.slotsContained.size()+liveAnimals;
  }


  public boolean complete(){
    return super.openPorts.size() == 0;
  }

  public String toString(){
    return "T "+super.toString() + "\n";
  }

}