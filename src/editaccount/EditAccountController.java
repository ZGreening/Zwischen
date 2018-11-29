///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        EditAccountController.java
// Group:       3
// Date:        October 24, 2018
// Description: Controller class for edit account window
///////////////////////////////////////////////////////////////////////////////

package editaccount;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import other.Globals;

public class EditAccountController {

  @FXML
  private AnchorPane root;

  @FXML
  private ImageView avatar;

  @FXML
  private Label username;

  @FXML
  private PasswordField password;

  @FXML
  private TextField phoneNum;

  @FXML
  private TextField email;

  @FXML
  private PasswordField confirmPassword;

  @FXML
  private Label feedbackLabel;

  @FXML
  void onReturnToMainScreenPressed(ActionEvent event) {
    Globals.changeScene("mainscreen/MainScreen.fxml", root);
  }


  void updateAccount(String passToUpdate, String emailToUpdate, String pNumberToUpdate) {
    try (Connection connection = DriverManager.getConnection("jdbc:derby:lib/ZwischenDB");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM LOGIN")) {

      if (!passToUpdate.isEmpty()) {
        statement.executeUpdate("UPDATE LOGIN SET PASSWORD = '" + passToUpdate + "' " +
            "WHERE USERNAME = '" + Globals.getCurrentUser().getUsername() + "'");
      } else {
        feedbackLabel.setText("Password not updated");
      }
      statement.executeUpdate(String
          .format("UPDATE LOGIN SET EMAIL = '%s' WHERE USERNAME = '%s'", emailToUpdate,
              Globals.getCurrentUser().getUsername()));
      statement.executeUpdate(String
          .format("UPDATE LOGIN SET PNUMBER = '%s' WHERE USERNAME = '%s'", pNumberToUpdate,
              Globals.getCurrentUser().getUsername()));
      feedbackLabel.setText("Account updated");
    } catch (SQLException sqlExcept) {
      sqlExcept.printStackTrace();
    }
  }

  @FXML
  void onUpdateAccountPressed(ActionEvent event) {
    String newpass = password.getText();
    String newConfirmPass = confirmPassword.getText();
    String newPNumber = phoneNum.getText();
    String newEmail = email.getText();

    if (!newpass.equals(newConfirmPass)) {
      feedbackLabel.setText("Passwords do not match");
    } else if (newEmail.isEmpty()) {
      feedbackLabel.setText("Email is empty");
    } else if (!newEmail
        .matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) {
      feedbackLabel.setText("Invalid Email");
    } else if (newPNumber.isEmpty()) {
      feedbackLabel.setText("Phone number is empty");
    } else if (!(newPNumber.matches("\\([0-9]{3}\\)[0-9]{3}-[0-9]{4}")
        || newPNumber.matches("[0-9]{3}-[0-9]{3}-[0-9]{4}")
        || newPNumber.matches("[0-9]{10}"))) {
      feedbackLabel.setText("Incorrect phone number format");
    } else {
      newPNumber = Globals.formatPhoneNum(newPNumber);
      updateAccount(newpass, newEmail, newPNumber);
    }
  }

  @FXML
  void onUploadPressed(ActionEvent event) {
    //Todo Add functionality
  }

  @FXML
  void onPhoneNumMouseClicked() {
    phoneNum.selectAll();
  }

  @FXML
  void onEmailMouseClicked() {
    email.selectAll();
  }

  @FXML
  void initialize() {
    //Set up image to use current user's username for image, "default" by default
    avatar.setImage(new Image(
        Paths.get("lib/UserData/" + Globals.getCurrentUser().getUserFolder() + "/avatar.png")
            .toUri().toString()));

    //display username
    username.setText(Globals.getCurrentUser().getUsername());

    //Display current user's phone number
    phoneNum.setText("(" + Globals.getCurrentUser().getPhoneNum().substring(0, 3) + ")"
        + Globals.getCurrentUser().getPhoneNum().substring(3, 6) + "-"
        + Globals.getCurrentUser().getPhoneNum().substring(6, 10));

    //Display current user's email
    email.setText(Globals.getCurrentUser().getEmail());
  }
}

