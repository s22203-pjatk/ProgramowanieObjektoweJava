package minesweeper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override                                                                                   // stawienie tak jakby *sceny* czyli program
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("levelSetter.fxml"));              // wczytujemy wybierator_trudności
        primaryStage.setTitle("minesweeper");                                                   // nazwanie tej *sceny*
        primaryStage.setScene(new Scene(root, 1000, 500));                                      // rozmiar okienka
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
