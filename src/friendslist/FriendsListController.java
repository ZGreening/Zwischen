package friendslist;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import other.Globals;

public class FriendsListController {



  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private AnchorPane root;

  @FXML
  private ComboBox<String> addUserComboBox;

  @FXML
  void onAddUserPressed(ActionEvent event) {

  }

  @FXML
  void onReturnHomePressed(ActionEvent event) {
    Globals.closeScene(root);
  }



  @FXML
  void initialize() {
    ArrayList<String> usernames = Globals.getAllUsernames();
    usernames.remove(Globals.getCurrentUser().getUsername());    //So you cannot send a message to yourself
    addUserComboBox.setItems(FXCollections.observableArrayList(usernames));
  }
}
