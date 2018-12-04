package other;

import java.sql.SQLException;

public class PastRide extends Ride {

  private String rider;

  /**
   * An overloaded constructor for the class PastRide.
   *
   * @param rider The passenger who requested the ride
   * @param driver The driver
   * @param dest The destination
   * @param startP The starting location
   * @param time The date of the ride
   */
  public PastRide(String rider, String driver, String dest, String startP, String time)
      throws SQLException {
    super(driver, dest, startP, time);
    this.rider = rider;
  }

  /**
   * An overloaded constructor for the class PastRide.
   *
   * @param rider The passenger who requested the ride
   * @param driver The driver
   * @param dest The destination
   * @param startP The starting location
   * @param time The time of the ride
   * @param idNum The id of the ride used for simplification
   */
  public PastRide(String rider, String driver, String dest, String startP, String time, int idNum)
      throws SQLException {
    super(driver, dest, startP, time);
    this.rider = rider;

  }

  public String getRider() {
    return rider;
  }

  public void setRider(String rider) {
    this.rider = rider;
  }
}
