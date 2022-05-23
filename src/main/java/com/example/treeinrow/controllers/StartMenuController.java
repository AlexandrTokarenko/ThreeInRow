package com.example.treeinrow.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class StartMenuController {

    @FXML private Pane pane;
    @FXML private Canvas canvas;
    @FXML private Button buttonStart;


    public void initialize() {
        canvas.widthProperty().bind(pane.widthProperty());
        canvas.heightProperty().bind(pane.heightProperty());

    }

    public void clickOnStart(ActionEvent actionEvent) {
        if (actionEvent.getEventType() == ActionEvent.ACTION) {
            buttonStart.getScene().getWindow().hide();
            //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/treeinrow/treeInRow.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/treeinrow/startMenu/StartMenu2.fxml"));
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setWidth(800);
            stage.setTitle("Three in Row");
            stage.setHeight(650);
            stage.showAndWait();
        }
    }
}
