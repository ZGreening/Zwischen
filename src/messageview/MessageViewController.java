///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        Message.java
// Group:       3
// Date:        November 24, 2018
// Description: The controller class for MessageView. Displays one message,
//    and gives the options to mark as read/unread, delete, or reply.
///////////////////////////////////////////////////////////////////////////////

package messageview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MessageViewController {

  @FXML
  private AnchorPane root;

  @FXML
  private ImageView senderAvatar;

  @FXML
  private Label sender;

  @FXML
  private Label messageText;

  @FXML
  void onDeletePressed(ActionEvent event) {

  }

  @FXML
  void onMarkAsUnreadPressed(ActionEvent event) {

  }

  @FXML
  void onReplyPressed(ActionEvent event) {

  }

  @FXML
  void onReturnPressed(ActionEvent event) {

  }

  @FXML
  void initialize() {

  }
}

