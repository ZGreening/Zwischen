///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        AvailableDriversController.java
// Group:       3
// Date:        October 24, 2018
// Description: Controller class for available drivers screen
///////////////////////////////////////////////////////////////////////////////

package availabledrivers;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import other.Globals;
import other.User;

public class AvailableDriversController {


  private ArrayList<GridPane> driverPane = new ArrayList<>();

  @FXML
  private AnchorPane availableDriversWindow;

  @FXML
  private Label feedbackLabel;

  @FXML
  private VBox scrollpaneVBox;

  @FXML
  void onCancelPressed(ActionEvent event) throws Exception {
    Stage stage = (Stage) availableDriversWindow.getScene().getWindow();

    stage.close();

    Parent root = FXMLLoader.load(
        getClass().getClassLoader().getResource("mainscreen/MainScreen.fxml"));

    Scene scene = new Scene(root);

    stage = new Stage();

    stage.setTitle("Zwischen");

    stage.setScene(scene);

    stage.show();
  }

  @FXML
  void onDeselectAllPressed(ActionEvent event) {
    if (driverPane.isEmpty()) {
      feedbackLabel.setText("No Available Drivers To Select");
    }
    for (GridPane gridPane : driverPane) {
      CheckBox checkBox = (CheckBox) gridPane.getChildren().get(1);
      checkBox.setSelected(false);
    }
  }

  @FXML
  void onSelectAllPressed(ActionEvent event) {
    if (driverPane.isEmpty()) {
      feedbackLabel.setText("No Available Drivers To Select");
    }
    for (GridPane gridPane : driverPane) {
      CheckBox checkBox = (CheckBox) gridPane.getChildren().get(1);
      checkBox.setSelected(true);
    }
  }

  @FXML
  void onSubmitPressed(ActionEvent event) throws Exception {
    boolean atLeastOneDriver = false;

    for (GridPane gridPane : driverPane) {
      CheckBox checkBox = (CheckBox) gridPane.getChildren().get(1);
      if (checkBox.isSelected()) {
        atLeastOneDriver = true;
        break;
      }
    }

    if (atLeastOneDriver) {
      Stage stage = (Stage) availableDriversWindow.getScene().getWindow();

      stage.close();

      Parent root = FXMLLoader.load(
          getClass().getClassLoader().getResource("mainscreen/MainScreen.fxml"));

      Scene scene = new Scene(root);

      stage = new Stage();

      stage.setTitle("Zwischen");

      stage.setScene(scene);

      stage.show();
    } else {
      feedbackLabel.setText("No Drivers Selected");
    }

  }

  @FXML
  void initialize() {
    for (User user : Globals.availableDrivers) {
      if (!user.isAvailableToDrive()) {
        continue;
      }

      GridPane gridPane = new GridPane();

      //Set width of grid pane so it takes up full space in parent
      ColumnConstraints column = new ColumnConstraints();
      column.setPercentWidth(50);
      gridPane.getColumnConstraints().add(column);
      gridPane.getColumnConstraints().add(column);

      //Create label for driver using username
      Label label = new Label();
      label.setText(user.getUsername());
      label.setFont(new Font(20));

      //Create checkbox to select driver
      CheckBox checkBox = new CheckBox();
      checkBox.setText("Select");
      checkBox.setFont(new Font(20));
      checkBox.setSelected(user.isSelectedToDrive());
      GridPane.setColumnIndex(checkBox, 1);

      //Add to GUI and store in driverPane ArrayList
      gridPane.getChildren().add(label);
      gridPane.getChildren().add(checkBox);
      driverPane.add(gridPane);
      scrollpaneVBox.getChildren().add(gridPane);
    }

  }

}

