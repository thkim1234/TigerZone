import java.util.*;
import java.lang.*;

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

  //Map of slot objects, stored by indices relative to the origin
  private SlotMap map;

  //This number is so that we need not incorporate negatives;
  //the center of the board should be at least equal to the number of tiles
  public static final int CENTER = 72;

  //create empty board with new origin slot at center
  public Board(){
    openLocations = new ArrayList<Integer>();
    openLocations.add(CENTER*1001);
    map = new SlotMap();
    map.put(CENTER*1001, newSlot(CENTER*1001));
  }

  //places the tile given the moveOption
  public void placeTile(Tile tile, MoveOption move){

    //access the slot object at the given index, sets the tile within that slot object
    map.get(move.location).setTile(tile,move.rotation);

    //update openLocations to reflect the updated board
    addNewOptions(move.location);

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
        }
      }
    }
    return possibleMoves;
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
        map.put(key,newSlot(key));
      }
    }
  }

  //creates a brand new slot and connects it to any pre-existing, adjacent slots on the map
  private Slot newSlot(int location){

    Slot slot = new Slot();
    int key;

    //test the adjacent slots in every direction
    for(int direction = 0; direction<Slot.NUM_SIDES; direction++){

      //get the index in this direction of the adjacent tile
      key = map.getAdjKey(location, direction);

      //if this index is in the map, connect this slot in that direction
      if(map.containsKey(key)){
        slot.connect(map.get(key),direction);
      }
    }
    return slot;
  }

  //will change
  public void placeMeepleOnBoard(int loc, int meeplePlacement){
    map.get(loc).placeMeeple(meeplePlacement);
  }

  //accessor
  public ArrayList<Integer> getOpenLocations() {
    return this.openLocations;
  }

  public String toString() {
      String boardString = "";
      Iterator<Integer> iter = map.keySet().iterator();
      int current;
      while(iter.hasNext()) {
          current = iter.next();
          boardString += (current/1000-CENTER)+" "+(current%1000-CENTER)+"    "+map.get(current) + "\n";
      }

      return boardString;
  }
}
