package edu.utsa.cs3443.foundtime;

import edu.utsa.cs3443.foundtime.model.EventManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class WeekScreenController {
    private EventManager eventManager;
    @FXML private GridPane weekGrid;
    @FXML private Button monthButton;
    @FXML private Button dayButton;
    @FXML private Button addEventButton;
    @FXML private Button logOutButton;

    //TODO
    public void initialize(){

    }

    //TODO
    public void setEventManager(EventManager eventManager){
        this.eventManager = eventManager;
    }

    //TODO
    public void handleMonthButton(ActionEvent event){
    }

    //TODO
    public void handleDayButton(ActionEvent event){

    }

    public void onBackButtonClick(ActionEvent actionEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(CalendarApplication.class.getResource("layouts/main-screen.fxml"));
            Parent newRoot = fxmlLoader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = stage.getScene();
            scene.setRoot(newRoot);
            stage.setTitle("FoundTime");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO
    public void handleAddEventButton(ActionEvent event){

    }

    //TODO
    public void handleLogOutButton(ActionEvent event){

    }
}
