package com.example.treeinrow.controllers;

import com.example.treeinrow.statusPanel.StatusPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class StartMenuController2 {

    @FXML private Canvas canvas;
    @FXML private Pane pane;

    @FXML private Button button;

    @FXML private RadioButton firstChoice;
    @FXML private RadioButton secondСhoice;

    @FXML private ToggleGroup group;

    @FXML private TextArea textArea;

    public void initialize() {

        textArea = new TextArea();
        textArea.setPrefSize(135,30);
        textArea.setLayoutX(331);
        textArea.setPromptText("Введіть кількість кроків");
        textArea.setFont(Font.font("Cambria",15));
        textArea.setLayoutY(245);
        pane.getChildren().add(textArea);
        textArea.setVisible(false);
    }

    public void click(ActionEvent actionEvent) {

        if (actionEvent.getEventType() == ActionEvent.ACTION) {
            if (group.getSelectedToggle() != null) {
                if (firstChoice.isSelected()) {
                    loop:
                    if (textArea.getText().length() != 0) {
                        int n;
                        try {
                             n = Integer.parseInt(textArea.getText());
                        } catch (NumberFormatException e) {
                            break loop;
                        }
                        if (n>=1) {
                            StatusPanel.setNumberOfMoves(n);
                            ((Stage) button.getScene().getWindow()).close();
                            drawMainWindow();
                        }
                    }
                }
                if (secondСhoice.isSelected()) {
                    StatusPanel.setNumberOfMoves(-1);
                    ((Stage) button.getScene().getWindow()).close();
                    drawMainWindow();
                }
            }
        }
    }

    private void drawMainWindow() {
        String file = "/com/example/treeinrow/treeInRow.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(file));
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
        stage.setResizable(false);
        stage.showAndWait();
    }

    public void clickingOnSelect(ActionEvent actionEvent) {
        if (actionEvent.getEventType() == ActionEvent.ACTION) {
            textArea.setVisible(true);
        }
    }

    public void clickingOnSelect2(ActionEvent actionEvent) {
        if (actionEvent.getEventType() == ActionEvent.ACTION) {
            textArea.setVisible(false);
        }
    }
}
