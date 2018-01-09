package boardgame;


import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GuiToken extends Token{
    private GridPane grid;
    private int row;
    private int col;
//    private ImageView iv;

    public GuiToken() {
        tv = TokenValue.Empty;

//        this.grid = grid;
//        this.row = row;
//        this.col = col;
        // Load the player's image
//        iv = new
//                ImageView(getClass().getResource("minion.png").toExternalForm());
    }
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
//    public void toggle() {
//        row--; // need to check that player doesn't hit a wall
//        redraw();
//    }
//    public void moveDown() {
//        row++;
//        redraw();
//    }
//    public void moveLeft() {
//        col--;
//        redraw();
//    }
//    public void moveRight() {
//        col++;
//        redraw();
//    }
//    private void redraw() {
//        grid.getChildren().remove(iv);
//        grid.add(iv, col, row);
//    }

}

