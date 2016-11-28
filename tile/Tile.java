package tile;

import board.Slot;

public class Tile {
    private char centerType;
    private char[] sideTypes = new char[4];
//    private char shieldLoc;

    public Tile () {
        centerType = 'J';
//        shieldLoc = '0';
        for(int i=0; i<Slot.NUM_SIDES; i++){
            sideTypes[i] = 'J';
        }

    }

    public Tile(String typeCode){
      int i;
      for(i=0; i<Slot.NUM_SIDES; i++){
        sideTypes[i] = typeCode.charAt(i);
      }

      centerType = typeCode.charAt(i);

    }

    public String toString() {
        String returnString = "";
        for(int i=0; i<Slot.NUM_SIDES; i++){
            returnString += this.sideTypes[i];
        }
        returnString += this.centerType;

        return returnString;
    }

    public char getSide(int i){
        return sideTypes[i];
    }
    public char getCenterType() { return this.centerType; }
//    public char getShieldLoc() { return this.shieldLoc; }
    public char[] getSideTypes() { return this.sideTypes; }


}
