package driverschedule;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import other.DailyRide;

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

  //ArrayList for each row (days of the week)
  ArrayList<String> monday = new ArrayList<String>();
  ArrayList<String> tuesday = new ArrayList<String>();
  ArrayList<String> wednesday = new ArrayList<String>();
  ArrayList<String> thursday = new ArrayList<String>();
  ArrayList<String> friday = new ArrayList<String>();
  ArrayList<String> saturday = new ArrayList<String>();
  ArrayList<String> sunday = new ArrayList<String>();

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

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

  private ArrayList<ComboBox[] availabilityArray = new ComboBox<String>[]{availabilityList0,
      availabilityList1,
      availabilityList2, availabilityList3, availabilityList4, availabilityList5,
      availabilityList6};

  private ComboBox<String>[] timeArray = new ComboBox[]{time0, time1, time2, time3, time4, time5,
      time6};

  private ComboBox<String>[] pickupArray = new ComboBox[]{pickup0, pickup1, pickup2, pickup3,
      pickup4, pickup5, pickup6};

  private ComboBox<String>[] destArray = new ComboBox[]{dest0, dest1, dest2, dest3, dest4, dest5,
      dest6};

  @FXML
  void initialize() {

    //Filling values to combo boxes
    for (int iii = 0; iii < availabilityArray.length; iii++) {
      System.out.println(availabilityArray[0]);
      availabilityArray[iii].setItems(availabilityListBox);
      timeArray[iii].setItems(timeBox);
      pickupArray[iii].setItems(destinationBox);
      destArray[iii].setItems(destinationBox);
    }
    /*
    availabilityList0.setItems(availabilityListBox);
    availabilityList1.setItems(availabilityListBox);
    availabilityList2.setItems(availabilityListBox);
    availabilityList3.setItems(availabilityListBox);
    availabilityList4.setItems(availabilityListBox);
    availabilityList5.setItems(availabilityListBox);
    availabilityList6.setItems(availabilityListBox);


    time0.setItems(timeBox);
    time1.setItems(timeBox);
    time2.setItems(timeBox);
    time3.setItems(timeBox);
    time4.setItems(timeBox);
    time5.setItems(timeBox);
    time6.setItems(timeBox);

    pickup0.setItems(destinationBox);
    pickup1.setItems(destinationBox);
    pickup2.setItems(destinationBox);
    pickup3.setItems(destinationBox);
    pickup4.setItems(destinationBox);
    pickup5.setItems(destinationBox);
    pickup6.setItems(destinationBox);

    dest0.setItems(destinationBox);
    dest1.setItems(destinationBox);
    dest2.setItems(destinationBox);
    dest3.setItems(destinationBox);
    dest4.setItems(destinationBox);
    dest5.setItems(destinationBox);
    dest6.setItems(destinationBox);
    */
  }

  /**
   * A function to store combobox values into separate arraylists.
   */
  @FXML
  public void saveSchedule(ActionEvent event) {
    DailyRide[] dailyRide = new DailyRide[availabilityArray.length];

    for (int iii = 0; iii < availabilityArray.length; iii++) {
      String string = (String) availabilityArray[iii].getValue();
      dailyRide[iii] = new DailyRide(string.equals("Available"),
          timeArray[iii].getValue(), pickupArray[iii].getValue(),
          destArray[iii].getValue());
    }

    /*
    DailyRide dailyRide=new DailyRide();
    String mon1 =  (String) availabilityList0.getValue();
    String mon2 = (String) time0.getValue();
    String mon3 = (String) pickup0.getValue();
    String mon4 = (String) dest0.getValue();


    String tue1 = (String) availabilityList1.getValue();
    String tue2 = (String) time1.getValue();
    String tue3 = (String) pickup1.getValue();
    String tue4 = (String) dest1.getValue();
    tuesday.addAll(Arrays.asList(tue1, tue2, tue3, tue4));

    String wed1 = (String) availabilityList2.getValue();
    String wed2 = (String) time2.getValue();
    String wed3 = (String) pickup2.getValue();
    String wed4 = (String) dest2.getValue();
    wednesday.addAll(Arrays.asList(wed1, wed2, wed3, wed4));

    String thur1 = (String) availabilityList3.getValue();
    String thur2 = (String) time3.getValue();
    String thur3 = (String) pickup3.getValue();
    String thur4 = (String) dest3.getValue();
    thursday.addAll(Arrays.asList(thur1, thur2, thur3, thur4));

    String fri1 = (String) availabilityList4.getValue();
    String fri2 = (String) time4.getValue();
    String fri3 = (String) pickup4.getValue();
    String fri4 = (String) dest4.getValue();
    friday.addAll(Arrays.asList(fri1, fri2, fri3, fri4));

    String sat1 = (String) availabilityList5.getValue();
    String sat2 = (String) time5.getValue();
    String sat3 = (String) pickup5.getValue();
    String sat4 = (String) dest5.getValue();
    saturday.addAll(Arrays.asList(sat1, sat2, sat3, sat4));

    String sun1 = (String) availabilityList6.getValue();
    String sun2 = (String) time6.getValue();
    String sun3 = (String) pickup6.getValue();
    String sun4 = (String) dest6.getValue();
    sunday.addAll(Arrays.asList(sun1, sun2, sun3, sun4));
    */
    System.out.println(dailyRide[0]);
  }

  public ArrayList<String> getMonday() {
    return monday;
  }

  public void setMonday(ArrayList<String> monday) {
    this.monday = monday;
  }

  public ArrayList<String> getTuesday() {
    return tuesday;
  }

  public void setTuesday(ArrayList<String> tuesday) {
    this.tuesday = tuesday;
  }

  public ArrayList<String> getWednesday() {
    return wednesday;
  }

  public void setWednesday(ArrayList<String> wednesday) {
    this.wednesday = wednesday;
  }

  public ArrayList<String> getThursday() {
    return thursday;
  }

  public void setThursday(ArrayList<String> thursday) {
    this.thursday = thursday;
  }

  public ArrayList<String> getFriday() {
    return friday;
  }

  public void setFriday(ArrayList<String> friday) {
    this.friday = friday;
  }

  public ArrayList<String> getSaturday() {
    return saturday;
  }

  public void setSaturday(ArrayList<String> saturday) {
    this.saturday = saturday;
  }

  public ArrayList<String> getSunday() {
    return sunday;
  }

  public void setSunday(ArrayList<String> sunday) {
    this.sunday = sunday;
  }
}

