package region;

import java.util.*;

public class Jungle extends Region{

  protected HashMap<Lake, Boolean> completedLakes;
  protected HashMap<Den, Boolean> completedDens;
  protected HashMap<GameTrail, Boolean> completedTrails;

  public Jungle(){
    super.init();
    completedLakes = new HashMap<Lake,Boolean>();
    completedDens = new HashMap<Den,Boolean>();
    completedTrails = new HashMap<GameTrail, Boolean>();
  }

  //absorb another region
  public void absorb(Region otherRegion){
    super.absorb(otherRegion);
    Jungle otherJungle = (Jungle) otherRegion;
    completedLakes.putAll(otherJungle.completedLakes);
    completedDens.putAll(otherJungle.completedDens);
  }

  public void addCompleteLake(Lake lake){
    completedLakes.put(lake, true);
  }

  public void addCompleteTrail(GameTrail gameTrail) { completedTrails.put(gameTrail, true); }

  public boolean complete(){
    return false;
  }

  //
  public void addCompleteDen(Den den) { completedDens.put(den,true); }

  public int totalScore(){
    return 3*completedLakes.size() + 5*completedDens.size();
  }
}
