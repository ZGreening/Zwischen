///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        MainScreenController.java
// Group:       3
// Date:        October 18, 2018
// Description: Controller Class for the main screen
///////////////////////////////////////////////////////////////////////////////

package mainscreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import other.Globals;

public class MainScreenController {

  @FXML
  private TextField destinationAddress;

  @FXML
  private TextField pickUpAddress;

  @FXML
  private WebView webViewMaps;

  @FXML
  private WebView webViewMaps1;

  @FXML
  private Label outputText;

  @FXML
  private AnchorPane root;

  @FXML
  void onSetWeeklyDriverSchedulePressed(ActionEvent event) {
    Globals.changeScene("driverweeklyschedule/DriverWeeklySchedule.fxml", root);
  }

  @FXML
  void onDriverAvailabilityChanged(ActionEvent event) {
    outputText
        .setText(outputText.getText() + "\nDriverAvailabilityToggled (Todo Add Functionality)");
    //Todo Add Driver Availabilty changed code
  }

  @FXML
  void onEditAccountPressed(ActionEvent event) {
    Globals.changeScene("editaccount/EditAccountWindow.fxml", root);
  }

  @FXML
  void onLogoutPressed(ActionEvent event) {
    Globals.changeScene("loginpage/LoginPage.fxml", root);
  }

  @FXML
  void onNotificationsPressed(ActionEvent event) {
    Globals.changeScene("notification/NotificationWindow.fxml", root);
  }

  @FXML
  void onRideRequested(ActionEvent event) {

//    final WebEngine engine = webViewMaps1.getEngine();
//    engine.load("https://www.openstreetmap.org/directions?engine=osrm_car&route=26.4635%2C-81.7740%3B26.4894%2C-81.8084");
//    //engine.load("https://www.openstreetmap.org/directions#map=14/26.4774/-81.8063");
//


    //outputText.setText(outputText.getText() + "\nRideRequested (Todo Add Functionality)");
    //Todo Add ride requested code
  }

  @FXML
  void onViewDriversPressed(ActionEvent event) {
    Globals.changeScene("availabledrivers/AvailableDrivers.fxml", root);
  }

  @FXML
  void onViewHistoryPressed(ActionEvent event) {
    Globals.changeScene("ridehistory/RideHistoryScreen.fxml", root);
  }

  @FXML
  void onViewMessagesPressed(ActionEvent event) {
    Globals.changeScene("messagescreen/messageScreen.fxml", root);
  }

  @FXML
  void onViewFriendsListPressed(ActionEvent event) {
    Globals.changeScene("friendslist/friendslist.fxml", root);
  }

  @FXML
  private void initialize() {
    final WebEngine engine = webViewMaps.getEngine();
    engine.load("https://www.openstreetmap.org/directions#map=13/26.4694/-81.7750");
  }

}

