///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        MessageScreenController.java
// Group:       3
// Date:        October 24, 2018
// Description: Controller class for message screen
///////////////////////////////////////////////////////////////////////////////

package messagescreen;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import other.Globals;

public class MessageScreenController {

  @FXML
  private TextArea message;

  @FXML
  private ComboBox<String> recipient;

  @FXML
  private AnchorPane root;

  @FXML
  void onCancelPressed(ActionEvent event) {
    Globals.closeScene(root);
  }

  @FXML
  void onSendPressed(ActionEvent event) {
    //Todo create message
    Globals.closeScene(root);
  }

  @FXML
  void initialize() {
    //Todo get list of usernames, sort and add to recipients
    recipient.setItems(FXCollections.observableArrayList("Sample user 1", "Sample user 2",
        "Sample user 3", "Sample user 4", "Sample user 5", "Sample user 6", "Sample user 7",
        "Sample user 8", "Sample user 9", "Sample user 10"));
  }
}
