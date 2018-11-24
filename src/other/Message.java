///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        Message.java
// Group:       3
// Date:        November 23, 2018
// Description: A class representing a message from one user to another
///////////////////////////////////////////////////////////////////////////////

package other;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class Message implements Serializable, Comparable {

  private String message;
  private String recipient;
  private String sender;
  private boolean read = false;
  private Date timeCreated = new Date();
  private String path;

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

  /**
   * Write the message to a file via serialization. The path of the file to write is the path stored
   * in the class attributes. The path must be set before a file can be written
   */
  public void writeFile() {
    if (path == null) {
      return;
    }

    try {
      Path path = Paths.get(this.path);

      FileOutputStream fileOutputStream = new FileOutputStream(path.toString());
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(this);

      fileOutputStream.close();
      objectOutputStream.close();
    } catch (FileNotFoundException exception) {
      System.out.println("File not found " + path);
    } catch (IOException exception) {
      System.out.println("IOException fileOutputStream");
      exception.printStackTrace();
    }
  }

  public boolean isRead() {
    return read;
  }

  public void setRead(boolean read) {
    this.read = read;
  }

  public String getMessage() {
    return message;
  }

  public String getSender() {
    return sender;
  }

  /**
   * Method to get the time created. To avoid Findbugs error, a new date object is created and set
   * to the time of timeCreated.
   *
   * @return a Date object of the time created
   */
  public Date getTimeCreated() {
    Date date = new Date();
    date.setTime(timeCreated.getTime());
    return date;
  }

  /**
   * Stores a message object via serialization into a users message folder. Assumes that the
   * recipient already has a folder with a messages folder inside.
   */
  public void sendMessage() {
    Path path1 = Paths.get("lib/UserData/" + recipient + "/messages");
    File[] file = new File(path1.toString()).listFiles();
    int numberOfMessages = 0;

    if (file != null) {
      numberOfMessages = file.length;
    }

    Path path2 = Paths.get("Message" + (numberOfMessages + 1) + ".message");

    Path newMessagePath = path1.resolve(path2);

    //Save relative path to message as string
    path = newMessagePath.toString();

    //Write the file stream to user messages folder
    writeFile();
  }

  //Added to fix FindBugs error
  @Override
  public boolean equals(Object object) {
    return (this == object);
  }

  @Override
  public int compareTo(Object message) {
    return ((Message) message).getTimeCreated().compareTo(this.timeCreated);
  }
}
