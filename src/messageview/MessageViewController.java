///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        Message.java
// Group:       3
// Date:        November 24, 2018
// Description: The controller class for MessageView. Displays one message,
//    and gives the options to mark as read/unread, delete, or reply.
///////////////////////////////////////////////////////////////////////////////

package messageview;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import messages.MessagesController;
import other.Globals;
import other.Message;

public class MessageViewController {

  private Message message;

  @FXML
  private AnchorPane root;

  @FXML
  private ImageView senderAvatar;

  @FXML
  private Label sender;

  @FXML
  private Label messageText;

  @FXML
  private Button markUnreadButton;

  @FXML
  private ScrollPane scrollPane;

  @FXML
  private Circle unreadIndicator;

  public MessageViewController(Message message) {
    this.message = message;
  }

  @FXML
  void onDeletePressed(ActionEvent event) {
    Alert alert = new Alert(AlertType.CONFIRMATION,
        "Do you really want to delete?\nThis cannot be undone.", ButtonType.OK, ButtonType.CANCEL);
    alert.showAndWait();

    if (alert.getResult() == ButtonType.OK) {
      ArrayList<Message> messages = Globals.getCurrentUser().getMessages();
      messages.remove(message);
      message.deleteFile();
      Globals.changeScene("notifications/Notifications.fxml", root);
    }
  }

  @FXML
  void onMarkAsUnreadPressed(ActionEvent event) {
    unreadIndicator.setVisible(!unreadIndicator.isVisible());
    message.setRead(!message.isRead());
    message.writeFile(); //Update message file
    if (markUnreadButton.getText().equals("Mark as Unread")) {
      markUnreadButton.setText("Mark as Read");
    } else {
      markUnreadButton.setText("Mark as Unread");
    }
  }

  @FXML
  void onReplyPressed(ActionEvent event) {
    try {
      //Fetch resources
      Stage stage = new Stage();
      FXMLLoader loader = new FXMLLoader(
          getClass().getClassLoader().getResource("messages/Messages.fxml"));

      //Load scene
      AnchorPane anchorPane = loader.load();
      Scene scene = new Scene(anchorPane);

      //Set up stage
      stage.setTitle("Zwischen");
      stage.setScene(scene);
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.show();

      //Set message recipient
      MessagesController controller = loader.getController();
      ComboBox<String> comboBox = controller.getRecipient();
      comboBox.getSelectionModel().select(message.getSender());

      //Close current window
      stage = (Stage) root.getScene().getWindow();
      stage.close();
    } catch (IOException exception) {
      System.out.println("Unable to open message window");
    }
  }

  @FXML
  void onReturnPressed(ActionEvent event) {
    Globals.changeScene("notifications/Notifications.fxml", root);
  }

  @FXML
  void initialize() {
    messageText.setText("Message: " + message.getMessage());
    sender.setText("Sender:\n" + message.getSender());
    senderAvatar.setImage(new Image(
        Paths.get("lib/UserData/" + message.getSender() + "/avatar.png").toUri().toString()));

    scrollPane.setFitToWidth(true);

    //If the message was unread, mark it as read
    if (!message.isRead()) {
      message.setRead(true);
      message.writeFile();
    }
  }
}

