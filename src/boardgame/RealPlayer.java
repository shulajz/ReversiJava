//
// Created by shulamit on 10/31/17.
//

package boardgame;
import java.util.List;

public class RealPlayer extends Player{
    private TokenValue tv;

    public RealPlayer(TokenValue tv) {
        this.tv = tv;
    }


    /**
     * the turn of one of the players.
     */
    public void doOneTurn(GameRules gameRules, Board board,
                                List<Coordinate> coordinates,
                                Coordinate input, BoardGraphic boardGraphic, Player player) {
        boolean inputValid = false;
        while (!inputValid) {
            boardGraphic.printWhosMove(player.getIdentity());
            boardGraphic.printMoves(coordinates);
            boardGraphic.printSpecialSituation(Situation.AskForRowAndCol);
            boardGraphic.receivePlayersAnswer(input);


            for (int i = 0; i < coordinates.size(); i++) {
                //checks if the input is one of the legal coordinates
                if(input.getRow() == coordinates.get(i).getRow()
                        && input.getCol() == coordinates.get(i).getCol()){
                    inputValid = true;
                    break;
                }
            }
            if (inputValid) {
                break;
            } else {
                boardGraphic.printSpecialSituation(Situation.IllegelMove);
            }
        }
    }

    public boolean isRealPlayer(){
        return true;
    }

}