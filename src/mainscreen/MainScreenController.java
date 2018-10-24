///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        MainScreenController.java
// By:          Zachary Greening
// Group:       3
// Date:        October 18, 2018
// Description: Controller Class for the main screen
///////////////////////////////////////////////////////////////////////////////

package mainscreen;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
  private AnchorPane mainmenu;

  @FXML
  void onDisplayAvailableRidesPressed(ActionEvent event) throws Exception {
    Parent root = FXMLLoader.load(
        getClass().getClassLoader().getResource("rideschedule/RideScheduleWindow.fxml"));

    Scene scene = new Scene(root);

    Stage stage = new Stage();

    stage.setTitle("Zwischen");

    stage.setScene(scene);

    stage.setX(stage.getX() - 150);

    stage.show();
  }

  @FXML
  void onDriverAvailabilityChanged(ActionEvent event) {
    output.setText(output.getText() + "\nDriverAvailabilityToggled (Todo Add Functionality)");
    //Todo Add Driver Availabilty changed code
  }

  @FXML
  void onEditAccountPressed(ActionEvent event) throws Exception {
    Stage stage = (Stage) mainmenu.getScene().getWindow();

    stage.close();

    Parent root = FXMLLoader.load(
        getClass().getClassLoader().getResource("editaccount/EditAccountWindow.fxml"));

    Scene scene = new Scene(root);

    stage = new Stage();

    stage.setTitle("Zwischen");

    stage.setScene(scene);

    stage.show();
  }

  @FXML
  void onLogoutPressed(ActionEvent event) throws Exception {
    Stage stage = (Stage) mainmenu.getScene().getWindow();

    stage.close();

    Parent root = FXMLLoader.load(
        getClass().getClassLoader().getResource("loginpage/LoginPage.fxml"));

    Scene scene = new Scene(root);

    stage = new Stage();

    stage.setTitle("Zwischen");

    stage.setScene(scene);

    stage.show();
  }

  @FXML
  void onNotificationsPressed(ActionEvent event) throws Exception {
    Parent root = FXMLLoader.load(
        getClass().getClassLoader().getResource("notification/NotificationWindow.fxml"));

    Scene scene = new Scene(root);

    Stage stage = new Stage();

    stage.setTitle("Zwischen");

    stage.setScene(scene);

    stage.setX(stage.getX() - 150);

    stage.show();
  }

  @FXML
  void onRideRequested(ActionEvent event) {
    output.setText(output.getText() + "\nRideRequested (Todo Add Functionality)");
    //Todo Add ride requested code
  }

  @FXML
  void onViewDriversPressed(ActionEvent event) {
    output.setText(output.getText() + "\nViewDriversPressed (Todo Add Functionality)");
    //todo add view Driver screen
  }

  @FXML
  void onViewHistoryPressed(ActionEvent event) {
    output.setText(output.getText() + "\nViewHistoryPressed (Todo Add Functionality)");
    //todo add view history screen
  }

  @FXML
  void onViewMessagesPressed(ActionEvent event) throws Exception {
    Parent root = FXMLLoader.load(
        getClass().getClassLoader().getResource("messagescreen/messageScreen.fxml"));

    Scene scene = new Scene(root);

    Stage stage = new Stage();

    stage.setTitle("Zwischen");

    stage.setScene(scene);

    stage.setX(stage.getX() - 150);

    stage.show();
  }

  @FXML
  void onViewFriendsListPressed(ActionEvent event) throws Exception {
    Parent root = FXMLLoader.load(
        getClass().getClassLoader().getResource("friendslist/friendslist.fxml"));

    Scene scene = new Scene(root);

    Stage stage = new Stage();

    stage.setTitle("Zwischen");

    stage.setScene(scene);

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

