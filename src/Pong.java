import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Pong implements ActionListener{

    //PongPanel panelManager;

    JFrame frame;
    JPanel panelPic;
    JButton back;
    JButton replay;

    final int PANEL_WIDTH = 900;
    final int PANEL_HEIGHT = 600;

    Image backgroundImage;

   Pong(){

        // Load the background image
        backgroundImage = new ImageIcon("pictures/background2.jpg").getImage();

        // Create the custom panel with background image
        panelPic = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, this);

                // Set the font and color for the text
                g.setFont(new Font("Ink Free", Font.BOLD, 50));
                g.setColor(new Color(255, 255, 255, 200)); // White color with some transparency

                // Draw the text on the background image
                g.drawString("Pong", PANEL_WIDTH / 2 - 80, 80);
            }
        };
        panelPic.setLayout(null); // Use null layout to place components manually

        // Add a back button
        back = new JButton("Back");
        back.setBounds(10, 10, 59, 23); // Position the button
        back.setFont(new Font("ITALIC", Font.BOLD, 10));
        back.setFocusable(false);
        back.addActionListener(this);
        panelPic.add(back);

       replay = new JButton("Replay");
       replay.setBounds(PANEL_WIDTH - 92, 10, 69, 23); // Position the button
       replay.setFont(new Font("ITALIC", Font.BOLD, 10));
       replay.setFocusable(false);
       replay.addActionListener(this);
       panelPic.add(replay);



        // Frame setup
        frame = new JFrame("Pong");
        frame.setContentPane(panelPic); // Set the custom panel as the content pane
        frame.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("pictures/icon.png"); // Create an ImageIcon
        frame.setIconImage(image.getImage()); // Change icon of the app
        frame.add(new PongPanel());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            frame.dispose();
            new GUI();
        }else if (e.getSource() == replay) {
            frame.dispose();
            new Pong();
        }
    }

}
