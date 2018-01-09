package boardgame;
import java.util.ArrayList;
import java.util.List;

//
// Created by shulamit on 10/31/17.
//
public class ClassicRules implements GameRules {

    /**
     *
     * @param dim the dim of the board.
     * @return the initial values for the start of the game
     */
    public List<Cell> getInitialValues(int dim)
    {
        List<Cell> cells = new ArrayList<Cell>();
        Coordinate coordinate = new Coordinate(dim/2, dim/2);
        cells.add(new Cell(coordinate, TokenValue.White));


        coordinate = new Coordinate( dim / 2 + 1,  dim / 2 + 1);
        cells.add(new Cell(coordinate, TokenValue.White));

        coordinate = new Coordinate( dim / 2 + 1,  dim / 2);
        cells.add(new Cell(coordinate, TokenValue.Black));

        coordinate = new Coordinate( dim / 2,  dim / 2 + 1);
        cells.add(new Cell(coordinate, TokenValue.Black));

        return cells;
    }

    /**
     * @param player the player which is playing
     * @return a token value
     */
    public TokenValue getOppositeValue(Player player)

    {
        if (player.getValue() == TokenValue.Black) {
            return TokenValue.White;
        } else if (player.getValue() == TokenValue.White) {
            return TokenValue.Black;
        }
        return null;
    }

    /**
     * gets the legal coordinates for the player
     *
     * @param b the board
     * @param player the player playing
     */
    public void getLegalCoordinates
    (Board b, Player player,
     List<Coordinate> validCoordinates) {

        TokenValue oppositeValue;
        oppositeValue = getOppositeValue(player);

        Token tokens[][] = b.getTokens();
        int dim = b.getDimensions();
//    int index = 0;
        for (int i = 1; i < dim; i++) {
            for (int j = 1; j < dim; j++) {
                if (tokens[i][j].isEmpty()) {
                    checkIfCellValid(tokens, i, j,
                            -1, -1,
                            validCoordinates, dim,
                            oppositeValue);

                    checkIfCellValid(tokens, i, j,
                            0, -1,
                            validCoordinates, dim,
                            oppositeValue);

                    checkIfCellValid(tokens, i, j,
                            1, -1,
                            validCoordinates, dim,
                            oppositeValue);

                    checkIfCellValid(tokens, i, j,
                            -1, 0,
                            validCoordinates, dim,
                            oppositeValue);

                    checkIfCellValid(tokens, i, j,
                            1, 0,
                            validCoordinates, dim,
                            oppositeValue);

                    checkIfCellValid(tokens, i, j,
                            -1, 1,
                            validCoordinates, dim,
                            oppositeValue);

                    checkIfCellValid(tokens, i, j,
                            0, 1,
                            validCoordinates, dim,
                            oppositeValue);

                    checkIfCellValid(tokens, i, j,
                            1, 1,
                            validCoordinates, dim,
                            oppositeValue);
                }
            }
        }
    }

    public void checkEightOptions(Coordinate inputCoordinate,
                                  Board board, Player player,
                                  List<Coordinate> coordinatesToFlip) {
        checkIfToFlipCell(
                inputCoordinate, -1, -1, coordinatesToFlip,
                board.getDimensions(), board.getTokens(), player);

        checkIfToFlipCell(
                inputCoordinate, 0, -1, coordinatesToFlip,
                board.getDimensions(), board.getTokens(), player);

        checkIfToFlipCell(
                inputCoordinate, 1, -1, coordinatesToFlip,
                board.getDimensions(), board.getTokens(), player);

        checkIfToFlipCell(
                inputCoordinate, -1, 0, coordinatesToFlip,
                board.getDimensions(), board.getTokens(), player);

        checkIfToFlipCell(
                inputCoordinate, 1, 0, coordinatesToFlip,
                board.getDimensions(), board.getTokens(), player);

        checkIfToFlipCell(
                inputCoordinate, -1, 1, coordinatesToFlip,
                board.getDimensions(), board.getTokens(), player);

        checkIfToFlipCell(
                inputCoordinate, 0, 1, coordinatesToFlip,
                board.getDimensions(), board.getTokens(), player);

        checkIfToFlipCell(inputCoordinate, 1, 1, coordinatesToFlip,
                board.getDimensions(), board.getTokens(), player);

    }

