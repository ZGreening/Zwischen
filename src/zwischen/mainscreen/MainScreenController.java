///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        MainScreenController.java
// By:          Zachary Greening
// Group:       3
// Date:        October 18, 2018
// Description: Controller Class for the main screen
///////////////////////////////////////////////////////////////////////////////

package zwischen.mainscreen;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainScreenController {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private TextField destinationAddress;

  @FXML
  private TextField pickUpAddress;

  @FXML
  void onDisplayAvailableRidesPressed(ActionEvent event) {

  }

  @FXML
  void onDriverAvailabilityChanged(ActionEvent event) {

  }

  @FXML
  void onEditAccountPressed(ActionEvent event) {

  }

  @FXML
  void onLogoutPressed(ActionEvent event) {

  }

  @FXML
  void onNotificationsPressed(ActionEvent event) throws Exception {
    Parent root = FXMLLoader.load(
        getClass().getClassLoader().getResource("zwischen/notification/NotificationWindow.fxml"));

    Scene mainScene = new Scene(root);

    Stage stage=new Stage();

    stage.setTitle("Notifications");

    stage.setScene(mainScene);

    stage.setX(stage.getX()-50);

    stage.setY(stage.getY()-50);

    stage.show();
  }

  @FXML
  void onRideRequested(ActionEvent event) {

  }

  @FXML
  void onViewDriversPressed(ActionEvent event) {

  }

  @FXML
  void onViewHistoryPressed(ActionEvent event) {

  }

  @FXML
  void onViewMessageWindowClicked(MouseEvent event) {

  }

  @FXML
  void initialize() {
    assert destinationAddress
        != null : "fx:id=\"DestinationAddress\" "
        + "was not injected: check your FXML file 'MainScreen.fxml'.";
    assert pickUpAddress
        != null : "fx:id=\"PickUpAddress\" "
        + "was not injected: check your FXML file 'MainScreen.fxml'.";

  }
}

