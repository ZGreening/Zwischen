package zwischen.loginpage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button CreateAccount;

    @FXML
    private PasswordField Password;

    @FXML
    private TextField Username;

    @FXML
    private Button Login;

    @FXML
    void goToCreateAccount(ActionEvent event) {

    }

    @FXML
    void initializeLoginInfo(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert CreateAccount != null : "fx:id=\"CreateAccount\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert Password != null : "fx:id=\"Password\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert Username != null : "fx:id=\"Username\" was not injected: check your FXML file 'LoginPage.fxml'.";
        assert Login != null : "fx:id=\"Login\" was not injected: check your FXML file 'LoginPage.fxml'.";

    }
}
