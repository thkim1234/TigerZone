import java.util.*;

public class Field extends Region{

  protected HashMap<City, Boolean> completedCities;
  protected HashMap<Monastery, Boolean> completedMonasteries;
  protected HashMap<Road, Boolean> completedRoads;

  public Field(){
    super.init();
    completedCities = new HashMap<City,Boolean>();
    completedMonasteries = new HashMap<Monastery,Boolean>();
    completedRoads = new HashMap<Road, Boolean>();
  }

  //absorb another region
  public void absorb(Region otherRegion){
    super.absorb(otherRegion);
    Field otherField = (Field) otherRegion;
    completedCities.putAll(otherField.completedCities);
    completedMonasteries.putAll(otherField.completedMonasteries);
  }

  public void addCompleteCity(City city){
    completedCities.put(city, true);
  }

  public void addCompleteRoad(Road road) { completedRoads.put(road, true); }

  //
  public void addCompleteMonastery(Monastery monastery) { completedMonasteries.put(monastery,true); }

  public int totalScore(){
    return 3*completedCities.size() + 5*completedMonasteries.size();
  }
}
