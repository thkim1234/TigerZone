// /**
//  * Created by camerongera on 11/6/16.
//  */
// public class Slot1 {
//
//     public static final int NUM_SIDES = 4;
//     private Tile tile;
//     private int orientation;
//     private char[] sides = new char[NUM_SIDES];
//     private Slot[] sidePorts = new Slot[NUM_SIDES];
//
//     public Slot1() {
//         tile = new Tile();
//         orientation = -1;
//         for (int i = 0; i < NUM_SIDES; i++) {
//             sidePorts[i] = new Slot();
//         }
//     }
//
//     public void setSlot(Tile tile, int orientation) {
//         this.tile = tile;
//         this.orientation = orientation;
//         for (int i = 0; i < NUM_SIDES; i++) {
//             int temp = (orientation+i)%NUM_SIDES;
//             sides[i] = tile.getSide(temp);
//         }
//     }
//
//     public void setAdjSlot(int index, Slot slot) {
//         this.sidePorts[index] = slot;
//     }
//
//     public boolean canFit(Tile t, int rotationAmt){
//
//         for(int i = 0; i<NUM_SIDES; i++){
//
//             if(sidePorts[i]!=null){
//                 if(connections[i].type != t.getSide((rotationAmt + i) % NUM_SIDES)){
//                     return false;
//                 }
//             }
//
//         }
//
//         return true;
//     }
//     class SlotConnection {
//         Slot slot;
//         char type;
//
//         SlotConnection() {
//             slot = new Slot();
//         }
//
//         public void setSlot(Slot slot) {
//             this.slot = slot;
//         }
//     }
// }
