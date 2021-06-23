package minesweeper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class sceneController {

   @FXML
   private Stage stage;
   private Scene scene;
   private Parent root;

    public void goToLevelSetter(KeyEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("levelSetter.fxml"));    //jeśli przegramy gierkę to może będziemy chcieli zagrać jeszcze raz
        root = loader.load();
        scene = new Scene(root);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setWidth(1000);
        stage.setHeight(500);
        stage.setResizable(false);
        stage.show();
    }

    public void goToGame(ActionEvent event, String level) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameBoard.fxml"));       //wczytujemy *pole* rozgrywki
        root = loader.load();
        scene = new Scene(root);                                                                //teraz jest sceną
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();                       // pobieramy źródło z event(wybierator_trudności) i zamieniamy to na Node
        stage.setScene(scene);                                                                  //pobiera dodatkowo scenę, i okienko apki
        stage.setResizable(false);

        GameBoard gameBoard = loader.getController();                                           //tworzymy nowy obiekt klasy w której jest cala logika gry
        gameBoard.stageSize = 1000;
        stage.setWidth(gameBoard.stageSize + 50);                                               // rozmiary
        stage.setHeight(gameBoard.stageSize + 50);

            switch (level){
                case "easy":
                    gameBoard.howManyInRow = 9;                                                 //wybierator_trudnosci i jego cechy charakterystyczne
                    gameBoard.setBombs(15);
                    break;
                case "medium":
                    gameBoard.howManyInRow = 15;
                    gameBoard.setBombs(30);
                    break;
                case "hard":
                    gameBoard.howManyInRow = 21;
                    gameBoard.setBombs(50);
                    break;
            }

        gameBoard.difficultyLevel = level;
        gameBoard.scene = scene;
        gameBoard.squareSize = (gameBoard.stageSize - 100)/ gameBoard.howManyInRow;
        gameBoard.bombImage = new Image("file:src/resources/gifBomb.gif");                  //dodajemy obraz bomby i flagi
        gameBoard.flagImage = new Image("file:src/resources/flag.png");
        gameBoard.createButtons();                                                              //inicjalizacja
        stage.show();
    }
}
