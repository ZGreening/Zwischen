///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        EditAccountController.java
// By:          Tyler Marlow
// Group:       3
// Date:        October 24, 2018
// Description: Controller class for edit account window
///////////////////////////////////////////////////////////////////////////////

package editaccount;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditAccountController {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private TextField firstName;

  @FXML
  private TextField lastName;

  @FXML
  private TextField phoneNumber;

  @FXML
  private TextField email;

  @FXML
  private PasswordField password;

  @FXML
  private AnchorPane editAccount;

  @FXML
  void onReturnToHomepagePressed(ActionEvent event) throws Exception {
    Stage stage = (Stage) editAccount.getScene().getWindow();

    stage.close();

    Parent root = FXMLLoader.load(
        getClass().getClassLoader().getResource("mainscreen/MainScreen.fxml"));

    Scene scene = new Scene(root);

    stage = new Stage();

    stage.setTitle("Zwischen");

    stage.setScene(scene);

    stage.show();
  }

  @FXML
  void onUpdateAccountPressed(ActionEvent event) {

  }

  @FXML
  void onViewDriversRidersPressed(ActionEvent event) {

  }

  @FXML
  void onViewMapsPressed(ActionEvent event) {

  }

  @FXML
  void initialize() {
    assert firstName
        != null : "fx:id=\"firstName\" "
        + "was not injected: check your FXML file 'EditAccountWindow.fxml'.";
    assert lastName
        != null : "fx:id=\"lastName\" "
        + "was not injected: check your FXML file 'EditAccountWindow.fxml'.";
    assert phoneNumber
        != null : "fx:id=\"phoneNumber\" "
        + "was not injected: check your FXML file 'EditAccountWindow.fxml'.";
    assert email
        != null : "fx:id=\"email\" "
        + "was not injected: check your FXML file 'EditAccountWindow.fxml'.";
    assert password
        != null : "fx:id=\"password\" "
        + "was not injected: check your FXML file 'EditAccountWindow.fxml'.";

  }
}
