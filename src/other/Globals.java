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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Globals {

  private static final ArrayList<User> availableDrivers = new ArrayList<>();
  public static final User currentUser = new User();
  private static final String dbURL = "jdbc:derby:lib/ZwischenDB";
  public static Statement statement;
  public static ResultSet resultSet;
  private static Connection connection;

  public static Connection getConnection() {
    return connection;
  }

  public static ArrayList<User> getAvailableDrivers() {
    return availableDrivers;
  }

  /**
   * A helper method to initialize a connection to the database.
   */
  public static void initializeDatabase() {
    try {
      connection = DriverManager.getConnection(dbURL);
      statement = connection.createStatement();
    } catch (Exception except) {
      System.out.println("db failed to connect");
    }
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
    } catch (IOException exception) {
      System.out.println("Failed to open window at path: " + newScenePath);
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
   * A helper method to shutdown connection to the database.
   */
  public static void shutdownDatabase() {
    try {
      if (statement != null) {
        statement.close();
      }
      if (resultSet != null) {
        resultSet.close();
      }
      if (connection != null) {
        DriverManager.getConnection(dbURL + ";shutdown=true");
        connection.close();
      }

    } catch (SQLException exception) {
      //A catch of the exception actually mean successful
      //shutdown according to the derby documentation
    }
  }
}

