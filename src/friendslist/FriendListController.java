package friendslist;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import other.Friends;
import other.Globals;

public class FriendListController implements Initializable {

  @FXML
  private AnchorPane root;
  @FXML
  private Label feedbackLabel;

  @FXML
  private TextField requestNameEntry;

  @FXML
  private Button sendRequest;

  @FXML
  private Button returnHome;

  @FXML
  private TableView<Friends> FriendListtableview;

  @FXML
  private TableColumn<Friends, String> UsernameCollumn;

  @FXML
  private TableColumn<Friends, Button> MessegeCollumn;

  @FXML
  private TableColumn<Friends, Button> removeCollumn;

  @FXML
  void friendRequestText(ActionEvent event) {

  }

  @FXML
  void onReturnHomePressed(ActionEvent event) {

    Globals.changeScene("mainscreen/MainScreen.fxml", root);

  }

  @FXML
  void onSendRequestPressed(ActionEvent event) {
    String requestNameEntryText = requestNameEntry.getText();

    if (requestNameEntryText.isEmpty()) {
      feedbackLabel.setText("request field empty");
    } else {
      try {
        Connection conn17 = DriverManager.getConnection(
            Globals.dbURL, "zwischen", "fundamentals");
        Statement stmt17 = conn17.createStatement();

        ResultSet resultSet17 = stmt17.executeQuery("SELECT * FROM USERS WHERE USERNAME='" +
            requestNameEntryText + "'");

        Connection conn18 = DriverManager.getConnection(
            Globals.dbURL, "zwischen", "fundamentals");
        Statement stmt18 = conn17.createStatement();

        ResultSet resultSet18 = stmt18.executeQuery("SELECT * FROM FRIENDS WHERE [[FRIEND1='" +
            requestNameEntryText + "']AND " + "[FRIEND2'" + Globals.currentUser + "']]OR[[FRIEND1'"
            +
            Globals.currentUser + "']AND[FRIEND1='" + requestNameEntryText + "']");

        if (resultSet18.next()) {

          feedbackLabel.setText("You are already friends");
          System.out.println("already friends");
        } else if (resultSet17.next()) {
          //todo send notification with friend request
          feedbackLabel.setText("Friend request sent");
          System.out.println("Friend request sent");
        } else {
          feedbackLabel.setText("invalid username entry");
          System.out.println("invalid username entry");
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }

    }


  }


  @Override
  public void initialize(URL location, ResourceBundle resources) {

    ObservableList<Friends> friends = FXCollections.observableArrayList();

    try {
      Connection conn19 = DriverManager.getConnection(
          Globals.dbURL, "zwischen", "fundamentals");
      Statement stmt19 = conn19.createStatement();

      ResultSet resultSet19 = stmt19.executeQuery("SELECT * FROM FRIENDS WHERE [FRIEND1='" +
          Globals.currentUser + "']OR " + "[FRIEND2'" + Globals.currentUser + "']");
      if (resultSet19.next()) {
        if (resultSet19.getString("FRIEND1") == Globals.currentUser.getUsername()) {
          Friends friend = new Friends(resultSet19.getString("FRIEND2"));
          friends.add(friend);
        } else if ((resultSet19.getString("FRIEND2") == Globals.currentUser.getUsername())) {
          Friends friend = new Friends(resultSet19.getString("FRIEND1"));
          friends.add(friend);
        }
      } else {
        feedbackLabel.setText("You have no friends");
      }
      stmt19.close();
    } catch (SQLException sqlExcept) {
      sqlExcept.printStackTrace();
      System.out.println("something went wrong");
    }

    UsernameCollumn.setCellValueFactory(new PropertyValueFactory<Friends, String>("friendName"));
    MessegeCollumn.setCellValueFactory(new PropertyValueFactory<Friends, Button>("messageFriend"));
    removeCollumn.setCellValueFactory(new PropertyValueFactory<Friends, Button>("removeFriend"));

    FriendListtableview.setItems(friends);
  }
}



