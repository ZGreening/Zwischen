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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import other.Globals;

public class CreateAccountController {

  @FXML
  private TextField username;

  @FXML
  private PasswordField password;

  @FXML
  private PasswordField confirmPassword;

  @FXML
  private TextField email;

  @FXML
  private TextField phoneNum;

  @FXML
  private Label feedbackLabel;

  @FXML
  private AnchorPane root;

  @FXML
  void onCreateAccountPressed(ActionEvent event) {
    //Ensure all fields are filled and email and phone number are in correct format
    //Assumes USA phone number type
    if (username.getText().isEmpty()) {
      feedbackLabel.setText("Username is empty");
    } else if (password.getText().isEmpty()) {
      feedbackLabel.setText("Password is empty");
    } else if (confirmPassword.getText().isEmpty()) {
      feedbackLabel.setText("Confirm password is empty");
    } else if (!password.getText().equals(confirmPassword.getText())) {
      feedbackLabel.setText("Passwords do not match");
    } else if (email.getText().isEmpty()) {
      feedbackLabel.setText("Email is empty");
    } else if (!email.getText()
        .matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) {
      feedbackLabel.setText("Invalid Email");
    } else if (phoneNum.getText().isEmpty()) {
      feedbackLabel.setText("Phone number is empty");
    } else if (!(phoneNum.getText().matches("\\([0-9]{3}\\)[0-9]{3}-[0-9]{4}") ||
        phoneNum.getText().matches("[0-9]{3}-[0-9]{3}-[0-9]{4}") ||
        phoneNum.getText().matches("[0-9]{10}"))) {
      feedbackLabel.setText("Incorrect phone number format");
    } else {
      //If none of the issues above, change screens
      Globals.changeScene("mainscreen/MainScreen.fxml", root);
    }
  }

  @FXML
  void onReturnToLoginPressed(ActionEvent event) {
    Globals.changeScene("loginpage/LoginPage.fxml", root);
  }
}
