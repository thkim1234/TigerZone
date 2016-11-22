import java.util.*;

public class RegionManager{
  HashMap<RegionContainer,Boolean> fields;
  HashMap<RegionContainer,Boolean> cities;
  HashMap<RegionContainer,Boolean> roads;
  SlotMap slots;

  public RegionManager(SlotMap map){
    fields = new HashMap<RegionContainer,Boolean>();
    cities = new HashMap<RegionContainer,Boolean>();
    roads = new HashMap<RegionContainer,Boolean>();
    slots = map;

    TileManager.init();
  }

  //update the regions array (corresponding to a slot) to reflect this placed tile
  public void addRegionsBasedOnTile(Tile tile, MoveOption move){

    //get the slot's region information (by ports)
    RegionContainer[] slotRegions = slots.get(move.location).getRegions();

    //make the potential new regions
    RegionContainer[] newRegions = createRegions(tile);

    Iterator<RegionContainer> newRegionsIt = (new RotatedIterator<RegionContainer>(newRegions,move.rotation)).iterator();

    RegionContainer currentNewRegion;

    //iterate through the slot regions passed in
    for(int i = 0; i<slotRegions.length; i++){

      currentNewRegion = newRegionsIt.next();

      //if there is no region to merge with, do so
      if(slotRegions[i] != null){

        //absorb the existing neighboring region
        currentNewRegion.absorb(slotRegions[i]);

        //replace the neighboring region with the newly merged region
        slotRegions[i].replaceWith(currentNewRegion);

        //erase old region from the list
        getListByType(currentNewRegion.type).remove(slotRegions[i]);

        //remove the port we linked through from the list of open ports
        currentNewRegion.closePort(move.location*100+i);

      } else {

        //otherwise, open the port on the adjacent tile
        currentNewRegion.addOpenPort(slots.getAdjKey(move.location,i/3)*100+oppositePort[i]);
      }

      //set the region on the slot
      slotRegions[i] = currentNewRegion;

      //add the region to the lists
      getListByType(currentNewRegion.type).put(currentNewRegion, true);

    }
  }

  private HashMap<RegionContainer,Boolean> getListByType(char type){
    switch(type){
      case 'g':
        return fields;
      case 'r':
        return roads;
    }
    return cities;
  }

  //this will be the conversion from a tile into a regions array
  private RegionContainer[] createRegions(Tile t){

    //get the information about the regions based on the tile
    TileAttributes tileInfo = TileManager.getTileAttributes(t);

    //
    RegionContainer[] newRegions = new RegionContainer[tileInfo.numRegions];

    //initialize the array of regions with the right types of region
    for(int i = 0; i<tileInfo.numRegions; i++){
      newRegions[i] = new RegionContainer(tileInfo.portTypes[i]);
    }

    RegionContainer[] regionsByPort = new RegionContainer[12];

    //go back through and add adjacent fields and ports
    for(int i = 0; i<tileInfo.numRegions; i++){

      //add adjacent fields for this region
      if (tileInfo.fields[i].length > 0) {
        for (int fieldIndex : tileInfo.fields[i]) {
          newRegions[i].addAdjacent(newRegions[fieldIndex]);
        }
      }

      //sort the regions by port
      for(int port: tileInfo.ports[i]){

        //sort the regions by port
        regionsByPort[port] = newRegions[i];
      }

    }


    return regionsByPort;
  }

  public String toString(){
    return Integer.toString(fields.keySet().size())+" fields: \n" + fields.keySet().toString()+"\n"
          + Integer.toString(cities.keySet().size())+" cities: \n" + cities.keySet().toString()+"\n"
          + Integer.toString(roads.keySet().size())+" roads: \n" + roads.keySet().toString()+"\n";
  }

  private int[] oppositePort = {8,7,6,11,10,9,2,1,0,5,4,3};
}
