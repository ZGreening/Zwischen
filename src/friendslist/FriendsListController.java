package friendslist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import other.Globals;
import other.User;

public class FriendsListController {

  @FXML
  private AnchorPane root;

  @FXML
  private ListView<String> friendsList;

  @FXML
  private ComboBox<String> addUserComboBox;

  private ObservableList<String> friendsListArray = FXCollections.observableArrayList();

  @FXML
  void onAddUserPressed(ActionEvent event) {
    String newFriend = addUserComboBox.getValue();

    if (newFriend != null) {
      //Adds selected friend to the list view
      if (!friendsListArray.contains(newFriend)) {
        friendsListArray.add(newFriend);
        friendsList.setItems(friendsListArray);
      }

      try (Connection connection = DriverManager.getConnection("jdbc:derby:lib/ZwischenDB");
          Statement statement = connection.createStatement();
          ResultSet resultSet = statement
              .executeQuery(String.format("SELECT * from LOGIN where USERNAME='%s'", newFriend))) {
        if (resultSet.next()) {
          String phoneNumber = resultSet.getString("PNUMBER");
          String email = resultSet.getString("EMAIL");
          Globals.getCurrentUser().getFriends().add(new User(newFriend, email, phoneNumber));
        }
      } catch (SQLException exception) {
        System.out.println("Failed to get friend information from database");
      }

      Globals.getCurrentUser().storeFriends();

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

    for (User friend : Globals.getCurrentUser().getFriends()) {
      friendsListArray.add(friend.getUsername());
      addUserComboBox.getItems().remove(friend.getUsername());
    }

    //Load friends
    friendsList.setItems(friendsListArray);
  }
}
