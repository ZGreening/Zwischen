package zwischen.editaccount;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class EditAccountController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button gotohomepage;

    @FXML
    private Button viewDrivers;

    @FXML
    private Button mapspage;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private Button updateAccount;

    @FXML
    void goToMapsPage(ActionEvent event) {

    }

    @FXML
    void goToViewDrivers(ActionEvent event) {

    }

    @FXML
    void returnToHomepage(ActionEvent event) {

    }

    @FXML
    void updateAccountInfo(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert gotohomepage != null : "fx:id=\"gotohomepage\" was not injected: check your FXML file 'editAccountWindow.fxml'.";
        assert viewDrivers != null : "fx:id=\"viewDrivers\" was not injected: check your FXML file 'editAccountWindow.fxml'.";
        assert mapspage != null : "fx:id=\"mapspage\" was not injected: check your FXML file 'editAccountWindow.fxml'.";
        assert firstName != null : "fx:id=\"firstName\" was not injected: check your FXML file 'editAccountWindow.fxml'.";
        assert lastName != null : "fx:id=\"lastName\" was not injected: check your FXML file 'editAccountWindow.fxml'.";
        assert phoneNumber != null : "fx:id=\"phoneNumber\" was not injected: check your FXML file 'editAccountWindow.fxml'.";
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'editAccountWindow.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'editAccountWindow.fxml'.";
        assert updateAccount != null : "fx:id=\"updateAccount\" was not injected: check your FXML file 'editAccountWindow.fxml'.";

    }
}
