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
import other.User;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    Globals.changeScene("login/Login.fxml");

    User temp = new User();
    User temp2 = new User();
    User temp3 = new User();
    Globals.getAvailableDrivers().add(temp);
    Globals.getAvailableDrivers().add(temp2);
    Globals.getAvailableDrivers().add(temp3);
  }
}
