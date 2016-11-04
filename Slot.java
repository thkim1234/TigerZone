public class Slot{

  static int NUM_SIDES = 4;

  private int meeplePlacement;
  protected Slot[] adjacent;
  protected char[] sideTypes;

  //1 = top, 2 = right, -1 = bottom, -2 = left;
  public Slot(Slot[] sides, int[] whichSide){

    adjacent = new Slot[NUM_SIDES];
    sideTypes = new char[NUM_SIDES];

    for(int i=0; i<sides.length; i++){
      connect(sides[i],whichSide[i]);
    }

  }

  private void connect(Slot s, int dir){
    this.adjacent[dir] = s;
    s.adjacent[-dir] = this;
    this.sideTypes[dir] = s.sideTypes[-dir];
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
