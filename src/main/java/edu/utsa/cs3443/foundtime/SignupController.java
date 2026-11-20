package edu.utsa.cs3443.foundtime;

import edu.utsa.cs3443.foundtime.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class SignupController {
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private PasswordField passwordConfirm;
    @FXML private Label errorLabel;

    //TODO
    public void initialize(){

    }

    //TODO
    public void handleSignUpButton(ActionEvent event){
        if(username.getText().isEmpty() || password.getText().isEmpty()){
            errorLabel.setText("Incorrect username or password.");
            return;
        }
        else{
            if(!passwordConfirm.getText().isEmpty() && !password.getText().equals(passwordConfirm.getText())){
                errorLabel.setText("Passwords do not match.");
                return;
            }
            else if(passwordConfirm.getText().isEmpty()){
                errorLabel.setText("Please confirm your password.");
                return;
            }
        }


        if(!User.findUsername(username.getText())){ //if username is available.
            LoginController.currentUser = new User(username.getText(), password.getText(), nextUserID());
            User.addUser(LoginController.currentUser);

            //Testing
            //System.out.println(LoginController.currentUser.getUsername() + " " + LoginController.currentUser.getPassword() + " " + LoginController.currentUser.getUserID());

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(CalendarApplication.class.getResource("layouts/main-screen.fxml"));
                Parent newRoot = fxmlLoader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = stage.getScene();
                scene.setRoot(newRoot);
                stage.setTitle("FindTime: Main Screen");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            if(User.authenticate(username.getText(), password.getText())!=null){
                errorLabel.setText("Trying to login?");
                return;
            }
            errorLabel.setText("Username Unavailable.");
        }

    }

    //TODO
    public void handleLoginLink(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(CalendarApplication.class.getResource("layouts/login-screen.fxml"));
            Parent newRoot = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = stage.getScene();
            scene.setRoot(newRoot);
            stage.setTitle("FindTime: Sign Up");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //signup method not needed.

    //TODO
    private int nextUserID(){
        int fileUserID = 0;
        try{
            //username, password, userid
            String usersFilePath = "data/users.csv";
            File file = new File(usersFilePath);
            Scanner scanner = new Scanner(file);

            scanner.nextLine(); //Throws out the first row
            while(scanner.hasNextLine()){
                String line =  scanner.nextLine();

                if(line.trim().isEmpty()){
                    continue;
                }

                String[] data = line.split(",");

                fileUserID = Integer.parseInt(data[2].trim());
            }

            scanner.close();
        }
        catch(IOException e){
            System.err.println("Details: " + e.getMessage());
        }
        return ++fileUserID;
    }

}
