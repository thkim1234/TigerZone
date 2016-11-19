import java.util.*;

public class RegionManager{
  ArrayList<Field> fields;
  ArrayList<City> cities;
  ArrayList<Road> roads;
  TileManager tileManager;

  public RegionManager(){
    fields = new ArrayList<Field>();
    cities = new ArrayList<City>();
    roads = new ArrayList<Road>();
    tileManager = new TileManager();
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

  //just a nice lil PSF
  private Region makeRegion(char type){
    Region newRegion = new City();
    switch(type){
      case 'f':
        newRegion = new Field();
        break;
      case 'r':
        newRegion = new Road();
        break;
    }
    return newRegion;
  }

  //this will be the conversion from a tile into a regions array
  private RegionWithType[] createRegions(Tile t){

    //get the information about the regions based on the tile
    TileAttributes tileInfo = tileManager.getTileAttributes(t);

    RegionWithType[] newRegions = new RegionWithType[tileInfo.numRegions];

    //initialize the array of regions with the right types of region
    for(int i = 0; i<tileInfo.numRegions; i++){
      newRegions[i] = new RegionWithType(makeRegion(tileInfo.portTypes[i]), tileInfo.portTypes[i]);
    }

    //go back through and add adjacent fields and ports
    for(int i = 0; i<tileInfo.numRegions; i++){

      //add adjacent fields for this region
      for(int fieldIndex: tileInfo.fields[i]){
        newRegions[i].region.addAdjacent(newRegions[fieldIndex].region);
      }

      //add port numbers for this region
      for(int port: tileInfo.ports[i]){
        newRegions[i].region.addOpenPort(port);
      }
    }


    return newRegions;
  }
}
