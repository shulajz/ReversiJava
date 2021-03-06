package reversiapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private Button settingsButton;
    @FXML
    private Button startGame;
    private boolean userDoSettings = false;

    /**
     * this method goes to the setting scene
     */
    @FXML
    protected void openSettings() {

        Settings settings = new Settings();
        Stage stage = (Stage) settingsButton.getScene().getWindow();
        try {
            settings.start(stage);
        } catch (Exception e) {

        }
    }

    /**
     * this method starts the game
     */
    @FXML
    protected void startGame() {
        userDoSettings =  true;
        ReversiGame reversi = new ReversiGame();
        Stage stage = (Stage) startGame.getScene().getWindow();
        try {
            reversi.start(stage);
        } catch (Exception e) {
        }

    }

}
