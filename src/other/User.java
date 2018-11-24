///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        User.java
// Group:       3
// Date:        November 3, 2018
// Description: Concrete class user represents a driver or rider user
//    of the program.
///////////////////////////////////////////////////////////////////////////////

package other;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
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

  /**
   * A function to deserialize all the message files in a users messages folder and load them into
   * the messages ArrayList.
   */
  public void loadMessages() {
    Path path = Paths.get("lib/UserData/" + username + "/messages");
    File file = new File(path.toString());

    if (file != null) {
      File[] messageFiles = file.listFiles();

      if (messageFiles != null) {
        for (File messageFile : messageFiles) {
          try {
            FileInputStream fileInputStream = new FileInputStream(messageFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Message message = (Message) objectInputStream.readObject();

            messages.add(message);

            fileInputStream.close();
            objectInputStream.close();
          } catch (IOException exception) {
            System.out.println("IOException: unable to read message file " + messageFile);
          } catch (ClassNotFoundException exception) {
            System.out
                .println("ClassNotFoundException: unable to read message file " + messageFile);
          }
        }
      }
    }
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
   * A helper method to reset all userdata to default on user logout.
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
