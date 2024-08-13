import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GUI implements ActionListener {

    JFrame frame;
    JPanel panel;
    JLabel label;
    JLabel label2;
    JLabel label3;
    RoundButton buttonTTT;
    RoundButton buttonQuiz;
    RoundButton buttonSnake;
    RoundButton buttonPong;

    final int PANEL_WIDTH = 900;
    final int PANEL_HEIGHT = 600;

    Image backgroundImage;

    GUI() {
        // Load the background image
        backgroundImage = new ImageIcon("pictures/background1.jpg").getImage();

        // Create the custom panel with background image
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, this);
            }
        };
        panel.setLayout(null); // Use null layout to place components manually

        // Initialize labels with text
        label3 = new JLabel("~ Welcome ~", SwingConstants.CENTER);
        label3.setBounds(150, 35, 600, 50);
        label3.setFont(new Font("MV Boli", Font.BOLD, 33));
        label3.setForeground(Color.WHITE);
        panel.add(label3);

        label = new JLabel("to the ultimate gaming experience!", SwingConstants.CENTER);
        label.setBounds(150, 80, 600, 50);
        label.setFont(new Font("MV Boli", Font.BOLD, 25));
        label.setForeground(Color.WHITE);
        panel.add(label);

        label2 = new JLabel("Pick your challenge and let the adventure begin!", SwingConstants.CENTER);
        label2.setBounds(150, 140, 600, 50);
        label2.setFont(new Font("MV Boli", Font.BOLD, 20));
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        // Initialize buttons
        buttonTTT = new RoundButton("Tic-Tac-Toe");
        buttonQuiz = new RoundButton("Quiz");
        buttonSnake = new RoundButton("Snake");
        buttonPong = new RoundButton("Pong");

        // Common button style
        Color buttonColor = new Color(153, 31, 0, 150); // Color with transparency
        Color textColor = Color.WHITE;

        // Configure buttons
        RoundButton[] buttons = {buttonTTT, buttonQuiz, buttonSnake, buttonPong};
        for (RoundButton button : buttons) {
            button.setFont(new Font("MV Boli", Font.BOLD, 23));
            button.setBackground(buttonColor);
            button.setForeground(textColor);
        }

        // Position buttons
        buttonTTT.setBounds(270, 390, 170, 40);
        buttonQuiz.setBounds(470, 390, 170, 40);
        buttonSnake.setBounds(270, 450, 170, 40);
        buttonPong.setBounds(470, 450, 170, 40);

        // Add action listeners
        buttonTTT.addActionListener(this);
        buttonQuiz.addActionListener(this);
        buttonSnake.addActionListener(this);
        buttonPong.addActionListener(this);

        // Add buttons to panel
        panel.add(buttonTTT);
        panel.add(buttonQuiz);
        panel.add(buttonSnake);
        panel.add(buttonPong);

        // Frame setup
        frame = new JFrame("Gaming");
        frame.setContentPane(panel);
        frame.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("pictures/icon.png"); // Create an ImageIcon
        frame.setIconImage(image.getImage()); // Change icon of the app
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonTTT) {
            frame.dispose();
            new Tic_tac_toe();
        } else if (e.getSource() == buttonQuiz) {
            frame.dispose();
            new Quiz();
        } else if (e.getSource() == buttonSnake) {
            frame.dispose();
            new Snake();
        } else if (e.getSource() == buttonPong) {
            frame.dispose();
            new Pong();
        }
    }


}
