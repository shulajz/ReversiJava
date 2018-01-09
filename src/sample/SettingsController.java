package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;

import static java.lang.System.exit;

public class SettingsController {

    ObservableList<String> colors = FXCollections.
            observableArrayList("black", "white", "blue", "yellow", "red");
    ObservableList<String> sizes = FXCollections.
            observableArrayList("4x4", "5x5", "6x6", "7x7", "8x8");
    ObservableList<String> players = FXCollections.
            observableArrayList("player 1", "player 2");

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
    private void initialize(){
        board_size.setValue("8x8");
        board_size.setItems(sizes);
       color_player1.setValue("black");
        color_player1.setItems(colors);
        color_player2.setValue("white");
        color_player2.setItems(colors);
        open_player.setValue("player 1");
        open_player.setItems(players);
    }
    @FXML
    protected void ok()  {
        String startPlayer = open_player.getValue().toString();
        String colorPlayer1 = color_player1.getValue().toString();
        String colorPlayer2 = color_player2.getValue().toString();
        String b_size = board_size.getValue().toString();

        System.out.println("start player: " + startPlayer +
                "  color Player 1: " + colorPlayer1 +
                "  color Player 2: " + colorPlayer2 +
                "  size board: " + b_size);
        writeToSettingFile(startPlayer,
                colorPlayer1, colorPlayer2, b_size);

        //return to the menu
        Menu menu= new Menu();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        try {
            menu.start(stage);
        } catch (Exception e) {

        }
    }
    private void writeToSettingFile(String startPlayer,
                                    String colorPlayer1, String colorPlayer2, String b_size){
        Writer writer = createNewFile();
        String settings = "start player: " + startPlayer +
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
    private Writer createNewFile(){
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("settingsFile.txt"), "utf-8"));

        } catch (IOException ex) {
            System.out.println("create file failed");
            exit(1);
        }
        return writer;
    }
}

