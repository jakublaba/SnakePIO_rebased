import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    private int gameState, gameSpeed;
    private ImageIcon headImg, bodyImg, foodImg, obstacleImg;
    private Graphics2D mainMenu, settingsScreen, gameScreen, gameOverScreen;
    private GameBoard gameBoard;

    public GamePanel() {

    }

    private boolean checkGameState(GameBoard gameBoard) {

    }

    private class MenuButton extends JButton implements ActionListener {
        public MenuButton() {

        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
