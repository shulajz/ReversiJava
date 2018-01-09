package boardgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ReversiGame extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            HBox root = (HBox) FXMLLoader.load(getClass().getResource("ReversiGameFXML.fxml"));
            Scene scene = new Scene(root,520,400);
//            scene.getStylesheets().add(getClass().getResource("applicationReversiGameController.css").toExternalForm());
            primaryStage.setTitle("Othello game");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}