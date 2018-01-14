package sample;

import boardgame.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static sample.Situation.NoMove;
import static sample.Situation.NoMovesForAll;
import static sample.Situation.ThereIsMove;


public class GuiBoard extends GridPane {
    private int cellSize;
    private Board board;
    private Player[] players;
    private GameRules gameRules;
    private Player playerCurrentTurn;
    private ReversiGameController gameController;
    public GuiBoard(Board board, Player players[],
                    GameRules gameRules, ReversiGameController controller) {
        this.gameController = controller;
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
        this.getChildren().clear();
        int width = (int)this.getPrefWidth();
        cellSize = width / board.getDimensions();
        initializeBoard();
    }

    public void initializeBoard(){
        for (int i = 1; i < board.getDimensions(); i++) {
            for (int j = 1; j < board.getDimensions(); j++) {
                Rectangle rec = new Rectangle(cellSize, cellSize,
                        Color.YELLOW);
                initializeRectangle(rec);
                //add rec to gridPane
                this.add(rec, j, i);
            }
        }
    }

    public void switchPlayer() {
        if (playerCurrentTurn.getValue() == TokenValue.White) {
            playerCurrentTurn = players[0];
        } else if (playerCurrentTurn.getValue() == TokenValue.Black) {
            playerCurrentTurn = players[1];

        }
    }

    public void draw(Token[][] tokens) {
        //before every draw of the current board check if we in a
        //spacial situations in the game like no move for one player
        // or no move for all.
        spacialSituationsInGame();
        for (int i = 1; i < board.getDimensions(); i++) {
            for (int j = 1; j < board.getDimensions(); j++) {
                tokens[i][j].draw(i, j, this, cellSize/2, players);
            }
        }
    }
    //this function check if we in spacial situations
    // in the game like no move for one player or no move for all
    // if so update the game manager about this that it will handle it
    public void spacialSituationsInGame(){
        Situation situation = checkGameFlowSituation();
        if(situation == NoMovesForAll){
            //check Who Win
            int[] sumScore = board.calcResults();
            int blackPlayer = sumScore[0];
            int whitePlayer = sumScore[1];
            Player winnerPlayer;
            if(blackPlayer > whitePlayer) { //black wins
                winnerPlayer = players[0];
            } else if (blackPlayer < whitePlayer){ //white wins
                winnerPlayer = players[1];
            }else {//tie
                winnerPlayer = null;
            }
            gameController.handleEndGame(winnerPlayer);

        }else if (situation == NoMove){
            gameController.handleNoMove(playerCurrentTurn);
        }
    }
    //in every clicked event we check if the click is valid coordinate
    //if so we change it depend in the game rules, and the current player
    public void clickEvent(double row, double col){
        List<Coordinate> validCoordinates = new ArrayList<Coordinate>();
        Coordinate coordinate = new Coordinate((int)row, (int)col);
        gameRules.getLegalCoordinates(
                board, playerCurrentTurn, validCoordinates);
        if (!validCoordinates.isEmpty()) { //the player has a turn
            for (int k = 0; k < validCoordinates.size(); k++) {
                //checks if the input is one of the legal coordinates
                if (row == validCoordinates.get(k).getRow()
                        && col == validCoordinates.get(k).getCol()) {
                    board.updateValue(coordinate, playerCurrentTurn.getValue());
                    gameRules.flipTokens(coordinate, board,
                            playerCurrentTurn);
                    switchPlayer();
                    int[] sumScore = board.calcResults();
                    int black = sumScore[0];
                    int white = sumScore[1];
                    gameController.setBlackScore(Integer.toString(black));
                    gameController.setWhiteScore(Integer.toString(white));
                    gameController.setCurrentPlayer(playerCurrentTurn.getColorName());
                    //if this the chosen rectangle we do the event and draw
                    draw(board.getTokens());
                    break;//don't need to check more coordinate
                }
            }
        }
    }

    public Situation checkGameFlowSituation(){

        List<Coordinate> validCoordinates = new ArrayList<Coordinate>();
        gameRules.getLegalCoordinates(
                board, playerCurrentTurn, validCoordinates);
        //clear the board
        initializeBoard();
        if (validCoordinates.isEmpty()) {
            switchPlayer();
            //checking if other player has a move
            List<Coordinate> validCoordinates1 = new ArrayList<Coordinate>();

            gameRules.getLegalCoordinates(
                    board, playerCurrentTurn, validCoordinates1);
            switchPlayer();
            //the player doesn't have any moves
            if (validCoordinates1.isEmpty()) {//no moves for all
                return NoMovesForAll;
            } else { //no moves only for current player
                //System.out.println("no move for one player");
                switchPlayer();
                return NoMove;
            }
        }
        //draw all the possiblesMoves
        drawPossibleMoves(validCoordinates);
        return ThereIsMove;
    }
    //this function get rectangle and add to him mouseClicked event
    public void initializeRectangle(Rectangle rec){
        rec.setStroke(Color.BLACK);
        rec.setOnMouseClicked(event -> {
            double row = Math.ceil(event.getSceneY() / cellSize);
            double col = Math.ceil(event.getSceneX() / cellSize);
            clickEvent(row, col);
        });
    }
    //draw all thw possibles moves in the board
    public void drawPossibleMoves(List<Coordinate> validCoordinates){
        for(int i = 0; i < validCoordinates.size(); i++){
            int row = validCoordinates.get(i).getRow();
            int col = validCoordinates.get(i).getCol();
            Rectangle rec = new Rectangle(cellSize, cellSize,
                    Color.DARKGREEN);
            initializeRectangle(rec);
            //add to the gridPane
            this.add(rec, col, row);
        }
    }
}
//