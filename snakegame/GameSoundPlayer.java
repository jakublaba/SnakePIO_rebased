package snakegame;

import javafx.scene.media.AudioClip;

public class GameSoundPlayer {
    private AudioClip foodEatenSound;
    private AudioClip snakeCrashedSound;
    private static double valueOfMusicVolume = 0.2;
    private static double valueOfSoundEffectVolume = 0.2;

    public static void setValueOfMusicVolume(double value) {
        valueOfMusicVolume = value;
    }
    public static double getValueOfMusicVolume() {
        return valueOfMusicVolume;
    }
    public static void setValueOfSoundEffectVolume(double value) {
        valueOfSoundEffectVolume = value;
    }
    public static double getValueOfSoundEffectVolume(){
        return valueOfSoundEffectVolume;
    }

    public GameSoundPlayer() {
        try {
            foodEatenSound = new AudioClip(getClass().getResource(
                    "resources/sounds/impactWood_light_001.mp3").toExternalForm());
            foodEatenSound.setVolume(getValueOfSoundEffectVolume());
        } catch (NullPointerException e) {
            System.err.println("Zły plik boży");
        }
        try {
            snakeCrashedSound = new AudioClip(getClass().getResource(
                    "resources/sounds/back_002.mp3").toExternalForm());
            snakeCrashedSound.setVolume(getValueOfSoundEffectVolume());
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
