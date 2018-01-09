package boardgame;
import boardgame.Board;
import boardgame.Coordinate;
import boardgame.GameRules;
import boardgame.TokenValue;

import java.util.ArrayList;
import java.util.List;

//
// Created by shulamit on 10/31/17.
//
public class GameFlow {


    private Player players[];
    private GameRules m_gameRules;
    private Board m_board;
    private TokenValue currentTurn;
    private BoardGraphic m_boardGraphic;

    public GameFlow(GameRules gameRules, Player players[],
                    Board board, BoardGraphic boardGraphic){
            this.m_gameRules = gameRules;
            this.m_board = board;
            this.m_boardGraphic = boardGraphic;
            this.players = players;
            this.currentTurn = TokenValue.Black;
        }


    /**
     * where the game runs
     */
    public void run() {
        Coordinate inputCoordinate = new Coordinate(0, 0);
        boolean first_move = true;
        boolean needToPrint = true;
        while (true) {
            //if the board full print and break.
            if (m_board.isFullOfTokens()) {
                m_board.draw();
                break;
            }
            if (needToPrint || players[currentTurn.getValue()].isRealPlayer()) {
                m_board.draw();
            }
//           Integer i = new Integer(5);
            //print what the computer played only if Player is AIPlayer
            // and this is not the first move and the computer have moves
//            if (!first_move && (needToPrint || players[currentTurn.getValue()].isRealPlayer())) {
                players[currentTurn.getValue()].printWhatThePlayerPlayed(inputCoordinate, m_boardGraphic);
//            }
            first_move = false;
            List<Coordinate> validCoordinates = new ArrayList<Coordinate>();
            m_gameRules.getLegalCoordinates(m_board, players[currentTurn.getValue()],
                    validCoordinates);
            if (validCoordinates.isEmpty()) {
                //switching to the other player in order to check
                // if he's got any legal moves
                players[currentTurn.getValue()].togglePlayer(currentTurn);
                m_gameRules.getLegalCoordinates(
                        m_board, players[currentTurn.getValue()],
                        validCoordinates);
                if (validCoordinates.isEmpty()) // checking if the other player has any legal moves
                { // there is no options for either of the players
                    m_boardGraphic.printSpecialSituation(Situation.NoMovesForAll);
                    break;
                } else {
                    players[currentTurn.getValue()].togglePlayer(currentTurn);
                    m_boardGraphic.printWhosMove(currentTurn);
                    m_boardGraphic.printSpecialSituation(Situation.Next); //no possible moves for one player
                    needToPrint = false;
                }
            } else {
                players[currentTurn.getValue()].printAfterTheRealPlayerMove(m_boardGraphic, needToPrint);
                players[currentTurn.getValue()].doOneTurn(m_gameRules, m_board,
                        validCoordinates, inputCoordinate, m_boardGraphic,
                        players[currentTurn.getValue()]);
                m_board.updateValue(inputCoordinate, currentTurn);
                m_gameRules.flipTokens(inputCoordinate, m_board, players[currentTurn.getValue()]);
                needToPrint = true;

            }
            players[currentTurn.getValue()].togglePlayer(currentTurn);

        }
        //func of print status
        int black = 0;
        int white = 0;
        m_board.calcResults(black, white);
        m_boardGraphic.drawStatus(black, white);
    }

}
