import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Tic_tac_toe implements ActionListener {

    JFrame frame;
    JPanel panelPic;
    JButton back;
    JButton replay;

    Random random = new Random();
    JPanel button_panel = new JPanel(); // the panel for the buttons
    JButton[] button = new JButton[9];
    boolean player1_turn;

    JLabel labelWin; // Single label for win/draw messages
    JPanel panelGO; // Panel for win/draw message

    final int PANEL_WIDTH = 900;
    final int PANEL_HEIGHT = 600;

    Image backgroundImage;

    // Variables for the winning line
    private int[] winningLineStart = new int[2];
    private int[] winningLineEnd = new int[2];
    private boolean drawWinningLine = false;

    Tic_tac_toe() {

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
                g.drawString("TIC TAC TOE", PANEL_WIDTH / 2 - 200, 80);

                // Draw winning line if needed
                if (drawWinningLine) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setColor(new Color(38, 0, 77)); // Set the color for the winning line
                    g2d.setStroke(new BasicStroke(5)); // Set the thickness of the line
                    g2d.drawLine(winningLineStart[0]+30, winningLineStart[1], winningLineEnd[0]-30, winningLineEnd[1]);
                }
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

        // Add a replay button
        replay = new JButton("Replay");
        replay.setBounds(PANEL_WIDTH - 92, 10, 69, 23); // Position the button
        replay.setFont(new Font("ITALIC", Font.BOLD, 10));
        replay.setFocusable(false);
        replay.addActionListener(this);
        panelPic.add(replay);

        // Setup button panel
        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setOpaque(false); // Make the panel transparent
        button_panel.setBounds(150, 120, 600, 400); // Position the button panel within the frame

        // Add buttons to the button panel
        for (int i = 0; i < 9; i++) {
            button[i] = new JButton();
            button[i].setFont(new Font("MV Boli", Font.BOLD, 100));
            button[i].setFocusable(false);

            // Make buttons transparent
            button[i].setBackground(Color.BLACK);
            button[i].setOpaque(true); // Ensure button itself is not transparent
            button[i].setContentAreaFilled(false); // Remove background fill
            button[i].setForeground(Color.WHITE); // Set default text color
            button[i].addActionListener(this);
            button_panel.add(button[i]);
        }

        // Initialize and configure the win/draw label
        labelWin = new JLabel();
        labelWin.setFont(new Font("MV Boli", Font.BOLD, 40));
//labelWin.setBackground(new Color(34, 0, 102)); // Uncomment if you want a background color
        labelWin.setForeground(Color.WHITE);
        labelWin.setOpaque(true);
        labelWin.setHorizontalAlignment(SwingConstants.LEFT); // Center the text horizontally
        labelWin.setVerticalAlignment(SwingConstants.TOP); // Align the text to the top
        labelWin.setBounds(120, 100, 550, 350); // Ensure it covers button_panel
        labelWin.setVisible(false); // Initially hidden

// Create the panelGO and add labelWin to it
        panelGO = new JPanel(null); // Use null layout for precise positioning
        panelGO.setBounds(120, 100, 550, 350); // Same size and position as the button_panel
        panelGO.setOpaque(false); // Make the panel transparent
        panelGO.add(labelWin);
        panelPic.add(panelGO);


        // Add the button panel to the main panel
        panelPic.add(button_panel);

        // Frame setup
        frame = new JFrame("Tic Tac Toe");
        frame.setContentPane(panelPic); // Set the custom panel as the content pane
        frame.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("pictures/icon.png"); // Create an ImageIcon
        frame.setIconImage(image.getImage()); // Change icon of the app
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            frame.dispose();
            new GUI();
        } else if (e.getSource() == replay) {
            frame.dispose();
            new Tic_tac_toe();
        }

        for (int i = 0; i < 9; i++) {
            if (e.getSource() == button[i]) {
                // Add logic for button clicks in the game
                System.out.println("Button " + i + " clicked");

                if (player1_turn) {
                    if (button[i].getText().isEmpty()) {
                        button[i].setForeground(new Color(128, 0, 96));
                        button[i].setText("X");
                        player1_turn = false;
                        check();
                    }
                } else {
                    if (button[i].getText().isEmpty()) {
                        button[i].setForeground(new Color(0, 51, 128));
                        button[i].setText("O");
                        player1_turn = true;
                        check();
                    }
                }
            }
        }
    }

    public void firstTurn() {
        if (random.nextInt(2) == 0) {
            player1_turn = true;
        } else {
            player1_turn = false;
        }
    }

    public void check() {
        // Check X win conditions
        if ((button[0].getText().equals("X")) &&
                (button[1].getText().equals("X")) &&
                (button[2].getText().equals("X"))) {
            XWins(0, 1, 2, new int[]{150, 150 + 35, 750, 150 + 35}); // Pass winning line coordinates
        } else if ((button[3].getText().equals("X")) &&
                (button[4].getText().equals("X")) &&
                (button[5].getText().equals("X"))) {
            XWins(3, 4, 5, new int[]{150, 150 +170, 750, 150 + 170}); // Pass winning line coordinates
        } else if ((button[6].getText().equals("X")) &&
                (button[7].getText().equals("X")) &&
                (button[8].getText().equals("X"))) {
            XWins(6, 7, 8, new int[]{150, 150 +310, 750, 150 + 310}); // Pass winning line coordinates
        } else if ((button[0].getText().equals("X")) &&
                (button[3].getText().equals("X")) &&
                (button[6].getText().equals("X"))) {
            XWins(0, 3, 6, new int[]{150 + 55, 150, 150 + 115, 480}); // Pass winning line coordinates
        } else if ((button[1].getText().equals("X")) &&
                (button[4].getText().equals("X")) &&
                (button[7].getText().equals("X"))) {
            XWins(1, 4, 7, new int[]{150 + 260, 150, 150 + 320, 480}); // Pass winning line coordinates
        } else if ((button[2].getText().equals("X")) &&
                (button[5].getText().equals("X")) &&
                (button[8].getText().equals("X"))) {
            XWins(2, 5, 8, new int[]{150 + 495, 150, 150 + 555, 480}); // Pass winning line coordinates
        } else if ((button[0].getText().equals("X")) &&
                (button[4].getText().equals("X")) &&
                (button[8].getText().equals("X"))) {
            XWins(0, 4, 8, new int[]{150, 150, 740, 480}); // Pass winning line coordinates
        } else if ((button[2].getText().equals("X")) &&
                (button[4].getText().equals("X")) &&
                (button[6].getText().equals("X"))) {
            XWins(2, 4, 6, new int[]{680, 150, 220, 490}); // Pass winning line coordinates
        }

        // Check O win conditions
        if ((button[0].getText().equals("O")) &&
                (button[1].getText().equals("O")) &&
                (button[2].getText().equals("O"))) {
            OWins(0, 1, 2, new int[]{150, 150 + 35, 750, 150 + 35}); // Pass winning line coordinates
        } else if ((button[3].getText().equals("O")) &&
                (button[4].getText().equals("O")) &&
                (button[5].getText().equals("O"))) {
            OWins(3, 4, 5, new int[]{150, 150 +170, 750, 150 + 170}); // Pass winning line coordinates
        } else if ((button[6].getText().equals("O")) &&
                (button[7].getText().equals("O")) &&
                (button[8].getText().equals("O"))) {
            OWins(6, 7, 8, new int[]{150, 150 +310, 750, 150 + 310}); // Pass winning line coordinates
        } else if ((button[0].getText().equals("O")) &&
                (button[3].getText().equals("O")) &&
                (button[6].getText().equals("O"))) {
            OWins(0, 3, 6, new int[]{150 + 55, 150, 150 + 115, 480}); // Pass winning line coordinates
        } else if ((button[1].getText().equals("O")) &&
                (button[4].getText().equals("O")) &&
                (button[7].getText().equals("O"))) {
            OWins(1, 4, 7, new int[]{150 + 260, 150, 150 + 320, 480}); // Pass winning line coordinates
        } else if ((button[2].getText().equals("O")) &&
                (button[5].getText().equals("O")) &&
                (button[8].getText().equals("O"))) {
            OWins(2, 5, 8, new int[]{150 + 495, 150, 150 + 555, 480}); // Pass winning line coordinates
        } else if ((button[0].getText().equals("O")) &&
                (button[4].getText().equals("O")) &&
                (button[8].getText().equals("O"))) {
            OWins(0, 4, 8, new int[]{150, 150, 740, 480}); // Pass winning line coordinates
        } else if ((button[2].getText().equals("O")) &&
                (button[4].getText().equals("O")) &&
                (button[6].getText().equals("O"))) {
            OWins(2, 4, 6, new int[]{680, 150, 220, 490}); // Pass winning line coordinates
        } else if (isBoardFull()) {
            draw();
        }
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 9; i++) {
            if (button[i].getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void XWins(int i1, int i2, int i3, int[] lineCoordinates) {
        drawWinningLine = true;
        winningLineStart[0] = lineCoordinates[0];
        winningLineStart[1] = lineCoordinates[1];
        winningLineEnd[0] = lineCoordinates[2];
        winningLineEnd[1] = lineCoordinates[3];
        labelWin.setText("<html><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;GameOver!<br>&nbsp;&nbsp;PLAYER X WINS</html>");
        labelWin.setBackground(new Color(128, 0, 96));
        labelWin.setVisible(true);
        disableButtons();
    }

    public void OWins(int i1, int i2, int i3, int[] lineCoordinates) {
        drawWinningLine = true;
        winningLineStart[0] = lineCoordinates[0];
        winningLineStart[1] = lineCoordinates[1];
        winningLineEnd[0] = lineCoordinates[2];
        winningLineEnd[1] = lineCoordinates[3];
        labelWin.setText("<html><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;GameOver!<br>&nbsp;&nbsp;PLAYER O WINS</html>");
        labelWin.setBackground(new Color(0, 51, 128));
        labelWin.setVisible(true);
        disableButtons();
    }


    public void draw() {
        labelWin.setText("<html><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;GameOver!<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;IT'S A TEI</html>");
        labelWin.setBackground(new Color(38, 0, 77));
        labelWin.setVisible(true);
        disableButtons();
    }

    public void disableButtons() {
        for (int i = 0; i < 9; i++) {
            button[i].setEnabled(false);
        }
    }

}
