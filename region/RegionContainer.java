package region;/* forwards all requests to the region objects
purpose of this class is to allow for replacement
of objects while making sure old references
reflect the changed object*/
import gameplay.Player;

public class RegionContainer{

  public char type;

  private Region region;

  public void replaceWith(RegionContainer otherRegion){
    region = otherRegion.getRegion();
  }

  public Region getRegion(){
    return region;
  }

  public void absorb(RegionContainer otherRegion){
    region.absorb(otherRegion.getRegion());
  }

  public void addMeeple(Player owner){
    region.addMeeple(owner);
  }

  public void addAdjacent(RegionContainer adjacentRegion){
    region.addAdjacent(adjacentRegion.getRegion());
  }

  public void addOpenPort(int port){
    region.addOpenPort(port);
  }

  public void closePort(int port){
    region.closePort(port);
  }

  public RegionContainer(char type){
    this.type = type;
    switch(type){
      case 'J':
        this.region = new Jungle();
      break;
      case 'T':
        this.region = new GameTrail();
      break;
      case 'L':
        this.region = new Lake();
      break;
      case 'X':
        this.region = new Den();
    }
  }

  public String toString(){
    return Character.toString(type)+ region.toString();
  }
}
