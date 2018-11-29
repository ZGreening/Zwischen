///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        Globals.java
// Group:       3
// Date:        November 3, 2018
// Description: A global class of public variables for use throughout the
//    program.
///////////////////////////////////////////////////////////////////////////////

package other;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import messages.MessagesController;

public class Globals {

  private static final ArrayList<User> availableDrivers = new ArrayList<>();
  private static final User currentUser = new User();

  public static User getCurrentUser() {
    return currentUser;
  }

  public static ArrayList<User> getAvailableDrivers() {
    return availableDrivers;
  }

  /**
   * A function that takes a phone number and formats it into a string without parentheses or
   * dashes. Must be US style phone number.
   *
   * @param phoneNum phone number to format
   * @return formatted phone number
   */
  public static String formatPhoneNum(String phoneNum) {
    String formattedNum;

    if (phoneNum.matches("\\([0-9]{3}\\)[0-9]{3}-[0-9]{4}")) {
      formattedNum = phoneNum.substring(1, 4) + phoneNum.substring(5, 8) + phoneNum.substring(9);
    } else if (phoneNum.matches("[0-9]{3}-[0-9]{3}-[0-9]{4}")) {
      formattedNum = phoneNum.substring(0, 3) + phoneNum.substring(4, 7) + phoneNum.substring(8);
    } else if (phoneNum.matches("[0-9]{10}")) {
      formattedNum = phoneNum;
    } else {
      formattedNum = ""; //Return empty string if not a recognized format
    }

    return formattedNum;
  }

  /**
   * An overloaded helper function to close the current window and open a new one.
   *
   * @param newScenePath The relative path to the new scene fxml file
   * @param oldSceneRoot The root of the current scene to close
   */
  public static void changeScene(String newScenePath, Node oldSceneRoot) {
    try {
      //Create new window
      Stage stage = new Stage();

      Parent root = FXMLLoader.load(
          Globals.class.getClassLoader().getResource(newScenePath));

      Scene scene = new Scene(root);

      stage.setTitle("Zwischen");

      stage.setScene(scene);

      stage.show();

      stage.setMinHeight(scene.getHeight());
      stage.setMinWidth(scene.getWidth());

      //If window is opened successfully, close old window
      stage = (Stage) oldSceneRoot.getScene().getWindow();

      stage.close();

    } catch (IOException exception) {
      System.out.println("Failed to open window at path: " + newScenePath);
    }
  }

  /**
   * An overloaded helper function to open a new window and disable the current one while the new
   * one is open.
   *
   * @param newScenePath The relative path to the new scene fxml file
   */
  public static void changeScene(String newScenePath) {
    try {
      Stage stage = new Stage();

      Parent root = FXMLLoader.load(Globals.class.getClassLoader().getResource(newScenePath));

      Scene scene = new Scene(root);

      stage.setTitle("Zwischen");

      stage.initModality(Modality.APPLICATION_MODAL);

      stage.setScene(scene);

      stage.show();

      stage.setMinHeight(scene.getHeight());
      stage.setMinWidth(scene.getWidth());

    } catch (IOException exception) {
      System.out.println("Failed to open window at path: " + newScenePath);
    }
  }

  public static void loadMessagesWithSendTo(String sendTo, Node root) {
    try {
      //Fetch resources
      Stage stage = new Stage();
      FXMLLoader loader = new FXMLLoader(
          Globals.class.getResource("messages/Messages.fxml"));

      //Load scene
      AnchorPane anchorPane = loader.load();
      Scene scene = new Scene(anchorPane);

      //Set up stage
      stage.setTitle("Zwischen");
      stage.setScene(scene);
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.show();

      //Set message recipient
      MessagesController controller = loader.getController();
      ComboBox<String> comboBox = controller.getRecipient();
      comboBox.getSelectionModel().select(sendTo);

      //Close current window
      stage = (Stage) root.getScene().getWindow();
      stage.close();
    } catch (IOException exception) {
      System.out.println("Unable to open message window");
    }
  }

  /**
   * A helper function to close a stage.
   *
   * @param root the root of the scene to close
   */
  public static void closeScene(Node root) {
    Stage stage = (Stage) root.getScene().getWindow();
    stage.close();
  }

  /**
   * Gets all usernames from the database and returns them in an string ArrayList.
   *
   * @return String ArrayList of usernames
   */
  public static ArrayList<String> getAllUsernames() {
    ArrayList<String> usernames = new ArrayList<>();

    try (Connection connection = DriverManager.getConnection("jdbc:derby:lib/ZwischenDB");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT USERNAME FROM LOGIN")) {

      while (resultSet.next()) {
        usernames.add(resultSet.getString("USERNAME"));
      }

    } catch (SQLException exception) {
      System.out.println("Unable to fetch all username");
    }

    Collections.sort(usernames);

    return usernames;
  }
}

