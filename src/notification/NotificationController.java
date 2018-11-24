///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        NotificationController.java
// Group:       3
// Date:        October 20, 2018
// Description: Controller Class for the notification window
///////////////////////////////////////////////////////////////////////////////

package notification;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import other.Globals;

public class NotificationController {

  @FXML
  private AnchorPane root;

  @FXML
  private VBox messageOutput;

  @FXML
  void onDeletePressed(ActionEvent event) {

  }

  @FXML
  void onDeselectAllPressed(ActionEvent event) {

  }

  @FXML
  void onReturnPressed(ActionEvent event) {
    Globals.closeScene(root);
  }

  @FXML
  void onSelectAllPressed(ActionEvent event) {

  }

  @FXML
  void initialize() {

  }

}
