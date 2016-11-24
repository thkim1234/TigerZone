import java.util.*;

class TileManager{

  private static HashMap<String,Integer> tilesToIndices;

  public static void init(){
    tilesToIndices = new HashMap<String, Integer>();
    for(int i = 0; i<tileTypes3.length; i++){
      tilesToIndices.put(tileTypes3[i],i);
    }
  }

  public static TileAttributes getTileAttributes(Tile tile){
    int index = tilesToIndices.get(tile.toString());
    TileAttributes tileAttributes = new TileAttributes();
    tileAttributes.numRegions = numReg3[index];
    tileAttributes.portTypes = portType3[index].toCharArray();
    tileAttributes.fields = fieldsAdj[index];
    tileAttributes.ports = portNum[index];
    tileAttributes.nodes = placement[index];
    return tileAttributes;
  }

  //redoing tiles, top>right>bottom>left>middle>prey 0=none 1=deer 2=boar 3=buffalo
  // 0->- | 1->D  | 2->P  | 3->B
  // still top > right > bottom> left

  public static String[] tileTypes = {"ggggg0", "ggggm0", "ggrgm0", "rrrrr0", "rgrgr0", "rggrg0", "rgrrr0", "ccccc0", "gcccc0", "ccggg0", "gcgcc0", "cgcgg0", "cgggg0", "gccgg0", "rcgrg0", "rcgrg2", "gcrrg0", "gcrrg3", "rcrgr0", "rcrgr1", "rcccc0", "rcrrr0", "rcrrr2", "rccrg0", "rccrg3", "cgrgr0", "cgrgr1"};
  //public static int[] numType = {1,4,2,1,8,9,4,1,4,5,3,3,5,2,1,2,1,2,1,2,3,1,2,3,2,1,2};
  public static int[] numReg =  {1,2,3,8,3,3,6,1,2,2,3,3,2,3,4,4,4,4,4,4,4,7,7,4,4,4,4};
  //don't forget int id field, which is just their ordering/index
  public static String[] portType = {"g","gm","gmr","grgrgrgr","grg","grg","grgrgr","c","gc","cg","gcg","cgc","cg","gcc","grgc","grgc","gcrg","gcrc","grgc","grgc","grgc","grgcrgr","grgcrgr","grgc","grgc","cgrg","cgrg"};


//Final version as of v2.2, need to remove the duplicate naming when finalizing

  public static String[] tileTypes3 = {"JJJJ-", "JJJJX", "JJTJX", "TTTT-", "TJTJ-", "TJJT-", "TJTT-", "LLLL-", "JLLL-", "LLJJ-", "JLJL-", "LJLJ-", "LJJJ-", "JLLJ-", "TLJT-", "TLJTP", "JLTT-", "JLTTB", "TLTJ-", "TLTJD", "TLLL-", "TLTT-", "TLTTP", "TLLT-", "TLLTB", "LJTJ-", "LJTJD","TLLLC"};
  public static int[] numType3 = {1,4,2,1,8,9,4,1,4,5,3,3,5,2,1,2,1,2,1,2,3,1,2,3,2,1,2,2};
  public static int[] numReg3 =  {1,2,3,8,3,3,6,1,2,2,3,3,2,3,4,4,4,4,4,4,4,7,7,4,4,4,4,4};
  public static String[] portType3 = {"J","JX","JXT","JTJTJTJT","JTJ","JTJ","JTJTJT","L","JL","LJ","JLJ","LJL","LJ","JLL","JTJL","JTJL","JLTJ","JLTL","JTJL","JTJL","JTJL","JTJLTJT","JTJLTJT","JTJL","JTJL","LJTJ","LJTJ","JTJL"};
  


  public static int[][][] fieldsAdj = {
    /* tile 1*/   { {0} },
    /* tile 2*/   { {0},{1} },
    /* tile 3*/   { {0},{1},{0} },
    /* tile 4*/   { {0},{0},{0},{0},{0},{0},{0},{0} },
    /* tile 5*/   { {0},{0},{0} },
    /* tile 6*/   { {0},{0},{0} },
    /* tile 7*/   { {0},{0},{0},{0},{0},{0} },
    /* tile 8*/   { {0} },
    /* tile 9*/   { {0},{1} },
    /* tile 10*/  { {2},{0} },
    /* tile 11*/  { {0},{1,3},{0} },
    /* tile 12*/  { {2},{0},{2} },
    /* tile 13*/  { {2},{0} },
    /* tile 14*/  { {0},{1},{1} },
    /* tile 15*/  { {0},{0},{0},{3} },
    /* tile 16*/  { {0},{0},{0},{3} },
    /* tile 17*/  { {0},{1},{0},{0} },
    /* tile 18*/  { {0},{1},{0},{0} },
    /* tile 19*/  { {0},{0},{0},{3} },
    /* tile 20*/  { {0},{0},{0},{3} },
    /* tile 21*/  { {0},{0},{0},{1,3} },
    /* tile 22*/  { {0},{0},{0},{3},{0},{0},{0} },
    /* tile 23*/  { {0},{0},{0},{3},{0},{0},{0} },
    /* tile 24*/  { {0},{0},{0},{3} },
    /* tile 25*/  { {0},{0},{0},{3} },
    /* tile 26*/  { {1,3},{0},{0},{0} },
    /* tile 27*/  { {1,3},{0},{0},{0} },
    /* tile 28*/  { {0},{0},{0},{1,3} }
  };

