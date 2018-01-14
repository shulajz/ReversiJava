package reversiapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.*;

import static java.lang.System.exit;

public class SettingsController {

    ObservableList<String> colors = FXCollections.
            observableArrayList("black", "white", "blue", "green",
                    "red", "orange", "pink", "brown", "purple", "gray");
    ObservableList<String> sizes = FXCollections.
            observableArrayList("4x4", "5x5", "6x6",
                    "7x7", "8x8", "9x9","10x10",
                    "11x11", "12x12", "13x13", "14x14", "15x15",
                    "16x16", "17x17","18x18","19x19", "20x20"
                    );
    ObservableList<String> players = FXCollections.
            observableArrayList("player1", "player2");

    @FXML
    private ComboBox board_size;
    @FXML
    private ComboBox color_player1;
    @FXML
    private ComboBox color_player2;
    @FXML
    private ComboBox open_player;
    @FXML
    private Button closeButton;
    @FXML
    private javafx.scene.control.Label sameColorMessageLabel;
    @FXML
    private void initialize(){
        board_size.setValue("8x8");
        board_size.setItems(sizes);
       color_player1.setValue("black");
        color_player1.setItems(colors);
        color_player2.setValue("white");
        color_player2.setItems(colors);
        open_player.setValue("player1");
        open_player.setItems(players);
        sameColorMessageLabel.setVisible(false);

    }
    @FXML
    protected void goBackToMenu()  {
        String startPlayer = open_player.getValue().toString();
        String colorPlayer1 = color_player1.getValue().toString();
        String colorPlayer2 = color_player2.getValue().toString();
        String b_size = board_size.getValue().toString();
        if(colorPlayer1.equals(colorPlayer2)) {
            sameColorMessageLabel.setVisible(true);
            sameColorMessageLabel.setOnMouseClicked(event -> {
                sameColorMessageLabel.setVisible(false);
                });
        }

        writeToSettingFile(startPlayer,
                colorPlayer1, colorPlayer2, b_size);

        //return to the menu
        Menu menu = new Menu();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        try {
            menu.start(stage);
        } catch (Exception e) {

        }
    }
    private void writeToSettingFile(String startPlayer,
                                    String colorPlayer1, String colorPlayer2, String b_size){
        Writer writer = createNewFile();
        String settings = "startPlayer: " + startPlayer +
                "\ncolorPlayer1: " + colorPlayer1 +
                "\ncolorPlayer2: " + colorPlayer2 +
                "\nsizeBoard: " + b_size;
        try {
            writer.write(settings);
            writer.close();
        }catch (IOException e){
            System.out.println("write to file failed");
            exit(1);
        }


    }
    public Writer createNewFile() {

        BufferedWriter output = null;
        try {
            File file = new File("settingsFile.txt");
            output = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}