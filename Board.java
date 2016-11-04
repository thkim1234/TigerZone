import java.util.*;
import java.lang.*;

public class Board{

  //private Map<Location, Slot> board;
  private ArrayList<Slot> openSlots;
  private ArrayList<Slot> placedSlots;

  public class MoveOption {
    Slot s;
    int rotation;
    MoveOption(Slot s, int rotation){
      this.s = s;
      this.rotation = rotation;
    }
  }

  public Board(){
    //create empty origin slot
    openSlots.add(new Slot());
  }

  public void placeTile(Slot s, Tile t, int rot){
    s.setTile(t,rot);
    placedSlots.add(s);
    openSlots.addAll(s.openOutSides());
  }

  public ArrayList<MoveOption> potentialMoves(Tile t){
    ArrayList<MoveOption> ans = new ArrayList<MoveOption>();
    for(Slot s: openSlots){
      for(int i=0; i<Slot.NUM_SIDES; i++){
        if(s.canFit(t,i)){
          ans.add(new MoveOption(s,i));
        }
      }
    }
    return ans;
  }

  public void placeMeepleOnBoard(Slot s, int meeplePlacement){
    s.placeMeeple(meeplePlacement);
  }
}
