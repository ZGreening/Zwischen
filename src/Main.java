///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        Main.java
// Group:       3
// Date:        September 29, 2018
// Description: Driver Class for the program Zwischen
///////////////////////////////////////////////////////////////////////////////

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import other.Globals;
import other.User;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("loginpage/LoginPage.fxml"));

    Scene mainScene = new Scene(root);

    stage.setTitle("Zwischen");

    stage.setScene(mainScene);

    stage.show();

    User temp = new User();
    User temp2 = new User();
    User temp3 = new User();
    Globals.availableDrivers.add(temp);
    Globals.availableDrivers.add(temp2);
    Globals.availableDrivers.add(temp3);
  }
}
