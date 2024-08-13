import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Quiz implements ActionListener{

    JFrame frame;
    JPanel panelPic;
    JButton back;
    JButton replay;

    final int PANEL_WIDTH = 900;
    final int PANEL_HEIGHT = 600;

    String[] questions = {
            "Which company created java?",
            "Which year was java created?",
            "What wad java originally called?",
            "Who is credited with creating java?"
    };
    String[][] options = {
            {"Sun Microsystems","Starbucks","Microsoft","Alphabet"},
            {"1989","1996","1972","1492"},
            {"Apple","Latte","Oak","Koffing"},
            {"Steve Jobs","Bill Gates","James Gosling","Mark Zuckerburg"}
    };
    char[] answers = {
            'A','B','C','C'
    };
    char guess;
    char answer;
    int index;
    int correct_guesses = 0;
    int total_questions = questions.length;
    int result;
    int seconds = 10;

    JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();
    //JLabel time_label = new JLabel();
    JLabel seconds_left = new JLabel();
    JTextField number_right = new JTextField();
    JTextField percentage = new JTextField();

    Image backgroundImage;


    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == back){
                frame.dispose();
                new GUI();
            }else if (e.getSource() == replay) {
                frame.dispose();
                new Quiz();
            }
            seconds --;
            seconds_left.setText(String.valueOf(seconds));
            if(seconds <= 0){
                dispalyAnswer();
            }
        }
    });


    public Quiz(){

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
                g.drawString("Quiz", PANEL_WIDTH / 2 - 80, 80);
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


        //textField
        textField.setBounds(20,130,PANEL_WIDTH-40,50);
        textField.setBackground(new Color(0, 0, 0, 0));
        textField.setForeground(Color.WHITE);
        textField.setFont(new Font("Ink Free",Font.BOLD,35));
        textField.setBorder(BorderFactory.createBevelBorder(1));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);
        textField.setFocusable(false);
        //textField.setText("hello there");
        textField.setOpaque(false);

        //textArea
        textArea.setBounds(20,190,PANEL_WIDTH-40,50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(0, 0, 0,0));
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font("MV Boli",Font.BOLD,30));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false);
        textArea.setFocusable(false);
        //textArea.setText("hello there");
        textArea.setOpaque(false);

        //buttons:
        buttonA.setBounds(80,280,50,40);
        buttonA.setFont(new Font("MV Boli",Font.BOLD,20));
        buttonA.setText("A");
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);

        buttonB.setBounds(80,280+60,50,40);
        buttonB.setFont(new Font("MV Boli",Font.BOLD,20));
        buttonB.setText("B");
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);

        buttonC.setBounds(80,280+60+60,50,40);
        buttonC.setFont(new Font("MV Boli",Font.BOLD,20));
        buttonC.setText("C");
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);

        buttonD.setBounds(80,280+60+60+60,50,40);
        buttonD.setFont(new Font("MV Boli",Font.BOLD,20));
        buttonD.setText("D");
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);

        //answer label
        answer_labelA.setBounds(80+50+30,280,PANEL_WIDTH-(80+50+30),40);
        answer_labelA.setFont(new Font("MV Boli",Font.BOLD,30));
        answer_labelA.setForeground(Color.WHITE);
        //answer_labelA.setText("hiiiiiiiiiiiiiiiii");

        answer_labelB.setBounds(80+50+30,280+60,PANEL_WIDTH-(80+50+30),40);
        answer_labelB.setFont(new Font("MV Boli",Font.BOLD,30));
        answer_labelB.setForeground(Color.WHITE);
        //answer_labelB.setText("hiiiiiiiiiiiiiiiii");

        answer_labelC.setBounds(80+50+30,280+60+60,PANEL_WIDTH-(80+50+30),40);
        answer_labelC.setFont(new Font("MV Boli",Font.BOLD,30));
        answer_labelC.setForeground(Color.WHITE);
        //answer_labelC.setText("hiiiiiiiiiiiiiiiii");

        answer_labelD.setBounds(80+50+30,280+60+60+60,PANEL_WIDTH-(80+50+30),40);
        answer_labelD.setFont(new Font("MV Boli",Font.BOLD,30));
        answer_labelD.setForeground(Color.WHITE);
        //answer_labelD.setText("hiiiiiiiiiiiiiiiii");

        //timer
        seconds_left.setBounds(PANEL_WIDTH-120,280+60+60+60+20,80,60);
        seconds_left.setBackground(new Color(0,0,0));
        seconds_left.setOpaque(false);
        seconds_left.setForeground(new Color(200,0,0));
        seconds_left.setFont(new Font("MV Boli",Font.BOLD,42));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1));
        seconds_left.setFocusable(false);
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setText(String.valueOf(seconds));

        number_right.setBounds(350,275,200,100);
        number_right.setBackground(new Color(0,0,0,100));
        number_right.setForeground(Color.WHITE);
        number_right.setFont(new Font("MV Boli",Font.BOLD,50));
        number_right.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);
        number_right.setFocusable(false);
        number_right.setOpaque(false);


        percentage.setBounds(350,375,200,100);
        percentage.setBackground(new Color(25,25,25));
        percentage.setForeground(Color.WHITE);
        percentage.setFont(new Font("MV Boli",Font.BOLD,50));
        percentage.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);
        percentage.setFocusable(false);
        percentage.setOpaque(false);


        // Frame setup
        frame = new JFrame("Quiz");
        frame.setContentPane(panelPic); // Set the custom panel as the content pane
        frame.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("pictures/icon.png"); // Create an ImageIcon
        frame.setIconImage(image.getImage()); // Change icon of the app

        frame.add(textField);
        frame.add(textArea);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);
        frame.add(seconds_left);


        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);

        nextQuestion();
    }


    //buttons:
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            frame.dispose();
            new GUI();
        }else if (e.getSource() == replay) {
            frame.dispose();
            new Quiz();
        }else {
            buttonA.setEnabled(false);
            buttonB.setEnabled(false);
            buttonC.setEnabled(false);
            buttonD.setEnabled(false);
        }

        if(e.getSource() == buttonA){
            answer = 'A';
            if(answer == answers[index]){
                correct_guesses++;
            }
        }
        if(e.getSource() == buttonB){
            answer = 'B';
            if(answer == answers[index]){
                correct_guesses++;
            }
        }
        if(e.getSource() == buttonC){
            answer = 'C';
            if(answer == answers[index]){
                correct_guesses++;
            }
        }
        if(e.getSource() == buttonD){
            answer = 'D';
            if(answer == answers[index]){
                correct_guesses++;
            }
        }

        dispalyAnswer();

    }


    //methods:
    public void nextQuestion(){

        if(index >= total_questions){
            results();
        }else {
            textField.setText("Question "+(index+1));
            textArea.setText(questions[index]);
            answer_labelA.setText(options[index][0]);
            answer_labelB.setText(options[index][1]);
            answer_labelC.setText(options[index][2]);
            answer_labelD.setText(options[index][3]);
            timer.start();
        }

    }

    public void dispalyAnswer(){

        timer.stop();

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(answers[index] != 'A')
            answer_labelA.setForeground(new Color(160, 0, 20));
        if(answers[index] != 'B')
            answer_labelB.setForeground(new Color(160, 0, 20));
        if(answers[index] != 'C')
            answer_labelC.setForeground(new Color(160, 0, 20));
        if(answers[index] != 'D')
            answer_labelD.setForeground(new Color(160, 0, 20));
        if(answers[index] == 'A')
            answer_labelA.setForeground(new Color(50, 10, 200));
        if(answers[index] == 'B')
            answer_labelB.setForeground(new Color(50, 10, 200));
        if(answers[index] == 'C')
            answer_labelC.setForeground(new Color(50, 10, 200));
        if(answers[index] == 'D')
            answer_labelD.setForeground(new Color(50, 10, 200));


        Timer pause = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(e.getSource() == back){
                    frame.dispose();
                    new GUI();
                }else if (e.getSource() == replay) {
                    frame.dispose();
                    new Quiz();
                }

                answer_labelA.setForeground(Color.WHITE);
                answer_labelB.setForeground(Color.WHITE);
                answer_labelC.setForeground(Color.WHITE);
                answer_labelD.setForeground(Color.WHITE);

                answer = ' ';
                seconds = 10;
                seconds_left.setText(String.valueOf(seconds));

                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);

                index++;
                nextQuestion();

            }
        });
        pause.setRepeats(false);
        pause.start();

    }

    public void results(){

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        result = (int)((correct_guesses/(double)total_questions)*100);
        textField.setText("RESULTS !");
        textArea.setText("       Congratulations on completing the quiz!");
        answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");

        number_right.setText("("+correct_guesses+"/"+total_questions+")");
        percentage.setText(result+"%");

        frame.add(percentage);
        frame.add(number_right);

    }
}
