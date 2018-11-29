///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        DailyRide.java
// Group:       3
// Date:        November 28, 2018
// Description: Ride class to store information of a ride.
///////////////////////////////////////////////////////////////////////////////

package other;

public class DailyRide {

  boolean isAvailable;
  String time;
  String pickupLocation;
  String destination;

  public DailyRide(boolean isAvailable, String time, String pickupLocation,
      String destination) {
    this.isAvailable = isAvailable;
    this.time = time;
    this.pickupLocation = pickupLocation;
    this.destination = destination;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public void setAvailable(boolean available) {
    isAvailable = available;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getPickupLocation() {
    return pickupLocation;
  }

  public void setPickupLocation(String pickupLocation) {
    this.pickupLocation = pickupLocation;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }
}
