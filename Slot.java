import java.util.*;

public class Slot{

  public static final int NUM_SIDES = 4;

  private int meeplePlacement; //to be made fixed later
  private Tile t;
  protected SlotConnection[] connections;

  //initializes SlotConnections
  public Slot(){
    connections = new SlotConnection[NUM_SIDES];
  }

  //returns true if this tile can fit with the given rotation
  public boolean canFit(Tile t, int rotationAmt){

    for(int i = 0; i<NUM_SIDES; i++){

      if(rotationAmt+i == NUM_SIDES){
        rotationAmt = -i;
      }

      if(connections[i].type != t.getSide(rotationAmt+i)){
        return false;
      }

    }

    return true;
  }

  // //returns an ArrayList of open slots adjacent to this one
  // public ArrayList<Slot> openOutSides(){
  //   ArrayList<Slot> ans = new ArrayList<Slot>();
  //   for(int i = 0; i<NUM_SIDES; i++){
  //     if(connections[i] == null){
  //
  //       ans.add(makeSide(i));
  //
  //       //somehow connect to other slots besides this one...
  //     }
  //   }
  //   return ans;
  // }

  //sets the tile, inits connection points based on tile
  public void setTile(Tile t, int rotationAmt){
    this.t = t;
    for(int i = 0; i<NUM_SIDES; i++){

      //wrap around
      if(rotationAmt+i == NUM_SIDES){
        rotationAmt = -i;
      }

      //set the connection points
      connections[i].type = t.getSide(rotationAmt+i);

    }
  }

  public void placeMeeple(int meeplePlacement){
    //call some combination of updateMeeple and updateMeepleOnConnection TBD
  }

  //creates a new slot connected to the given side of this slot
  private Slot makeSide(int side){
    Slot newSide = new Slot();
    newSide.connections[opposite(side)] = new SlotConnection();
    newSide.connect(this, opposite(side));

    return newSide;
  }

  //to manage the interfacing between each Slot
  //each has an integer to denote which side it's on and a char to denote the type of connection
  private class SlotConnection{

    protected Slot s;
    protected int side;
    protected char type;
    protected boolean[] meeplePlacement = new boolean[3];

    //sets this slotConnection based on the given side
    //and a (completed) connections slot
    protected void connect(Slot adjSlot, int side){
      this.s = adjSlot;
      this.side = side;
      Connection adjC = adjSlot.connections[opposite(side)];
      this.type = adjC.type;
      adjC.s = Slot.this;
      this.meeplePlacement[0] = adjC.meeplePlacement[2];
      this.meeplePlacement[1] = adjC.meeplePlacement[1];
      this.meeplePlacement[2] = adjC.meeplePlacement[0];
    }

    protected void updateMeepleOnConnection(int i){
      this.meeplePlacement[i] = true;
      if(s != null){
        s.updateMeeples(opposite(side), (i+2)%2);
      }
    }
  }

  public void connect(Slot slot, int side){
    connections[side].connect(slot,side);
  }

  public void updateMeeples(int side, int loc){
    for(int i = 0; i<NUM_SIDES; i++){
      if(i != side){
        //logically, we need to do something different here to determine
        //how updateMeepleOnConnection is called
        connections[i].updateMeepleOnConnection(loc);
      }
    }
  }

  //just for code readability
  private static int opposite(int i){ return (i-2>=0)?(i-2):(i+2); }

  public String toString() {
      // Need to print connections (with their tiles), the tile in this slot, and meeple placement in this slot.
      String slotString = "";
      slotString += "Tile: " + this.t +"\n";
      slotString += "\tMeeple Placement: " + this.meeplePlacement +"\n";
      for (int i = 0; i < connections.length; i++) {
          switch (connections[i].side) {
              case 0:
                  slotString += "\tAbove Tile: " + connections[i].s.t +"\n";
                  break;
              case 1:
                  slotString += "\tRight Tile: " + connections[i].s.t +"\n";
                  break;
              case 2:
                  slotString += "\tBelow Tile: " + connections[i].s.t +"\n";
                  break;
              case 3:
                  slotString += "\tLeft Tile: " + connections[i].s.t +"\n";
                  break;
              default:
                  break;
          }
      }

      return slotString;

  }
}
