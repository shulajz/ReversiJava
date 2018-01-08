package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
//hi this is or
    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene scene = new Scene(root,400,350);
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("FXML Welcome");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

//        primaryStage.setTitle("FXML Welcome");
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
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //primaryStage.setTitle("Hello World");
//        Label leb = new Label("hello please chose the size of the board (3X3-20x20)");
//       VBox v_box = new VBox();
//        v_box.getChildren().add(leb);
        //primaryStage.setScene(new Scene(root, 500, 275));
        //primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
