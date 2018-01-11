//
// Created by shulamit on 10/31/17.
//
package boardgame;


public class Cell {
    private Coordinate coordinate;
    private TokenValue tv;

    public Cell(Coordinate coordinate, TokenValue tv){
        this.coordinate = coordinate;
        this.tv = tv;
    }
    public Coordinate getCoordinate() {
        return coordinate;
    }

    public TokenValue getTokenValue(){
        return tv;
    }
};





