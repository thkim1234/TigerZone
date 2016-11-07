import java.util.*;
import java.lang.*;

public class Board{

  //private Map<Location, Slot> board;
  private ArrayList<Integer> openLocations;
  private SlotMap map;
  public static final int CENTER = 72;
  private Slot firstSlot;

  //create empty origin slot
  public Board(Game gg){
    openLocations = new ArrayList<Integer>();
    openLocations.add(CENTER*1000+CENTER);
    map = new SlotMap();
    firstSlot = newSlot(CENTER*1000+CENTER);
    map.put(CENTER*1000+CENTER, firstSlot);
    GameVisualization g = new GameVisualization(gg);
    g.printBoard();

  }

  //places the tile given the moveOption
  public void placeTile(Tile t, MoveOption m){
    map.get(m.location).setTile(t,m.rotation);
    addNewOptions(m.location);
  }

  //tries all open slots and tile orientations, returns a list of possible moves
  public ArrayList<MoveOption> potentialMoves(Tile t){
    ArrayList<MoveOption> ans = new ArrayList<MoveOption>();
    Slot s;
    for(int l: openLocations){
      s = map.get(l);
      for(int i=0; i<Slot.NUM_SIDES; i++){
        if(s.canFit(t,i)){
          ans.add(new MoveOption(l,i));
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
    //System.out.println("calling newSlot from "+ location);
    Slot s = new Slot();
    int key;
    for(int i = 0; i<Slot.NUM_SIDES; i++){
      key = map.getAdjKey(location,i);
      //System.out.println(key);
      if(map.containsKey(key)){
        s.connect(map.get(key),i);
      }
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
      String keyStr = "";
      int k;
      Iterator<Integer> keysIter = map.keySet().iterator();
      Iterator<Slot> iter = map.values().iterator();
      while(iter.hasNext()) {
          k = keysIter.next();
          keyStr = Integer.toString(k%1000-72) + " "+ Integer.toString(k/1000-72);
          boardString += "location " + keyStr + ", " +iter.next() + "\n";
      }

      return boardString;
  }
}
