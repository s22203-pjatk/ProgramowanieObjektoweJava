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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("levelSetter.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameBoard.fxml"));
        root = loader.load();
        scene = new Scene(root);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        GameBoard gameBoard = loader.getController();

        gameBoard.stageSize = 1000;
        stage.setWidth(gameBoard.stageSize + 50);
        stage.setHeight(gameBoard.stageSize + 50);

            switch (level){
                case "easy":
                    gameBoard.howManyInRow = 9;
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
        gameBoard.bombImage = new Image("file:src/resources/gifBomb.gif");
        gameBoard.flagImage = new Image("file:src/resources/flag.png");
        gameBoard.createButtons();
        stage.show();
    }
}
