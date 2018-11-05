///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        CreateAccountController.java
// Group:       3
// Date:        October 24, 2018
// Description: Controller class for create account screen
///////////////////////////////////////////////////////////////////////////////

package createaccount;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import other.Globals;

public class CreateAccountController {
  @FXML
  private PasswordField password;

  @FXML
  private TextField username;

  @FXML
  private PasswordField confirmPassword;

  @FXML
  private TextField email;

  @FXML
  private TextField phoneNumber;

  @FXML
  private AnchorPane root;

  @FXML
  void onBackToHomepagePressed(ActionEvent event) {
    //todo should this be "homepage" or "loginpage"?

    Globals.changeScene("loginpage/LoginPage.fxml", root);
  }

  @FXML
  void onCreateYourAccountPressed(ActionEvent event) {
    Globals.changeScene("mainscreen/MainScreen.fxml", root);
  }
}
