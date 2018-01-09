//
// Created by shulamit on 10/30/17.
//
package boardgame;
public abstract class Token {
    protected TokenValue tv;

    public void toggle() {
        if (tv == TokenValue.White) {
            tv = TokenValue.Black;
        } else {
            tv = TokenValue.White;
        }
    }

    abstract public void draw();
//    {

//    }
    void setValue(TokenValue tv1) {
        tv = tv1;
    }

    public TokenValue getOppositeValue(TokenValue tv) {
        if (tv == TokenValue.Black) {
            return TokenValue.White;
        } else if (tv == TokenValue.White) {
            return TokenValue.Black;
        } else {
            return TokenValue.Empty;
        }
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