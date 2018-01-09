package sample;

import boardgame.ReversiGame;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Set;

public class MenuController {

    @FXML
    private Button settingsButton;
    @FXML
    private Button startGame;

    @FXML
    protected void openSettings() {

        Settings settings = new Settings();
        Stage stage = (Stage) settingsButton.getScene().getWindow();
        try {
            settings.start(stage);
        } catch (Exception e) {

        }
    }
    @FXML
    protected void startGame() {
        ReversiGame settings = new ReversiGame();
        Stage stage = (Stage) startGame.getScene().getWindow();
        try {
            settings.start(stage);
        } catch (Exception e) {
        }

    }

}