    /**
     * flips the tokens of the player playing
     */
    public void flipTokens(Coordinate inputCoordinate, Board board, Player player) {
        List<Coordinate> coordinatesToFlip = new ArrayList<Coordinate>();
        checkEightOptions(inputCoordinate, board, player, coordinatesToFlip);

        int i = 0;
        while (i != coordinatesToFlip.size()) {
            board.getTokens()[coordinatesToFlip.get(i).getRow()]
                    [coordinatesToFlip.get(i).getRow()].toggle();
            i++;
        }
    }

    /**
     * A method we call 8 times in order to check if to flip the cell
     *
     * @param rowDir - the direction of the row going
     * @param colDir - the direction of the col going
     */
    public void checkIfToFlipCell(Coordinate inputCoordinate, int rowDir, int colDir,
                                  List<Coordinate> coordinatesToFlip,
                                  int dim, Token[][] tokens,
                                  Player player){
//    public void checkIfToFlipCell(Coordinate inputCoordinate, int rowDir, int colDir,
//                      vector<Coordinate> &coordinatesToFlip, int dim,
//                      Token**tokens, Player *player) {
        List<Coordinate> tempCoordinates = new ArrayList<Coordinate>();
        boolean isValid = false;
        TokenValue oppositeValue = getOppositeValue(player);

        int paramRow = inputCoordinate.row + rowDir;
        int paramCol = inputCoordinate.col + colDir;

        do {
            if (paramRow >= 1 && paramRow <= dim - 1 && paramCol >= 1 && paramCol <= dim - 1) //if its in a valid place
            {
                if (tokens[paramRow][paramCol].getValue() != oppositeValue && isValid) {// we found the cell of the same kind + after we found at least one from the opposite

                    int i = 0;
                    //tempCoordinate[i].col != 0
                    while (i != tempCoordinates.size()) {
                        Coordinate coordinate =
                                new Coordinate(
                                        (tempCoordinates.get(i).getRow()),
                                        tempCoordinates.get(i).getCol());
                        coordinatesToFlip.add(coordinate);
                        i++;
                    }
                    return;
                } else if (tokens[paramRow][paramCol].getValue() == oppositeValue) {
                    Coordinate coordinate = new Coordinate(paramRow, paramCol);
                    tempCoordinates.add(coordinate);
                    paramRow += rowDir;
                    paramCol += colDir;
                    isValid = true; // we got a token in the array
                } else if (tokens[paramRow][paramCol].getValue() != oppositeValue) {
                    return;
                }
            } else {
                return; // cell not valid
            }
            if (!(paramRow >= 1 && paramRow <= dim - 1 && paramCol >= 1 && paramCol <= dim - 1)) {
                //if its not in a valid place
                return;
            }
        } while (!tokens[paramRow][paramCol].isEmpty());
    }

    /**
     * checks if a cell is valid for the valid coordinates
     *
     * @param rowDir - the direction the row is going
     * @param colDir - the direction the col is going
     */
    public void checkIfCellValid(Token token[][], int row, int col,
                                 int rowDir, int colDir, List<Coordinate> pValidCoordinates,
                                 int dim, TokenValue oppositeValue){
        boolean alreadyExist = false;
        boolean isValid = false;
        int paramRow = row + rowDir;
        int paramCol = col + colDir;
        if (!(paramRow >= 1 && paramRow <= dim - 1 && paramCol >= 1 && paramCol <= dim - 1)) {
            //if its not in a valid place
            return;
        }
        while (token[paramRow][paramCol].getValue() == oppositeValue) {
            if (paramRow >= 1 && paramRow <= dim - 1 && paramCol >= 1 && paramCol <= dim - 1) {//if its in a valid place
                paramRow += rowDir;
                paramCol += colDir;
                isValid = true;
            } else {
                return; // cell not valid
            }

            if (!(paramRow >= 1 && paramRow <= dim - 1 && paramCol >= 1 && paramCol <= dim - 1)) {
                return;
            } else if (token[paramRow][paramCol].isEmpty()) {
                return;
            }

            if (token[paramRow][paramCol].getValue() != oppositeValue && isValid)
            // we found an optional cell
            {
                int x = 0;
                while (x != pValidCoordinates.size()) {
                    // checking if it exists already
                    if (pValidCoordinates.get(x).getRow() == row
                            && pValidCoordinates.get(x).getCol() == col) {
                        alreadyExist = true;
                        break;
                    }
                    x++;
                }
                if (!alreadyExist) {
                    Coordinate coordinate = new Coordinate(row, col);
                    pValidCoordinates.add(coordinate);
                }
            }
        }
    }
}