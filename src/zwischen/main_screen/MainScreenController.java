///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        MainScreenController.java
// By:          Zachary Greening
// Group:       3
// Date:        October 18, 2018
// Description: Controller Class for the main screen
///////////////////////////////////////////////////////////////////////////////

package zwischen.main_screen;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MainScreenController {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private TextField DestinationAddress;

  @FXML
  private TextField PickUpAddress;

  @FXML
  void OnDisplayAvailableRidesPressed(ActionEvent event) {

  }

  @FXML
  void OnDriverAvailabilityChanged(ActionEvent event) {

  }

  @FXML
  void OnEditAccountPressed(ActionEvent event) {

  }

  @FXML
  void OnLogoutPressed(ActionEvent event) {

  }

  @FXML
  void OnNotificationsPressed(ActionEvent event) {

  }

  @FXML
  void OnRideRequested(ActionEvent event) {

  }

  @FXML
  void OnViewDriversPressed(ActionEvent event) {

  }

  @FXML
  void OnViewHistoryPressed(ActionEvent event) {

  }

  @FXML
  void OnViewMessageWindowClicked(MouseEvent event) {

  }

  @FXML
  void initialize() {
    assert DestinationAddress != null : "fx:id=\"DestinationAddress\" was not injected: check your FXML file 'MainScreen.fxml'.";
    assert PickUpAddress != null : "fx:id=\"PickUpAddress\" was not injected: check your FXML file 'MainScreen.fxml'.";

  }
}

