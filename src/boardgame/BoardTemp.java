package boardgame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.io.IOException;

public class BoardTemp extends GridPane {
    private int[][] board;
    private static final int FREE = 0;
    private static final int WALL = 1;

    public BoardTemp(int[][] board) {
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

        int cellHeight = height / board.length;
        int cellWidth = width / board[0].length;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
              if (board[i][j] == FREE) {
//                  this.add(new Rectangle(cellWidth, cellHeight,Col), j, i);
                  Node button = new javafx.scene.control.Button();
                  this.add(button, j, i);
              }
                else {
                  this.add(new Rectangle(cellWidth, cellHeight,Color.YELLOW), j, i);
              }
                GridPane shula = new GridPane();
                Button kll = new Button("d");
                Node or = new javafx.scene.control.Button("d");
                shula.add(or, 9 ,0);
            }
        }
    }
}

//}
