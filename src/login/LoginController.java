///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        LoginController.java
// Group:       3
// Date:        October 24, 2018
// Description: Controller class for login window
///////////////////////////////////////////////////////////////////////////////

package login;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import other.Globals;


public class LoginController {

  @FXML
  private Label feedbackLabel;

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

  private void checkAndLogin(String username, String password) {
    Globals.initializeDatabase();

    try {
      Globals.statement = Globals.getConnection().createStatement();
      Globals.resultSet = Globals.statement.executeQuery("SELECT * FROM LOGIN");
      boolean loggedIn = false;

      while (Globals.resultSet.next()) {
        //Get username and password from row
        String databaseUsername = Globals.resultSet.getString("USERNAME");
        String databasePassword = Globals.resultSet.getString("PASSWORD");
        String databaseEmail = Globals.resultSet.getString("EMAIL");
        String databasePhoneNumber = Globals.resultSet.getString("PNUMBER");
        String databaseFolder = Globals.resultSet.getString("FOLDER");

        //Determine if user entered password and username match
        if ((username.equals(databaseUsername)) && (password.equals(databasePassword))) {
          Globals.currentUser
              .loginUser(databaseUsername, databaseEmail, databasePhoneNumber, databaseFolder);
          Globals.changeScene("mainscreen/MainScreen.fxml", root);
          loggedIn = true;
          break;
        }
      }

      if (!loggedIn) {
        feedbackLabel.setText("Username or Password is incorrect");
      }

    } catch (SQLException sqlExcept) {
      System.out.println("Unable to check login database");
    } finally {
      Globals.shutdownDatabase();
    }
  }

  @FXML
  void onLoginPressed(ActionEvent event) {
    String localuser = username.getText();
    String localpass = password.getText();

    if ((localuser.isEmpty()) && (localpass.isEmpty())) {
      feedbackLabel.setText("Username and Password is empty");
    } else if (localuser.isEmpty()) {
      feedbackLabel.setText("Username Is Empty");
    } else if (localpass.isEmpty()) {
      feedbackLabel.setText("Password is empty");
    } else if (localuser.length() < 5) {
      feedbackLabel.setText("Username must be more than 5 characters");
    } else if (localpass.length() < 5) {
      feedbackLabel.setText("Password must be more than 5 characters");
    } else {
      checkAndLogin(localuser, localpass);
    }
  }
}
