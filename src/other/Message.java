///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        Message.java
// Group:       3
// Date:        November 23, 2018
// Description: A class representing a message from one user to another
///////////////////////////////////////////////////////////////////////////////

package other;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Message implements Serializable {

  private String message;
  private String recipient;
  private String sender;
  private boolean read = false;

  /**
   * Constructor for the class Message.
   *
   * @param message The message to send
   * @param recipient The username of the recipient
   * @param sender The username of the sender
   */
  public Message(String message, String recipient, String sender) {
    this.message = message;
    this.recipient = recipient;
    this.sender = sender;
  }

  public boolean isRead() {
    return read;
  }

  public void setRead(boolean read) {
    this.read = read;
  }

  /**
   * Stores a message object via serialization into a users message folder. Assumes that the
   * recipient already has a folder with a messages folder inside.
   */
  public void sendMessage() {
    try {
      Path path1 = Paths.get("lib/UserData/" + recipient + "/messages");
      File[] file = new File(path1.toString()).listFiles();
      int numberOfMessages = 0;

      if (file != null) {
        numberOfMessages = file.length;
      }

      Path path2 = Paths.get("Message" + (numberOfMessages + 1) + ".message");

      Path newMessagePath = path1.resolve(path2);

      FileOutputStream fileOutputStream = new FileOutputStream(newMessagePath.toString());
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(this);

      fileOutputStream.close();
      objectOutputStream.close();

    } catch (IOException exception) {
      //Todo Send message to user on main screen that message was not sent
      System.out.println("Unable to send Message");
      exception.printStackTrace();
    }
  }
}
