///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        Main.java
// By:          Zachary Greening
// Group:       3
// Date:        September 29, 2018
// Description: Driver Class for the program Zwischen
///////////////////////////////////////////////////////////////////////////////

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
  }
}
