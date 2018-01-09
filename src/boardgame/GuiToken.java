package boardgame;


import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GuiToken extends Token{
    public void draw(int i, int j, GridPane gridPane, int radius) {
        drawTokenValue(tv, i, j, gridPane, radius);
    }
    public void drawTokenValue(TokenValue tv, int i, int j, GridPane gridPane, int radius){
        if (tv == TokenValue.Black){
            Circle circle = new Circle(i, j, radius, Color.BLACK);
            circle.setStroke(Color.BLACK);
//            circle.setFill(Color.BLACK);
            gridPane.add(circle, j, i);

        }else if(tv == TokenValue.White){
            Circle circle = new Circle(i, j, radius, Color.WHITE);
            circle.setStroke(Color.BLACK);
//            circle.setFill(Color.WHITE);
            gridPane.add(circle, j, i);

        }
    }

}

