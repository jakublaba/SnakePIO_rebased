package snakegame;


import javafx.scene.media.AudioClip;

public class SoundPlayer {
    private final String foodEatenSound;
    private final String snakeCrashedSound;
    private final String soundtrack;
    private final String buttonClickedSound;

    public SoundPlayer() {
        foodEatenSound = "/sounds/impactWood_light_001.mp3";
        snakeCrashedSound = "/sounds/back_002.mp3";
        soundtrack = null;
        buttonClickedSound = null;
    }

    public void playFoodEatenSound() {
        try {
            var sound = new AudioClip(getClass().getResource(foodEatenSound).toExternalForm());
            sound.play();
        }
        catch (NullPointerException e){
            System.err.println("Zły plik boży");
        }
    }

    public void playSnakeCrashedSound() {
        try {
            var sound = new AudioClip(getClass().getResource(snakeCrashedSound).toExternalForm());
            sound.play();
        }
        catch (NullPointerException e){
            System.err.println("Zły plik boży");
        }
    }

    public void startSoundtrack() {

    }

    public void stopSoundtrack() {

    }
}
