package other;

import java.util.Date;

public class PastRide extends Ride {

  public String getRider() {
    return rider;
  }

  public void setRider(String rider) {
    this.rider = rider;
  }

  private String rider;

  /**
   * An overloaded constructor for the class PastRide.
   *
   * @param rider The passenger who requested the ride
   * @param driver The driver
   * @param dest The destination
   * @param startP The starting location
   * @param date The date of the ride
   */
  public PastRide(String rider, String driver, String dest, String startP, String time) {
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
   * @param idNum The id of the ride //Todo What is the IDNumber???
   */
  public PastRide(String rider, String driver, String dest, String startP, String time, int idNum) {
    super(driver, dest, startP, time);
    this.rider = rider;
    this.setIdnumber(idNum);
  }
}
