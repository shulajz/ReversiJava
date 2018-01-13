//
// Created by shulamit on 07/11/17.
//

package boardgame;

import javafx.scene.paint.Color;



public abstract class Player {

    protected TokenValue tv;
    protected Color color;
    protected String colorName;
    public TokenValue getValue() {
        return tv;
    }

    public Color getColor() {
        return color;
    }

    public String getColorName() {
        return colorName;
    }
}