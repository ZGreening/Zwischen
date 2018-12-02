///////////////////////////////////////////////////////////////////////////////
// Project:     Zwischen
// File:        RideRequest.java
// Group:       3
// Date:        October 24, 2018
// Description: Controller class for available drivers screen
///////////////////////////////////////////////////////////////////////////////

package riderequest;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import other.Globals;

public class RideRequest implements Initializable {

  //private Message message;

  @FXML
  private AnchorPane root;

  @FXML
  private VBox scrollpaneVBox;

  @FXML
  private ComboBox<String> pickupComboBox;

  @FXML
  private ComboBox<String> destinationComboBox;

  @FXML
  private ComboBox<String> timeComboBox;

  private ObservableList<String> time = FXCollections
      .observableArrayList("12:00 AM", "12:30 AM", "1:00 AM", "1:30 AM", "2:00 AM", "2:30 AM",
          "3:00 AM", "3:30 AM", "4:00 AM", "4:30 AM", "5:00 AM", "5:30 AM", "6:00 AM", "6:30 AM",
          "7:00 AM", "7:30 AM", "8:00 AM", "8:30 AM", "9:00 AM", "9:30 AM", "10:00 AM", "10:30 AM",
          "11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM", "1:00 PM", "1:30 PM", "2:00 PM",
          "2:30 PM", "3:00 PM", "3:30 PM", "4:00 PM", "4:30 PM", "5:00 PM", "5:30 PM", "6:00 PM",
          "6:30 PM", "7:00 PM", "7:30 PM", "8:00 PM", "8:30 PM", "9:00 PM", "9:30 PM", "10:00 PM",
          "10:30 PM", "11:00 PM", "11:30 PM");

  private ObservableList<String> locations = FXCollections
      .observableArrayList("Coastal Village Apartments",
          "Coconut Point Mall", "Florida Gulf Coast University",
          "Florida SouthWestern State College",
          "Gulf Coast Town Center", "Miromar Outlets", "The Reef Apartments",
          "Walmart Supercenter (Estero)");


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    pickupComboBox.setItems(locations);
    destinationComboBox.setItems(locations);
    timeComboBox.setItems(time);
  }

  @FXML
  void onCancelPressed(ActionEvent event) {
    Globals.closeScene(root);
  }


  @FXML
  void onSubmitPressed(ActionEvent event) {

  }

  @FXML
  void onSearchPressed(ActionEvent event) {

  }
}
