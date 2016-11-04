public class Tile {
    private char centerType;
    private char[] sideTypes;
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
      shieldLoc = typeCode.charAt(i+1);

    }

}
