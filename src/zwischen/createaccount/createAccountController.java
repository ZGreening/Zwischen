package zwischen.createaccount;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.awt.event.ActionEvent;

public class createAccountController {

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
    private Button createAccount;

    @FXML
    private Button backToHomePage;

    @FXML
    void goToHomePage(ActionEvent event) {

    }

    @FXML
    void initializeAccountInfo(ActionEvent event) {

    }

}
