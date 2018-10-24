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
  private PasswordField Password;

  @FXML
  private TextField UserName;

  @FXML
  private PasswordField ConfirmPassword;

  @FXML
  private TextField Email;

  @FXML
  private TextField PhoneNumber;

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
    assert Password
        != null : "fx:id=\"Password\" was not injected: check your FXML file 'CreateAccount.fxml'.";
    assert UserName
        != null : "fx:id=\"UserName\" was not injected: check your FXML file 'CreateAccount.fxml'.";
    assert ConfirmPassword
        != null : "fx:id=\"ConfirmPassword\" was not injected: check your FXML file 'CreateAccount.fxml'.";
    assert Email
        != null : "fx:id=\"Email\" was not injected: check your FXML file 'CreateAccount.fxml'.";
    assert PhoneNumber
        != null : "fx:id=\"PhoneNumber\" was not injected: check your FXML file 'CreateAccount.fxml'.";

  }
}
