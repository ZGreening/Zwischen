///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        EditAccountController.java
// Group:       3
// Date:        October 24, 2018
// Description: Controller class for edit account window
///////////////////////////////////////////////////////////////////////////////

package editaccount;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import other.Globals;

public class EditAccountController {

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
  private AnchorPane root;

  @FXML
  void onReturnToHomepagePressed(ActionEvent event) {
    Globals.changeScene("mainscreen/MainScreen.fxml", root);
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
}
