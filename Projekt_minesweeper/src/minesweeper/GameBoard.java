package minesweeper;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.*;

public class GameBoard {
    public AnchorPane gameBoard;
    public String difficultyLevel;
    public Scene scene;
    public int stageSize;
    public int howManyInRow;
    public int squareSize;
    public Button actualButton;
    public Image bombImage;
    public Image flagImage;
    public ArrayList<String> bombIDList = new ArrayList<>();
    public ArrayList<String> flagIDList = new ArrayList<>();

    public void createButtons(){
        int x = 0;
        int y = 0;
        for(int i = 0; i < stageSize; i+= squareSize){
            for(int j = 0; j < stageSize; j += squareSize){
                Button button = new Button();
                button.setId("square-" + x + "-" + y);
                button.setUserData("square-" + x + "-" + y);
                button.setPrefWidth(squareSize);
                button.setPrefHeight(squareSize);
                button.setLayoutX(i);
                button.setLayoutY(j);
                button.setOnMouseClicked(event -> {
                    actualButton = ((Button) event.getSource());
                    if (event.getButton() == MouseButton.PRIMARY) {
                        if (isThisBomb(actualButton.getId()) == 0) {
                            if((isThisFlag(actualButton.getId()) == 0)){
                                setClue(actualButton.getId());
                            }
                        }else{
                            showAllBombs();
                        }
                    } else if (event.getButton() == MouseButton.SECONDARY) {
                        setFlag();
                        try {
                            Win();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                gameBoard.getChildren().add(button);
                y++;
            }
            y = 0;
            x++;
        }
    }

    public void Win() throws IOException {
        Collections.sort(bombIDList);
        Collections.sort(flagIDList);
        if(bombIDList.equals(flagIDList)){
            exit("You won!");
        }
    }

    public void exit(String text){
        Button button = new Button();
        button.setPrefWidth(squareSize*4);
        button.setPrefHeight(squareSize*4);
        button.setLayoutX(squareSize*4);
        button.setLayoutY(squareSize*4);
        button.setText(text);
        button.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                minesweeper.sceneController sceneController = new sceneController();
                try {
                    sceneController.goToLevelSetter(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        gameBoard.getChildren().add(button);
    }

    public void setClue(String buttonID){
        for (Node n : gameBoard.getChildren()) {
            if(buttonID.equals(n.getId())){
                int value = howManyBombs(n.getId());
                n.setDisable(true);
                if(value != 0){
                    n.setStyle("-fx-opacity:1.0; -fx-font-size:" + (squareSize - 75) +"; -fx-font-family: 'Helvetica', Arial, sans-serif;");
                    ((Button)n).setText(String.valueOf(value));
                }else{
                    n.setStyle("-fx-background-color: #393939; -fx-opacity:1.0 ");
                }
            }
        }
    }

    public void setFlag(){
        ImageView flagImageView = new ImageView(flagImage);
        flagImageView.setFitWidth(squareSize - 15);
        flagImageView.setFitHeight(squareSize - 15);
        if(actualButton.getGraphic() == null){
            actualButton.setGraphic(flagImageView);
            flagIDList.add(actualButton.getId());
        }else{
            actualButton.setGraphic(null);
            flagIDList.remove(actualButton.getId());
        }
    }

    public int isThisFlag(String buttonID){
        int i = 0;
        while (i < flagIDList.size()){
            if(buttonID.equals(flagIDList.get(i))){
                return 1;
            }
            i++;
        }
        return 0;
    }

    public void setBombs(int amount){
        Random random = new Random();
        Set<String> bombIDSet = new TreeSet<>();
        int i = 0;
        while (i < amount){
            int newRandX = random.nextInt(howManyInRow);
            int newRandY = random.nextInt(howManyInRow);
            bombIDSet.add("square-" + newRandX + "-" + newRandY);
            i++;
        }
        bombIDList.addAll(bombIDSet);
    }

    public int isThisBomb(String buttonID){
        int i = 0;
        while (i < bombIDList.size()){
            if(buttonID.equals(bombIDList.get(i))){
                return 1;
            }
            i++;
        }
        return 0;
    }

    public int howManyBombs(String buttonID){
        String[] xy =  buttonID.split("-");
        int x =  Integer.parseInt(xy[1]);
        int y =  Integer.parseInt(xy[2]);
        return + isThisBomb("square-"+(x-1)+"-"+(y-1))
                + isThisBomb("square-"+(x-1)+"-"+y)
                + isThisBomb("square-"+(x-1)+"-"+(y+1))
                + isThisBomb("square-"+x +"-"+(y-1))
                + isThisBomb("square-"+x +"-"+(y+1))
                + isThisBomb("square-"+(x+1)+"-"+(y-1))
                + isThisBomb("square-"+(x+1)+"-"+y)
                + isThisBomb("square-"+(x+1)+"-"+(y+1));
    }

    public void showAllBombs(){
        int i = 0;
        while (i < bombIDList.size()){
            ImageView bombImageView = new ImageView(bombImage);
            bombImageView.setFitWidth(squareSize - 15);
            bombImageView.setFitHeight(squareSize - 15);
            for (Node n : gameBoard.getChildren()) {
                   n.setDisable(true);
                if (bombIDList.get(i).equals(n.getUserData())) {
                   ((Button) n).setGraphic(bombImageView);
                }
            }
            i++;
        }
        exit("You lose..");
    }
}