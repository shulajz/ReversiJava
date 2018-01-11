package boardgame;


import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GuiToken extends Token{

    public GuiToken() {
        tv = TokenValue.Empty;
    }
    public void draw(int i, int j, GridPane gridPane, int radius, Player[] players) {
        drawTokenValue(tv, i, j, gridPane, radius, players);
    }
    public void drawTokenValue(TokenValue tv, int i, int j, GridPane gridPane,
                               int radius, Player[] players){
        if (tv == TokenValue.Black){
            Circle circle = new Circle(i, j, radius, players[0].getColor());
            circle.setStroke(Color.BLACK);

            gridPane.add(circle, j, i);

        }else if(tv == TokenValue.White){
            Circle circle = new Circle(i, j, radius, players[1].getColor());
            circle.setStroke(Color.BLACK);
            gridPane.add(circle, j, i);

        }
    }

}

