        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

public class Settings extends JFrame implements ActionListener {
    JPanel panel = new JPanel();

    public Settings() {
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
        this.Title();
        this.myButton();
    }

    public void Title() {
        JLabel tekst = new JLabel("Settings");
        tekst.setFont(new Font("Courier New", (Font.BOLD), 20));
        tekst.setBounds(365, 200, 100, 100);
        panel.add(tekst);
    }

    JButton button;
    JButton button1;
    JButton button2;

    public void myButton() {
        button = new JButton("Game mode");
        button.setBounds(300, 300, 200, 50);
        panel.add(button);
        button2 = new JButton("Result");
        button2.setBounds(300, 400, 200, 50);
        panel.add(button2);
        button1 = new JButton("Back");
        button1.setBounds(300, 500, 200, 50);
        panel.add(button1);
        button.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == button) {
        }
        if(source==button1){
        }
        if (source == button1) {
            //setVisible(false);
            dispose();
            new MenuFrame();
        }
    }

}