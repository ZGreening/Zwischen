///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        LoginController.java
// Group:       3
// Date:        October 24, 2018
// Description: Controller class for login window
///////////////////////////////////////////////////////////////////////////////

package loginpage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import other.Globals;

public class LoginController {

  @FXML
  private PasswordField password;

  @FXML
  private TextField username;

  @FXML
  private AnchorPane root;

  @FXML
  void onCreateAccountPressed(ActionEvent event) {
    Globals.changeScene("createaccount/CreateAccount.fxml", root);
  }

  @FXML
  void onLoginPressed(ActionEvent event) {
    //Todo give user feedback on why it didn't login
    Globals.changeScene("mainscreen/mainScreen.fxml", root);
  }
}
