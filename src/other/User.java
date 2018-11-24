///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        User.java
// Group:       3
// Date:        November 3, 2018
// Description: Concrete class user represents a driver or rider user
//    of the program.
///////////////////////////////////////////////////////////////////////////////

package other;

import java.util.ArrayList;

public class User {

  private String username = "default";
  private String email = "default@mydomain.org";
  private String phoneNum = "1234567890";
  private ArrayList<Message> messages = new ArrayList<>();
  private boolean isSelectedToDrive = false;

  public ArrayList<Message> getMessages() {
    return messages;
  }

  public void setMessages(ArrayList<Message> messages) {
    this.messages = messages;
  }

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

  /**
   * A helper method to reset all userdata to default on user logout
   */
  public void resetUser() {
    username = "default";
    email = "default@mydomain.org";
    phoneNum = "1234567890";
    messages = new ArrayList<>();
    isSelectedToDrive = false;
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
