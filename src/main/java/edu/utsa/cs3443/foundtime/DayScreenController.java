package edu.utsa.cs3443.foundtime;

import edu.utsa.cs3443.foundtime.model.EventManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class DayScreenController {
    private EventManager eventManager;
    @FXML private GridPane dayGrid;
    @FXML private Button monthButton;
    @FXML private Button weekButton;
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
    public void handleWeekButton(ActionEvent event){

    }

    //TODO
    public void handleAddEventButton(ActionEvent event){

    }

    //TODO
    public void handleLogOutButton(ActionEvent event){

    }


    //Ignore this stuff, I was playing around with javaFX features. specifically stack panes

    // Matches fx:id="optionsBtn" in FXML (only needed if opening on left-click)
    @FXML
    private Button optionsBtn;

    // Matches fx:id="myContextMenu" in FXML (only needed if opening on left-click)
    @FXML
    private ContextMenu myContextMenu;

    // Matches onAction="#handleEdit" on the first MenuItem
    @FXML
    public void handleEdit(ActionEvent event) {
        System.out.println("Edit option clicked!");
    }

    @FXML
    public void handleDelete(ActionEvent event) {
        System.out.println("Delete option clicked!");
    }

    // Optional: Add onAction="#showMenu" to the <Button> in FXML to open on left-click
    @FXML
    public void showMenu(ActionEvent event) {
        // Toggle behavior: close if already open, otherwise show below the button
        if (myContextMenu.isShowing()) {
            myContextMenu.hide();
        } else {
            myContextMenu.show(optionsBtn, Side.BOTTOM, 0, 0);
        }
    }

    @FXML
    private VBox popupMenu;

    // Stores the mouse offset during a drag
    private double dragOffsetX;
    private double dragOffsetY;

    @FXML
    private void openPopup() {
        // Optional: Reset position to center each time it opens
        popupMenu.setTranslateX(0);
        popupMenu.setTranslateY(0);

        popupMenu.setVisible(true);
        popupMenu.setManaged(true);
    }

    @FXML
    private void closePopup() {
        popupMenu.setVisible(false);
        popupMenu.setManaged(false);
    }

    @FXML
    private void handleMousePressed(MouseEvent event) {
        dragOffsetX = event.getSceneX() - popupMenu.getTranslateX();
        dragOffsetY = event.getSceneY() - popupMenu.getTranslateY();
    }

    @FXML private StackPane rootPane;

    @FXML
    private void handleMouseDragged(MouseEvent event) {
        double targetX = event.getSceneX() - dragOffsetX;
        double targetY = event.getSceneY() - dragOffsetY;

        // Calculate maximum distance from center before hitting an edge
        double maxX = Math.max(0, (rootPane.getWidth() - popupMenu.getWidth()) / 2.0);
        double maxY = Math.max(0, (rootPane.getHeight() - popupMenu.getHeight()) / 2.0);

        // Clamp values between -max and +max
        double clampedX = Math.max(-maxX, Math.min(maxX, targetX));
        double clampedY = Math.max(-maxY, Math.min(maxY, targetY));

        popupMenu.setTranslateX(clampedX);
        popupMenu.setTranslateY(clampedY);
    }
}
