package com.example.treeinrow.controllers;

import com.example.treeinrow.menu.ConditionMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MenuController {

    @FXML private Pane pane;

    private static ConditionMenu menu;

    public static ConditionMenu getMenu() {

        return menu;
    }

    public void clickOnResume(ActionEvent actionEvent) {
        if (actionEvent.getEventType() == ActionEvent.ACTION) {
            menu = ConditionMenu.RESUME;
            ((Stage) pane.getScene().getWindow()).close();
        }
    }

    public void clickOnRestart(ActionEvent actionEvent) {
        if (actionEvent.getEventType() == ActionEvent.ACTION) {
            menu = ConditionMenu.RESTART;
            ((Stage) pane.getScene().getWindow()).close();
        }
    }

    public void clickOnExit(ActionEvent actionEvent) {
        if (actionEvent.getEventType() == ActionEvent.ACTION) {
            menu = ConditionMenu.EXIT;
            ((Stage) pane.getScene().getWindow()).close();
        }
    }
}
