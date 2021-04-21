import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener {
    private GamePanel gPanel;
    private int windowX, windowY;

    public GameFrame(int windowX, int windowY, GamePanel gPanel) {
        this.windowX = windowX;
        this.windowY = windowY;
        this.gPanel = gPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
