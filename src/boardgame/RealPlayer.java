//
// Created by shulamit on 10/31/17.
//

package boardgame;
import javafx.scene.paint.Color;

import java.util.List;

public class RealPlayer extends Player{

    public RealPlayer(TokenValue tv, Color color, String colorName) {
        this.tv = tv;
        this.color = color;
        this.colorName = colorName;

    }

}