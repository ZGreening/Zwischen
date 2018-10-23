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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
  private Label output;

  @FXML
  void onDisplayAvailableRidesPressed(ActionEvent event) throws Exception {
    Parent root = FXMLLoader.load(
        getClass().getClassLoader().getResource("zwischen/rideschedule/RideScheduleWindow.fxml"));

    Scene mainScene = new Scene(root);

    Stage stage = new Stage();

    stage.setTitle("Notifications");

    stage.setScene(mainScene);

    stage.setX(stage.getX() - 150);

    stage.show();
  }

  @FXML
  void onDriverAvailabilityChanged(ActionEvent event) {
    output.setText(output.getText() + "\nDriverAvailabilityToggled (Todo Add Functionality)");
  }

  @FXML
  void onEditAccountPressed(ActionEvent event) {
    output.setText(output.getText() + "\nEditAccountPressed (Todo Add Functionality)");
  }

  @FXML
  void onLogoutPressed(ActionEvent event) {
    output.setText(output.getText() + "\nLogoutPressed (Todo Add Functionality)");
  }

  @FXML
  void onNotificationsPressed(ActionEvent event) throws Exception {
    Parent root = FXMLLoader.load(
        getClass().getClassLoader().getResource("zwischen/notification/NotificationWindow.fxml"));

    Scene mainScene = new Scene(root);

    Stage stage = new Stage();

    stage.setTitle("Notifications");

    stage.setScene(mainScene);

    stage.setX(stage.getX() - 150);

    stage.show();
  }

  @FXML
  void onRideRequested(ActionEvent event) {
    output.setText(output.getText() + "\nRideRequested (Todo Add Functionality)");
  }

  @FXML
  void onViewDriversPressed(ActionEvent event) {
    output.setText(output.getText() + "\nViewDriversPressed (Todo Add Functionality)");
  }

  @FXML
  void onViewHistoryPressed(ActionEvent event) {
    output.setText(output.getText() + "\nViewHistoryPressed (Todo Add Functionality)");
  }

  @FXML
  void onViewMessagesPressed(ActionEvent event) throws Exception {
    Parent root = FXMLLoader.load(
        getClass().getClassLoader().getResource("zwischen/messagescreen/messageScreen.fxml"));

    Scene mainScene = new Scene(root);

    Stage stage = new Stage();

    stage.setTitle("Messages");

    stage.setScene(mainScene);

    stage.setX(stage.getX() - 150);

    stage.show();
  }

  @FXML
  void onViewFriendsListPressed(ActionEvent event) throws Exception {
    Parent root = FXMLLoader.load(
        getClass().getClassLoader().getResource("zwischen/friendslist/friendslist.fxml"));

    Scene mainScene = new Scene(root);

    Stage stage = new Stage();

    stage.setTitle("Friends List");

    stage.setScene(mainScene);

    stage.setX(stage.getX() - 150);

    stage.show();
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

