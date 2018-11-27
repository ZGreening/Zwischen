package other;

import java.util.Date;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class Ride {

  public String driver;
  public String dest;
  public Date date;
  public String startP;
  public Integer seats;
  public Button messege;
  public CheckBox checkBox;

  public Ride(String driver, String dest, String startP, Date date, int seats) {
    this.driver = driver;
    this.dest = dest;
    this.date = date;
    this.startP = startP;
    this.seats = seats;
    this.messege = new Button();
    this.checkBox = new CheckBox();
  }
}