//
// Created by shulamit on 10/31/17.
//

package boardgame;
import java.util.List;

public interface GameRules {

     List<Cell> getInitialValues(int dim);

     void getLegalCoordinates(Board b, Player player,
                                     List<Coordinate> validCoordinates);

     TokenValue getOppositeValue(Player player);

     void checkIfCellValid(Token token[][], int row, int col,
                                  int rowDir, int colDir, List<Coordinate> pValidCoordinates,
                                  int dim, TokenValue opposite);

    void checkIfToFlipCell(Coordinate inputCoordinate, int rowDir, int colDir,
                                   List<Coordinate> coordinatesToFlip, int dim, Token[][] tokens,
                                   Player player);
    void checkEightOptions(Coordinate inputCoordinate,
                                  Board board, Player player,
                                  List<Coordinate> coordinatesToFlip);

    void flipTokens(Coordinate inputCoordinate, Board board, Player player);

};


