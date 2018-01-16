package reversiapp;
import boardgame.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import reversiapp.GuiBoard;
import reversiapp.Menu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReversiGameController implements Initializable {

    final static int PLAYER1 = 0;
    final static int PLAYER2 = 1;
    final static int DEFAULT_BOARD_DIM = 8;
    final static int BOARD_SIZE = 400;

    @FXML
    private HBox root;
    @FXML
    private Alert alert;
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

    private String color_player1_name = "black";//default
    private String color_player2_name = "white";//default
    private int dim = DEFAULT_BOARD_DIM + 1;//default
    private Color color_player1 = Color.BLACK;//default
    private Color color_player2 = Color.WHITE;//default
    private int open_player = PLAYER1;//default
    private Player[] players;
    private GuiBoard guiBoard;
    private Board board;

    /**
     * initialize ReversiGameController.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle
            resources) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");

        ClassicRules classicRules = new ClassicRules();
        readFromSettingsFile();
        initializePlayers();
        board = new Board(dim, classicRules.getInitialValues(dim));
        GameFlow gameFlow = new GameFlow(board, players, classicRules, this);
        guiBoard = new GuiBoard(board, players, gameFlow);

        guiBoard.setPrefWidth(BOARD_SIZE);
        guiBoard.setPrefHeight(BOARD_SIZE);
        root.getChildren().add(0, guiBoard);
        guiBoard.draw(board.getTokens());
        displayPlayersScore();

        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            guiBoard.setPrefWidth(boardNewWidth);
            guiBoard.draw(board.getTokens());
        });

        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            guiBoard.setPrefHeight(newValue.doubleValue());
            guiBoard.draw(board.getTokens());
        });
    }

    /**
     * display the players score on the screen.
     */
    public void displayPlayersScore(){
        if (open_player == PLAYER1) {
            player1Score.setText(color_player1_name + " score:");
            player2Score.setText(color_player2_name + " score:");
        }else{
            player1Score.setText(color_player2_name + " score:");
            player2Score.setText(color_player1_name + " score:");
        }
    }

    /**
     * read the the setting file.
     */
    public void readFromSettingsFile() {
        try (BufferedReader br = new BufferedReader
                (new FileReader("settingsFile.txt"))) {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                getSettings(sCurrentLine);
            }
            br.close();

        } catch (IOException e) {
        }
    }

    /**
     * get string of the file and initialize the
     * members depend the stringFile.
     * @param stringFile
     */
    public void getSettings(String stringFile) {
        String[] lines = stringFile.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split(" ");
            switch (parts[0]) {
                case "startPlayer:":
                    if (parts[1].equals( "player1")) {
                        open_player = PLAYER1;
                    } else {
                        open_player = PLAYER2;
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

    /**
     * initialize players.
     */
    public void initializePlayers() {
        players = new Player[2];
        if (open_player == PLAYER1) {
            currPlayer.setText(color_player1_name);
            players[0] = new RealPlayer(TokenValue.Black, color_player1,color_player1_name);
            players[1] = new RealPlayer(TokenValue.White, color_player2, color_player2_name );
        } else {
            currPlayer.setText(color_player2_name);
            players[0] = new RealPlayer(TokenValue.Black, color_player2, color_player2_name);
            players[1] = new RealPlayer(TokenValue.White, color_player1, color_player1_name);

        }
    }

    /**
     * set text of the label currPlayer.
     * @param tv
     */
    public void setCurrentPlayer(String tv) {
        currPlayer.setText(tv);
    }

    /**
     * set text of the label whiteScore.
     * @param whiteScore1
     */
    public void setWhiteScore(String whiteScore1) {
        whiteScore.setText(whiteScore1);
    }

    /**
     * set text of the label blackScore.
     * @param blackScore1
     */
    public void setBlackScore(String blackScore1) {
        blackScore.setText(blackScore1);
    }

    /**
     * this function return to the menu stage.
     */
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

    /**
     * handle end game situation.
     * @param winnerPlayer
     */
    public void handleEndGame(Player winnerPlayer){

        if(winnerPlayer == players[0]) {//black win
            if (open_player == PLAYER1){
                alert.setHeaderText("game over! " + color_player1_name + " wins");
            }else{
                alert.setHeaderText("game over! " + color_player2_name + " wins");
            }
        } else if (winnerPlayer == players[1]){
            if (open_player == PLAYER1){//
                alert.setHeaderText("game over! " + color_player2_name + " wins");
            }else{
                alert.setHeaderText("game over! " + color_player1_name + " wins");
            }
        }else{//tie
            alert.setHeaderText("game over! tie");
        }
        alert.showAndWait();
    }

    /**
     * handle no move situation.
     * @param player
     */
    public void handleNoMove(Player player){
        alert.setHeaderText("sorry, you have no move");
        alert.showAndWait();

            if (player == players[0]){
                currPlayer.setText(color_player1_name);
            }else{
                currPlayer.setText(color_player2_name);
            }
            guiBoard.draw(board.getTokens());
    }
}