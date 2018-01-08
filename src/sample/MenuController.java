package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;
import java.util.Set;

public class MenuController {

    @FXML
    private MenuButton board_size;
    @FXML
    private MenuButton color_player1;
    @FXML
    private MenuButton color_player2;
    @FXML
    private MenuButton open_player;

    @FXML
    protected void openSettings() {
//      ("/sample/Settings.fxml");
        Settings settings = new Settings();
        try {
            settings.showSettings();
        } catch (IOException e) {

        }
    }
    @FXML
    protected void startGame() {


    }
    @FXML
    protected void setStage() {


    }

}
