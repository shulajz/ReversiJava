/*
 * Board.cpp
 *
 *  Created on: Oct 29, 2017
 *      Author: Shulamit
 */
package boardgame;

import reversiapp.GuiTokenFactory;

import java.util.List;

import static boardgame.TokenValue.Black;
import static boardgame.TokenValue.Empty;
import static boardgame.TokenValue.White;

public class Board {
    private Token[][] boardArr;
    private int dimensions;
    private TokenFactory pTokenFactory;
/**
 *
 * @param dimensions - the dimentions of the board.
 * @param initialCells - the cells we need to init in the beginning.
 */
    public Board(int dimensions, List<Cell> initialCells)
    {

        pTokenFactory = new GuiTokenFactory();
        this.dimensions = dimensions;

        pTokenFactory = pTokenFactory;
        boardArr = new Token[dimensions][dimensions];

        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                boardArr[i][j] = pTokenFactory.Create();
            }
        }
        for (int i = 0; i < 4; i++) {
            //set values to the initial cells
            boardArr[initialCells.get(i).getCoordinate().getRow()]
                    [initialCells.get(i).getCoordinate().getCol()].
                    setValue(initialCells.get(i).getTokenValue());
        }
    }


    /**
     * @return the dimenstions of the board
     */
    public int getDimensions()

    {
        return this.dimensions;
    }


/**
 * @return the array of tokens.
 */
    public Token[][] getTokens()
    {
        return this.boardArr;
    }

    /**
     * @param coordinate - the coordinate to update
     * @param tv the tokenValue we're updating
     */
   public void updateValue(Coordinate coordinate, TokenValue tv) {
        boardArr[coordinate.row][coordinate.col].setValue(tv);
    }

    /**
     * calculates who wins the game.
     */
    public int[] calcResults() {
        int sum[] ={2,2};
        int black = 0, white = 0;
        for (int i = 1; i < dimensions; i++) {
            for (int j = 1; j < dimensions; j++) {
                if (boardArr[i][j].getValue() == Black) {
                    black++;
                } else if (boardArr[i][j].getValue() == White) {
                    white++;
                } else if ((boardArr[i][j].getValue() == Empty)) {
                    continue;
                }
            }
        }
        sum[0] = black;
        sum[1] = white;
        return sum;
    }

}
