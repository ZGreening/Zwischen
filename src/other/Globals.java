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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Globals {

  //FindBugs flags this, however the purpose is to leave it
  //mutable from anywhere in code.
  public static final ArrayList<User> availableDrivers = new ArrayList<>();

  //Global constant for the current user
  public static final User currentUser = new User();
  private static String dbURL = "jdbc:derby:lib/ZwischenDB4";
  // jdbc Connection
  public static Connection conn;
  public static Statement stmt, stmt1;
  //public static ResultSet resultSet;

  public static void createConnection() {
    try {
      conn = DriverManager.getConnection(
              dbURL, "tyler", "zwischen");
      stmt = conn.createStatement();
      System.out.println("db connected");
    } catch (Exception except) {
      except.printStackTrace();
      System.out.println("db failed to connect");
    }
  }
  /**
   * A helper function to close the current window and open a new one.
   *
   * @param newScene The relative path to the new scene fxml file
   * @param oldSceneRoot The root of the current scene to close
   */
  public static void changeScene(String newScene, Node oldSceneRoot) {
    try {
      Stage stage = (Stage) oldSceneRoot.getScene().getWindow();

      stage.close();

      Parent root = FXMLLoader.load(
          Globals.class.getClassLoader().getResource(newScene));

      Scene scene = new Scene(root);

      stage = new Stage();

      stage.setTitle("Zwischen");

      stage.setScene(scene);

      stage.show();

    } catch (IOException exception) {
      System.out.println("Could not open window at path: " + newScene);
    }
  }

  private static void shutdown() {
    try {
      if (stmt != null) {
        stmt.close();
      }
      if (conn != null) {
        DriverManager.getConnection(dbURL + ";shutdown=true");
        conn.close();
      }
    } catch (SQLException sqlExcept) {

    }

  }
}

