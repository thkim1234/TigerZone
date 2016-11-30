package region;

import board.SlotMap;
import gameplay.MoveOption;
import tile.Tile;
import tile.TileAttributes;
import tile.TileManager;
import tools.RotatedIterator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class RegionManager{
  HashMap<RegionContainer,Boolean> jungles;
  HashMap<RegionContainer,Boolean> lakes;
  HashMap<RegionContainer,Boolean> trails;
  HashMap<RegionContainer,Boolean> dens;
  HashMap<Region, ArrayList<RegionContainer>> containersByRegion;
  HashMap<Integer, ArrayList<RegionContainer>> slotListeners;
  SlotMap slots;
  Stack<RegionContainer> regionsToScore;

  private static int id = 0;

  public static int getId(){
    return id++;
  }

  public RegionManager(SlotMap map){
    jungles = new HashMap<RegionContainer,Boolean>();
    lakes = new HashMap<RegionContainer,Boolean>();
    trails = new HashMap<RegionContainer,Boolean>();
    dens = new HashMap<RegionContainer,Boolean>();
    slotListeners = new HashMap<Integer,ArrayList<RegionContainer>>();
    regionsToScore = new Stack<RegionContainer>();
    containersByRegion = new HashMap<Region, ArrayList<RegionContainer>>();
    slots = map;

    TileManager.init();
  }

  //update the regions array (corresponding to a slot) to reflect this placed tile
  public void addRegionsBasedOnTile(Tile tile, MoveOption move){

    //get the slot's region information (by ports)
    RegionContainer[] slotRegions = slots.get(move.location).getRegions();

    //make the potential new regions
    RegionContainer[] newRegions = createRegions(tile, move.location);

    ArrayList<Region> regionsToReplace = new ArrayList<Region>();
    ArrayList<RegionContainer> replacements = new ArrayList<RegionContainer>();

    Iterator<RegionContainer> newRegionsIt = (new RotatedIterator<RegionContainer>(newRegions,3*move.rotation)).iterator();

    RegionContainer currentNewRegion;

    //iterate through the slot regions passed in
    for(int i = 0; i<slotRegions.length; i++){

      currentNewRegion = newRegionsIt.next();

      //if there is no region to merge with, do so
      if(slotRegions[i] != null){

        //absorb the existing neighboring region
        currentNewRegion.absorb(slotRegions[i]);

        //replace the neighboring region with the newly merged region

        regionsToReplace.add(slotRegions[i].getRegion());
        replacements.add(currentNewRegion);
        slotRegions[i].replaceWith(currentNewRegion);


        //remove the port we linked through from the list of open ports
        currentNewRegion.closePort(move.location*100+i);

      } else {

        //otherwise, open the port on the adjacent tile
        currentNewRegion.addOpenPort(slots.getAdjKey(move.location,i/3)*100+oppositePort[i]);

        //set the region on the slot
        slotRegions[i] = currentNewRegion;

        //add the region to the lists
        getListByType(currentNewRegion.type).put(currentNewRegion, true);
      }

    }

    //updateScores();

    //go back and replace regions
    for(int i = 0; i<regionsToReplace.size(); i++){
      try {
        replaceRegion(regionsToReplace.get(i),replacements.get(i));
      }
      catch (NullPointerException e) {
        System.err.println("NullPointerException: " + e.getMessage());
      }
      catch (ArrayIndexOutOfBoundsException e) {
        System.err.println("ArrayIndexOutOfBoundsException: " + e.getMessage());
      }
      catch (IndexOutOfBoundsException e) {
        System.err.println("IndexOutOfBoundsException: " + e.getMessage());
      }
    }

    notifyPlaced(move.location);
  }

  private HashMap<RegionContainer,Boolean> getListByType(char type){
    switch(type){
      case 'J':
        return jungles;
      case 'T':
        return trails;
      case 'X':
        return dens;
    }
    return lakes;
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
      addContainer(newRegions[i]);
      newRegions[i].addSlot(location);
    }

    RegionContainer[] regionsByPort = new RegionContainer[12];

    //go back through and add adjacent jungles and ports
    for(int i = 0; i<tileInfo.numRegions; i++){

      //add these to go back and check if finished later
      regionsToScore.push(newRegions[i]);

      //add adjacent jungles for this region
      if (tileInfo.jungles[i].length > 0) {
        for (int jungleIndex : tileInfo.jungles[i]) {
          newRegions[jungleIndex].addAdjacent(newRegions[i]);
          newRegions[i].addAdjacent(newRegions[jungleIndex]);
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

      if(tileInfo.animal != '-' && tileInfo.animal != 'X'){
        newRegions[i].addAnimal(tileInfo.animal);
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
    slots.get(location).setCenter(centerRegion);
    getListByType(centerRegion.type).put(centerRegion, true);
  }

  private void notifyPlaced(int slotLocation){
    if(slotListeners.containsKey(slotLocation)){
      ArrayList<RegionContainer> observingRegions = slotListeners.get(slotLocation);
      for(RegionContainer region: observingRegions){
        region.closePort(1);
        if(region.complete()){
          regionsToScore.add(region);
        }
      }
    }
  }

  public void updateScores(){
    RegionContainer currentRegion;
    while(!regionsToScore.isEmpty()){
      currentRegion = regionsToScore.pop();
      if(currentRegion.complete())
        currentRegion.score();
    }
  }

  private void replaceRegion(Region oldRegion, RegionContainer newRegion){
    if(oldRegion == newRegion.getRegion() || !containersByRegion.containsKey(oldRegion))
      return;
    for(RegionContainer container : containersByRegion.get(oldRegion)){
      container.replaceWith(newRegion);
      addContainer(container);
    }
    containersByRegion.remove(oldRegion);
  }

  private void addContainer(RegionContainer container){
    Region region = container.getRegion();
    if(containersByRegion.containsKey(region)){
      containersByRegion.get(region).add(container);
    } else {
      ArrayList<RegionContainer> containers = new ArrayList<RegionContainer>();
      containers.add(container);
      containersByRegion.put(region,containers);
    }
  }

  public String toString(){
    return containersByRegion.keySet().toString()+"\n";
  }

  private int[] oppositePort = {8,7,6,11,10,9,2,1,0,5,4,3};
  private int[] addForNeighbors = {-1001,-1000,-999,-1,1,999,1000,1001};

}