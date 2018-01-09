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
    }
    public void eventHandler(){
//        System.out.println(rec.getX());
//        System.out.println(rec.getY());
    }

//        try {
//            fxmlLoader.load();
//            this.setOnKeyPressed(event -> {
//                switch (event.getCode()) {
//                    case DOWN:
//                        player.moveDown();
//                        break;
//                    case UP:
//                        player.moveUp();
//                        break;
//                    case LEFT:
//                        player.moveLeft();
//                        break;
//                    case RIGHT:
//                        player.moveRight();
//                        break;
//                } catch (Exception e) {
//
//                }
//                event.consume();
//            });
//        }
    public void switchPlayer() {
        if (playerCurrentTurn.getValue() == TokenValue.White) {
            playerCurrentTurn.setTokenValue(TokenValue.Black);
        } else if (playerCurrentTurn.getValue() == TokenValue.Black) {
            playerCurrentTurn.setTokenValue(TokenValue.White);

        }
    }

    public void draw(Token[][] tokens) {
        this.getChildren().clear();

        int height = (int)this.getPrefHeight();
        int width = (int)this.getPrefWidth();

        int cellHeight = height / board.getDimensions();
        int cellWidth = width / board.getDimensions();

        for (int i = 1; i < board.getDimensions(); i++) {
            for (int j = 1; j < board.getDimensions(); j++) {
                Rectangle rec = new Rectangle(cellWidth, cellHeight,
                        Color.YELLOW);
                rec.setStroke(Color.BLACK);


                rec.setOnMouseClicked(event -> {
                    List<Coordinate> validCoordinates = new ArrayList<Coordinate>();
                    double row = Math.ceil(event.getSceneY()/cellHeight);
                    double col = Math.ceil(event.getSceneX()/cellWidth);
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
//                            inputValid = true;
//                            break;
                        } else {

                        }
                    }
                    System.out.println(Math.ceil(event.getSceneX()/cellWidth));
                    System.out.println(Math.ceil(event.getSceneY()/cellHeight));
                    System.out.println();
                });
                this.add(rec, j, i);
                tokens[i][j].draw(i, j, this, cellWidth/2);
            }
        }

    }

}
