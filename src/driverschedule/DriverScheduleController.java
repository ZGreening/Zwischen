///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        DriverScheduleController.java
// Group:       3
// Date:        October 22, 2018
// Description: Controller Class for the ride schedule window
///////////////////////////////////////////////////////////////////////////////

package driverschedule;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import other.DailyRide;
import other.Globals;
import other.Ride;

public class DriverScheduleController {

  //values for combo boxes
  ObservableList<String> availabilityListBox = FXCollections
      .observableArrayList("Available", "Not Available");

  ObservableList<String> timeBox = FXCollections
      .observableArrayList("12:00 AM", "12:30 AM", "1:00 AM",
          "1:30 AM", "2:00 AM", "2:30 AM", "3:00 AM", "3:30 AM", "4:00 AM", "4:30 AM", "5:00 AM",
          "5:30 AM", "6:00 AM", "6:30 AM", "7:00 AM", "7:30 AM", "8:00 AM", "8:30 AM", "9:00 AM",
          "9:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM",
          "1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM", "3:00 PM", "3:30 PM", "4:00 PM", "4:30 PM",
          "5:00 PM", "5:30 PM", "6:00 PM", "6:30 PM", "7:00 PM", "7:30 PM", "8:00 PM",
          "8:30 PM", "9:00 PM", "9:30 PM", "10:00 PM", "10:30 PM", "11:00 PM", "11:30 PM");

  ObservableList<String> destinationBox = FXCollections
      .observableArrayList("Coastal Village Apartments", "Coconut Point Mall",
          "Florida Gulf Coast University", "Florida SouthWestern State College",
          "Gulf Coast Town Center", "Miromar Outlets", "The Reef Apartments",
          "Walmart Supercenter (Estero)");

  /*//ArrayList for each row (days of the week)
  ArrayList<String> monday = new ArrayList<String>();
  ArrayList<String> tuesday = new ArrayList<String>();
  ArrayList<String> wednesday = new ArrayList<String>();
  ArrayList<String> thursday = new ArrayList<String>();
  ArrayList<String> friday = new ArrayList<String>();
  ArrayList<String> saturday = new ArrayList<String>();
  ArrayList<String> sunday = new ArrayList<String>();*/

  @FXML
  private ComboBox availabilityList0;

  @FXML
  private ComboBox availabilityList1;

  @FXML
  private ComboBox availabilityList2;

  @FXML
  private ComboBox availabilityList3;

  @FXML
  private ComboBox availabilityList4;

  @FXML
  private ComboBox availabilityList5;

  @FXML
  private ComboBox availabilityList6;

  @FXML
  private ComboBox time0;

  @FXML
  private ComboBox time1;

  @FXML
  private ComboBox time2;

  @FXML
  private ComboBox time3;

  @FXML
  private ComboBox time4;

  @FXML
  private ComboBox time5;

  @FXML
  private ComboBox time6;

  @FXML
  private ComboBox pickup0;

  @FXML
  private ComboBox pickup1;

  @FXML
  private ComboBox pickup2;

  @FXML
  private ComboBox pickup3;

  @FXML
  private ComboBox pickup4;

  @FXML
  private ComboBox pickup5;

  @FXML
  private ComboBox pickup6;

  @FXML
  private ComboBox dest0;

  @FXML
  private ComboBox dest1;

  @FXML
  private ComboBox dest2;

  @FXML
  private ComboBox dest3;

  @FXML
  private ComboBox dest4;

  @FXML
  private ComboBox dest5;

  @FXML
  private ComboBox dest6;

  private Control[] availabilityArray;

  private Control[] timeArray;

  private Control[] pickupArray;

  private Control[] destArray;

  @FXML
  void initialize() {
    //To store a combobox array a temporary array must be made and then assign it to class variables
    //Otherwise values will be null
    final Control[] temp = {availabilityList0,
        availabilityList1, availabilityList2, availabilityList3, availabilityList4,
        availabilityList5, availabilityList6};

    final Control[] temp2 = {time0, time1, time2, time3, time4, time5, time6};

    final Control[] temp3 = {pickup0, pickup1, pickup2, pickup3, pickup4, pickup5, pickup6};

    final Control[] temp4 = {dest0, dest1, dest2, dest3, dest4, dest5, dest6};

    availabilityArray = temp;
    timeArray = temp2;
    pickupArray = temp3;
    destArray = temp4;

    //Filling values to combo boxes
    for (int iii = 0; iii < availabilityArray.length; iii++) {
      ((ComboBox<String>) availabilityArray[iii]).setItems(availabilityListBox);
      ((ComboBox<String>) timeArray[iii]).setItems(destinationBox);
      ((ComboBox<String>) pickupArray[iii]).setItems(destinationBox);
      ((ComboBox<String>) destArray[iii]).setItems(timeBox);
    }

    ArrayList<DailyRide> dailyRides = Globals.getCurrentUser().getDailyRides();
    //Todo getting out of array and displaying on the GUI
    for (int iii = 0; iii < dailyRides.size(); iii++) {
      if (dailyRides.get(iii).isAvailable()) {
        ((ComboBox<String>) availabilityArray[iii]).setValue("Available");
      } else {
        ((ComboBox<String>) availabilityArray[iii]).setValue("Not Available");
      }

      ((ComboBox<String>) timeArray[iii]).setValue(dailyRides.get(iii).getTime());
      ((ComboBox<String>) pickupArray[iii]).setValue(dailyRides.get(iii).getTime());
      ((ComboBox<String>) destArray[iii]).setValue(dailyRides.get(iii).getTime());
    }
  }

  /**
   * A function to store combobox values into separate arraylists.
   */
  @FXML
  public void saveSchedule(ActionEvent event) throws SQLException {
    Globals.getCurrentUser().getDailyRides().clear();
    try {
      Connection conn150 = DriverManager.getConnection("jdbc:derby:lib/ZwischenDB");
      Statement stmt150 = conn150.createStatement();
      stmt150.executeUpdate(String.format("DELETE * FROM RIDE WHERE DRIVER = %s",
          Globals.getCurrentUser().getUsername()));
    } catch (SQLException e) {
    }
    for (int iii = 0; iii < availabilityArray.length; iii++) {
      String string = ((ComboBox<String>) availabilityArray[iii]).getValue();

      if (string != null && !((ComboBox<String>) timeArray[iii]).getValue().isEmpty()
          && !((ComboBox<String>) pickupArray[iii]).getValue().isEmpty()
          && !((ComboBox<String>) destArray[iii]).getValue().isEmpty()) {
        Globals.getCurrentUser().getDailyRides().add(new DailyRide(string.equals("Available"),
            ((ComboBox<String>) timeArray[iii]).getValue(),
            ((ComboBox<String>) pickupArray[iii]).getValue(),
            ((ComboBox<String>) destArray[iii]).getValue()));
        Ride ride = new Ride(Globals.getCurrentUser().getUsername(),
            ((ComboBox<String>) timeArray[iii]).getValue(),
            ((ComboBox<String>) pickupArray[iii]).getValue(),
            ((ComboBox<String>) destArray[iii]).getValue(), 3);
        try {
          Connection conn151 = DriverManager.getConnection("jdbc:derby:lib/ZwischenDB");
          Statement stmt151 = conn151.createStatement();
          String query3 = String.format("INSERT INTO RIDE VALUES('%s','%s','%s','%d','%s')",
              Globals.getCurrentUser().getUsername(), ride.getDest(), ride.getStartP(),
              ride.getSeats(), ride.getOccurrance());
          stmt151.executeUpdate(query3);
          stmt151.close();
        } catch (SQLException e) {

        }
      }

    }
  }
}

