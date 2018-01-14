package reversiapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ReversiGame extends Application {
    final static int SCENE_SIZE_HEIGHT = 550;
    final static int SCENE_SIZE_WIDTH = 880;

    @Override
    public void start(Stage primaryStage) {
        try {
            HBox root = (HBox) FXMLLoader.load(getClass().getResource(
                    "ReversiGameFXML.fxml"));
            Scene scene = new Scene(root,SCENE_SIZE_WIDTH, SCENE_SIZE_HEIGHT);
            scene.getStylesheets().add(getClass().getResource(
                    "applicationReversiGameController.css").toExternalForm());
            primaryStage.setTitle("Othello game");
            primaryStage.setScene(scene);
//            primaryStage.setMinHeight(SCENE_SIZE_HEIGHT);
//            primaryStage.setMinWidth(SCENE_SIZE_WIDTH);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}