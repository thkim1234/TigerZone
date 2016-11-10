import java.util.*;

public class Slot{

  public static final int NUM_SIDES = 4;

  private int meeplePlacement; //to be made fixed later
  private Tile tile;
  protected SlotConnection[] connections;

  //initializes SlotConnections
  public Slot(){
      meeplePlacement = 0;
      connections = new SlotConnection[NUM_SIDES];
      for (int i = 0; i < NUM_SIDES; i++) {
          connections[i] = new SlotConnection(i);
      }
  }

  //returns true if this tile can fit with the given rotation
  public boolean canFit(Tile tile, int rotationAmt){

    for(int i = 0; i<NUM_SIDES; i++){

        if(connections[i]!=null){
            if(connections[i].type != '!' && connections[i].type != tile.getSide((rotationAmt + i) % NUM_SIDES)){
                return false;
            }
        }

    }

    return true;
  }

  //sets the tile, inits connection points based on tile
  public void setTile(Tile tile, int rotationAmt){
      this.tile = tile;
      for(int i = 0; i<NUM_SIDES; i++){
        connections[i].type = tile.getSide((i+rotationAmt) % NUM_SIDES);
    }
  }

  //to manage the interfacing between each Slot
  //each has an integer to denote which side it's on and a char to denote the type of connection
  private class SlotConnection{

    protected int side;
    protected char type;

      public SlotConnection(int side) {
          this.side = side;
          this.type = '!';
      }

    //sets this slotConnection based on the given side
    //and a (completed) connections slot
    protected void connect(Slot adjSlot, int side){
      this.side = side;
      this.type = adjSlot.connections[opposite(side)].type;
    }
  }

  public void connect(Slot slot, int side){
    connections[side].connect(slot,side);
  }

  public void placeMeeple (int meeplePlacement) {
      this.meeplePlacement = meeplePlacement;
  }

  //just for code readability
  private static int opposite(int i){ return (i+2)%4; }

  public String toString() {
      // Need to print connections (with their tiles), the tile in this slot, and meeple placement in this slot.

      return (this.tile != null)?("tile: "+this.tile):"(empty)";

  }
}
