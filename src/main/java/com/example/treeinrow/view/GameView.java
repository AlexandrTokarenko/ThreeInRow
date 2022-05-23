package com.example.treeinrow.view;

import com.example.treeinrow.items.MoveResult;
import com.example.treeinrow.model.Game;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

import javafx.scene.paint.Color;

public class GameView {

    private Canvas canvas;
    private Game game;

    public GameView(Game game, Canvas canvas) {

        this.game = game;
        this.canvas = canvas;
    }

    public void drawElements() {

        Image image = new Image("A:\\CourseWork\\TreeInRow\\elements.png");
        clear();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        if (game.getMoveResult() == MoveResult.SELECTION) {
            gc.setStroke(Color.DARKGOLDENROD);
            gc.setLineWidth(5);
            gc.strokeRoundRect(game.getPair().getRow() * 50 + 5, game.getPair().getColumn() * 50 + 5, 50, 50, 10, 10);
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                WritableImage writableImage = new WritableImage(image.getPixelReader(), game.getGrid().getGrid()[i][j] * 50, 0, 50, 50);
                ImageView imageView = new ImageView(writableImage);
                gc.drawImage(imageView.getImage(), i*50 + 5, j*50 + 5);
            }
        }
    }

    public void clear() {

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.web("#899cf2"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}