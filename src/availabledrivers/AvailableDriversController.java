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
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import other.Users;

public class AvailableDriversController {

  private ArrayList<Users> availableDrivers = new ArrayList<Users>();

  @FXML
  private Label feedbackLabel;

  @FXML
  private VBox scrollpaneVBox;

  void addAvailableDrivers() {

  }

  @FXML
  void onCancelPressed(ActionEvent event) {

  }

  @FXML
  void onDeselectAllPressed(ActionEvent event) {

  }

  @FXML
  void onSelectAllPressed(ActionEvent event) {

  }

  @FXML
  void onSubmitPressed(ActionEvent event) {

  }

  @FXML
  void initialize() {

  }

}

