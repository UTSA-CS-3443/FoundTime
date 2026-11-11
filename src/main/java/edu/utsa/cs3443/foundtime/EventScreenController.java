package edu.utsa.cs3443.foundtime;

import edu.utsa.cs3443.foundtime.model.Event;
import edu.utsa.cs3443.foundtime.model.EventManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;

public class EventScreenController {
    private EventManager eventManager;
    private Event selectedEvent;

    @FXML private TextField nameIn;
    @FXML private DatePicker startDateIn;
    @FXML private DatePicker endDateIn;
    @FXML private TextField startTimeIn;
    @FXML private TextField endTimeIn;
    @FXML private TextField locationIn;
    @FXML private TextField descriptionIn;
    @FXML private ChoiceBox<String> typeIn;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    //TODO
    public void initialize(){

    }

    //TODO
    public void setEventManager(EventManager eventManager){
        this.eventManager = eventManager;
    }

    //TODO
    public void setEventToEdit(Event selectedEvent){
        this.selectedEvent = selectedEvent;
    }

    //TODO
    public void handleSaveButton(ActionEvent event){

    }

    //TODO
    public void handleCancelButton(ActionEvent event){

    }
}
