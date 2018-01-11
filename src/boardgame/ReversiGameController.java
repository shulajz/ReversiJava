package boardgame;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Menu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReversiGameController implements Initializable {



    @FXML
    private HBox root;
    @FXML
    private Button returnToMenuButton;
    @FXML
    private Label currPlayer;
    @FXML
    private Label blackScore;
    @FXML
    private Label whiteScore;
    @FXML
    private Label  player1Score;
    @FXML
    private Label  player2Score;
    @FXML
    private Label message;

    private String color_player1_name = "black";//default
    private String color_player2_name = "white";//default
    private int dim = 9;//default
    private Color color_player1 = Color.BLACK;//default
    private Color color_player2 = Color.WHITE;//default
    private int open_player = 0;//default
    private Player[] players;
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
        player1Score.setText(color_player1_name + " score");
        player2Score.setText(color_player2_name + " score");
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
                    color_player1_name = parts[1];
                    color_player1 = Color.valueOf(parts[1]);
                    break;
                case "colorPlayer2:":
                    color_player2 = Color.valueOf(parts[1]);
                    color_player2_name = parts[1];
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
            currPlayer.setText(color_player1_name);
            players[0] = new RealPlayer(TokenValue.Black, color_player1,color_player1_name);
            players[1] = new RealPlayer(TokenValue.White, color_player2, color_player2_name );
        } else {
            currPlayer.setText(color_player2_name);
            players[0] = new RealPlayer(TokenValue.Black, color_player2, color_player2_name);
            players[1] = new RealPlayer(TokenValue.White, color_player1, color_player1_name);

        }
    }

    public void setCurrentPlayer(String tv) {
        currPlayer.setText(tv);
    }
    public void setWhiteScore(String whiteScore1) {
        whiteScore.setText(whiteScore1);
    }
    public void setBlackScore(String blackScore1) {
        blackScore.setText(blackScore1);
    }
    @FXML
    protected void returnToMenu(){
        //return to the menu
        Menu menu= new Menu();
        Stage stage = (Stage) returnToMenuButton.getScene().getWindow();
        try {
            menu.start(stage);
        } catch (Exception e) {

        }
    }
    public void handleEndGame(){
      //  message.setText("no move for both players!");
        System.out.println("no move for both players!");
    }
    public void handleNoMove(Player player){
        if (player == players[0]){
            currPlayer.setText(color_player1_name);
        }else{
            currPlayer.setText(color_player2_name);
        }
//        message.setText("no move! please press any key to continue");
        System.out.println("no move! please press any key to continue");
    }

}

