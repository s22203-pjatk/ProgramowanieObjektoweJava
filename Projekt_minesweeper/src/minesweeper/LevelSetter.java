package minesweeper;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import java.io.IOException;

public class LevelSetter {                                                                      // przesyłamy konkretne jakby kliknięcie(easy,medium,hard)
    public void setDifficulty(ActionEvent event) throws IOException {                           //  zachodzi event(to co kliknąłem) i zamieniam to z źródłem tego(kliku)
       String level = ((Button)event.getSource()).getId();                                           // na button, finalnie biore z tego ID
       minesweeper.sceneController sceneController = new minesweeper.sceneController();
       sceneController.goToGame(event, level);                                                  // sedno minesweepera przesyłaja zdażenie(klik)
    }                                                                                                // i zdażenie ale w formie wyżej, z którego poźniej nadajemy sens wartościom
}
