package boardgame;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReversiGameController implements Initializable {


    @FXML
    private HBox root;
    @FXML
    private Label currPlayer;
    @FXML
    private Label blackScore;
    @FXML
    private Label whiteScore;
    private int dim = 9;//default
    private Color color_player1 = Color.BLACK;//default
    private Color color_player2 = Color.WHITE;//default
    private int open_player = 0;//default
    private Player[] players;
    @FXML
    private Button returnToMenuButton;



    @Override
    public void initialize(URL location, ResourceBundle
            resources) {
        ClassicRules classicRules = new ClassicRules();
        readFromSettingsFile();
        initializePlayers();
        Board board = new Board(dim, classicRules.getInitialValues(dim));
        GuiBoard boardTemp = new GuiBoard(board, players, classicRules, this);
        boardTemp.setPrefWidth(400);
        boardTemp.setPrefHeight(400);
        root.getChildren().add(0, boardTemp);
        boardTemp.draw(board.getTokens());
    }

    public void readFromSettingsFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("settingsFile.txt"))) {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                getSettings(sCurrentLine);
            }
            br.close();

        } catch (IOException e) {
        }


    }

    public void getSettings(String stringFile) {
        String[] lines = stringFile.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split(" ");
            switch (parts[0]) {
                case "startPlayer:":
                    if (parts[1] == "player1") {
                        open_player = 0;
                    } else {
                        open_player = 1;
                    }
                    break;
                case "colorPlayer1:":
                    color_player1 = Color.valueOf(parts[1]);
                    break;
                case "colorPlayer2:":
                    color_player2 = Color.valueOf(parts[1]);
                    break;
                case "sizeBoard:":
                    dim = Integer.parseInt(parts[1].split("x")[0]) + 1;
                    break;
            }
        }
    }

    public void initializePlayers() {
        players = new Player[2];
        if (open_player == 0) {
            players[0] = new RealPlayer(TokenValue.Black, color_player1);
            players[1] = new RealPlayer(TokenValue.White, color_player2);
        } else {
            players[0] = new RealPlayer(TokenValue.Black, color_player2);
            players[1] = new RealPlayer(TokenValue.White, color_player1);

        }
    }

}
