package edu.utsa.cs3443.foundtime;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;

public class SignupController {
    @FXML private TextField usernameIn;
    @FXML private TextField passwordIn;
    @FXML private Hyperlink loginLink;

    //TODO
    public void initialize(){

    }

    //TODO
    public void handleSignUpButton(ActionEvent event){

    }

    //TODO
    public void handleLogin(ActionEvent event){
        //Call signup method to make sure the username is available
        //Change screens
    }

    //TODO
    private boolean signup(String username, String password){
        return false;
    }

    //TODO
    private int nextUserID(){
        return 0;
    }
}
