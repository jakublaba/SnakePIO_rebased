import javax.swing.JFrame;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        super("Snake");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GamePanel gamePanel = new GamePanel();
        gamePanel.setPreferredSize(new Dimension(800, 800));
        this.setContentPane(gamePanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setMinimumSize(this.getSize());
        this.setVisible(true);
        this.setResizable(false);
    }
}