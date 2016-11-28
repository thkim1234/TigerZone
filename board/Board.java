package board;
import java.util.*;
import java.lang.*;
import region.*;
import gameplay.*;
import tile.*;

/* Stores all of the information about the
Current objects in the board.
The central data structures:
- A Map of slot objects, stored by indices relative to the origin
  - in this, slots are mapped by an integer whose upper digits are row and lower digits are column
  - index = row*1000+column
- An arrayList of open locations for potential moves, updated whenever a new tile is placed*/

public class Board{

  //Open locations for potential moves, updated whenever a new tile is placed
  private ArrayList<Integer> openLocations;

  //effectively a map of regions
  RegionManager regionManager;

  //Map of slot objects, stored by indices relative to the origin
  private SlotMap map;

  //This number is so that we need not incorporate negatives;
  //the center of the board should be at least equal to the number of tiles
  public static final int CENTER = 77;

  //create empty board with new origin slot at center
  public Board(){
    openLocations = new ArrayList<Integer>();
    openLocations.add(CENTER*1001);
    map = new SlotMap();
    map.put(CENTER*1001, new Slot());
    regionManager = new RegionManager(map);
  }

  //places the tile given the moveOption
  public void placeTile(Tile tile, MoveOption move){

    //get the slot we're putting this in
    Slot slot = map.get(move.location);

    //access the slot object at the given index, sets the tile within that slot object
    slot.setTile(tile,move.rotation);

    //update the map of regions to reflect the new information
    regionManager.addRegionsBasedOnTile(tile,move);

    //update openLocations to reflect the updated board
    addNewOptions(move.location);

    updateAdjacent(move.location);

    //remove this slot from the openLocations (it's been used)
    int indexToRemove = openLocations.indexOf(move.location);
    openLocations.remove(indexToRemove);
  }

  //tries all slots in openLocations with all orientations of a given tile, returns a list of possible moves
  public ArrayList<MoveOption> potentialMoves(Tile tile){

    ArrayList<MoveOption> possibleMoves = new ArrayList<MoveOption>();
    Slot slot;

    //iterate through the slots in open locations
    for(int location: openLocations){
      slot = map.get(location);

      //try all possible locations
      for(int rotation=0; rotation<Slot.NUM_SIDES; rotation++){

        //if that would be a valid placement of the tile, add it to possibleMoves
        if(slot.canFit(tile,rotation)){
          possibleMoves.add(new MoveOption(location,rotation));
          //for AI testing
          //possibleMoves2.add(new MoveOption(location,rotation, tigerLocation, tigerType));

        }
      }
    }
    return possibleMoves;
  }

  public ArrayList<TigerOption> potentialTigers(ArrayList<MoveOption> possibleMoves, Tile tile){

    ArrayList<TigerOption> tigerMoves = new ArrayList<TigerOption>();

    Iterator<MoveOption> iter = possibleMoves.iterator();
    MoveOption currentMove;

    Slot slot;
     
    while(iter.hasNext()){
        currentMove = iter.next();
        TileAttributes tileInfo = TileManager.getTileAttributes(tile);
        slot = map.get(currentMove.location);

        //not sure what's going on here...
        
        for(int i = 0; i < tileInfo.numRegions; i++){

           boolean temp = true;

            //test if the region can have a tiger placed, set temp to false if unable to

           if (temp == true){

            tigerMoves.add(new TigerOption(currentMove.location, currentMove.rotation,/*find min*/ getPlacementLocation(tileInfo.placement[i],currentMove.rotation), tileInfo.portTypes[i]));

           }
        }
    }

    return tigerMoves;
  }

  //updates the openLocations ArrayList with the slots that have opened up from the current slot
  private void addNewOptions(int location){
    int key;

    //try all adjacent slots
    for(int i = 0; i<Slot.NUM_SIDES; i++){
      key = map.getAdjKey(location,i);

      //if this key doesn't exist in the map,
      if(!map.containsKey(key)){

        //add a new slot to the board, and denote that the location is open
        openLocations.add(key);
        map.put(key,new Slot());
      }
    }
  }

  //creates a brand new slot and connects it to any pre-existing, adjacent slots on the map

  private void updateAdjacent(int slotNum){
    Slot slot = map.get(slotNum);
    int key;

    for(int i = 0; i<slot.NUM_SIDES; i++){
      map.get(map.getAdjKey(slotNum,i)).connect(slot,(i+2)%4);
    }
  }

  public void updateScores(){
    regionManager.updateScores();
  }

//  public void doAllScores(){
//    regionManager.doAllScores();
//  }

  //will change
  public void placeTigerOnBoard(int loc, int tigerPlacement, Player player){
    map.get(loc).placeTiger(tigerPlacement, player.giveTiger());

  }

  public String toString() {
      String boardString = "LOCATIONS: \n";
      Iterator<Integer> iter = map.keySet().iterator();
      int current;
      while(iter.hasNext()) {
          current = iter.next();
          boardString += (current/1000-CENTER)+" "+(current%1000-CENTER)+"    "+map.get(current) + "\n";
      }

      boardString += "\n REGIONS: \n" + regionManager;

      return boardString;
  }

  //Accessors
  public SlotMap getMap(){ return map; }
  public ArrayList<Integer> getOpenLocations() {
    return this.openLocations;
  }
  public Slot getSlot(int location){ return map.get(location); }

  private int getPlacementLocation(int[] a, int b){ return 0; }

}
