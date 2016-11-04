import java.util.*;

public class Slot{

  public static int NUM_SIDES = 4;

  private int meeplePlacement;
  protected Slot[] adjacent;
  protected char[] sideTypes;

  public class SlotConnection{
    Slot s;
    int side;
    SlotConnection(Slot s, int side){
      this.s = s;
      this.side = side;
    }
  }

  //0 = top, 1 = right, 2 = bottom, 3 = left;
  public Slot(SlotConnection ... slotConnections){

    adjacent = new Slot[NUM_SIDES];
    sideTypes = new char[NUM_SIDES];

    for(SlotConnection sc: slotConnections){
      connect(sc.s,sc.side);
    }

  }

  private void connect(Slot s, int dir){
    int opposite = (dir-2>=0)?(dir-2):(dir+2);
    this.adjacent[dir] = s;
    s.adjacent[opposite] = this;
    this.sideTypes[dir] = s.sideTypes[opposite];
  }

  //borders

  private Tile t;

  public boolean canFit(Tile t, int rotationAmt){

    for(int i = 0; i<NUM_SIDES; i++){

      if(rotationAmt+i == NUM_SIDES){
        rotationAmt = -i;
      }

      if(sideTypes[i] != t.getSide(rotationAmt+i)){
        return false;
      }

    }

    return true;
  }

  private Slot next(int dir){
    return adjacent[dir];
  }

  public ArrayList<Slot> openOutSides(){
    ArrayList<Slot> ans = new ArrayList<Slot>();
    for(int i = 0; i<NUM_SIDES; i++){
      if(adjacent[i] == null){

        ans.add(new Slot(new SlotConnection(this, (i-2>=0)?(i-2):(i+2))));

        //somehow connect to other slots besides this one...
      }
    }
    return ans;
  }

  public void setTile(Tile t, int rotationAmt){
    this.t = t;
    for(int i = 0; i<NUM_SIDES; i++){

      if(rotationAmt+i == NUM_SIDES){
        rotationAmt = -i;
      }

      sideTypes[i] = t.getSide(rotationAmt+i);

    }
  }

  public void placeMeeple(int meeplePlacement){
    this.meeplePlacement = meeplePlacement;
  }


}
