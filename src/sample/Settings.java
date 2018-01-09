package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class Settings extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

        try {

            GridPane root = (GridPane) load(getClass().getResource("SettingsFXML.fxml"));
            Scene scene = new Scene(root,400,350);
            scene.getStylesheets().add(getClass().getResource("settingsApplication.css").toExternalForm());
            primaryStage.setTitle("Settings");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
