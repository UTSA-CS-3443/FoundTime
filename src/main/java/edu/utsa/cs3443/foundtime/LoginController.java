package edu.utsa.cs3443.foundtime;

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
    @FXML private TextField Username;
    @FXML private PasswordField Password;
    @FXML private Hyperlink signUpLink;

    //Do we need this?
    public void initialize(){

    }

    //TODO: Implement this
    public void handleLoginButton(ActionEvent event){

    }

    //TODO: Implement this
    public void handleSignUp(ActionEvent event){

    }

    //TODO: This authenticates the users
    private boolean login(String username, String password){
        return false;
    }


}
