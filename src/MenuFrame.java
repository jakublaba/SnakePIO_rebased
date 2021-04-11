import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame implements ActionListener {
    JPanel panel = new JPanel();
    public MenuFrame(){
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel.setBackground(new Color(3, 192, 60));
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(800, 800));
        this.setContentPane(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        this.myBorderPanel();
        this.myButton();

    }
    public void myBorderPanel(){
        JLabel tekst = new JLabel("Snake");
        tekst.setFont(new Font("Courier New", (Font.BOLD),20));
        tekst.setBounds(365,200,100,100);
        panel.add(tekst);
    }
    JButton button;
    JButton button1;
    public void myButton(){
        button = new JButton("New Game");
        button.setBounds(300,300,200,50);
        panel.add(button);
        button1 = new JButton("Exit");
        button1.setBounds(300,400,200,50);
        panel.add(button1);
        button.addActionListener(this);
        button1.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == button) {
            setVisible(false);
            new GameFrame();
        }
        if(source == button1) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new MenuFrame();
    }
}