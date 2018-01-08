package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class Menu extends Application {
    //hi this is or

    @FXML
    private MenuButton board_size;
    @FXML
    private MenuButton color_player1;
    @FXML
    private MenuButton color_player2;
    @FXML
    private MenuButton open_player;

    @FXML
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {

            GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("MenuFXML.fxml"));
            Scene scene = new Scene(root, 400, 350);
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Menu");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
