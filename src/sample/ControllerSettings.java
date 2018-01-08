package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;

public class ControllerSettings {

    @FXML
    private MenuButton board_size;
    @FXML
    private MenuButton color_player1;
    @FXML
    private MenuButton color_player2;
    @FXML
    private MenuButton open_player;
    @FXML
    private Button closeButton;

    @FXML
    protected void ok()  {
        String startPlayer = open_player.getText();
        String colorPlayer1 = color_player1.getText();
        String colorPlayer2 = color_player2.getText();
        String b_size = board_size.getText();

        //System.out.print(startPlayer + "  " + colorPlayer1 + "  " + colorPlayer2 + "  " + b_size);
        //NEED TO WRITE TO FILE
        //return to the menu
        Menu menu= new Menu();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        try {
            menu.start(stage);
        } catch (Exception e) {

        }




//        String password = passwordField.getText();
//        if (username.equals("roi") && password.equals("123456")) {
//            messageText.setText("You are approved!");
//            messageText.setFill(Color.BLUE);
//        }
//        else {
//            messageText.setText("Invalid username or password");
//            messageText.setFill(Color.RED);
//        }
    }
}
