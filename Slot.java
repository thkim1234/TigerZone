public class Slot{

  //meeple placement

  private Orientation o;

  //borders

  private Tile t;

  public boolean canFit(Tile t, Orientation o){
    //if this tile can go here
    return true; // for now
  }

  public void setTile(Tile t, Orientation o){
    this.t = t;
    this.o = o;
  }


}
