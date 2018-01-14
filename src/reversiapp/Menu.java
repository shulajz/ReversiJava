package reversiapp;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static javafx.fxml.FXMLLoader.load;

public class Menu extends Application {
    final static int SCENE_SIZE = 500;

    @FXML
    private MenuButton board_size;
    @FXML
    private MenuButton color_player1;
    @FXML
    private MenuButton color_player2;
    @FXML
    private MenuButton open_player;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("MenuFXML.fxml"));
            Scene scene = new Scene(root, SCENE_SIZE, SCENE_SIZE/2);
            scene.getStylesheets().add(getClass().getResource("menuApplication.css").toExternalForm());
            primaryStage.setTitle("Menu");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try {
            File file = new File("settingsFile.txt");
            Files.deleteIfExists(file.toPath());
        }catch(IOException e){

        }
        launch(args);
    }
}
