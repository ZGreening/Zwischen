///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        CreateAccountController.java
// Group:       3
// Date:        October 24, 2018
// Description: Controller class for create account screen
///////////////////////////////////////////////////////////////////////////////

package createaccount;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import other.Globals;

import static other.Globals.stmt;

public class CreateAccountController {

  private File file;

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
  private ImageView avatar;

  @FXML
  private Label feedbackLabel;

  @FXML
  private AnchorPane root;

  /**
   * Saves a user image to the lib/UserData folder. Username must be unique, otherwise the file will
   * not save.
   */
  private void saveUserImage() {
    CopyOption[] copyOptions = new CopyOption[]{
        StandardCopyOption.COPY_ATTRIBUTES
    };

    if (file != null) {
      try {
        Files.copy(Paths.get(file.getAbsolutePath()),
            Paths.get("lib/UserData/" + Globals.currentUser.getUsername() + ".png"), copyOptions);
      } catch (IOException exception) {
        System.out.println("Unable to save image\n" + Globals.currentUser.getUsername()
            + ".png may already exist");
      }
    }
  }

  private void storeNewAccount(String username, String password, String email, String phoneNum) {
    //todo add database code
    try {
       stmt = Globals.conn.createStatement();
       stmt.execute("INSERT INTO LOGIN(USERNAME, PASSWORD, EMAIL, PNUMBER) " +
               "VALUES ('" + username + "','" + password + "','" + email +"','" + phoneNum +"')");
       stmt.close();
    } catch (SQLException sqlExcept) {
      sqlExcept.printStackTrace();
    }

  }

  @FXML
  void onCreateAccountPressed(ActionEvent event) {
    //Get text strings
    String usernameText = username.getText();
    String passwordText = password.getText();
    String confirmPasswordText = confirmPassword.getText();
    String emailText = email.getText();
    String phoneNumText = phoneNum.getText();

    //Ensure all fields are filled and email and phone number are in correct format
    if (usernameText.isEmpty()) {
      feedbackLabel.setText("Username is empty");
    } else if (passwordText.isEmpty()) {
      feedbackLabel.setText("Password is empty");
    } else if (confirmPasswordText.isEmpty()) {
      feedbackLabel.setText("Confirm password is empty");
    } else if (!passwordText.equals(confirmPasswordText)) {
      feedbackLabel.setText("Passwords do not match");
    } else if (emailText.isEmpty()) {
      feedbackLabel.setText("Email is empty");
    } else if (!emailText
        .matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) {
      feedbackLabel.setText("Invalid Email");
    } else if (phoneNumText.isEmpty()) {
      feedbackLabel.setText("Phone number is empty");
    } else if (!(phoneNumText.matches("\\([0-9]{3}\\)[0-9]{3}-[0-9]{4}")
        || phoneNumText.matches("[0-9]{3}-[0-9]{3}-[0-9]{4}")
        || phoneNumText.matches("[0-9]{10}"))) {
      feedbackLabel.setText("Incorrect phone number format");
    } else {
      //Set the current user's info
      Globals.currentUser.setUsername(usernameText);
      Globals.currentUser.setEmail(emailText);
      Globals.currentUser.setPhoneNum(phoneNumText);

      //Sees if db is connected
      Globals.createConnection();

      //If none of the issues above, change screens
      saveUserImage();

      //Add account to database
      storeNewAccount(usernameText, passwordText, emailText, phoneNumText);

      Globals.changeScene("loginpage/LoginPage.fxml", root);
    }
  }

  @FXML
  void onReturnToLoginPressed(ActionEvent event) {
    Globals.changeScene("loginpage/LoginPage.fxml", root);
  }

  @FXML
  void onUploadPressed(ActionEvent event) {
    //Open up file chooser to user's documents directory
    FileChooser fileChooser = new FileChooser();
    fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/Documents"));

    //Only allow jpeg jpg and png to be selected
    ArrayList<String> extensionList = new ArrayList<>();
    extensionList.add("*.jpeg");
    extensionList.add("*.jpg");
    extensionList.add("*.png");
    fileChooser.getExtensionFilters().addAll(
        new ExtensionFilter("PNG, JPG, or JPEG", extensionList));

    //Open file chooser
    file = fileChooser.showOpenDialog(root.getScene().getWindow());

    //When a file is selected, set as avatar
    if (file != null) {
      avatar.setImage(new Image(file.toURI().toString()));
    }
  }

  @FXML
  void initialize() {
    //Set up image to use current user's username for image, "default" by default
    avatar.setImage(new Image(
        Paths.get("lib/UserData/" + Globals.currentUser.getUsername()).toUri().toString()
            + ".png"));
  }
}
