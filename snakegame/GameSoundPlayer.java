package snakegame;

import javafx.scene.media.AudioClip;

public class GameSoundPlayer {
    private AudioClip foodEatenSound;
    private AudioClip snakeCrashedSound;

    public GameSoundPlayer() {
        try {
            foodEatenSound = new AudioClip(getClass().getResource(
                    "resources/sounds/impactWood_light_001.mp3").toExternalForm());
        } catch (NullPointerException e) {
            System.err.println("Zły plik boży");
        }
        try {
            snakeCrashedSound = new AudioClip(getClass().getResource(
                    "resources/sounds/back_002.mp3").toExternalForm());
        } catch (NullPointerException e) {
            System.err.println("Zły plik boży");
        }
    }

    public void playFoodEatenSound() {
        foodEatenSound.play();
    }
    public void playSnakeCrashedSound() {
        snakeCrashedSound.play();
    }
}
