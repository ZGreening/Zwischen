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

  private void saveUserImage() {
    new File(Globals.userdataPath.toUri()).mkdirs();

    CopyOption[] copyOptions = new CopyOption[]{
        StandardCopyOption.REPLACE_EXISTING,
        StandardCopyOption.COPY_ATTRIBUTES
    };

    if (file != null) {
      try {
        Files.copy(Paths.get(file.getAbsolutePath()),
            Paths.get(Globals.userdataPath.toString() + "/Avatar(" + (
                new File(Globals.userdataPath.toUri()).listFiles().length + 1) + ").png"),
            copyOptions);
      } catch (IOException exception) {
        System.out.println("Unable to save image");
      }
    }
  }

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
    } else if (!(phoneNum.getText().matches("\\([0-9]{3}\\)[0-9]{3}-[0-9]{4}")
        || phoneNum.getText().matches("[0-9]{3}-[0-9]{3}-[0-9]{4}")
        || phoneNum.getText().matches("[0-9]{10}"))) {
      feedbackLabel.setText("Incorrect phone number format");
    } else {
      //If none of the issues above, change screens
      saveUserImage();
      Globals.changeScene("mainscreen/MainScreen.fxml", root);
    }
  }

  @FXML
  void onReturnToLoginPressed(ActionEvent event) {
    Globals.changeScene("loginpage/LoginPage.fxml", root);
  }

  @FXML
  void onUploadPressed(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/Documents"));

    ArrayList<String> extensionList = new ArrayList<>();
    extensionList.add("*.jpeg");
    extensionList.add("*.jpg");
    extensionList.add("*.png");

    fileChooser.getExtensionFilters().addAll(
        new ExtensionFilter("PNG, JPG, or JPEG", extensionList));

    file = fileChooser.showOpenDialog(root.getScene().getWindow());

    if (file != null) {
      Globals.currentUser.setImageUrl(file.toURI().toString());
      avatar.setImage(new Image(Globals.currentUser.getImageUrl()));
    }
  }

  @FXML
  void initialize() {
    avatar.setImage(new Image(Globals.currentUser.getImageUrl()));
  }
}
