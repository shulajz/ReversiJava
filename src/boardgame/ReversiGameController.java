package boardgame;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ReversiGameController implements Initializable {


    @FXML
    private HBox root;
    private int[][] board = {
            {0,1,0,1,0,0,0,1,0,0,0},
            {0,1,0,1,1,1,0,1,0,1,0},
};

    @Override
    public void initialize(URL location, ResourceBundle
            resources) {
        BoardTemp boardTemp = new BoardTemp(board);
        boardTemp.setPrefWidth(400);
        boardTemp.setPrefHeight(400);
        root.getChildren().add(0, boardTemp);
        boardTemp.draw();

    }

}
