///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        RideHistoryScreenController.java
// Group:       3
// Date:        October 24, 2018
// Description: Controller class for ride history screen
///////////////////////////////////////////////////////////////////////////////

package ridehistory;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RideHistoryScreenController {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Button mainReturn;

  @FXML
  void messageUser1(ActionEvent event) {
    //todo These really should be one function
    //since we will want to dynamically resize the number of drivers
    //that can be seen in the window
    //You will just want one function that gets the name of the current driver
    //and other relevant information.
  }

  @FXML
  void messageUser2(ActionEvent event) {
    //todo These really should be one function
  }

  @FXML
  void messageUser3(ActionEvent event) {
    //todo These really should be one function
  }

  @FXML
  void returnToMainScreen(ActionEvent event) {

  }
}
