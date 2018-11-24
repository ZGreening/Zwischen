///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        MainScreenController.java
// Group:       3
// Date:        October 18, 2018
// Description: Controller Class for the main screen
///////////////////////////////////////////////////////////////////////////////

package mainscreen;

import java.nio.file.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import other.Globals;

public class MainScreenController {

  @FXML
  private WebView webViewMaps;

  @FXML
  private Label feedbackLabel;

  @FXML
  private AnchorPane root;

  @FXML
  private Label username;

  @FXML
  private ImageView avatar;

  @FXML
  private Button notification;

  @FXML
  void onSetWeeklyDriverSchedulePressed(ActionEvent event) {
    Globals.changeScene("driverschedule/DriverSchedule.fxml");
  }

  @FXML
  void onEditAccountPressed(ActionEvent event) {
    Globals.changeScene("editaccount/EditAccount.fxml", root);
  }

  @FXML
  void onLogoutPressed(ActionEvent event) {
    Globals.changeScene("login/Login.fxml", root);
  }

  @FXML
  void onNotificationsPressed(ActionEvent event) {
    Globals.changeScene("notification/Notification.fxml");
  }

  @FXML
  void onRequestRidePressed(ActionEvent event) {
    Globals.changeScene("riderequest/RideRequest.fxml");
  }

  @FXML
  void onViewHistoryPressed(ActionEvent event) {
    Globals.changeScene("ridehistory/RideHistory.fxml");
  }

  @FXML
  void onViewMessagesPressed(ActionEvent event) {
    Globals.changeScene("messages/Messages.fxml");
  }

  @FXML
  void onViewFriendsListPressed(ActionEvent event) {
    Globals.changeScene("friendslist/friendslist.fxml");
  }

  @FXML
  private void initialize() {
    //Get username for current user
    String currentUsername = Globals.currentUser.getUsername();

    //Setup Maps in WebView
    final WebEngine engine = webViewMaps.getEngine();
    engine.load("https://www.openstreetmap.org/directions#map=13/26.4694/-81.7750");

    //Load current users avatar in
    avatar.setImage(new Image(
        Paths.get("lib/UserData/" + currentUsername + "/avatar.png").toUri().toString()));

    //Display current user
    username.setText("Username:\n" + currentUsername);

    //Output welcome message
    feedbackLabel.setText("Welcome " + currentUsername + "!");
  }
}

