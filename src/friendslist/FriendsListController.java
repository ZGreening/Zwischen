package friendslist;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
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
  private ScrollPane scrollFriends;

  @FXML
  private ListView<String> friendslist;

  @FXML
  private ComboBox<String> addUserComboBox;

  private ObservableList<String> friendsListArray = FXCollections.observableArrayList();

  @FXML
  void onAddUserPressed(ActionEvent event) {
    String newFriend = addUserComboBox.getValue();
    if (!newFriend.isEmpty()) {
      //Adds selected friend to the list view
      if (!friendsListArray.contains(newFriend)) {
        friendsListArray.add(newFriend);
        friendslist.setItems(friendsListArray);
      }
      // Removes friend from the combobox
      if (friendsListArray.contains(newFriend)) {
        addUserComboBox.getItems().remove(newFriend);
      }
    }
  }

  @FXML
  void onReturnHomePressed(ActionEvent event) {
    Globals.closeScene(root);
  }

  @FXML
  void initialize() {
    ArrayList<String> usernames = Globals.getAllUsernames();
    usernames.remove(Globals.getCurrentUser().getUsername());
    addUserComboBox.setItems(FXCollections.observableArrayList(usernames));
  }
}
