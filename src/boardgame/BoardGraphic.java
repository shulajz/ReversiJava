//
// Created by shulamit on 17/11/17.
//
package boardgame;
import boardgame.Coordinate;
import boardgame.Token;
import boardgame.TokenValue;

import java.util.List;


public interface BoardGraphic {
    void draw(Token[][] tokenArr);
    void printSpecialSituation(Situation message);
    void printWhosMove(TokenValue tv);
    /**
     * print the valid moves of the player.
     * @param validCoordinates
     */
    void printMoves(List<Coordinate> validCoordinates);
    /**
     * Print who wins the game.
     * @param black
     * @param white
     */
    void drawStatus(int black, int white);
    void printThePlayersChoice(TokenValue tv, Coordinate coordinate);
    void printAfterRealPlayer();
    void receivePlayersAnswer(Coordinate input);
}