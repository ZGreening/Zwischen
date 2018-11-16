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

  /**
   * A helper function to close the current window and open a new one.
   *
   * @param newScene The relative path to the new scene fxml file
   * @param oldSceneRoot The root of the current scene to close
   */
  public static void changeScene(String newScene, Node oldSceneRoot) {
    try {
      //Create new window
      Stage stage = new Stage();

      Parent root = FXMLLoader.load(
          Globals.class.getClassLoader().getResource(newScene));

      Scene scene = new Scene(root);

      stage.setTitle("Zwischen");

      stage.setScene(scene);

      stage.show();

      //If window is opened successfully, close old window
      stage = (Stage) oldSceneRoot.getScene().getWindow();

      stage.close();

    } catch (IOException exception) {
      System.out.println("Failed to open window at path: " + newScene);
    }
  }
}
