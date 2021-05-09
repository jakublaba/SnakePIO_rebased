package snakegame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ReaderOfHighscore {
    private int highscore;

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public void readHighScore() throws InputMismatchException {
        Scanner readingFile;
        try {
            readingFile = new Scanner(new File("src/snakegame/resources/.highscore.txt"));
            while (readingFile.hasNextLine()) {
                String tmpLine = readingFile.nextLine();
                if (!tmpLine.matches("^\\d+$")) {
                    throw new InputMismatchException("Incorrect input format: " + tmpLine);
                }
                highscore = Integer.parseInt(tmpLine);
                System.out.println("Wczytano highscore: " + highscore);
            }
        } catch (FileNotFoundException e) {
            System.out.println("There is no file:" + "resources/.highscore.txt");
        }
    }

    public void writeHighScore() throws InputMismatchException {
        PrintWriter writingFile;
        try {
            writingFile = new PrintWriter("src/snakegame/resources/.highscore.txt");
            writingFile.println(highscore);
            writingFile.close();
            System.out.println("Zapisano highscore: " + highscore);
        } catch (FileNotFoundException e) {
            System.out.println("There is no file:" + "resources/.highscore.txt");
        }
    }
}