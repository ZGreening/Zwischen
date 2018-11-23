///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        RideRequestController.java
// Group:       3
// Date:        October 24, 2018
// Description: Controller class for available drivers screen
///////////////////////////////////////////////////////////////////////////////

package riderequest;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import other.Globals;
import other.User;

public class RideRequestController {


  private ArrayList<GridPane> driverPane = new ArrayList<>();

  @FXML
  private AnchorPane root;

  @FXML
  private Label feedbackLabel;

  @FXML
  private VBox scrollpaneVBox;

  @FXML
  void onCancelPressed(ActionEvent event) {
    Globals.closeScene(root);
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
  void onSubmitPressed(ActionEvent event) {
    boolean atLeastOneDriver = false;
    int count = 0;

    for (GridPane gridPane : driverPane) {
      CheckBox checkBox = (CheckBox) gridPane.getChildren().get(1);
      if (checkBox.isSelected()) {
        atLeastOneDriver = true;
        Globals.availableDrivers.get(count).setSelectedToDrive(true);
      } else {
        Globals.availableDrivers.get(count).setSelectedToDrive(false);
      }
      count++;
    }

    if (atLeastOneDriver) {
      Globals.changeScene("mainscreen/MainScreen.fxml", root);
    } else {
      feedbackLabel.setText("No Drivers Selected");
    }

  }

  @FXML
  void initialize() {
    for (User user : Globals.availableDrivers) {
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
      checkBox.setFocusTraversable(false);
      GridPane.setColumnIndex(checkBox, 1);

      //Add to GUI and store in driverPane ArrayList
      gridPane.getChildren().add(label);
      gridPane.getChildren().add(checkBox);
      driverPane.add(gridPane);
      scrollpaneVBox.getChildren().add(gridPane);
    }

  }

}

