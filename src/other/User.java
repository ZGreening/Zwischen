///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        User.java
// Group:       3
// Date:        November 3, 2018
// Description: Concrete class user represents a driver or rider user
//    of the program.
///////////////////////////////////////////////////////////////////////////////

package other;

public class User {

  private String username = "sampleUserName";
  private String email;
  private String phoneNum;
  private boolean isSelectedToDrive = false;
  private boolean isAvailableToDrive = true;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNum() {
    return phoneNum;
  }

  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }

  public boolean isSelectedToDrive() {
    return isSelectedToDrive;
  }

  public void setSelectedToDrive(boolean selectedToDrive) {
    isSelectedToDrive = selectedToDrive;
  }

  public boolean isAvailableToDrive() {
    return isAvailableToDrive;
  }

  public void setAvailableToDrive(boolean availableToDrive) {
    isAvailableToDrive = availableToDrive;
  }
}
