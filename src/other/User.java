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

  private String username = "Default";
  private String email;
  private String phoneNum;
  private boolean isSelectedToDrive = false;
  private String imageUrl = "file:lib/UserData/default.png";

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

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  @Override
  public String toString() {
    return "User{"
        + "username='" + username + '\''
        + ", email='" + email + '\''
        + ", phoneNum='" + phoneNum + '\''
        + ", isSelectedToDrive=" + isSelectedToDrive
        + '}';
  }
}
