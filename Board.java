import java.util.*;

public class Board{

  private Map<Location, Slot> board;

  public void placeTile(Location l, Orientation o, Tile t){
    if(board.get(l).canFit(t,o)){
      board.get(l).setTile(t,o);
    }
  }

  public void placeMeepleOnBoard(Location l, int meeplePlacement){
    board.get(l).placeMeeple(meeplePlacement);
  }
}
