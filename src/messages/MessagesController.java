///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        MessagesController.java
// Group:       3
// Date:        October 24, 2018
// Description: Controller class for message screen
///////////////////////////////////////////////////////////////////////////////

package messages;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import other.Globals;
import other.Message;

public class MessagesController {

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
    //if (recipient.getValue() == null)
    //Todo provide feedback
    //else if (message.getText().isEmpty())
    //Todo provide feedback
    System.out.println(this.message.getText());
    System.out.println(recipient.getValue());
    System.out.println(Globals.currentUser.getUsername());

    Message message = new Message(this.message.getText(), recipient.getValue(),
        Globals.currentUser.getUsername());
    //message.sendMessage();

    Globals.closeScene(root);
  }

  @FXML
  void initialize() {
    //Todo get list of usernames (excluding current user), sort and add to recipients
    recipient
        .setItems(FXCollections.observableArrayList("default", "Sample user 1", "Sample user 2",
        "Sample user 3", "Sample user 4", "Sample user 5", "Sample user 6", "Sample user 7",
        "Sample user 8", "Sample user 9", "Sample user 10"));
  }
}
