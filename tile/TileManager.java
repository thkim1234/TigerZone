package tile;

import java.util.*;

public class TileManager{

  private static HashMap<String,Integer> tilesToIndices;

  public static void init(){
    tilesToIndices = new HashMap<String, Integer>();
    for(int i = 0; i<tileTypes.length; i++){
      tilesToIndices.put(tileTypes[i],i);
    }
  }

  public static TileAttributes getTileAttributes(Tile tile){
    int index = tilesToIndices.get(tile.toString());
    TileAttributes tileAttributes = new TileAttributes();
    tileAttributes.numRegions = numReg[index];
    tileAttributes.portTypes = portType[index].toCharArray();
    tileAttributes.jungles = junglesAdj[index];
    tileAttributes.ports = portNum[index];
    return tileAttributes;
  }

  //redoing tiles, top>right>bottom>left>middle>prey 0=none 1=deer 2=boar 3=buffalo
  // 0->- | 1->D  | 2->P  | 3->B
  // still top > right > bottom> left

  public static String[] tileTypes = {"ggggg0", "ggggm0", "ggrgm0", "rrrrr0", "rgrgr0", "rggrg0", "rgrrr0", "ccccc0", "gcccc0", "ccggg0", "gcgcc0", "cgcgg0", "cgggg0", "gccgg0", "rcgrg0", "rcgrg2", "gcrrg0", "gcrrg3", "rcrgr0", "rcrgr1", "rcccc0", "rcrrr0", "rcrrr2", "rccrg0", "rccrg3", "cgrgr0", "cgrgr1"};
  //public static int[] numType = {1,4,2,1,8,9,4,1,4,5,3,3,5,2,1,2,1,2,1,2,3,1,2,3,2,1,2};
  public static int[] numReg =  {1,2,3,8,3,3,6,1,2,2,3,3,2,3,4,4,4,4,4,4,4,7,7,4,4,4,4};
  //don't forget int id jungle, which is just their ordering/index
  public static String[] portType = {"g","gm","gmr","grgrgrgr","grg","grg","grgrgr","c","gc","cg","gcg","cgc","cg","gcc","grgc","grgc","gcrg","gcrc","grgc","grgc","grgc","grgcrgr","grgcrgr","grgc","grgc","cgrg","cgrg"};
  public static int[][][] junglesAdj = {
    /* tile 1*/   { {} },
    /* tile 2*/   { {},{0} },
    /* tile 3*/   { {},{0},{0} },
    /* tile 4*/   { {},{0,2},{},{2,4},{},{4,6},{},{6,0} },
    /* tile 5*/   { {},{0,2},{} },
    /* tile 6*/   { {},{0,2},{} },
    /* tile 7*/   { {},{0,2},{},{2,4},{},{4,0} },
    /* tile 8*/   { {} },
    /* tile 9*/   { {},{0} },
    /* tile 10*/  { {1},{} },
    /* tile 11*/  { {},{0,2},{} },
    /* tile 12*/  { {1},{},{1} },
    /* tile 13*/  { {1},{} },
    /* tile 14*/  { {},{0},{0} },
    /* tile 15*/  { {},{0,2},{},{2} },
    /* tile 16*/  { {},{0,2},{},{2} },
    /* tile 17*/  { {},{0},{},{0,3} },
    /* tile 18*/  { {},{0},{},{0,3} },
    /* tile 19*/  { {},{0,2},{},{2} },
    /* tile 20*/  { {},{0,2},{},{2} },
    /* tile 21*/  { {},{0,2},{},{0,2} },
    /* tile 22*/  { {},{0,2},{},{2},{2,5},{},{5,0} },
    /* tile 23*/  { {},{0,2},{},{2},{2,5},{},{5,0} },
    /* tile 24*/  { {},{0,2},{},{2} },
    /* tile 25*/  { {},{0,2},{},{2} },
    /* tile 26*/  { {1,3},{},{1,3},{} },
    /* tile 27*/  { {1,3},{},{1,3},{} }
  };

  // jungles is for lakes and monastaries (lakes and dens) to keep track of which ones to notify when they are completed.
  // 0 indicates that they either do not have an adjacent jungle, or they are not applicable



  public static int portNum[][][] =
  {
    /* tile 1*/   { {0,1,2,3,4,5,6,7,8,9,10,11} },
    /* tile 2*/   { {0,1,2,3,4,5,6,7,8,9,10,11},{-1} },
    /* tile 3*/   { {0,1,2,3,4,5,6,8,9,10,11},{-1},{7} },
    /* tile 4*/   { {0,11},{1},{2,3},{4},{5,6},{7},{8,9},{10} },
    /* tile 5*/   { {0,8,9,10,11},{1,7},{2,3,4,5,6} },
    /* tile 6*/   { {0,11},{1,10},{2,3,4,5,6,7,8,9} },
    /* tile 7*/   { {0,11},{1},{2,3,4,5,6},{7},{8,9},{10},{11} },
    /* tile 8*/   { {0,1,2,3,4,5,6,7,8,9,10,11} },
    /* tile 9*/   { {0,1,2},{3,4,5,6,7,8,9,10,11} },
    /* tile 10*/  { {0,1,2,3,4,5},{6,7,8,9,10,11} },
    /* tile 11*/  { {0,1,2},{3,4,5,9,10,11},{6,7,8} },
    /* tile 12*/  { {0,1,2},{3,4,5,9,10,11},{6,7,8} },
    /* tile 13*/  { {0,1,2},{3,4,5,6,7,8,9,10,11} },
    /* tile 14*/  { {0,1,2,9,10,11},{3,4,5},{6,7,8} },
    /* tile 15*/  { {0,11},{1,10},{2,6,8,9},{3,4,5} },
    /* tile 16*/  { {0,11},{1,10},{2,6,8,9},{3,4,5} },
    /* tile 17*/  { {0,1,2,6},{7,10},{8,9},{3,4,5} },
    /* tile 18*/  { {0,1,2,6},{7,10},{8,9},{3,4,5} },
    /* tile 19*/  { {0,8,9,10,11},{1,7},{2,6},{3,4,5} },
    /* tile 20*/  { {0,8,9,10,11},{1,7},{2,6},{3,4,5} },
    /* tile 21*/  { {0},{1},{2},{3,4,5,6,7,8,9,10,11} },
    /* tile 22*/  { {0,11},{1},{2,6},{3,4,5},{7},{8,9},{10} },
    /* tile 23*/  { {0,11},{1},{2,6},{3,4,5},{7},{8,9},{10} },
    /* tile 24*/  { {0,11},{1,10},{2,9},{3,4,5,6,7,8} },
    /* tile 25*/  { {0,11},{1,10},{2,9},{3,4,5,6,7,8} },
    /* tile 26*/  { {0,1,2},{3,4,5,6},{7},{8,9,10,11} },
    /* tile 27*/  { {0,1,2},{3,4,5,6},{7},{8,9,10,11} },

  };

}
