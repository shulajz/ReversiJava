package reversiapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static javafx.fxml.FXMLLoader.load;

public class Settings extends Application {
    final static int SCENE_SIZE = 400;
    @Override
    public void start(Stage primaryStage) throws Exception{

        try {

            GridPane root = (GridPane) load(getClass().getResource("SettingsFXML.fxml"));
            Scene scene = new Scene(root,SCENE_SIZE,SCENE_SIZE - 50);
            scene.getStylesheets().add(getClass().getResource("settingsApplication.css").toExternalForm());
            primaryStage.setTitle("Settings");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
//
}
