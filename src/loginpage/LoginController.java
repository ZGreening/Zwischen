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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import other.Globals;
import static other.Globals.createConnection;
import static other.Globals.stmt;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;


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

  private void checkLogin(String username, String password) {
    //todo add database code
    createConnection();
    try {
      String databaseUsername = "";
      String databasePassword = "";
      ResultSet resultSet;
      String query = "SELECT * FROM LOGIN";
      resultSet = stmt.executeQuery(query);

      System.out.println("Login checked");

      while(resultSet.next()) {
        databaseUsername = resultSet.getString("USERNAME");
        databasePassword = resultSet.getString("PASSWORD");
        if ((username.equals(databaseUsername)) && (password.equals(databasePassword))) {
          Globals.changeScene("mainscreen/MainScreen.fxml", root);
        } else {
          feedbackLabel.setText("Invalid Login Credentials");
        }
      }

      stmt.close();
    } catch (SQLException sqlExcept) {
      sqlExcept.printStackTrace();
      System.out.println("failed to check login");
    }

  }

  @FXML
  void onLoginPressed(ActionEvent event) {
    //Demonstration of feedback label
    String localuser = username.getText();
    String localpass = password.getText();

    //Todo give user feedback on why it didn't login
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
    }
    else {
      //SELECT * FROM LOGIN WHERE USERNAME = '" + username + "' AND PASSWORD = '" + password + "'"
      //does user exist?
      checkLogin(localuser, localpass);

      //if user exists enter program
      //Globals.changeScene("mainscreen/MainScreen.fxml", root);
    }
  }
}
