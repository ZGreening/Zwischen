package loginpage;

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

public class LoginController {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private PasswordField password;

  @FXML
  private TextField username;

  @FXML
  private AnchorPane loginWindow;

  @FXML
  void onCreateAccountPressed(ActionEvent event) throws Exception {
    Stage stage = (Stage) loginWindow.getScene().getWindow();

    stage.close();

    Parent root = FXMLLoader.load(
        getClass().getClassLoader().getResource("createaccount/CreateAccount.fxml"));

    Scene scene = new Scene(root);

    stage = new Stage();

    stage.setTitle("Zwischen");

    stage.setScene(scene);

    stage.show();

  }

  @FXML
  void onLoginPressed(ActionEvent event) throws Exception {
    //Todo give user feedback on why it didn't login
    if (username.getText().isEmpty() || password.getText().isEmpty()) {
      return;
    }

    Stage stage = (Stage) loginWindow.getScene().getWindow();

    stage.close();

    Parent root = FXMLLoader.load(
        getClass().getClassLoader().getResource("mainscreen/mainScreen.fxml"));

    Scene scene = new Scene(root);

    stage = new Stage();

    stage.setTitle("Zwischen");

    stage.setScene(scene);

    stage.show();
  }

  @FXML
  void initialize() {
    assert password
        != null : "fx:id=\"password\" was not injected: check your FXML file 'LoginPage.fxml'.";
    assert username
        != null : "fx:id=\"username\" was not injected: check your FXML file 'LoginPage.fxml'.";

  }
}
