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

  public PastRide( String rider,String driver, String dest, String startP, Date date) {
    super(driver, dest, startP, date);
    this.rider = rider;
  }
  public PastRide( String rider,String driver, String dest, String startP, Date date, int idNum) {
    super(driver, dest, startP, date);
    this.rider = rider;
    this.setIdnumber(idNum);
  }
}
