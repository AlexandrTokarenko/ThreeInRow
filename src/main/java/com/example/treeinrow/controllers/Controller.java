package com.example.treeinrow.controllers;

import com.example.treeinrow.Main;
import com.example.treeinrow.items.MoveResult;
import com.example.treeinrow.items.Pair;
import com.example.treeinrow.menu.ConditionMenu;
import com.example.treeinrow.model.Game;
import com.example.treeinrow.view.GameView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML private Label textMoves;
    @FXML private Label textPoints;

    @FXML private Pane pane;

    @FXML private Button buttonMenu;

    @FXML private Canvas canvas;

    private GameView gameView;
    private Game game;

    public void initialize() {

        canvas.widthProperty().bind(pane.widthProperty());
        canvas.heightProperty().bind(pane.heightProperty());

        canvas.heightProperty().addListener(e -> gameView.drawElements());
        canvas.widthProperty().addListener(e -> gameView.drawElements());

        textPoints.setFont(Font.font("Cambria",32));
        textPoints.setTextFill(Color.web("#000000"));
        textMoves.setFont(Font.font("Cambria",32));
        textMoves.setTextFill(Color.web("#000000"));

        game = new Game();
        gameView = new GameView(game,canvas);

        writeText();
    }

    private void writeText() {

        textPoints.setText("Очок: " + game.getPoints());
        textMoves.setText("Кроків: " + game.getMoves());
    }

    public void processMouse(MouseEvent mouseEvent) {

        int x1 = (int) (mouseEvent.getX()/50)-4;
        int y1 = (int) (mouseEvent.getY()/50)-4;

        if (game.checkPair(x1,y1) && (game.getPair() != null) && game.checkTwoPairs(x1,y1)) {
            game.setMoveResult(MoveResult.SIMLE);
            game.swap(x1, y1);
        } else if ((new Pair(x1, y1)).equals(game.getPair())) {
            game.setMoveResult(MoveResult.SIMLE);
            game.changePair();
        } else if (game.checkPair(x1,y1)) {
            game.setMoveResult(MoveResult.SELECTION);
            game.setPair(x1,y1);
        }
        writeText();

        gameView.drawElements();
        if (game.getMoveResult() == MoveResult.WIN) {
            showAlert();
        }
    }

    private void showAlert() {

        ButtonType foo = new ButtonType("Спочатку", ButtonBar.ButtonData.OK_DONE);
        ButtonType bar = new ButtonType("Вихід", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Кількість очок: " + game.getPoints(),foo,bar);
        alert.setTitle("Три в ряд");
        alert.setHeaderText("Гру закінчено!");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.orElse(foo) == foo) {
            alert.close();
            ((Stage) buttonMenu.getScene().getWindow()).close();
            Main main = new Main();
            try {
                main.start(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (option.orElse(bar) == bar) {
            alert.close();
            ((Stage) buttonMenu.getScene().getWindow()).close();
        }
    }

    public void clickMenu(ActionEvent actionEvent) {

        if (actionEvent.getEventType() == ActionEvent.ACTION) {
            buttonMenu.getScene().getWindow().hide();
            openWindow();
            if(MenuController.getMenu() == ConditionMenu.EXIT) {
                ((Stage) buttonMenu.getScene().getWindow()).close();
            } else if (MenuController.getMenu() == ConditionMenu.RESTART) {
                ((Stage) buttonMenu.getScene().getWindow()).close();
                Main main = new Main();
                try {
                    main.start(new Stage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                ((Stage) buttonMenu.getScene().getWindow()).show();
            }
        }
    }

    private void openWindow() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/treeinrow/menu/menu.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setWidth(800);
        stage.setTitle("Three in Row");
        stage.setHeight(650);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
