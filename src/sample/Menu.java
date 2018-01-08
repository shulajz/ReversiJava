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
    private static Stage m_primStage;

    @FXML
    @Override
    public void start(Stage primaryStage) throws Exception {
        m_primStage = primaryStage;
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
//
//        primaryStage.setTitle("Setting");
//        Label lbl1 = new Label("please chose the board size: 3x3-20x20");
//        Label lbl2 = new Label("hi welcome!!");
//        lbl2.setFont(new Font("Arial", 30));
//        lbl1.setFont(new Font("Arial", 30));
//        Button btn = new Button("3x3");
//        VBox root = new VBox();
//        root.getChildren().add(lbl2);
//        root.getChildren().add(lbl1);
//        root.getChildren().add(btn);
//        btn.setOnAction(event -> {
//            lbl1.setText("Button clicked!");
//            String s =btn.getText();
//            Label chosen = new Label("you chose " + s);
//            chosen.setFont(new Font("Arial", 30));
//            root.getChildren().add(chosen);
//        });
//
//        primaryStage.setScene(new Scene(root,400,350));
//        primaryStage.show();
        //Parent root = FXMLLoader.load(getClass().getResource("SettingsFXML.fxml"));
        //primaryStage.setTitle("Hello World");
//        Label leb = new Label("hello please chose the size of the board (3X3-20x20)");
//       VBox v_box = new VBox();
//        v_box.getChildren().add(leb);
        //primaryStage.setScene(new Scene(root, 500, 275));
        //primaryStage.show();


    }
    @FXML
    protected void openSettings () throws Exception {
////      ("/sample/Settings.fxml");
            Settings settings = new Settings();
            try {
                settings.start(m_primStage);
            } catch (IOException e) {
                System.out.print("k");
            }
//        try {
////            System.out.print("hi");
//
//            GridPane root = (GridPane) load(getClass().getResource("SettingsFXML.fxml"));
//            Scene scene = new Scene(root,400,350);
//            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//            m_primStage.setTitle("Settings");
//            m_primStage.setScene(scene);
//            m_primStage.show();
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//            }
        }
    @FXML
    protected void startGame () {


     }




    public static void main(String[] args) {
        launch(args);
    }
}
