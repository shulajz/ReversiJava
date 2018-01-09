package boardgame;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GuiToken extends Token{
    public void draw() {
        drawTokenValue(tv);
    }
    public void drawTokenValue(TokenValue tv){
        if (tv == TokenValue.Black){
            Circle circle = new Circle();
            circle.setStroke(Color.BLACK);
            circle.setFill(Color.BLACK);
        }else if(tv == TokenValue.White){
            Circle circle = new Circle();
            circle.setStroke(Color.BLACK);
            circle.setFill(Color.WHITE);
        }
    }

}

