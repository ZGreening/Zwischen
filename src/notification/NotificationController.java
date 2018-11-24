///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        NotificationController.java
// Group:       3
// Date:        October 20, 2018
// Description: Controller Class for the notification window
///////////////////////////////////////////////////////////////////////////////

package notification;

import java.nio.file.Paths;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import other.Globals;
import other.Message;

public class NotificationController {

  private ArrayList<GridPane> messageDisplays = new ArrayList<>();

  @FXML
  private AnchorPane root;

  @FXML
  private VBox messageOutput;

  @FXML
  void onDeletePressed(ActionEvent event) {

  }

  @FXML
  void onMarkAsReadPressed(ActionEvent event) {

  }

  @FXML
  void onDeselectAllPressed(ActionEvent event) {
    for (GridPane gridPane : messageDisplays) {
      CheckBox checkBox = (CheckBox) gridPane.getChildren().get(4);
      checkBox.setSelected(false);
    }
  }

  @FXML
  void onReturnPressed(ActionEvent event) {
    Globals.closeScene(root);
  }

  @FXML
  void onSelectAllPressed(ActionEvent event) {
    for (GridPane gridPane : messageDisplays) {
      CheckBox checkBox = (CheckBox) gridPane.getChildren().get(4);
      checkBox.setSelected(true);
    }
  }

  @FXML
  void initialize() {
    for (Message message : Globals.currentUser.getMessages()) {
      GridPane gridPane = new GridPane();

      //Set column spacing
      ColumnConstraints column = new ColumnConstraints();
      column.setPercentWidth(5);
      gridPane.getColumnConstraints().add(column);

      column = new ColumnConstraints();
      column.setPercentWidth(15);
      gridPane.getColumnConstraints().add(column);
      gridPane.getColumnConstraints().add(column);

      column = new ColumnConstraints();
      column.setPercentWidth(45);
      gridPane.getColumnConstraints().add(column);

      column = new ColumnConstraints();
      column.setPercentWidth(20);
      gridPane.getColumnConstraints().add(column);

      //Create visible marker for read and unread files
      Circle circle = new Circle();
      circle.setRadius(5);
      circle.setStyle("-fx-fill: royalblue");
      if (message.isRead()) {
        circle.setVisible(false);
      }

      //Get sender
      Label sender = new Label();
      sender.setText("Sender:\n" + message.getSender());
      sender.setFont(new Font(14));

      //Get sender's avatar
      ImageView imageView = new ImageView();
      imageView.setImage(new Image(
          Paths.get("lib/UserData/" + message.getSender() + "/avatar.png").toUri().toString()));
      imageView.setFitHeight(75);
      imageView.setFitWidth(75);

      //Get message
      Label messageLabel = new Label();
      messageLabel.setText("Message:\n" + message.getMessage());
      sender.setFont(new Font(14));
      messageLabel.setWrapText(true);

      //Add checkbox
      CheckBox checkBox = new CheckBox();
      checkBox.setText("Select");
      checkBox.setFont(new Font(14));
      checkBox.setFocusTraversable(false);

      //Add message
      gridPane.add(circle, 0, 0);
      gridPane.add(imageView, 1, 0);
      gridPane.add(sender, 2, 0);
      gridPane.add(messageLabel, 3, 0);
      gridPane.add(checkBox, 4, 0);
      gridPane.setStyle("-fx-background-color: lightgrey");

      messageOutput.getChildren().add(gridPane);
      messageDisplays.add(gridPane);
    }
  }
}
