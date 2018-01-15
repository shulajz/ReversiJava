package reversiapp;

import boardgame.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static reversiapp.Situation.NoMove;
import static reversiapp.Situation.NoMovesForAll;
import static reversiapp.Situation.ThereIsMove;


public class GuiBoard extends GridPane {
    private int cellSize;
    private Board board;
    private Player[] players;
    private GameRules gameRules;
    private Player playerCurrentTurn;
    private ReversiGameController gameController;
    private GameFlow gameFlow;
    public GuiBoard(Board board, Player players[],
                    GameRules gameRules, ReversiGameController controller, GameFlow gameflow) {
        this.gameController = controller;
        this.gameRules = gameRules;
        this.players = players;
        this.board = board;
        this.gameFlow = gameflow;
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
    //this function receives a rectangle and adds to him mouseClicked event
    public void initializeRectangle(Rectangle rec){
        rec.setStroke(Color.BLACK);
        rec.setOnMouseClicked(event -> {
            double row = Math.ceil(event.getSceneY() / cellSize);
            double col = Math.ceil(event.getSceneX() / cellSize);
            boolean isValid = gameFlow.clickEvent(row, col);
            if(isValid){
                draw(board.getTokens());
            }

        });
    }

    /**
     * this method draws the tokens of the game
     * @param tokens
     */
    public void draw(Token[][] tokens) {
        //before every draw of the current board check if we're in a
        //special situations in the game like no move for one player
        // or no move for all.
        List<Coordinate> validCoordinates = new ArrayList<Coordinate>();
        Situation situation = gameFlow.checkGameFlowSituation(validCoordinates);
        drawPossibleMoves(validCoordinates);
        for (int i = 1; i < board.getDimensions(); i++) {
            for (int j = 1; j < board.getDimensions(); j++) {
                tokens[i][j].draw(i, j, this, cellSize/2, players);
            }
        }
        handleSpecialSituationsInGame(situation);
    }
    //this function checks if we're in special situations
    // in the game like no move for one player or no move for all
    // if so update the game manager about this that it will handle it
    public void handleSpecialSituationsInGame(Situation situation){
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
    //draw all thw possibles moves in the board
    public void drawPossibleMoves(List<Coordinate> validCoordinates){
        for(int i = 0; i < validCoordinates.size(); i++){
            int row = validCoordinates.get(i).getRow();
            int col = validCoordinates.get(i).getCol();
            Rectangle rec = new Rectangle(cellSize, cellSize,
                    Color.DARKGREEN);
            initializeRectangle(rec);
            //add to the gridPane
            add(rec, col, row);
        }
    }

}
//