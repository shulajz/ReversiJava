package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class Settings extends Application {
    private static Stage m_primaryStage;
//hi this is or
    @Override

    public void start(Stage primaryStage) throws Exception{
    m_primaryStage = primaryStage;
        try {
//            System.out.print("hi");

            GridPane root = (GridPane) load(getClass().getResource("SettingsFXML.fxml"));
            Scene scene = new Scene(root,400,350);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            m_primaryStage.setTitle("Settings");
            m_primaryStage.setScene(scene);
            m_primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }


    }
    public void showSettings() throws IOException {

    }
    public static void main(String[] args) {
        launch(args);
    }
}
