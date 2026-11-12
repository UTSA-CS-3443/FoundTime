package edu.utsa.cs3443.foundtime;

import edu.utsa.cs3443.foundtime.model.EventManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.awt.event.ActionEvent;

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

    //TODO
    public void handleAddEventButton(ActionEvent event){

    }

    //TODO
    public void handleLogOutButton(ActionEvent event){

    }
}
