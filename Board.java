import java.util.*;
import java.lang.*;

public class Board{

  //private Map<Location, Slot> board;
  private ArrayList<Slot> openSlots;
  private ArrayList<Slot> placedSlots;

  //create empty origin slot
  public Board(){
    openSlots = new ArrayList<Slot>();
    placedSlots = new ArrayList<Slot>();
    openSlots.add(new Slot());
  }

  //places the tile given the moveOption
  public void placeTile(Tile t, MoveOption m){
    m.makeMove(t);
    placedSlots.add(m.slot);
    openSlots.addAll(m.slot.openOutSides());
  }

  //tries all open slots and tile orientations, returns a list of possible moves
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
