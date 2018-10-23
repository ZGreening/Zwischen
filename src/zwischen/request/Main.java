package zwischen.request;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Request a Ride");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

      Label addressEntry = new Label("Please Enter Current Coordinates: latitude");
      grid.add(addressEntry, 0, 1);
      TextField latitudeTextField = new TextField();
      grid.add(latitudeTextField, 1, 1);
      //create x1
      Label addressEntry2 = new Label("Please Enter Current Coordinates: longitude");
      grid.add(addressEntry2, 0, 2);
      TextField longitudeTextField = new TextField();
      grid.add(longitudeTextField, 1, 2);
      //create y1

      Label destinationEntry = new Label("Please Enter Destination Coordinates: latitude");
      grid.add(destinationEntry, 0, 3);
      TextField latitudeTextField2 = new TextField();
      grid.add(latitudeTextField2, 1, 3);
      //create x2
      Label destinationEntry2 = new Label("Please Enter Destination Coordinates: longitude");
      grid.add(destinationEntry2, 0, 4);
      TextField longitudeTextField2 = new TextField();
      grid.add(longitudeTextField2, 1, 4);

        Label pw = new Label("Please enter number of Passengers");
        grid.add(pw, 0, 5);

        TextField passengerNumberTextField = new TextField();
        grid.add(passengerNumberTextField, 1, 5);

        Button btn = new Button("Request pickup now");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 6);
        //this creates a Date object at the current day and time
        //that date is then taken along with the initial x and y coordinates
        //and final x and y coordinates in order to make an object of the class "Trip"


        Label scheduleLabel = new Label("Please enter the day of the trip(1-31)");
        grid.add(scheduleLabel, 0, 7);
        TextField scheduleTextField1 = new TextField();
        grid.add(scheduleTextField1, 1, 7);
        //create int for day

        Label scheduleLabel2 = new Label("Please enter the month of the trip(1-12)");
        grid.add(scheduleLabel2, 0, 8);
        TextField scheduleTextField2 = new TextField();
        grid.add(scheduleTextField2, 1, 8);
        // create int for month

        Label scheduleLabel3 = new Label("Please enter the year of the trip(20xx)");
        grid.add(scheduleLabel3, 0, 9);
        TextField scheduleTextField3 = new TextField();
        grid.add(scheduleTextField3, 1, 9);
        //create int for year

        Label scheduleLabel4 = new Label("Please enter the hour of the trip(0-24)");
        grid.add(scheduleLabel4, 0, 10);
        TextField scheduleTextField4 = new TextField();
        grid.add(scheduleTextField4, 1, 10);
        //create int for hour

        Label scheduleLabel5 = new Label("Please enter the minute the trip (0-59)");
        grid.add(scheduleLabel5, 0, 11);
        TextField scheduleTextField5 = new TextField();
        grid.add(scheduleTextField5, 1, 11);
        //crete int for year

        Button scheduleBtn = new Button("Request pickup at listed time");
        HBox hbScheduleBtn = new HBox(10);
        hbScheduleBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbScheduleBtn.getChildren().add(scheduleBtn);
        grid.add(scheduleBtn, 1, 12);
        //requesting this way time calls the constructor for date
        //with the taken values as parameters
        //that date is then taken along with the initial x and y coordinates
        //and final x and y coordinates in order to make an object of the class "Trip"
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 13);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("submission");
            }
        });

      final Text actiontarget2 = new Text();
      grid.add(actiontarget2, 1, 13);

      scheduleBtn.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent e) {
          actiontarget.setFill(Color.FIREBRICK);
          actiontarget.setText("submission");
        }
      });

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
