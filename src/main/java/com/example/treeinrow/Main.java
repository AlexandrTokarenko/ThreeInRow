package com.example.treeinrow;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("startMenu/startMenu2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 650);
        primaryStage.setTitle("Three in row");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}


//url('file:A:/CourseWork/TreeInRow/back.gif')
