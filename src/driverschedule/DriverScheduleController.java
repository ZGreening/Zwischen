///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        DriverScheduleController.java
// Group:       3
// Date:        October 22, 2018
// Description: Controller Class for the ride schedule window
///////////////////////////////////////////////////////////////////////////////

package driverschedule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import other.Globals;

public class DriverScheduleController {

  @FXML
  private VBox monday;

  @FXML
  private VBox tuesday;

  @FXML
  private VBox wednesday;

  @FXML
  private VBox thursday;

  @FXML
  private VBox friday;

  @FXML
  private VBox saturday;

  @FXML
  private VBox sunday;

  private ArrayList<Ride> dailyRides = new ArrayList<>();

  private void generateNewRideRow(VBox day) {
    GridPane gridPane = new GridPane();
    String dayString;

    if (day == monday) {
      dayString = "Monday";
    } else if (day == tuesday) {
      dayString = "Tuesday";
    } else if (day == wednesday) {
      dayString = "Wednesday";
    } else if (day == thursday) {
      dayString = "Thursday";
    } else if (day == friday) {
      dayString = "Friday";
    } else if (day == saturday) {
      dayString = "Saturday";
    } else if (day == sunday) {
      dayString = "Sunday";
    } else {
      dayString = "Not a day";
    }

    ComboBox<String> originBox = new ComboBox<>(Globals.getLocationList());
    ComboBox<String> destinationBox = new ComboBox<>(Globals.getLocationList());
    ComboBox<String> timeBox = new ComboBox<>(Globals.getTimeList());

    Button deleteButton = new Button();
    deleteButton.setText("Delete");
    deleteButton.setFont(new Font(12));

    Ride ride = new Ride(originBox, destinationBox, timeBox, dayString);

    originBox.setEditable(true);
    destinationBox.setEditable(true);

    originBox.setFocusTraversable(false);
    destinationBox.setFocusTraversable(false);
    timeBox.setFocusTraversable(false);
    deleteButton.setFocusTraversable(false);

    //Give actions to all objects
    originBox.setOnAction(event -> {
      if (ride.rowIsfilled()) {
        generateNewRideRow(day);
      }
    });

    destinationBox.setOnAction(event -> {
      if (ride.rowIsfilled()) {
        generateNewRideRow(day);
      }
    });

    timeBox.setOnAction(event -> {
      if (ride.rowIsfilled()) {
        generateNewRideRow(day);
      }
    });

    deleteButton.setOnAction(event -> {
      if (ride.rowIsfilled()) {
        day.getChildren().remove(gridPane);
        dailyRides.remove(ride);
      }
    });

    gridPane.add(originBox, 0, 0);
    gridPane.add(destinationBox, 1, 0);
    gridPane.add(timeBox, 2, 0);
    gridPane.add(deleteButton, 3, 0);

    dailyRides.add(ride);

    day.getChildren().add(gridPane);
  }

  @FXML
  void onSavePressed(ActionEvent event) {
    for (Ride ride : dailyRides) {
      System.out.println(ride);
      System.out.println();
    }

    try (Connection connection = DriverManager.getConnection("jdbc:derby:lib/ZwischenDB");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String
            .format("select tablename from SYS.SYSTABLES where tablename ='%s'",
                Globals.getCurrentUser().getUserFolder().toUpperCase()))) {

      if (resultSet.next()) {
        //resultSet=statement.executeQuery(String.format("select * from %s",Globals.getCurrentUser().getUserFolder());
      } else {
        statement.executeUpdate(String.format(
            "create table %s(DAY VARCHAR(10),ORIGIN VARCHAR(255),DESTINATION VARCHAR(255),TIME VARCHAR(10))",
            Globals.getCurrentUser().getUserFolder()));
        for (Ride ride : dailyRides) {
          if (ride.rowIsfilled()) {
            statement.executeUpdate(String.format("INSERT INTO %s VALUES('%s','%s','%s','%s')",
                Globals.getCurrentUser().getUserFolder(), ride.day, ride.origin.getValue(),
                ride.destination.getValue(),
                ride.time.getValue()));
          }
        }
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }

  @FXML
  void initialize() {
    generateNewRideRow(monday);
    generateNewRideRow(tuesday);
    generateNewRideRow(wednesday);
    generateNewRideRow(thursday);
    generateNewRideRow(friday);
    generateNewRideRow(saturday);
    generateNewRideRow(sunday);
  }

  private class Ride {

    private ComboBox<String> origin;
    private ComboBox<String> destination;
    private ComboBox<String> time;
    private String day;

    private Ride(ComboBox<String> origin, ComboBox<String> destination, ComboBox<String> time,
        String day) {
      this.origin = origin;
      this.destination = destination;
      this.time = time;
      this.day = day;
    }

    private boolean rowIsfilled() {
      return (origin.getValue() != null && destination.getValue() != null
          && time.getValue() != null);
    }

    public String toString() {
      return "Origin=" + origin.getValue() + "\nDestination=" + destination.getValue() + "\nTime="
          + time.getValue();
    }
  }
}
