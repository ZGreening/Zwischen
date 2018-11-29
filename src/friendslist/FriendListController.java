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
  void onSendRequestPressed(ActionEvent event) throws SQLException {
    String requestNameEntryText = requestNameEntry.getText();

    if (requestNameEntryText.isEmpty()) {
      feedbackLabel.setText("request field empty");
    } else {

        Connection conn17 = DriverManager.getConnection(
            "jdbc:derby:lib/ZwischenDB");
        Statement stmt17 = conn17.createStatement();

        ResultSet resultSet18;
        try (ResultSet resultSet17 = stmt17.executeQuery(String.format("SELECT * FROM LOGIN WHERE USERNAME=%S",
            requestNameEntryText))) {

          Connection conn18 = DriverManager.getConnection(
              "jdbc:derby:lib/ZwischenDB");
          Statement stmt18 = conn18.createStatement();

          resultSet18 = stmt18.executeQuery(String.format("SELECT * FROM FRIENDS WHERE [FRIEND1=%s]AND[FRIEND2=%S]'",
              requestNameEntryText, Globals.getCurrentUser()));

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
          } resultSet17.close();
          stmt18.close();
          conn18.close();
          resultSet18.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      stmt17.close();

      conn17.close();
    }


  }



  @Override
  public void initialize(URL location, ResourceBundle resources) {

    ObservableList<Friends> friends = FXCollections.observableArrayList();

    try {
      try (Connection conn19 = DriverManager.getConnection(
          "jdbc:derby:lib/ZwischenDB")) {
        Statement stmt19 = conn19.createStatement();

        try (ResultSet resultSet19 = stmt19
            .executeQuery(String.format("SELECT * FROM FRIENDS WHERE [FRIEND1=%s]OR[FRIEND2=%s]",
                Globals.getCurrentUser(), Globals.getCurrentUser()
                    .getUsername()))) {
          if (resultSet19.next()) {
            while (resultSet19.next()) {
              if (resultSet19.getString("FRIEND1").equals(Globals.getCurrentUser().getUsername())) {
                Friends friend = new Friends(resultSet19.getString("FRIEND2"));
                friends.add(friend);
              } else if ((resultSet19.getString("FRIEND2").equals(Globals.getCurrentUser()
                  .getUsername()))) {
                Friends friend = new Friends(resultSet19.getString("FRIEND1"));
                friends.add(friend);
              }
            }
            stmt19.close();
            conn19.close();
          } else {
            feedbackLabel.setText("You have no friends");

          }
          stmt19.close();
          conn19.close();
        }
      }
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



