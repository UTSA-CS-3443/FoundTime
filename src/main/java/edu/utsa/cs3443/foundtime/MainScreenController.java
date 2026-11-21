package edu.utsa.cs3443.foundtime;

import edu.utsa.cs3443.foundtime.model.Event;
import edu.utsa.cs3443.foundtime.model.EventManager;
import edu.utsa.cs3443.foundtime.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


import java.util.*;
import java.time.*;

import javafx.event.ActionEvent;

import java.io.IOException;

public class MainScreenController {
    private EventManager eventManager;
    //private GridPane calendarGrid;
    @FXML
    private GridPane calendarPane;

    @FXML
    private Button weekButton;
    @FXML
    private Button addEventButton;
    @FXML
    private Button logOutButton;

    @FXML
    private Button forwardMonthButton;

    @FXML
    private Button backMonthButton;

    @FXML
    private Label showUsername;

    @FXML
    private Label monthLabel;
    @FXML
    private Label yearLabel;
    ZonedDateTime dateTime;
    ZonedDateTime today;
    private Map<LocalDate, ArrayList<Event>> events;
    private User currentUser;



    //TODO
    public void initialize(){
        currentUser = User.getCurrentUser();
        eventManager = new EventManager();
        events = new HashMap<>();
        dateTime = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();
    }

    //TODO
    public void setEventManager(EventManager eventManager){
        this.eventManager = eventManager;
    }

    //TODO
    public void handleWeekButton(ActionEvent actionEvent){

    }

    //TODO
    public void handleAddEventButton(ActionEvent actionEvent){

    }

    //TODO
    public void handleLogOutButton(ActionEvent actionEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(CalendarApplication.class.getResource("layouts/login-screen.fxml"));
            Parent newRoot = fxmlLoader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = stage.getScene();
            scene.setRoot(newRoot);
            stage.setTitle("FindTime: Login Screen");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawCalendar(){
        showUsername.setText(currentUser.getUsername());
        monthLabel.setText(String.valueOf(dateTime.getMonth()));
        yearLabel.setText(String.valueOf(dateTime.getYear()));

        calendarPane.getChildren().clear();
        calendarPane.setStyle("-fx-background-color: black; -fx-padding: 1;");
        calendarPane.setHgap(1);
        calendarPane.setVgap(1);


        int endOfMonth = dateTime.getMonth().maxLength();
        int offset = ZonedDateTime.of(dateTime.getYear(), dateTime.getMonthValue(),
                1,0,0,0,0,dateTime.getZone()).getDayOfWeek().getValue() % 7;


        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 7; j++){
                StackPane pane = new StackPane();
                pane.setStyle("-fx-background-color: #FFF8EF");

                //adds date text for each box
                int calcDate = (j+1)+(7*i);
                if(calcDate > offset){
                    int currDate = calcDate - offset;
                    if (currDate <= endOfMonth){
                        Text date = new Text(String.valueOf(currDate));
                        StackPane.setAlignment(date, Pos.TOP_LEFT);
                        StackPane.setMargin(date, new Insets(4,0,0,6));
                        pane.getChildren().add(date);
                    }
//                    if(today.getYear() == dateTime.getYear() && today.getMonth() == dateTime.getMonth() && today.getDayOfMonth() == dateTime.getDayOfMonth()){
//                        rectangle.setFill(Color.valueOf("FFB3D4"));
//                    }
                    calendarPane.add(pane,j,i);
                }
            }
        }

    }

}