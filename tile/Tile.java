package tile;

import board.Slot;

public class Tile {
    private char centerType;
    private char[] sideTypes = new char[4];
    private char shieldLoc;

    public char getSide(int i){
      return sideTypes[i];
    }

    public Tile(String typeCode){
      int i;
      for(i=0; i<Slot.NUM_SIDES; i++){
        sideTypes[i] = typeCode.charAt(i);
      }

      centerType = typeCode.charAt(i);
      //shieldLoc = typeCode.charAt(i+1);

    }

    public String toString() {
        String returnString = "";
        for(int i=0; i<Slot.NUM_SIDES; i++){
            returnString += this.sideTypes[i];
        }
        returnString += this.centerType;
        //returnString += this.shieldLoc;

        return returnString;
    }

}
