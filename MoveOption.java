//to group all the information relevant to a move (the slot and the tile's rotation)
public class MoveOption {
  public Slot slot;
  public int rotation;
  public MoveOption(Slot slot, int rotation){
    this.slot = slot;
    this.rotation = rotation;
  }
  public void makeMove(Tile t){
    slot.setTile(t,rotation);
  }
}