  // fields is for cities and monastaries (lakes and dens) to keep track of which ones to notify when they are completed.
  // 0 indicates that they either do not have an adjacent jungle, or they are not applicable

  //-1 indicates that there are no ports, hence it is a den

  public static int portNum[][][] =
  {
    /* tile 1*/   { {0,1,2,3,4,5,6,7,8,9,10,11} },
    /* tile 2*/   { {0,1,2,3,4,5,6,7,8,9,10,11},{ -1} },
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
    /* tile 28*/  { {0},{1},{2},{3,4,5,6,7,8,9,10,11} }

  };

public static int placement[][][] =
{
    /* tile 1*/  { {1,2,3,4,5,6,7,8,9} },
    /* tile 2*/  { {1,2,3,4,6,7,8,9},{5} },
    /* tile 3*/  { {1,2,3,4,6,7,9},{5},{8} },
    /* tile 4*/  { {1},{2,},{3},{6},{9},{8},{7},{4} },
    /* tile 5*/  { {1,4,7},{2,5,8},{3,6,9} },
    /* tile 6*/  { {1},{2,4,5},{6,7,8,9} },
    /* tile 7*/  { {1},{2},{3,6,9},{8},{7},{4} },
    /* tile 8*/  { {1,2,3,4,5,6,7,8,9} },
    /* tile 9*/  { {1,2,3},{4,5,6,7,8,9} },
    /* tile 10*/ { {2,3,6,},{1,4,5,7,8,9} },
    /* tile 11*/ { {1,2,3,7,8,9},{4,5,6} },
    /* tile 12*/ { {1,3,4,5,6,7,9},{2,8} },
    /* tile 13*/ { {2},{1,3,4,5,6,7,8,9} },
    /* tile 14*/ { {1,2,3,4,5,7,9},{6},{8} }, //NEED VERIFICATION, THINK WE EXCLUDE 9  --> confirmed 9 is a jungle
    /* tile 15*/ { {1},{2,4,5},{3,7,8,9},{6} }, //ASK IF TILE 5 IS ROAD                --> confirmed 5 is a road
    /* tile 16*/ { {1},{2,4,5},{3,7,8,9},{6} }, //same
    /* tile 17*/ { {1,2,3,9},{6},{4,5,8},{7} },
    /* tile 18*/ { {1,2,3,9},{6},{4,5,8},{7} },
    /* tile 19*/ { {1,4,7},{2,5,8},{3,9},{6} },
    /* tile 20*/ { {1,4,7},{2,5,8},{3,9},{6} },
    /* tile 21*/ { {1},{2},{3},{4,5,6,7,8,9} },
    /* tile 22*/ { {1},{2},{3,9},{6},{8},{7},{4} },
    /* tile 23*/ { {1},{2},{3,9},{6},{8},{7},{4} },
    /* tile 24*/ { {1},{2,4,5},{3,7},{6,8,9} } //WHERE DOES 5 GO                      --> confirmed 5 is a road
    /* tile 25*/ { {1},{2,4,5},{3,7},{6,8,9} } //same
    /* tile 26*/ { {2},{1,3,4,6,7,9},{5,8} },  //IS 5 A ROAD?                        --> confirmed 5 is a road
    /* tile 27*/ { {2},{1,3,4,6,7,9},{5,8} },
    /* tile 28*/ { {1},{2},{3},{4,5,6,7,8,9} }
}
}



// Sample code for  meeple placement

/*
int getPlacementLocation(int region, int rotations)
{
  int minVal = 100;
  int array[] = tileAttributes.nodes[region];
  for(int i = 0; i < array.size; i++)
  {
    for(int j = 0; j < rotations; j++)
    {
      switch (array[i])
      {
         case 1: 
         array[i] = 7;
         break;

         case 2: 
         array[i] = 4;
         break;

         case 3: 
         array[i] = 1;
         break;

         case 4: 
         array[i] = 8;
         break;

         case 5: 
         array[i] = 5;
         break;

         case 6: 
         array[i] = 2;
         break;

         case 7: 
         array[i] = 9;
         break;

         case 8: 
         array[i] = 6;
         break;

         case 9: 
         array[i] = 3;
         break;
         default: array[i] = 100;
         break;
      }
    }

    minVal = min(minVal,array[i]);

  }

  return minVal;
  
}




*/