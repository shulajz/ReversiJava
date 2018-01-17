package reversiapp;

import boardgame.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

import static reversiapp.Situation.NoMove;
import static reversiapp.Situation.NoMovesForAll;
import static reversiapp.Situation.ThereIsMove;

public class GameFlow {
    private Player playerCurrentTurn;
    private Board board;
    private Player[] players;
    private GameRules gameRules;
    private ReversiGameController gameController;

    /**
     * GameFlow constructor.
     * @param board
     * @param players
     * @param gameRules
     * @param controller
     */
    public GameFlow(Board board, Player players[],
                    GameRules gameRules, ReversiGameController controller){

        this.gameController = controller;
        this.gameRules = gameRules;
        this.players = players;
        this.board = board;
        TokenValue blackTv = TokenValue.Black;
        this.playerCurrentTurn = players[blackTv.getValue()];

    }

    /**
     * this function in every clicked event we
     * check if the click is valid coordinate
     * if so we change it, depend in the game rules, and depend in
     * the current player.
     * @param row
     * @param col
     */
    public boolean clickEvent(double row, double col){
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
                    return true;//don't need to check more coordinate
                }
            }
        }
        return false;
    }

    /**
     * switch between the players for the current turn.
     */
    public void switchPlayer() {
        if (playerCurrentTurn.getValue() == players[0].getValue()) {
            playerCurrentTurn = players[1];
        } else if (playerCurrentTurn.getValue() == players[1].getValue()) {
            playerCurrentTurn = players[0];

        }
    }

    /**
     * check what moves are available
     * @return the situation.
     */
    public Situation checkGameFlowSituation(List<Coordinate> validCoordinates){
        gameRules.getLegalCoordinates(
                board, playerCurrentTurn, validCoordinates);
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
                switchPlayer();
                return NoMove;
            }
        }
        return ThereIsMove;
    }

    /**
     * this function checks if we're in special situations
     * in the game like no move for one player or no move for all
     * if so update the game manager about this that it will handle it
     * @param situation
     */
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

}
