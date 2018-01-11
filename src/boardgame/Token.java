//
// Created by shulamit on 10/30/17.
//
package boardgame;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public abstract class Token  {
    protected TokenValue tv;

    public void toggle() {
        if (tv == TokenValue.White) {
            tv = TokenValue.Black;
        } else {
            tv = TokenValue.White;
        }
    }

    abstract public void draw(int i, int j, GridPane gridPane, int radius,Player[] players);
//    {

//    }
    void setValue(TokenValue tv1) {
        tv = tv1;
    }

    public boolean isWhite() {
        if (tv == TokenValue.White) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty()
    {
        if (tv == TokenValue.Empty) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isBlack() {
        if (tv == TokenValue.Black) {
            return true;
        } else {
            return false;
        }
    }

    public TokenValue getValue() {
        return tv;
    }
}