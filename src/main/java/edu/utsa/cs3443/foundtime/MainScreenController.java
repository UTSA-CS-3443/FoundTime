package edu.utsa.cs3443.foundtime;

import edu.utsa.cs3443.foundtime.model.EventManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class MainScreenController {
    private EventManager eventManager;
    private GridPane calendarGrid;
    private Button weekButton;
    private Button addEventButton;
    private Button logOutButton;

    //TODO
    public void initialize(){

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

}
