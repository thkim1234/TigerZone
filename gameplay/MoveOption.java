package gameplay;

//to group all the information relevant to a move (the slot and the tile's rotation)
public class MoveOption {
  public int location;
  public int rotation;
  public MoveOption(int location, int rotation){
    this.location = location;
    this.rotation = rotation;
  }
}
