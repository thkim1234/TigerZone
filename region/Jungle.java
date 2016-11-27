package region;

import java.util.*;

public class Jungle extends Region{

  protected HashMap<RegionContainer, Boolean> adjacents;
  //protected HashMap<Den, Boolean> dens;
  //protected HashMap<GameTrail, Boolean> trails;

  public Jungle(){
    super.init();
    adjacents = new HashMap<RegionContainer,Boolean>();
    //dens = new HashMap<Den,Boolean>();
    //trails = new HashMap<GameTrail, Boolean>();
  }

  //absorb another region
  public void absorb(Region otherRegion){
    super.absorb(otherRegion);
    Jungle otherJungle = (Jungle) otherRegion;
    adjacents.putAll(otherJungle.adjacents);
    //lakes.putAll(otherJungle.lakes);
    //dens.putAll(otherJungle.dens);
  }

  public void addAdjacent(RegionContainer region){
    adjacents.put(region,true);
//    switch(region.type){
//      case 'L':
//        lakes.put((Lake) region.getRegion(), true);
//        break;
//      case 'X':
//        dens.put((Den) region.getRegion(), true);
//        break;
//      case 'T':
//        trails.put((GameTrail) region.getRegion(), true);
//        break;
//    }
  }

//  private void addLake(Lake lake){
//    lakes.put(lake, true);
//  }

//  public void addCompleteTrail(GameTrail gameTrail) { trails.put(gameTrail, true); }

  public boolean readyToScore(){
    return false;
  }

  //
  //public void addCompleteDen(Den den) { dens.put(den,true); }

  public int totalScore(){
    return 3*countComplete('L') + 5*countComplete('X');
  }

  private int countComplete(char type){
    int count = 0;
    HashMap<Integer,Boolean> counted = new HashMap<Integer,Boolean>();
    for(RegionContainer region: adjacents.keySet()){
      if(region.type == type && region.readyToScore() == true && !counted.containsKey(region.getId())){
        counted.put(region.getId(),true);
        count ++;
      }
    }
    return count;
  }

}
