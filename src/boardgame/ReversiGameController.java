package boardgame;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ReversiGameController implements Initializable {


    @FXML
    private HBox root;
    private Board board;
    @Override
    public void initialize(URL location, ResourceBundle
            resources) {
        ClassicRules classicRules = new ClassicRules();
        int dim = 9;
        board = new Board(dim, classicRules.getInitialValues(dim));
        GuiBoard boardTemp = new GuiBoard(board);
        boardTemp.setPrefWidth(400);
        boardTemp.setPrefHeight(400);
        root.getChildren().add(0, boardTemp);
        boardTemp.draw(board.getTokens());

    }

}
