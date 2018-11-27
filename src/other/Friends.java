package other;

import javafx.scene.control.Button;

public class Friends extends User {
  public String friendName;
  public Button removeFriend;
  public Button messsegeFriend;

  public Friends( String friendName){

    this.friendName = friendName;
    this.removeFriend = new Button();
    this.messsegeFriend = new Button();
  }

}
