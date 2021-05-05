package snakegame;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Controller {
    @FXML
    private javafx.scene.control.Button settingsButton;
    @FXML
    private javafx.scene.control.Button closeButton;
    @FXML
    private javafx.scene.control.Button settingsButtonGame;
    @FXML
    private javafx.scene.control.Button settingsButtonMusic;
    @FXML
    private javafx.scene.control.Button settingsButtonStyle;
    @FXML
    private javafx.scene.control.Button settingsButtonChooseStyleOne;
    @FXML
    private javafx.scene.control.Button settingsButtonChooseStyleTwo;
    @FXML
    private javafx.scene.control.Button settingsButtonChooseStyleThree;
    @FXML
    private javafx.scene.layout.Pane settingsPaneGame;
    @FXML
    private javafx.scene.layout.Pane settingsPaneMusic;
    @FXML
    private javafx.scene.layout.Pane settingsPaneStyle;
    @FXML
    private javafx.scene.control.Slider settingsMusicSlider;
    @FXML
    private javafx.scene.control.Slider settingsSoundSlider;
    @FXML
    private javafx.scene.control.ChoiceBox<String> settingsChoiceGameMode;

    private boolean pause;

    @FXML
    public void startButtonAction() {
        Stage Menu = (Stage) settingsButton.getScene().getWindow();
        Menu.hide();
        Pane gameField;
        PointVector mousePosition = new PointVector(0, 0);
        Stage primaryStage = new Stage();
        BorderPane root = new BorderPane();
        StackPane layerPane = new StackPane();

        gameField = new Pane();
        Pane pausePane = new Pane();
        pausePane.setVisible(false);
        pausePane.setStyle("-fx-background-color: rgba(255,255,255,0.6); -fx-background-radius: 10; ");
        Label centerLabel = new Label("Game Paused!");
        centerLabel.setStyle("-fx-font-size: 3em; ");
        pausePane.getChildren().add(centerLabel);
        pausePane.setMaxWidth(400);
        pausePane.setMaxHeight(200);

        Button pauseButton = new Button("Pause");
        pauseButton.setTranslateX(GameSettings.BOARD_WIDTH / 2 - 30);
        pauseButton.setTranslateY(GameSettings.BOARD_HEIGHT / 2 - 25);
        layerPane.getChildren().addAll(gameField);
        layerPane.getChildren().add(pauseButton);
        layerPane.getChildren().add(pausePane);
        root.setCenter(layerPane);

        Scene scene = new Scene(root, GameSettings.BOARD_WIDTH, GameSettings.BOARD_HEIGHT);
        primaryStage.setTitle("SnakeFX");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        GamePane GamePane = new GamePane();
        gameField.getChildren().add(GamePane);

        /*
         * tworzymy soundtrack który będzie grał w trakcie gry
         * ustalony na zapętlenie
         * dalej w pętli game loop (animation timer) jest stopowany
         */

            var soundtrack = new Media(Objects.requireNonNull(getClass().getResource(
                    "resources/sounds/happy_0.mp3")).toExternalForm());
            var soundtrackPlayer = new MediaPlayer(soundtrack);
            soundtrackPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            //soundtrackPlayer.setVolume(0.2); //0.0 - muted; 1.0 - full volume -- Teraz jest ustawiany z ustawień
            soundtrackPlayer.setVolume(GameSoundPlayer.getValueOfMusicVolume());



        scene.addEventFilter(MouseEvent.ANY, e -> mousePosition.set(e.getX(), e.getY()));

        GameBoard myBoard = new GameBoard();

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long currentTime) {
                soundtrackPlayer.play();
                myBoard.updateGame(mousePosition); //process controls
                GamePane.render(myBoard); //render
            }
        };
        pause = false;
        gameLoop.start();

        pauseButton.setOnAction(event -> {
            if (!pause) {
                gameLoop.stop();
                pausePane.setVisible(true);
                pause = true;
            } else {
                gameLoop.start();
                pausePane.setVisible(false);
                pause = false;
            }
        });

    }

    @FXML
    private void exitButtonAction() {
        System.exit(0);
    }

    @FXML
    public void settingsButtonGameAction() {
        settingsButtonMusic.setStyle("-fx-font-size: 1em; -fx-text-fill: #828282; ");
        settingsButtonStyle.setStyle("-fx-font-size: 1em; -fx-text-fill: #828282; ");
        settingsButtonGame.setStyle("-fx-font-size: 2em; -fx-text-fill: #000000; ");
        settingsPaneGame.setVisible(true);
        settingsPaneMusic.setVisible(false);
        settingsPaneStyle.setVisible(false);
    }
    @FXML
    public void settingsButtonMusicAction() {
        settingsButtonMusic.setStyle("-fx-font-size: 2em; -fx-text-fill: #000000; ");
        settingsButtonStyle.setStyle("-fx-font-size: 1em; -fx-text-fill: #828282; ");
        settingsButtonGame.setStyle("-fx-font-size: 1em; -fx-text-fill: #828282; ");
        settingsPaneGame.setVisible(false);
        settingsPaneMusic.setVisible(true);
        settingsPaneStyle.setVisible(false);
    }
    @FXML
    public void settingsButtonStyleAction() {
        settingsButtonMusic.setStyle("-fx-font-size: 1em; -fx-text-fill: #828282; ");
        settingsButtonStyle.setStyle("-fx-font-size: 2em; -fx-text-fill: #000000; ");
        settingsButtonGame.setStyle("-fx-font-size: 1em; -fx-text-fill: #828282; ");
        settingsPaneGame.setVisible(false);
        settingsPaneMusic.setVisible(false);
        settingsPaneStyle.setVisible(true);
    }

    @FXML
    public void settingsMusicSliderAction() {
         GameSoundPlayer.setValueOfMusicVolume(settingsMusicSlider.getValue()/100);
         System.out.println("Ustawiam głośność muzyki na: " + GameSoundPlayer.getValueOfMusicVolume());
    }

    @FXML
    public void settingsSoundSliderAction() {
        GameSoundPlayer.setValueOfSoundEffectVolume(settingsSoundSlider.getValue()/100);
        System.out.println("Ustawiam głośność efektów na: " + GameSoundPlayer.getValueOfSoundEffectVolume());
    }

    @FXML
    public void settingsButtonChooseStyleOneAction() {
        GamePane.setBG_COLORS(Color.web("#ffbd59"), Color.web("#ffde59"));
        System.out.println("Ustawiam styl tła na opcje 1");
        settingsButtonChooseStyleOne.setStyle("-fx-border-color: black; -fx-border-width: 4; ");
        settingsButtonChooseStyleTwo.setStyle("-fx-border-color: transparent; -fx-border-width: 0; ");
        settingsButtonChooseStyleThree.setStyle("-fx-border-color: transparent; -fx-border-width: 0; ");
    }


    @FXML
    public void settingsButtonChooseStyleTwoAction() {
        GamePane.setBG_COLORS(Color.web("#ffffff"), Color.web("#000000"));
        System.out.println("Ustawiam styl tła na opcje 2");
        settingsButtonChooseStyleOne.setStyle("-fx-border-color: transparent; -fx-border-width: 0; ");
        settingsButtonChooseStyleTwo.setStyle("-fx-border-color: black; -fx-border-width: 4; ");
        settingsButtonChooseStyleThree.setStyle("-fx-border-color: transparent; -fx-border-width: 0; ");
    }

    @FXML
    public void settingsButtonChooseStyleThreeAction() {
        GamePane.setBG_COLORS(Color.web("#7ed967"), Color.web("#12743c"));
        System.out.println("Ustawiam styl tła na opcje 3");
        settingsButtonChooseStyleOne.setStyle("-fx-border-color: transparent; -fx-border-width: 0; ");
        settingsButtonChooseStyleTwo.setStyle("-fx-border-color: transparent; -fx-border-width: 0; ");
        settingsButtonChooseStyleThree.setStyle("-fx-border-color: black; -fx-border-width: 4; ");
    }
    @FXML
    public void settingsChoiceGameModeAction() {
        if(settingsChoiceGameMode.getItems().isEmpty()) {
            settingsChoiceGameMode.getItems().add("Easy");
            settingsChoiceGameMode.getItems().add("Medium");
            settingsChoiceGameMode.getItems().add("Hard");
        }
    }

    @FXML
    public void settingsButtonAction() {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("settings.fxml")));
            Stage stage = new Stage();
            stage.setTitle("SnakeFX - Settings");
            stage.setScene(new Scene(root, GameSettings.MENU_WIDTH, GameSettings.MENU_HEIGHT));
            stage.setResizable(false);
            stage.show();
            Stage stage1 = (Stage) settingsButton.getScene().getWindow();
            stage1.hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void backButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menu.fxml")));
            Stage stage1 = new Stage();
            stage1.setTitle("Snake");
            stage1.setScene(new Scene(root, GameSettings.MENU_WIDTH, GameSettings.MENU_HEIGHT));
            stage1.show();
            stage.hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
