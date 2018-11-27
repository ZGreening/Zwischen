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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
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

    //If an image file path was not loaded, use default avatar.png
    if (file == null) {
      file = new File("lib/UserData/default/avatar.png");
    }

    //Copy image to users folder
    try {
      Files.copy(Paths.get(file.getAbsolutePath()),
          Paths.get("lib/UserData/" + Globals.currentUser.getUserFolder() + "/avatar.png"),
          copyOptions);
    } catch (IOException exception) {
      System.out.println("Unable to save image\n" + Globals.currentUser.getUserFolder()
          + "/avatar.png may already exist");
    }
  }

  private void createUserFolder() {
    try {
      Path path1 = Paths.get("lib/UserData/" + Globals.currentUser.getUserFolder());
      Path path2 = Paths.get("messages");
      Path userMessagesPath = path1.resolve(path2);

      //Create user folder and user messages folder
      Files.createDirectory(path1);
      Files.createDirectory(userMessagesPath);
    } catch (IOException exception) {
      System.out.println("Unable to create user directory");
    }
  }

  /**
   * Generates a unique user foldername. Folder name will be the same as the username in all lower
   * case. If a folder already exists with the users username, then a number will be appended to the
   * foldername.
   *
   * @param username the username to generate a folder name for
   * @return the folder name as a string
   */
  private String generateUniqueFolderName(String username) {
    File file = new File("lib/UserData");
    File[] files = file.listFiles();
    boolean uniqueNameFound = false;
    String uniqueName = username.toLowerCase();
    int iii = 1;

    while (!uniqueNameFound) {
      uniqueNameFound = true;

      if (iii > 1) {
        uniqueName = username.toLowerCase() + iii;
      }

      for (File file1 : files) {
        if (file1.getName().toLowerCase().equals(uniqueName)) {
          uniqueNameFound = false;
          iii++;
          break;
        }
      }
    }

    return uniqueName;
  }

  /**
   * A function to store a new user account to the database. The function gets a unique foldername,
   * creates the user folders and saves the user image. It then logs in as the user. **Must have a
   * unique username**
   *
   * @param username new user's username
   * @param password new user's password
   * @param email new user's email
   * @param phoneNum new user's phone number
   */
  private void storeAccountAndLogin(String username, String password, String email,
      String phoneNum) {
    Globals.initializeDatabase();
    String folderName = generateUniqueFolderName(username);

    try {
      Globals.statement = Globals.getConnection()
          .prepareStatement(("INSERT INTO LOGIN(USERNAME, PASSWORD, EMAIL, PNUMBER) "
              + "VALUES('" + username + "','" + password + "','" + email + "','" + phoneNum + "','"
              + folderName + "')"));

      Globals.resultSet = Globals.statement
          .executeQuery("SELECT * FROM LOGIN WHERE USERNAME='" + username + "'");

      if (Globals.resultSet.next()) {
        feedbackLabel.setText("User already exists");
      } else {
        PreparedStatement statement = (PreparedStatement) Globals.statement;
        statement.executeUpdate();
        Globals.currentUser.loginUser(username, email, phoneNum, folderName);
        createUserFolder();
        saveUserImage();
      }
    } catch (SQLException exception) {
      System.out.println("Unable to create new user: " + username);
    } finally {
      Globals.shutdownDatabase();
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

      //if phone number was entered with parenthesis or dashes, format to a number only string
      if (phoneNumText.matches("\\([0-9]{3}\\)[0-9]{3}-[0-9]{4}")) {
        phoneNumText =
            phoneNumText.substring(1, 4) + phoneNumText.substring(5, 8) + phoneNumText.substring(9);
      } else if (phoneNumText.matches("[0-9]{3}-[0-9]{3}-[0-9]{4}")) {
        phoneNumText =
            phoneNumText.substring(0, 3) + phoneNumText.substring(4, 7) + phoneNumText.substring(8);
      }

      storeAccountAndLogin(usernameText, passwordText, emailText, phoneNumText);
    }
  }

  @FXML
  void onReturnToLoginPressed(ActionEvent event) {
    Globals.changeScene("login/Login.fxml", root);
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
        Paths.get("lib/UserData/" + Globals.currentUser.getUserFolder()).toUri().toString()
            + "/avatar.png"));
  }
}
