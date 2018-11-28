///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        RideHistoryController.java
// Group:       3
// Date:        October 24, 2018
// Description: Controller class for ride history screen
///////////////////////////////////////////////////////////////////////////////

package ridehistory;


import static other.Globals.getCurrentUser;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import messages.MessagesController;
import other.Globals;
import other.PastRide;
import other.Ride;

public class RideHistoryController implements Initializable {




    @FXML
    private AnchorPane root;

    @FXML
    private TableView<PastRide> availableDriversTableview;

    @FXML
    private TableColumn<PastRide, String> driverColumn;

    @FXML
    private TableColumn<PastRide, String> fromColumn;

    @FXML
    private TableColumn<PastRide, String> toColumn;

    @FXML
    private TableColumn<PastRide, Date> dateColumn;

    @FXML
    private TableColumn<PastRide, Button> messageColumn;


    @FXML
    private TableColumn<PastRide, CheckBox> deleteColumn;

    @FXML
    private Label feedbackLabel;

    @FXML
    private Button deleteAllButton;

    @FXML
    private Button deleteCheckedButton;

    @FXML
    private Button ReturnHomeButton;

    @FXML
    void onDeleteAllButtonPressed(ActionEvent event) throws SQLException {
      ObservableList<PastRide> rides = FXCollections.observableArrayList();
      for (PastRide ride : rides) {

        Connection conn121 = DriverManager.getConnection(
            "jdbc:derby:lib/ZwischenDB");
        PreparedStatement stmt121 = conn121.prepareStatement("DELETE FROM PAST_RIDE WHERE [DRIVER ='" +
            getCurrentUser().getUsername() + "']OR[RIDER ='" +
            getCurrentUser().getUsername() + "']");
        stmt121.execute();

          System.out.println(("deleted"));
          stmt121.close();
          conn121.close();
        }


    }

    @FXML
    void onDeleteCheckedButtonPressed(ActionEvent event) throws SQLException {

      ObservableList<PastRide> rides = FXCollections.observableArrayList();
      for (PastRide ride : rides) {
        if (ride.getCheckBox().isSelected()) {
          Connection conn123 = DriverManager.getConnection(
              "jdbc:derby:lib/ZwischenDB");
          PreparedStatement stmt123 = conn123.prepareStatement("DELETE FROM PAST_RIDE WHERE [DRIVER ='" +
              ride.getDriver() + "']AND[RIDER ='" +
              ride.getRider() + "']AND[GOINGTO ='" +
              ride.getDest() + "']AND[COMINGFROM ='" +
              ride.getStartP() + "']AND [OCCURRANCE ='" +
              ride.getDate() + "']");
          stmt123.execute();
          stmt123.close();
          conn123.close();
          System.out.println(("deleted"));

        }

      }
    }

    @FXML
    void onReturnHomeButtonPressed(ActionEvent event) {

    }
  public ObservableList<PastRide> getPastRides() {

    ObservableList<PastRide> pastRides = FXCollections.observableArrayList();

    try {
      Connection conn120 = DriverManager.getConnection(
          "jdbc:derby:lib/ZwischenDB");
      Statement stmt120 = conn120.createStatement();

      //String query1 = "SELECT USERNAME FROM LOGIN WHERE UserName='"+ username+"';
      ResultSet resultSet120 = stmt120.executeQuery("SELECT * FROM PAST_RIDE WHERE [DRIVER ='" +
          getCurrentUser().getUsername() + "']OR" + "[RIDER='" + getCurrentUser().getUsername() + "']");
      if(resultSet120.next()){
      while (resultSet120.next()) {
        PastRide pastRide = new PastRide(resultSet120.getString("DRIVER"), resultSet120.getString("RIDER"),
            resultSet120.getString("GOINGTO"), resultSet120.getString("COMINGFROM"),
            resultSet120.getDate("DATE"));
        pastRides.add(pastRide);
      }} else {
        feedbackLabel.setText("No Available Drivers To show");
        PastRide ride = new PastRide("Dummy", "driver", "destination", "Start", new Date());
        pastRides.add(ride);
        PastRide ride2 = new PastRide("Dummy2", "driver", "destination", "Start", new Date());
        pastRides.add(ride2);
      }
      conn120.close();
      stmt120.close();
      resultSet120.close();
    } catch (SQLException sqlExcept) {
      sqlExcept.printStackTrace();
      System.out.println("something went wrong");
    }
    return pastRides;
  }

  Button []messege = new Button[availableDriversTableview.getItems().size()];




  @Override
  public void initialize(URL location, ResourceBundle resources) {
    driverColumn.setCellValueFactory(new PropertyValueFactory<PastRide, String>("driver"));
    toColumn.setCellValueFactory(new PropertyValueFactory<PastRide, String>("to"));
    fromColumn.setCellValueFactory(new PropertyValueFactory<PastRide, String>("StartP"));
    dateColumn.setCellValueFactory(new PropertyValueFactory<PastRide, Date>("date"));
    messageColumn.setCellValueFactory(new PropertyValueFactory<PastRide, Button>("message"));
    deleteColumn.setCellValueFactory(new PropertyValueFactory<PastRide, CheckBox>("checkBox"));

    for(int p = 0; p<availableDriversTableview.getItems().size();p++){
      messege[p]= new Button();
      final int j = p;
      messege[p].setOnAction((ActionEvent event) -> {    Globals.changeScene("messages/Messages.fxml");
        try {
          FXMLLoader loader = new FXMLLoader(
              getClass().getClassLoader().getResource("messages/Messages.fxml"));
          MessagesController controller = loader.getController();
          ComboBox<String> comboBox = controller.getRecipient();
          comboBox.getSelectionModel().select(changeAndMessege(j));
        } catch (SQLException e) {
          e.printStackTrace();
        }
      });
    }

    availableDriversTableview.setItems(getPastRides());
}
String changeAndMessege(int p) throws SQLException {
  PastRide[] pastRides = new PastRide[p];
  Connection conn126 = DriverManager.getConnection(
      "jdbc:derby:lib/ZwischenDB");
  Statement stmt126 = conn126.createStatement();


  //String query1 = "SELECT USERNAME FROM LOGIN WHERE UserName='"+ username+"';
  ResultSet resultSet126 = stmt126.executeQuery("SELECT * FROM PAST_RIDE WHERE [DRIVER ='" +
      getCurrentUser().getUsername() + "']OR" + "[RIDER='" + getCurrentUser().getUsername() + "']");
  if (resultSet126.next()) {
    while (resultSet126.next()) {
      int i = 0;
      PastRide pastRide = new PastRide(resultSet126.getString("DRIVER"),
          resultSet126.getString("RIDER"),
          resultSet126.getString("GOINGTO"), resultSet126.getString("COMINGFROM"),
          resultSet126.getDate("DATE"));
      pastRides[i]=pastRide;
    }

  }      conn126.close();
  stmt126.close();
  resultSet126.close();
  if(getCurrentUser().getUsername().equals(pastRides[p].getRider())){
    return  pastRides[p].getDriver();}else{
    return  pastRides[p].getRider();
  }

}

}

