///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        EditAccountController.java
// Group:       3
// Date:        October 24, 2018
// Description: Controller class for edit account window
///////////////////////////////////////////////////////////////////////////////

package editaccount;

import java.nio.file.Paths;
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

  @FXML
  void onUpdateAccountPressed(ActionEvent event) {
    //Todo Add functionality
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

