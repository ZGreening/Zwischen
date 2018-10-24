package createaccount;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreateAccountController {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

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
  private AnchorPane createAccount;

  @FXML
  void onBackToHomepagePressed(ActionEvent event) throws Exception {
    //todo should this be "homepage" or "loginpage"?

    Stage stage = (Stage) createAccount.getScene().getWindow();

    stage.close();

    Parent root = FXMLLoader.load(
        getClass().getClassLoader().getResource("loginpage/LoginPage.fxml"));

    Scene scene = new Scene(root);

    stage = new Stage();

    stage.setTitle("Zwischen");

    stage.setScene(scene);

    stage.show();
  }

  @FXML
  void onCreateYourAccountPressed(ActionEvent event) throws Exception {
    Stage stage = (Stage) createAccount.getScene().getWindow();

    stage.close();

    Parent root = FXMLLoader.load(
        getClass().getClassLoader().getResource("mainscreen/MainScreen.fxml"));

    Scene scene = new Scene(root);

    stage = new Stage();

    stage.setTitle("Zwischen");

    stage.setScene(scene);

    stage.show();
  }

  @FXML
  void initialize() {
    assert password
        != null : "fx:id=\"password\" was not injected: check your FXML file 'CreateAccount.fxml'.";
    assert username
        != null : "fx:id=\"username\" was not injected: check your FXML file 'CreateAccount.fxml'.";
    assert confirmPassword
        != null : "fx:id=\"confirmPassword\" "
        + "was not injected: check your FXML file 'CreateAccount.fxml'.";
    assert email
        != null : "fx:id=\"email\" was not injected: check your FXML file 'CreateAccount.fxml'.";
    assert phoneNumber
        != null : "fx:id=\"phoneNumber\" "
        + "was not injected: check your FXML file 'CreateAccount.fxml'.";

  }
}
