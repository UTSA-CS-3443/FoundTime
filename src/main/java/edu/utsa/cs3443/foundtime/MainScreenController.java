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
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


import java.net.URL;
import java.util.*;
import java.time.*;

import javafx.event.ActionEvent;
import javafx.stage.Window;

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
    private Label showUsername;

    @FXML
    private Button forwardMonthButton;

    @FXML
    private Button backMonthButton;

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

    public void clickForwardMonthButton(ActionEvent event){
        dateTime = dateTime.plusMonths(1);
        drawCalendar();
    }

    public void clickBackMonthButton(ActionEvent event){
        dateTime = dateTime.minusMonths(1);
        drawCalendar();
    }

    //TODO
    public void setEventManager(EventManager eventManager){
        this.eventManager = eventManager;
    }

    //TODO
    public void handleWeekButton(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(CalendarApplication.class.getResource("layouts/week-screen.fxml"));
            Parent newRoot = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = stage.getScene();
            scene.setRoot(newRoot);
            stage.setTitle("FoundTime: Week View");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO
    public void handleAddEventButton(ActionEvent event){
        openEventScreen(null);
    }

    //TODO
    public void handleLogOutButton(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(CalendarApplication.class.getResource("layouts/login-screen.fxml"));
            Parent newRoot = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = stage.getScene();
            scene.setRoot(newRoot);
            stage.setTitle("FoundTime: Log In");

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


        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 7; j++){
                StackPane pane = new StackPane();
                pane.setStyle("-fx-background-color: #FFF8EF");

                //adds date text for each box
                int calcDate = (j+1)+(7*i);
                int currDate = calcDate - offset;

                if(dateTime.getYear() % 4 != 0 && endOfMonth == 29){
                    endOfMonth = 28;
                }

                if (currDate >= 1 && currDate <= endOfMonth){
                    LocalDate date = LocalDate.of(dateTime.getYear(),dateTime.getMonthValue(),currDate);

                    VBox event = new VBox(2);
                    event.setPadding(new Insets(4,4,4,6));
                    event.setAlignment(Pos.TOP_LEFT);

                    Text day = new Text(String.valueOf(currDate));
                    event.getChildren().add(day);

                    List<Event> userEvents = eventManager.getEventsByDate(date);

                    for (Event e : userEvents){
                                Label eventLabel = new Label(e.getName());
                                eventLabel.setMaxWidth(Double.MAX_VALUE);
                                eventLabel.setPadding(new Insets(1, 3, 1, 3));
                                eventLabel.setStyle("-fx-background-color: #FFB3D4; -fx-background-radius: 3;");
                                eventLabel.setOnMouseClicked(mouseEvent -> showEvent(e));
                                event.getChildren().add(eventLabel);
//                        if(today.getYear() == dateTime.getYear() && today.getMonth() == dateTime.getMonth() && today.getDayOfMonth() == dateTime.getDayOfMonth()){
//                            eventLabel.setStyle("-fx-background-color: #ff99c4");
//                        }
                    }

                    pane.getChildren().add(event);
                }
                    calendarPane.add(pane,j,i);

            }
        }

    }

    private void showEvent(Event e){
        Alert event = new Alert(Alert.AlertType.INFORMATION);
        DialogPane pane = event.getDialogPane();
        pane.setStyle("-fx-background-color: #FFF8EF");
        event.setGraphic(null);

        Node header = pane.lookup(".header-panel");
        if (header != null) {header.setStyle("-fx-background-color: #FFB3D4");}

        ButtonType editButton = new ButtonType("Edit", ButtonBar.ButtonData.OTHER);

        event.setTitle("Event Details");
        event.setHeaderText(e.getName());
        event.setContentText(eventManager.displayEventDetails(e));

        ButtonType editEvent = new ButtonType("Edit Event", ButtonBar.ButtonData.OTHER);
        event.getButtonTypes().setAll(editEvent, ButtonType.CLOSE);

        Optional<ButtonType> result = event.showAndWait();

        if (result.isPresent() && result.get() == editEvent){
            openEventScreen(e);
        }
    }

    private void openEventScreen(Event selectedEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(CalendarApplication.class.getResource("layouts/event-screen.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            EventScreenController controller = fxmlLoader.getController();
            if (selectedEvent != null){
                controller.setEventToEdit(selectedEvent);
            }

            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(calendarPane.getScene().getWindow());

            stage.setTitle("FoundTime: Event Editor");
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}