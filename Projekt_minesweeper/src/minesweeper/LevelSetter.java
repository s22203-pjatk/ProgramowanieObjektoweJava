package minesweeper;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import java.io.IOException;

public class LevelSetter {

    public void setDifficulty(ActionEvent event) throws IOException {
       String level = ((Button)event.getSource()).getId();
       minesweeper.sceneController sceneController = new minesweeper.sceneController();
       sceneController.goToGame(event, level);
    }
}
