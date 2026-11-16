package edu.utsa.cs3443.foundtime;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class CalendarApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try{
            Font customFont = Font.loadFont(
                    getClass().getResourceAsStream("edu/utsa/cs3443/foundtime/layouts/fonts/HeadlandOne-Regular.ttf"),
                    16
            );

            FXMLLoader fxmlLoader = new FXMLLoader(CalendarApplication.class.getResource("layouts/login-screen.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 450);
            primaryStage.setTitle("Hello!");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
