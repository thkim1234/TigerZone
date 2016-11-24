import java.util.*;

public class RegionManager{
  HashMap<RegionContainer,Boolean> fields;
  HashMap<RegionContainer,Boolean> cities;
  HashMap<RegionContainer,Boolean> roads;
  HashMap<RegionContainer,Boolean> monasteries;
  HashMap<Integer, ArrayList<RegionContainer>> slotListeners;
  SlotMap slots;

  public RegionManager(SlotMap map){
    fields = new HashMap<RegionContainer,Boolean>();
    cities = new HashMap<RegionContainer,Boolean>();
    roads = new HashMap<RegionContainer,Boolean>();
    monasteries = new HashMap<RegionContainer,Boolean>();
    slotListeners = new HashMap<Integer,ArrayList<RegionContainer>>();
    slots = map;

    TileManager.init();
  }

  //update the regions array (corresponding to a slot) to reflect this placed tile
  public void addRegionsBasedOnTile(Tile tile, MoveOption move){

    //get the slot's region information (by ports)
    RegionContainer[] slotRegions = slots.get(move.location).getRegions();

    //make the potential new regions
    RegionContainer[] newRegions = createRegions(tile, move.location);

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

    notifyPlaced(move.location);
  }

  private HashMap<RegionContainer,Boolean> getListByType(char type){
    switch(type){
      case 'J':
        return fields;
      case 'T':
        return roads;
      case 'L':
        return monasteries;
    }
    return cities;
  }

  //this will be the conversion from a tile into a regions array
  private RegionContainer[] createRegions(Tile t, int location){

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

        //if it's in the center, initialize ports for neighboring tiles
        if(port == -1){
          initCenterRegion(newRegions[i], location);
        } else {
          //sort the regions by port
          regionsByPort[port] = newRegions[i];
        }
      }

    }

    return regionsByPort;
  }

  //add all ports in tiles surrounding this region
  private void initCenterRegion(RegionContainer centerRegion, int location){
    for(int neighbor: addForNeighbors){
      if(slots.containsKey(location+neighbor) && slots.get(location+neighbor).hasTile()){
        centerRegion.closePort(1);
      } else {
        if(slotListeners.containsKey(location+neighbor)){
          slotListeners.get(location+neighbor).add(centerRegion);
        } else {
          ArrayList<RegionContainer> newList = new ArrayList<RegionContainer>();
          newList.add(centerRegion);
          slotListeners.put(location+neighbor, newList);
        }
      }
    }
    getListByType(centerRegion.type).put(centerRegion, true);
  }

  private void notifyPlaced(int slotLocation){
    if(slotListeners.containsKey(slotLocation)){
      ArrayList<RegionContainer> observingRegions = slotListeners.get(slotLocation);
      for(RegionContainer region: observingRegions){
        region.closePort(1);
      }
    }
  }


  public String toString(){
    return Integer.toString(fields.keySet().size())+" fields: \n" + fields.keySet().toString()+"\n"
          + Integer.toString(cities.keySet().size())+" cities: \n" + cities.keySet().toString()+"\n"
          + Integer.toString(roads.keySet().size())+" roads: \n" + roads.keySet().toString()+"\n"
          + Integer.toString(monasteries.keySet().size())+" monasteries: \n" + monasteries.keySet().toString()+"\n";
  }

  private int[] oppositePort = {8,7,6,11,10,9,2,1,0,5,4,3};
  private int[] addForNeighbors = {-1001,-1000,-999,-1,1,999,1000,1001};

}
