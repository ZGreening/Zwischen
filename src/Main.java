///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        Main.java
// Group:       3
// Date:        September 29, 2018
// Description: Driver Class for the program Zwischen
///////////////////////////////////////////////////////////////////////////////

import javafx.application.Application;
import javafx.stage.Stage;
import other.Globals;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    Globals.changeScene("login/Login.fxml");
    //Todo Known issues:
    //  Method for storing default image looks like an account, default image should not be in a
    //    folder
    //  Ride request needs functionality
    //  driver schedule needs fixing, switch to database for schedule
    //  Friends list needs integrated
    //  issue with user image loading up after user logout
  }
}
