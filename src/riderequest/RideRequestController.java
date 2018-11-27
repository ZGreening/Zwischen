///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        AvailableDriversController.java
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import other.Globals;
import other.Ride;


public class RideRequestController implements Initializable {

  @FXML
  private AnchorPane root;

  @FXML
  private Button requestAllButton;

  @FXML
  private Button requestCheckedButton;

  @FXML
  private Button ReturnHomeButton;

  @FXML
  private Label feedbackLabel;

  //setting up columns for tableview
  @FXML
  private TableView<Ride> availableDriversTableview;

  @FXML
  private TableColumn<Ride, String> driverColumn;

  @FXML
  private TableColumn<Ride, String> fromColumn;

  @FXML
  private TableColumn<Ride, String> toColumn;

  @FXML
  private TableColumn<Ride, LocalDate> dateColumn;

  @FXML
  private TableColumn<Ride, Integer> seatsColumn;

  @FXML
  private TableColumn<Ride, Button> messageColumn;

  @FXML
  private TableColumn<Ride, CheckBox> checkboxColumn;
  private ArrayList<GridPane> driverPane = new ArrayList<>();
  @FXML
  private VBox scrollpaneVBox;

  @FXML
  void onRequestAllButtonPressed(ActionEvent event) {
    if (availableDriversTableview.getItems().size() == 0) {
      feedbackLabel.setText("No Available Drivers To Select");
    } else {
      try {
        Connection conn13 = DriverManager.getConnection(
            Globals.dbURL, "zwischen", "fundamentals");
        Statement stmt13 = conn13.createStatement();

        //String query1 = "SELECT USERNAME FROM LOGIN WHERE UserName='"+ username+"';
        ResultSet resultSet13 = stmt13
            .executeQuery("SELECT * FROM RIDE WHERE [TO='" + Globals.rideRequested.dest + "']AND "
                + "[FROM='" + Globals.rideRequested.startP + "'] AND [TIME='"
                + Globals.rideRequested.date + "'] ");
        if (resultSet13.next()) {
          //Send request notification
          System.out.println("Ride Request sent");

        } else {
          feedbackLabel.setText("No Available Drivers To show");
        }
        stmt13.close();
      } catch (SQLException sqlExcept) {
        sqlExcept.printStackTrace();
        System.out.println("something went wrong");
      }


    }
  }

  @FXML
  void onRequestCheckedButtonPressed(ActionEvent event) {

    ObservableList<Ride> rides = FXCollections.observableArrayList();
    for (Ride ride : rides) {
      if (ride.checkBox.isSelected()) {
        //send request
        System.out.println(("request sent"));
      }

    }
  }

  @FXML
  void onReturnHomeButtonPressed(ActionEvent event) {
    Globals.changeScene("mainscreen/MainScreen.fxml", root);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    driverColumn.setCellValueFactory(new PropertyValueFactory<Ride, String>("driver"));
    toColumn.setCellValueFactory(new PropertyValueFactory<Ride, String>("to"));
    fromColumn.setCellValueFactory(new PropertyValueFactory<Ride, String>("StartP"));
    dateColumn.setCellValueFactory(new PropertyValueFactory<Ride, LocalDate>("date"));
    seatsColumn.setCellValueFactory(new PropertyValueFactory<Ride, Integer>("seats"));
    messageColumn.setCellValueFactory(new PropertyValueFactory<Ride, Button>("message"));
    checkboxColumn.setCellValueFactory(new PropertyValueFactory<Ride, CheckBox>("checkBox"));

    availableDriversTableview.setItems(getRides());

  }

  public ObservableList<Ride> getRides() {

    ObservableList<Ride> rides = FXCollections.observableArrayList();

    try {
      Connection conn12 = DriverManager.getConnection(
          Globals.dbURL, "zwischen", "fundamentals");
      Statement stmt12 = conn12.createStatement();

      //String query1 = "SELECT USERNAME FROM LOGIN WHERE UserName='"+ username+"';
      ResultSet resultSet12 = stmt12.executeQuery("SELECT * FROM RIDE");
      if (resultSet12.next()) {
        Ride ride = new Ride(resultSet12.getString("Driver"), resultSet12.getString("GOINGTO"),
            resultSet12.getString("COMINGFROM"), resultSet12.getDate("TIME"),
            resultSet12.getInt("SEATS_AVAILABLE"));
        rides.add(ride);
      } else {
        feedbackLabel.setText("No Available Drivers To show");
        Ride ride = new Ride("Dummy", "destination", "Start", new Date(), 3);
        rides.add(ride);
        Ride ride2 = new Ride("Dummy2", "destination", "Start", new Date(), 3);
        rides.add(ride2);
      }
      stmt12.close();
    } catch (SQLException sqlExcept) {
      sqlExcept.printStackTrace();
      System.out.println("something went wrong");
    }
    return rides;
  }






 /* @FXML
  void onSubmitPressed(ActionEvent event) {
    boolean atLeastOneDriver = false;
    int count = 0;

    for (GridPane gridPane : driverPane) {
      CheckBox checkBox = (CheckBox) gridPane.getChildren().get(1);
      if (checkBox.isSelected()) {
        atLeastOneDriver = true;
        Globals.availableDrivers.get(count).setSelectedToDrive(true);
      } else {
        Globals.availableDrivers.get(count).setSelectedToDrive(false);
      }
      count++;
    }

    if (atLeastOneDriver) {
      Globals.changeScene("mainscreen/MainScreen.fxml", root);
    } else {
      feedbackLabel.setText("No Drivers Selected");
    }

  }*/


}

