///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        RideRequest.java
// Group:       3
// Date:        October 24, 2018
// Description: Controller class for available drivers screen
///////////////////////////////////////////////////////////////////////////////

package riderequest;

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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import messages.MessagesController;
import other.Globals;
import other.Request;
import other.Ride;

public class RideRequest implements Initializable {

  //private Message message;
  ObservableList<Ride> available = getRides();
  @FXML
  private AnchorPane root;
  @FXML
  private TableView<Ride> requestDriversTableview;

  @FXML
  private TableColumn<Ride, String> driverColumn;

  @FXML
  private TableColumn<Ride, String> fromColumn;

  @FXML
  private TableColumn<Ride, String> toColumn;

  @FXML
  private TableColumn<Ride, String> dateColumn;

  @FXML
  private TableColumn<Ride, Button> messageColumn;

  @FXML
  private TableColumn<Ride, CheckBox> requestColumn;

  @FXML
  private ComboBox<String> pickupComboBox;

  @FXML
  private ComboBox<String> destinationComboBox;

  @FXML
  private ComboBox<String> timeComboBox;

  private ObservableList<String> time = FXCollections
      .observableArrayList("12:00 AM", "12:30 AM", "1:00 AM", "1:30 AM", "2:00 AM", "2:30 AM",
          "3:00 AM", "3:30 AM", "4:00 AM", "4:30 AM", "5:00 AM", "5:30 AM", "6:00 AM", "6:30 AM",
          "7:00 AM", "7:30 AM", "8:00 AM", "8:30 AM", "9:00 AM", "9:30 AM", "10:00 AM", "10:30 AM",
          "11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM", "1:00 PM", "1:30 PM", "2:00 PM",
          "2:30 PM", "3:00 PM", "3:30 PM", "4:00 PM", "4:30 PM", "5:00 PM", "5:30 PM", "6:00 PM",
          "6:30 PM", "7:00 PM", "7:30 PM", "8:00 PM", "8:30 PM", "9:00 PM", "9:30 PM", "10:00 PM",
          "10:30 PM", "11:00 PM", "11:30 PM");

  private ObservableList<String> locations = FXCollections
      .observableArrayList("Coastal Village Apartments",
          "Coconut Point Mall", "Florida Gulf Coast University",
          "Florida SouthWestern State College",
          "Gulf Coast Town Center", "Miromar Outlets", "The Reef Apartments",
          "Walmart Supercenter (Estero)");

  private ObservableList<Ride> getRides() {
    ObservableList<Ride> rides = FXCollections.observableArrayList();

    try {

      try (

          Connection conn130 = DriverManager.getConnection(
              "jdbc:derby:lib/ZwischenDB");

          Statement stmt130 = conn130.createStatement()) {

        String query = String
            .format("SELECT * FROM RIDE WHERE GOINGTO = '%s' AND COMINGFROM = '%s'",
                Globals.currentRequest.getGoing(), Globals.currentRequest.getComing());

        ResultSet resultSet130 = stmt130.executeQuery(query);

        if (!resultSet130.wasNull()) {
          while (resultSet130.next()) {
            Ride ride = new Ride(resultSet130.getString("DRIVER"),
                resultSet130.getString("GOINGTO"),
                resultSet130.getString("COMINGFROM"),
                resultSet130.getString("OCCURRANCE"), resultSet130.getInt("SEATS"));
            rides.add(ride);
          }

        }
        resultSet130.close();
      }
    } catch (SQLException sqlExcept) {
      sqlExcept.printStackTrace();
      System.out.println("something went wrong");
    }
    return rides;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    pickupComboBox.setItems(locations);
    destinationComboBox.setItems(locations);
    timeComboBox.setItems(time);

    driverColumn.setCellValueFactory(new PropertyValueFactory<Ride, String>("driver"));
    toColumn.setCellValueFactory(new PropertyValueFactory<Ride, String>("to"));
    fromColumn.setCellValueFactory(new PropertyValueFactory<Ride, String>("startP"));
    dateColumn.setCellValueFactory(new PropertyValueFactory<Ride, String>("occurance"));
    messageColumn.setCellValueFactory(new PropertyValueFactory<Ride, Button>("message"));
    requestColumn.setCellValueFactory(new PropertyValueFactory<Ride, CheckBox>("checkBox"));

    for (int p = 0; p < requestDriversTableview.getItems().size(); p++) {

      requestDriversTableview.setItems(available);

    }
  }

  @FXML
  void onCancelPressed(ActionEvent event) {
    Globals.closeScene(root);
  }


  @FXML
  void onSubmitPressed(ActionEvent event) {


    for (Ride ride : available) {
      String query = String.format(
          String.format("INSERT INTO PAST_RIDE VALUES('%s','%s','%s','%d','%s')", ride.getDriver(),
              Globals.getCurrentUser().getUsername(), ride.getDest(), ride.getStartP(),
              ride.getIdnumber(),
              ride.getOccurrance()));

      if (ride.getCheckBox().isSelected()) {


        try (Connection conn133 = DriverManager.getConnection("jdbc:derby:lib/ZwischenDB")) {
          Statement stmt133 = conn133.createStatement();
          ResultSet rs133 = stmt133.executeQuery(
              String.format("SELECT * FROM PAST_RIDE WHERE IDENTIFIER = %d", ride.getIdnumber()));
          if (rs133.next()) {
            System.out.println("Already sent. Go to Ride History and  send a message");
          } else {
            stmt133.executeUpdate(query);
            System.out.println(("sent"));
          }
          stmt133.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }

        Globals.changeScene("messages/Messages.fxml");

        FXMLLoader loader = new FXMLLoader(
            getClass().getClassLoader().getResource("messages/Messages.fxml"));
        MessagesController controller = loader.getController();
        ComboBox<String> comboBox = controller.getRecipient();
        comboBox.getSelectionModel().select(ride.getDriver());
        available.remove(ride);


      }
    }
  }

  @FXML
  void onSearchPressed(ActionEvent event) {
    driverColumn.setCellValueFactory(new PropertyValueFactory<Ride, String>("driver"));
    toColumn.setCellValueFactory(new PropertyValueFactory<Ride, String>("to"));
    fromColumn.setCellValueFactory(new PropertyValueFactory<Ride, String>("startP"));
    dateColumn.setCellValueFactory(new PropertyValueFactory<Ride, String>("occurance"));
    messageColumn.setCellValueFactory(new PropertyValueFactory<Ride, Button>("message"));
    requestColumn.setCellValueFactory(new PropertyValueFactory<Ride, CheckBox>("checkBox"));

    Globals.currentRequest = new Request(destinationComboBox.getValue(), pickupComboBox.getValue(),
        timeComboBox.getValue());
    available = getRides();
    requestDriversTableview.setItems(available);
  }
}
