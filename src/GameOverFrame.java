import javax.swing.*;
import java.awt.*;

public class GameOverFrame extends JFrame{
    public GameOverFrame() {
        super("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(3, 192, 60));
        panel.setPreferredSize(new Dimension(800, 800));
        JLabel message = new JLabel("Game Over");
        panel.add(message);
        message.setFont(new Font("Arial", Font.PLAIN, 32));
        this.setContentPane(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setMinimumSize(this.getSize());
        this.setVisible(true);
        this.setResizable(false);
    }
}
