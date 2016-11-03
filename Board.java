import java.util.*;

public class Board{

  private Slot[][] board;

  public void placeTile(int r, int c, Tile t /*, some way of showing orientation*/){
    if(board[r][c].canFit(t,o)){
      board[r][c].placeTile(t,o);
    }
  }
}
