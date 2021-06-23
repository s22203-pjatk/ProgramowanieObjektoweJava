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

    public void createButtons(){                                                    //tworzymy tutaj przyciski, po przez tablicę 2wymiarową
        int x = 0;
        int y = 0;
        for(int i = 0; i < stageSize; i+= squareSize){
            for(int j = 0; j < stageSize; j += squareSize){
                Button button = new Button();
                button.setId("square-" + x + "-" + y);                              //ustawianie id po współrzędnych
                button.setUserData("square-" + x + "-" + y);                        //do userdata łatwiej się odwołać więc też ma współrzędne
                button.setPrefWidth(squareSize);                                    //rozmiary ustalone w sceneController.java
                button.setPrefHeight(squareSize);
                button.setLayoutX(i);
                button.setLayoutY(j);
                button.setOnMouseClicked(event -> {
                    actualButton = ((Button) event.getSource());                                    //ustawia aktualny przycisk jaki jest przycisnięty
                    if (event.getButton() == MouseButton.PRIMARY) {                                 //JEŻELI przycisk myszy LEWY, sprawdz czy to nie bomba
                        if (isThisBomb(actualButton.getId()) == 0) {                                //0 = nie jest bomba
                            if((isThisFlag(actualButton.getId()) == 0)){                            //jeżeli wcisnąłeś flagę, nic sie nie stanie
                                setClue(actualButton.getId());                                      //ustaw podpowiedź
                            }
                        }else{
                            showAllBombs();
                        }
                    } else if (event.getButton() == MouseButton.SECONDARY) {                    //JEŻELI przycisk myszy prawy, ustaw flagę
                        setFlag();
                        try {                                                                   // noo i sprawdz czy nie wygrałeś
                            Win();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                gameBoard.getChildren().add(button);
                y++;
            }
            y = 0;                      // nie chcę żeby najeżdzały na siebie przyciski
            x++;
        }
    }

    public void Win() throws IOException {
        Collections.sort(bombIDList);                       //sprawdzamy listę bomb i flag , czy są równe
        Collections.sort(flagIDList);
        if(bombIDList.equals(flagIDList)){
            exit("You won!");
        }
    }

    public void exit(String text){
        Button button = new Button();
        button.setPrefWidth(squareSize*4);
        button.setPrefHeight(squareSize*4);
        button.setLayoutX(squareSize*4);                                                                    //tworzy się mały komunikat
        button.setLayoutY(squareSize*4);
        button.setText(text);
        button.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                minesweeper.sceneController sceneController = new sceneController();
                try {
                    sceneController.goToLevelSetter(event);                                                 //gramy na nowo
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        gameBoard.getChildren().add(button);
    }

    public void setClue(String buttonID){                     // program szuka id i buttonu, tak zeby się zgadzały. nie były rozbierzne z tym co jest w aplikacji i w kodzie
        for (Node n : gameBoard.getChildren()) {
            if(buttonID.equals(n.getId())){                   // jeżeli znalazł, to za pomocą ^howManyBombs^ pokazuje ile jest bomb
                int value = howManyBombs(n.getId());
                n.setDisable(true);                           // dezaktywuję przycisk, żeby go nie wcisnąc 2raz
                if(value != 0){                               // jeżeli nie równa się zero, to wstaw odpowiednią cyferkę odpowiadającą bomb w poblizu
                    n.setStyle("-fx-opacity:1.0; -fx-font-size:" + (squareSize - 75) +"; -fx-font-family: 'Helvetica', Arial, sans-serif;");
                    ((Button)n).setText(String.valueOf(value));
                }else{                                                                      // jezeli zero, to szary przycisk
                    n.setStyle("-fx-background-color: #393939; -fx-opacity:1.0 ");
                }
            }
        }
    }

    public void setFlag(){
        ImageView flagImageView = new ImageView(flagImage);             //ustawiam nowy obraz, i ustawiam grafike aktualnego przycisku
        flagImageView.setFitWidth(squareSize - 15);
        flagImageView.setFitHeight(squareSize - 15);
        if(actualButton.getGraphic() == null){                          //Jeżeli jest flaga, to nie dostawiaj drugiej flagi
            actualButton.setGraphic(flagImageView);
            flagIDList.add(actualButton.getId());                       //id button łączymy z id flagi, więc nie jest już przyciskiem a flagą
        }else{
            actualButton.setGraphic(null);
            flagIDList.remove(actualButton.getId());
        }
    }

    public int isThisFlag(String buttonID){
        int i = 0;                                          //to samo co w isthisBomb, tylko z flagami
        while (i < flagIDList.size()){
            if(buttonID.equals(flagIDList.get(i))){
                return 1;
            }
            i++;
        }
        return 0;
    }

    public void setBombs(int amount){
        Random random = new Random();                           //randomowo wrzuca bomby
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
        while (i < bombIDList.size()){                              //zwracam 1 lub 0
            if(buttonID.equals(bombIDList.get(i))){
                return 1;
            }
            i++;
        }
        return 0;
    }

    public int howManyBombs(String buttonID){
        String[] xy =  buttonID.split("-");
        int x =  Integer.parseInt(xy[1]);                               //zmieniam square ( - ) i dostaję x oraz y
        int y =  Integer.parseInt(xy[2]);
        return + isThisBomb("square-"+(x-1)+"-"+(y-1))          // dla wszystkich przycisków obok,wysyła czy to jest bomba
                + isThisBomb("square-"+(x-1)+"-"+y)
                + isThisBomb("square-"+(x-1)+"-"+(y+1))
                + isThisBomb("square-"+x+"-"+(y-1))
                + isThisBomb("square-"+x+"-"+(y+1))
                + isThisBomb("square-"+(x+1)+"-"+(y-1))         //jeżeli JEST tu bomba, no to dodaje 1
                + isThisBomb("square-"+(x+1)+"-"+y)
                + isThisBomb("square-"+(x+1)+"-"+(y+1));        //jeżeli nie ma tu bomby, no to zwraca 0
    }

    public void showAllBombs(){
        int i = 0;
        while (i < bombIDList.size()){
            ImageView bombImageView = new ImageView(bombImage);
            bombImageView.setFitWidth(squareSize - 15);                             //odsłania  bomby
            bombImageView.setFitHeight(squareSize - 15);
            for (Node n : gameBoard.getChildren()) {
                   n.setDisable(true);                                              //uniemożliwia ponowne kliknięcie
                if (bombIDList.get(i).equals(n.getUserData())) {
                   ((Button) n).setGraphic(bombImageView);
                }
            }
            i++;
        }
        exit("You lose..");                                                     //wracamy do exit, ale z napisem YouLose !
    }
}