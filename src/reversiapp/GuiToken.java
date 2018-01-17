package reversiapp;


import boardgame.Player;
import boardgame.Token;
import boardgame.TokenValue;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GuiToken extends Token {

    public GuiToken() {
        tv = TokenValue.Empty;
    }

    /**
     * this method draws a token on the board
     * @param i
     * @param j
     * @param gridPane
     * @param radius
     * @param players
     */
    public void draw(int i, int j, GridPane gridPane, int radius, Player[] players) {
        if (tv == TokenValue.Black){
            if(players[0].getValue() == TokenValue.Black) {
                Circle circle = new Circle(i, j, radius, players[0].getColor());
                circle.setStroke(Color.BLACK);
                gridPane.add(circle, j, i);
            } else {
                Circle circle = new Circle(i, j, radius, players[1].getColor());
                circle.setStroke(Color.BLACK);
                gridPane.add(circle, j, i);
            }
        }else if(tv == TokenValue.White){
            if(players[1].getValue() == TokenValue.Black) {
                Circle circle = new Circle(i, j, radius, players[0].getColor());
                circle.setStroke(Color.BLACK);
                gridPane.add(circle, j, i);
            } else {
                Circle circle = new Circle(i, j, radius, players[1].getColor());
                circle.setStroke(Color.BLACK);
                gridPane.add(circle, j, i);
            }

        }
    }

}

