//this is just the bare bones to get it running rn, we likely want to make it abstract
//and it also still needs board knowledge

public class Player{

  private Location currentChosenLocation;
  private Orientation currentChosenOrientation;
  int meeplePlacement;

  //this is like visitor WEE
  //so this could really be what the client implements? Eh?
  public void chooseLocation(Tile t){
    //obvi will be different, but we'll update these fields here
    currentChosenLocation = new Location();
    currentChosenOrientation = new Orientation();
    meeplePlacement = 5;
  }

  public Location getChosenLocation(){
    return currentChosenLocation;
  }

  public Orientation getChosenOrientation(){
    return currentChosenOrientation;
  }

  public int getChosenMeeplePlacement(){
    //we'll likely use something other than an integer
    return meeplePlacement;
  }
}
