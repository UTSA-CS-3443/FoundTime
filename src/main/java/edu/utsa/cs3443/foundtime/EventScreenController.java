package edu.utsa.cs3443.foundtime;

import edu.utsa.cs3443.foundtime.model.Event;
import edu.utsa.cs3443.foundtime.model.EventManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;


public class EventScreenController {
    public CheckBox allDayCheckBox;
    public Label editLabel;
    private EventManager eventManager;
    private Event selectedEvent;

    @FXML private TextField titleLabel;
    @FXML private DatePicker startDateIn;
    @FXML private DatePicker endDateIn;
    @FXML private TextField startTimeIn;
    @FXML private TextField endTimeIn;
    @FXML private TextField Location;
    @FXML private TextArea descriptionArea;
    @FXML private ChoiceBox<String> typeIn;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;
    private Event eventToEdit;

    @FXML private ComboBox<LocalTime> timeComboBox;
    @FXML private ComboBox<LocalTime> timeComboBox2;

    @FXML
    public void initialize() {
        // Populate 30-minute intervals across the day
        LocalTime time = LocalTime.MIDNIGHT;
        do {
            timeComboBox.getItems().add(time);
            timeComboBox2.getItems().add(time);
            time = time.plusMinutes(15);
        } while (!time.equals(LocalTime.MIDNIGHT));

        // Format display as "9:30 AM" while keeping LocalTime objects underneath
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        timeComboBox.setConverter(new StringConverter<LocalTime>() {
            @Override
            public String toString(LocalTime t) {
                return (t == null) ? "" : t.format(formatter);
            }

            @Override
            public LocalTime fromString(String s) {
                return (s == null || s.isEmpty()) ? null : LocalTime.parse(s, formatter);
            }
        });

        timeComboBox2.setConverter(new StringConverter<LocalTime>() {
            @Override
            public String toString(LocalTime t) {
                return (t == null) ? "" : t.format(formatter);
            }

            @Override
            public LocalTime fromString(String s) {
                return (s == null || s.isEmpty()) ? null : LocalTime.parse(s, formatter);
            }
        });

        timeComboBox.setValue(LocalTime.of(9, 0)); // Default to 9:00 AM
        timeComboBox2.setValue(LocalTime.of(9, 30)); // Default to 9:30 AM

        timeComboBox.visibleProperty().bind(allDayCheckBox.selectedProperty().not());
        timeComboBox.managedProperty().bind(allDayCheckBox.selectedProperty().not());

        timeComboBox2.visibleProperty().bind(allDayCheckBox.selectedProperty().not());
        timeComboBox2.managedProperty().bind(allDayCheckBox.selectedProperty().not());


        allDayCheckBox.selectedProperty().addListener((observable, wasSelected, isNowSelected) -> {
            timeComboBox.setDisable(isNowSelected);
            timeComboBox2.setDisable(isNowSelected);
        });
    }

    //TODO
    public void setEventManager(EventManager eventManager){
        this.eventManager = eventManager;
    }

    //TODO
    public void setEventToEdit(Event selectedEvent){
        this.selectedEvent = selectedEvent;
        titleLabel.setText(selectedEvent.getName());
        startDateIn.setValue(selectedEvent.getStartDate());
        endDateIn.setValue(selectedEvent.getEndDate());
        timeComboBox.setValue(selectedEvent.getStartTime());
        timeComboBox2.setValue(selectedEvent.getEndTime());
        Location.setText(selectedEvent.getLocation());
        descriptionArea.setText(selectedEvent.getDescription());

        boolean isAllDay = selectedEvent.isAllDayEvent();
        allDayCheckBox.setSelected(isAllDay);
        timeComboBox.setDisable(isAllDay);
        timeComboBox2.setDisable(isAllDay);
    }

    //TODO
    public void handleSaveButton(ActionEvent event) throws IOException {
        String title = titleLabel.getText();
        String location = (Location.getText().isEmpty()) ? "N/A" : Location.getText();
        String description = (descriptionArea.getText().isEmpty()) ? "N/A" : descriptionArea.getText();

        LocalDate startDate = startDateIn.getValue();
        LocalDate endDate = (endDateIn.getValue() != null) ? endDateIn.getValue() : startDate;
        boolean isAllDay = allDayCheckBox.isSelected();

        LocalTime startTime = isAllDay ? LocalTime.MIDNIGHT : timeComboBox.getValue();
        LocalTime endTime = isAllDay ? LocalTime.of(23, 59) : timeComboBox2.getValue();

        if (selectedEvent != null) {
            eventManager.editEvent(selectedEvent, new Event(LoginController.currentUser.getUserID(), title, description, startDate, endDate, startTime, endTime, location, "Event"));
        } else {
            eventManager.addEvent(new Event(LoginController.currentUser.getUserID(), title, description, startDate, endDate, startTime, endTime, location, "Event"));
        }

        Stage stage = (Stage) titleLabel.getScene().getWindow();
        stage.close();
    }

    //TODO
    public void handleCancelButton(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    //TODO
    public void handleDeleteButton(ActionEvent event){
        eventManager.removeEvent(selectedEvent);

        Stage stage = (Stage) titleLabel.getScene().getWindow();
        stage.close();
    }
}
