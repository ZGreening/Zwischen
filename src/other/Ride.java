///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        Ride.java
// Group:       3
// Date:        November 26, 2018
// Description: Concrete class ride represents a ride generated by user input
///////////////////////////////////////////////////////////////////////////////

package other;

import static other.Globals.getCurrentUser;

import com.sun.xml.internal.bind.v2.model.core.ID;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import messages.MessagesController;

public class Ride {

  public String getDriver() {
    return driver;
  }

  public void setDriver(String driver) {
    this.driver = driver;
  }

  public String getDest() {
    return dest;
  }

  public void setDest(String dest) {
    this.dest = dest;
  }

  public String getOccurrance() {
    return occurrance;
  }

  public void setOccurrance(String occurrance) {
    this.occurrance = occurrance;
  }

  public String getStartP() {
    return startP;
  }

  public void setStartP(String startP) {
    this.startP = startP;
  }





  public Integer getSeats() {
    return seats;
  }

  public void setSeats(Integer seats) {
    this.seats = seats;
  }

  private static int nextIDNumber = 0;
  private Button message;

  public CheckBox getCheckBox() {
    return checkBox;
  }

  public void setCheckBox(CheckBox checkBox) {
    this.checkBox = checkBox;
  }

  private String driver;
  private String dest;
  private String occurrance;
  private String startP;
  private Integer seats;
  private CheckBox checkBox;
  private int idnumber;
  public int getIdnumber(){
    return this.idnumber;
  }
  private int idnumber() throws SQLException {

    try(Connection conn140 = DriverManager.getConnection(
        "jdbc:derby:lib/ZwischenDB")){

    try (Statement stmt140 = conn140.createStatement()) {

      //String query1 = "SELECT USERNAME FROM LOGIN WHERE UserName='"+ username+"';
      ResultSet resultSet140 = stmt140
          .executeQuery("SELECT TOP 1 * FROM IDNUMBER ORDER BY ID DESC");
      this.idnumber = resultSet140.getInt("ID") + 1;

    }}
    try(Connection conn141 = DriverManager.getConnection(
        "jdbc:derby:lib/ZwischenDB");){try (Statement stmt141 = conn141.createStatement()) {
          String query2 = String.format("INSERT INTO IDNUMBER VALUES('%d')", this.idnumber);
          //String query1 = "SELECT USERNAME FROM LOGIN WHERE UserName='"+ username+"';
      stmt141.executeUpdate(query2);


    }}

    return this.idnumber;
  }

  /**
   * Constructor fo the class Ride.
   *
   * @param driver the driver
   * @param dest the destination
   * @param startP the starting location
   * @param time the time of the ride
   * @param seats the number of seats in the car
   */
  public Ride(String driver, String dest, String startP, String time, int seats)
      throws SQLException {
    setDriver(driver);
    setDest(dest);
    setOccurrance(time);
    setStartP(startP);
    setSeats(seats);
    idnumber();
    this.message = new Button();

    this.message.setOnAction((ActionEvent event) -> {
      Globals.changeScene("messages/Messages.fxml");
      try {
        FXMLLoader loader = new FXMLLoader(
            getClass().getClassLoader().getResource("messages/Messages.fxml"));
        MessagesController controller = loader.getController();
        ComboBox<String> comboBox = controller.getRecipient();
        comboBox.getSelectionModel().select(changeAndMessage(idnumber()));
      } catch (SQLException e) {
        e.printStackTrace();
      }
    });

    this.checkBox = new CheckBox();
  }

  /**
   * An overloaded constructor for the class Ride.
   *
   * @param driver the driver
   * @param dest the destination
   * @param startP the starting location
   * @param time the time of the ride
   */
  public Ride(String driver, String dest, String startP, String time) {
    this.driver = driver;
    this.dest = dest;
    setOccurrance(time);
    this.startP = startP;
    this.message = new Button();

    this.message.setOnAction((ActionEvent event) -> {
      Globals.changeScene("messages/Messages.fxml");
      try {
        FXMLLoader loader = new FXMLLoader(
            getClass().getClassLoader().getResource("messages/Messages.fxml"));
        MessagesController controller = loader.getController();
        ComboBox<String> comboBox = controller.getRecipient();
        comboBox.getSelectionModel().select(changeAndMessage(idnumber));
      } catch (SQLException e) {
        e.printStackTrace();
      }
    });

    this.checkBox = new CheckBox();

  }

  /*
  This method already exists
  private void setIdnumber() {
    this.idnumber = nextIDNumber++;
  }
  */

  public Button getMessage() {
    return message;
  }

  public void setMessage(Button message) {
    this.message = message;
  }

  String changeAndMessage(int p) throws SQLException {
    PastRide[] pastRides = new PastRide[p];
    Connection conn126 = DriverManager.getConnection(
        "jdbc:derby:lib/ZwischenDB");
    ResultSet resultSet126;
    try (Statement stmt126 = conn126.createStatement()) {

      //String query1 = "SELECT USERNAME FROM LOGIN WHERE UserName='"+ username+"';
      resultSet126 = stmt126
          .executeQuery(String.format("SELECT * FROM PAST_RIDE WHERE DRIVER = '%s' OR RIDER = '%s'",
              getCurrentUser().getUsername(), getCurrentUser().getUsername()));
      if (resultSet126.next()) {
        while (resultSet126.next()) {
          int i = 0;
          PastRide pastRide = new PastRide(resultSet126.getString("DRIVER"),
              resultSet126.getString("RIDER"),
              resultSet126.getString("GOINTTO"), resultSet126.getString("COMINGFROM"),
              resultSet126.getString("OCCURRANCE"));
          pastRides[i] = pastRide;
        }

      }
      conn126.close();

    }
    resultSet126.close();
    if (getCurrentUser().getUsername().equals(pastRides[p].getRider())) {
      return pastRides[p].getDriver();
    } else {
      return pastRides[p].getRider();
    }

  }
}
