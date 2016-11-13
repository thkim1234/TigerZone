import java.util.*;

public class RegionManager{
  ArrayList<Field> fields;
  ArrayList<City> cities;
  ArrayList<Road> roads;

  public RegionManager(){
    fields = new ArrayList<Field>();
    cities = new ArrayList<City>();
    roads = new ArrayList<Road>();
  }

  //update the regions array (corresponding to a slot) to reflect this placed tile
  public void addRegionsBasedOnTile(Slot slot, Tile tile, int rotation){
    Region[] slotRegions = slot.getRegions();

    //make the potential new regions
    RegionWithType[] newRegions = createRegions(tile);

    //iterate through the slot regions passed in
    for(int i = 0; i<slotRegions.length; i++){

      //if there is no region already, add the new one to the appropriate list
      if(slotRegions[i] == null){

        //set this region to the
        slotRegions[i] = newRegions[(i+3*rotation)%12].region;
        add(newRegions[(i+3*rotation)%12]);

      } else {
        //this region exists, so absorb the new one created
        slotRegions[i].absorb(newRegions[(i+3*rotation)%12].region);
      }
    }
  }

  //hopefully self explanatory
  private class RegionWithType{
    Region region;
    char type;
    RegionWithType(Region region, char type){
      this.region = region;
      this.type = type;
    }
  }

  //hopefully self explanatory
  private void add(RegionWithType rwt){
    switch(rwt.type){
      case 'f':
        fields.add((Field) rwt.region);
      break;
      case 'r':
        roads.add((Road) rwt.region);
      break;
      case 'c':
        cities.add((City) rwt.region);
      break;
    }
  }

  //this will be the conversion from a tile into a regions array
  private RegionWithType[] createRegions(Tile t){
    return null;
  }
}
