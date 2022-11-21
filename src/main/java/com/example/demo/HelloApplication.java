package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

import java.io.IOException;

public class HelloApplication extends Application {
    double x,y;
    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            primaryStage.setResizable(false);
            Parent fxmlLoader = FXMLLoader.load(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader);
            primaryStage.setTitle("Scanna Login");

            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    // hello this is git test
    //test response
    //second test response
    public static void main(String[] args)
    {
        launch();
    }
}