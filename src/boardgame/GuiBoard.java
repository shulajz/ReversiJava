package boardgame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import java.awt.*;
import java.io.IOException;

public class GuiBoard extends GridPane {
    private Board board;

    public GuiBoard(Board board) {
        this.board = board;
        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("BoardFXML.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    public void draw() {
        this.getChildren().clear();

        int height = (int)this.getPrefHeight();
        int width = (int)this.getPrefWidth();

        int cellHeight = height / board.getDimensions();
        int cellWidth = width / board.getDimensions();

        for (int i = 0; i < board.getDimensions(); i++) {
            for (int j = 0; j < board.getDimensions(); j++) {

///
                Rectangle rec = new Rectangle(cellWidth, cellHeight,
                        Color.YELLOW);
                rec.setStroke(Color.BLACK);
                this.add(rec, j, i);

            }
        }
    }
}

//}
