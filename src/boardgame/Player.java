//
// Created by shulamit on 07/11/17.
//

package boardgame;
import boardgame.Board;
import boardgame.Coordinate;
import boardgame.GameRules;

import boardgame.TokenValue;
import javafx.scene.paint.Color;

import java.util.List;


public abstract class Player {

    protected TokenValue tv;
    protected Color color;
    public TokenValue getValue() {
        return tv;
    }
    public abstract boolean isRealPlayer();
    public abstract void doOneTurn(GameRules gameRules, Board board,
                                   List<Coordinate> coordinates,
                                   Coordinate input, BoardGraphic boardGraphic, Player player);
    /**
     * checks if its the white turn
     */
    public boolean isWhitePlayer() {
        if (tv == TokenValue.White) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the value of the current player.
     */
    public TokenValue getIdentity() {
        return tv;
    }

    /**
     * switches between the players
     */
    public void togglePlayer(TokenValue tokenValue) {
        if (isWhitePlayer()) {
            tokenValue = TokenValue.Black;
        } else {
            tokenValue = TokenValue.White;
        }
    }

    public void printWhatThePlayerPlayed(Coordinate coordinate, BoardGraphic boardGraphic){

    }



    public void printAfterTheRealPlayerMove(BoardGraphic boardGraphic, boolean need_to_print){

    }
    public void setTokenValue(TokenValue tv1) {
        tv = tv1;
    }

    public Color getColor() {
        return color;
    }
}