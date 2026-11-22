package edu.utsa.cs3443.foundtime;

import edu.utsa.cs3443.foundtime.model.User;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Hyperlink signUpLink;
    @FXML private Label errorLabel;
    private final String usersFilePath = "data/users.csv";
    public static User currentUser;

    //Do we need this?
    public void initialize(){

    }

    //TODO: Implement this
    public void handleLoginButton(ActionEvent event){
        if(login(username.getText(), password.getText())){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(CalendarApplication.class.getResource("layouts/main-screen.fxml"));
                Parent newRoot = fxmlLoader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = stage.getScene();
                scene.setRoot(newRoot);
                stage.setTitle("FoundTime");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            errorLabel.setText("Incorrect username or password.");
        }
    }

    //TODO: Implement this
    public void handleSignUp(ActionEvent event){
        //Just changes the screen, doesn't need any other authentication
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(CalendarApplication.class.getResource("layouts/signup-screen.fxml"));
            Parent newRoot = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = stage.getScene();
            scene.setRoot(newRoot);
            stage.setTitle("FindTime");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO: This authenticates the users
    private boolean login(String username, String password){
        currentUser = User.authenticate(username, password);
        User.setCurrentUser(currentUser);
        return currentUser != null;
    }


}
