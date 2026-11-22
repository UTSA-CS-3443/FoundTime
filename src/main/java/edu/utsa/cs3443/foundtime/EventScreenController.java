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

    }

    //TODO
    public void handleSaveButton(ActionEvent event) throws IOException {
        // 1. Grab text inputs
        String title = titleLabel.getText();
        String location = Location.getText();
        String description = descriptionArea.getText();

        // 2. Grab dates, times, and checkbox
        LocalDate startDate = startDateIn.getValue();
        LocalDate endDate = (endDateIn.getValue() != null) ? endDateIn.getValue() : startDate;
        boolean isAllDay = allDayCheckBox.isSelected();

        // Use midnight/end-of-day if "All day" is checked, otherwise grab the ComboBox times
        LocalTime startTime = isAllDay ? LocalTime.MIDNIGHT : timeComboBox.getValue();
        LocalTime endTime = isAllDay ? LocalTime.of(23, 59) : timeComboBox2.getValue();

        // (Optional) Combine into LocalDateTime if your Event / EventManager uses LocalDateTime:
        // LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
        // LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

        // 3. Edit existing event or add a new one via EventManager
        if (selectedEvent != null) {
            eventManager.editEvent(selectedEvent, new Event(LoginController.currentUser.getUserID(), title, description, startDate, endDate, startTime, endTime, location, ""));
        } else {
            eventManager.addEvent(new Event(LoginController.currentUser.getUserID(), title, description, startDate, endDate, startTime, endTime, location, ""));
        }

        // 4. Close the window
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


    //Ignore this stuff, I was playing around with javaFX features. specifically stack panes
//    // Matches fx:id="optionsBtn" in FXML (only needed if opening on left-click)
//    @FXML
//    private Button optionsBtn;
//
//    // Matches fx:id="myContextMenu" in FXML (only needed if opening on left-click)
//    @FXML
//    private ContextMenu myContextMenu;
//
//    // Matches onAction="#handleEdit" on the first MenuItem
//    @FXML
//    private void handleEdit(ActionEvent event) {
//        System.out.println("Edit option clicked!");
//    }
//
//    // Matches onAction="#handleDelete" on the second MenuItem
//    @FXML
//    private void handleDelete(ActionEvent event) {
//        System.out.println("Delete option clicked!");
//    }
//
//    // Optional: Add onAction="#showMenu" to the <Button> in FXML to open on left-click
//    @FXML
//    private void showMenu(ActionEvent event) {
//        // Toggle behavior: close if already open, otherwise show below the button
//        if (myContextMenu.isShowing()) {
//            myContextMenu.hide();
//        } else {
//            myContextMenu.show(optionsBtn, Side.BOTTOM, 0, 0);
//        }
//    }
//
//    @FXML
//    private VBox popupMenu;
//
//    // Stores the mouse offset during a drag
//    private double dragOffsetX;
//    private double dragOffsetY;
//
//    @FXML
//    private void openPopup() {
//        // Optional: Reset position to center each time it opens
//        popupMenu.setTranslateX(0);
//        popupMenu.setTranslateY(0);
//
//        popupMenu.setVisible(true);
//        popupMenu.setManaged(true);
//    }
//
//    @FXML
//    private void closePopup() {
//        popupMenu.setVisible(false);
//        popupMenu.setManaged(false);
//    }
//
//    @FXML
//    private void handleMousePressed(MouseEvent event) {
//        dragOffsetX = event.getSceneX() - popupMenu.getTranslateX();
//        dragOffsetY = event.getSceneY() - popupMenu.getTranslateY();
//    }
//
//    @FXML private StackPane rootPane;
//
//    @FXML
//    private void handleMouseDragged(MouseEvent event) {
//        double targetX = event.getSceneX() - dragOffsetX;
//        double targetY = event.getSceneY() - dragOffsetY;
//
//        // Calculate maximum distance from center before hitting an edge
//        double maxX = Math.max(0, (rootPane.getWidth() - popupMenu.getWidth()) / 2.0);
//        double maxY = Math.max(0, (rootPane.getHeight() - popupMenu.getHeight()) / 2.0);
//
//        // Clamp values between -max and +max
//        double clampedX = Math.max(-maxX, Math.min(maxX, targetX));
//        double clampedY = Math.max(-maxY, Math.min(maxY, targetY));
//
//        popupMenu.setTranslateX(clampedX);
//        popupMenu.setTranslateY(clampedY);
//    }
}
