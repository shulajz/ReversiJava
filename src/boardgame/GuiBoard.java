package boardgame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiBoard extends GridPane {
    private int cellSize;
    private Board board;
    private Player[] players;
    private GameRules gameRules;
    private Player playerCurrentTurn;
    public GuiBoard(Board board, Player players[], GameRules gameRules) {
        this.gameRules = gameRules;
        this.players = players;
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
        TokenValue blackTv = TokenValue.Black;
        this.playerCurrentTurn = players[blackTv.getValue()];
        initializeBoard();
    }

    public void initializeBoard(){
        this.getChildren().clear();
        int width = (int)this.getPrefWidth();
        cellSize = width / board.getDimensions();
        for (int i = 1; i < board.getDimensions(); i++) {
            for (int j = 1; j < board.getDimensions(); j++) {
                Rectangle rec = new Rectangle(cellSize, cellSize,
                        Color.YELLOW);
                rec.setStroke(Color.BLACK);
                rec.setOnMouseClicked(event -> {
                    List<Coordinate> validCoordinates = new ArrayList<Coordinate>();
                    double row = Math.ceil(event.getSceneY()/cellSize);
                    double col = Math.ceil(event.getSceneX()/cellSize);
                    Coordinate coordinate = new Coordinate((int)row, (int)col);
                    gameRules.getLegalCoordinates(
                            board, playerCurrentTurn, validCoordinates);
                    for (int k = 0; k < validCoordinates.size(); k++) {
                        //checks if the input is one of the legal coordinates
                        if(row == validCoordinates.get(k).getRow()
                                && col == validCoordinates.get(k).getCol()){
                            board.updateValue(coordinate, playerCurrentTurn.getValue());
                            gameRules.flipTokens(coordinate, board,
                                    playerCurrentTurn);
                            switchPlayer();
                            draw(board.getTokens());
                        } else {

                        }
                    }
                    System.out.println(Math.ceil(event.getSceneX()/cellSize));
                    System.out.println(Math.ceil(event.getSceneY()/cellSize));
                    System.out.println();
                });
                this.add(rec, j, i);
            }
        }
    }
    public void switchPlayer() {
        if (playerCurrentTurn.getValue() == TokenValue.White) {
            playerCurrentTurn =players[0];
        } else if (playerCurrentTurn.getValue() == TokenValue.Black) {
            playerCurrentTurn = players[1];

        }
    }

    public void draw(Token[][] tokens) {
        for (int i = 1; i < board.getDimensions(); i++) {
            for (int j = 1; j < board.getDimensions(); j++) {
                tokens[i][j].draw(i, j, this, cellSize/2, players);
            }
        }

    }

}
