package region;

import java.util.*;

public class Jungle extends Region{

  protected HashMap<RegionContainer, Boolean> adjacents;
  //protected HashMap<Den, Boolean> completedDens;
  //protected HashMap<GameTrail, Boolean> completedTrails;

  public Jungle(){
    super.init();
    adjacents = new HashMap<RegionContainer,Boolean>();
//    completedLakes = new HashMap<Lake,Boolean>();
//    completedDens = new HashMap<Den,Boolean>();
//    completedTrails = new HashMap<GameTrail, Boolean>();
  }

  //absorb another region
  public void absorb(Region otherRegion){
    super.absorb(otherRegion);
    Jungle otherJungle = (Jungle) otherRegion;
    adjacents.putAll(otherJungle.adjacents);
    //completedLakes.putAll(otherJungle.completedLakes);
    //completedDens.putAll(otherJungle.completedDens);
  }

  public void addCompleteLake(Lake lake){
    //completedLakes.put(lake, true);
  }

  public void addCompleteTrail(GameTrail gameTrail) { }//completedTrails.put(gameTrail, true); }

  public boolean complete(){
    return false;
  }

  public void addAdjacent(RegionContainer region){
    adjacents.put(region,true);
  }

  //
  public void addCompleteDen(Den den) { }//completedDens.put(den,true); }

  public int totalScore(){
    int lakes = 0;
    int dens = 0;
    ArrayList<Integer> scored = new ArrayList<Integer>();
    for(RegionContainer current: adjacents.keySet()){
      if(current.complete() && !scored.contains(current.getId())) {
        scored.add(current.getId());
        switch (current.type) {
          case 'L':
            lakes++;
            break;
          case 'X':
            dens++;
            break;
        }
      }
    }

    return 3*lakes + 5*dens;
    //return 3*completedLakes.size() + 5*completedDens.size();
  }



  public String toString(){
    return "J "+ super.toString() + " adj: " + adjacents.keySet().toString()+"\n";
  }
}