package snakegame;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;

public final class Controller {
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
    public static StackPane layerPane;
    public static AnimationTimer gameLoop;
    public static MediaPlayer soundtrackPlayer;

    //Pause Pane
    private Pane pausePane;
    private ImageView imageViewCornerPlay;
    private ImageView imageViewCornerPause;
    private Button resumeButton;
    private Slider musicSlider;
    private Slider soundSlider;
    private Button pauseCornerButton;

    //Lose Pane
    public static final Pane losePane = new Pane();
    private static ImageView imageViewGameover;

    @FXML
    public void startButtonAction() throws FileNotFoundException {
        Stage Menu = (Stage) settingsButton.getScene().getWindow();
        Menu.hide();
        Pane gameField;
        PointVector mousePosition = new PointVector(0, 0);
        Stage primaryStage = new Stage();
        BorderPane root = new BorderPane();
        layerPane = new StackPane();
        gameField = new Pane();
        layerPane.getChildren().addAll(gameField);
        setPausePane();
        layerPane.getChildren().add(pausePane);
        pauseCornerButton = new Button("", imageViewCornerPause);
        pauseCornerButton.setVisible(true);
        pauseCornerButton.setTranslateX(GameSettings.BOARD_WIDTH / 2 - 30);
        pauseCornerButton.setTranslateY(GameSettings.BOARD_HEIGHT / 2 - 30);
        pauseCornerButton.setStyle("-fx-background-color: transparent");
        layerPane.getChildren().add(pauseCornerButton);
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
        var soundtrack = new Media(Objects.requireNonNull(getClass().getResource("resources/sounds/happy_0.mp3")).toExternalForm());
        soundtrackPlayer = new MediaPlayer(soundtrack);
        soundtrackPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        soundtrackPlayer.setVolume(GameSoundPlayer.getValueOfMusicVolume());
        scene.addEventFilter(MouseEvent.ANY, e -> mousePosition.set(e.getX(), e.getY()));
        GameBoard myBoard = new GameBoard();
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long currentTime) {
                soundtrackPlayer.play();
                try {
                    myBoard.updateGame(mousePosition); //process controls
                } catch (IOException e) {
                    e.printStackTrace();
                }
                GamePane.render(myBoard); //render
            }
        };
        pause = false;
        gameLoop.start();

        pauseCornerButton.setOnAction(event -> {
            if (!pause) {
                gameLoop.stop();
                soundtrackPlayer.stop();
                musicSlider.setValue(GameSoundPlayer.getValueOfMusicVolume() * 100);
                soundSlider.setValue(GameSoundPlayer.getValueOfSoundEffectVolume() * 100);
                pausePane.setVisible(true);
                pauseCornerButton.setGraphic(imageViewCornerPlay);
                pause = true;
                resumeButton.setOnAction(eve -> {
                    pausePane.setVisible(false);
                    gameLoop.start();
                    soundtrackPlayer.play();
                    pauseCornerButton.setGraphic(imageViewCornerPause);
                    pause = false;
                });

                musicSlider.setOnMouseReleased(e1 -> {
                    soundtrackPlayer.setVolume(musicSlider.getValue() / 100);
                    GameSoundPlayer.setValueOfMusicVolume(musicSlider.getValue() / 100);
                });

                soundSlider.setOnMouseReleased(e2 -> GameSoundPlayer.setValueOfSoundEffectVolume(soundSlider.getValue() / 100));

            } else {
                gameLoop.start();
                pausePane.setVisible(false);
                pauseCornerButton.setGraphic(imageViewCornerPause);
                pause = false;
            }
        });
    }

    protected void setLosePane() {
        losePane.setVisible(true);
        losePane.setStyle("-fx-background-color: transparent;" + "-fx-background-radius: 10;");
        losePane.setMaxSize(700, 700);
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Score: " + GameBoard.score, GameBoard.score),
                new PieChart.Data("Highscore: " + GameBoard.highscore, GameBoard.highscore));
        PieChart chart = new PieChart(pieChartData);
        chart.setLegendVisible(false);
        chart.setStyle("-fx-font-size: 2em; -fx-font-weight: bold");
        chart.setMaxSize(500, 500);
        chart.setMinSize(500, 500);
        chart.setTranslateY(100);
        chart.setTranslateX(100);
        Button quitButton = new Button("Quit");
        quitButton.setStyle("-fx-background-color: rgba(255,255,255,0.7); -fx-font-size: 2em;");
        quitButton.setPrefSize(100, 50);
        quitButton.setTranslateX(200);
        quitButton.setTranslateY(500);
        quitButton.setOnAction(e -> quitButtonAction());
        Button againButton = new Button("Again!");
        againButton.setStyle("-fx-background-color: rgba(255,255,255,0.7); -fx-font-size: 2em;");
        againButton.setPrefSize(100, 50);
        againButton.setTranslateX(400);
        againButton.setTranslateY(500);
        againButton.setOnAction(e -> System.out.println("siur123"));
        imageViewGameover.setX(150);
        losePane.getChildren().add(imageViewGameover);
        losePane.getChildren().add(chart);
        losePane.getChildren().add(quitButton);
        losePane.getChildren().add(againButton);
    }

    private void setPausePane() {
        try {
            pausePane = new Pane();
            pausePane.setVisible(false);
            pausePane.setStyle("-fx-background-color: rgba(255,255,255,0.2);"
                    + "-fx-background-radius: 10;");
            Label centerLabel = new Label("Game Paused!");
            centerLabel.setStyle("-fx-font-size: 3em; -fx-padding: 10px;");
            centerLabel.setTranslateX(60);
            Label musicSliderLabel = new Label("Music Volume");
            musicSliderLabel.setStyle("-fx-font-size: 2em;");
            musicSliderLabel.setTranslateX(50);
            musicSliderLabel.setTranslateY(80);
            Label soundSliderLabel = new Label("Sound Volume");
            soundSliderLabel.setStyle("-fx-font-size: 2em;");
            soundSliderLabel.setTranslateX(50);
            soundSliderLabel.setTranslateY(150);
            FileInputStream inputPauseIcon = new FileInputStream("src/snakegame/resources/img/playIcon.png");
            Image imagePause = new Image(inputPauseIcon);
            ImageView imageViewPause = new ImageView(imagePause);
            FileInputStream inputPlayCornerIcon = new FileInputStream("src/snakegame/resources/img/playCornerIcon.png");
            Image imageCornerPlay = new Image(inputPlayCornerIcon);
            imageViewCornerPlay = new ImageView(imageCornerPlay);
            FileInputStream inputPauseCornerIcon = new FileInputStream("src/snakegame/resources/img/pauseCornerIcon.png");
            Image imageCornerPause = new Image(inputPauseCornerIcon);
            imageViewCornerPause = new ImageView(imageCornerPause);
            FileInputStream inputGameoverGraphics = new FileInputStream("src/snakegame/resources/img/gameoverGraphics.png");
            Image imageGameover = new Image(inputGameoverGraphics);
            imageViewGameover = new ImageView(imageGameover);
            resumeButton = new Button("Resume", imageViewPause);
            resumeButton.setTranslateX(110);
            resumeButton.setTranslateY(230);
            resumeButton.setMinSize(180, 65);
            resumeButton.setMaxSize(180, 65);
            resumeButton.setStyle("-fx-background-color: rgba(255,255,255,0.7); -fx-font-size: 2em;");
            musicSlider = new Slider();
            musicSlider.setMin(0);
            musicSlider.setMax(100);
            musicSlider.setTranslateX(50);
            musicSlider.setTranslateY(120);
            musicSlider.setMinWidth(300);
            musicSlider.setMaxWidth(300);
            soundSlider = new Slider();
            soundSlider.setMin(0);
            soundSlider.setMax(100);
            soundSlider.setTranslateX(50);
            soundSlider.setTranslateY(190);
            soundSlider.setMinWidth(300);
            soundSlider.setMaxWidth(300);
            pausePane.getChildren().add(centerLabel);
            pausePane.getChildren().add(resumeButton);
            pausePane.getChildren().add(musicSlider);
            pausePane.getChildren().add(musicSliderLabel);
            pausePane.getChildren().add(soundSliderLabel);
            pausePane.getChildren().add(soundSlider);
            pausePane.setMaxSize(400, 300);
        } catch (FileNotFoundException e) {
            System.out.println("nie udało się wczytać pliku");
        }
    }

    private static void quitButtonAction() {
        System.exit(0);
    }

    @FXML
    private void exitButtonAction() {
        quitButtonAction();
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
        GameSoundPlayer.setValueOfMusicVolume(settingsMusicSlider.getValue() / 100);
        System.out.println("Ustawiam głośność muzyki na: " + GameSoundPlayer.getValueOfMusicVolume());
    }

    @FXML
    public void settingsSoundSliderAction() {
        GameSoundPlayer.setValueOfSoundEffectVolume(settingsSoundSlider.getValue() / 100);
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
        if (settingsChoiceGameMode.getItems().isEmpty()) {
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
