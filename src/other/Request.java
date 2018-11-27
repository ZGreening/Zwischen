package other;

import java.time.LocalDate;
import java.util.Date;

public class Request {
  public String rider;
  public String dest;
  public Date date;
  public String startP;

  public Request(String rider, String dest, Date date, String startP) {
    this.rider = rider;
    this.dest = dest;
    this.date = date;
    this.startP = startP;
  }
}