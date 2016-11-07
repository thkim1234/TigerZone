import java.util.*;
import java.lang.*;

public class Board{

  //private Map<Location, Slot> board;
  private ArrayList<Integer> openLocations;
  private SlotMap map;
  public static final int CENTER = 72;
  private Slot firstSlot;

  //create empty origin slot
  public Board(){
    openLocations = new ArrayList<Integer>();
    openLocations.add(CENTER*1000+CENTER);
    map = new SlotMap();
    firstSlot = firstSlot(CENTER*1000+CENTER);
    map.put(CENTER*1000+CENTER, firstSlot);

  }

  //places the tile given the moveOption
  public void placeTile(Tile t, MoveOption m){
    map.get(m.location).setTile(t,m.rotation);
    addNewOptions(m.location);
    map.remove(m.location);
  }

  //tries all open slots and tile orientations, returns a list of possible moves
  public ArrayList<MoveOption> potentialMoves(Tile t){
    ArrayList<MoveOption> ans = new ArrayList<MoveOption>();
    Slot s;
    for(int location: openLocations){
      s = map.get(location);
      for(int i=0; i<Slot.NUM_SIDES; i++){
        if(s.canFit(t,i)){
          ans.add(new MoveOption(location,i));
        }
      }
    }
    return ans;
  }

  private void addNewOptions(int location){
    int key;
    for(int i = 0; i<Slot.NUM_SIDES; i++){
      key = map.getAdjKey(location,i);
      if(!map.containsKey(key)){
        openLocations.add(key);
        map.put(key,newSlot(key));
      }
    }
  }

  private Slot newSlot(int location){
    Slot s = new Slot();
    int key = location;
    for(int i = 1; i<=Slot.NUM_SIDES; i++){
//      key = map.get(location);
      if(map.containsKey(key)){
        s.connect(map.get(key),i);
      }
    }
    return s;
  }

  private Slot firstSlot(int location){
    Slot s = new Slot();
    int key = location;
    for(int i = 0; i<Slot.NUM_SIDES; i++){
//      key = map.getAdjKey(location, i);
//      if(map.containsKey(key)){
//      map.put(key,s);
      s.connect(map.get(key),i);
//      }
    }
    return s;
  }

  public void placeMeepleOnBoard(int loc, int meeplePlacement){
    map.get(loc).placeMeeple(meeplePlacement);
  }

  public ArrayList<Integer> getOpenLocations() {
    return this.openLocations;
  }
  public String toString() {
    //Need to print out all the slots of the board. Or print out map.values();
      String boardString = "";
      Iterator<Slot> iter = map.values().iterator();
      while(iter.hasNext()) {
          boardString += iter.next() + "\n";
      }

      return boardString;
  }
}
