/* forwards all requests to the region objects
purpose of this class is to allow for replacement
of objects while making sure old references
reflect the changed object*/

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
        this.region = new Field();
      break;
      case 'T':
        this.region = new Road();
      break;
      case 'L':
        this.region = new City();
      break;
      case 'X':
        this.region = new Monastery();
    }
  }

  public String toString(){
    return Character.toString(type) + ": "+ region.toString();
  }
}
