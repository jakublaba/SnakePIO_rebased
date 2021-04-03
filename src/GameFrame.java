import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        super("Snake GameFrame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setResizable(false);
        this.add(new MousePanel());
    }
}
