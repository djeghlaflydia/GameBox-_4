import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class SnakeManager extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 851;
    static final int SCREEN_HEIGHT = 600-150;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 75;

    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyPartes = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    SnakeManager(){

        random = new Random();
        //this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBounds(18,100,SCREEN_WIDTH,SCREEN_HEIGHT);
        this.setBackground(new Color(0,0,0,200));
        //this.setOpaque(true);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapet());

        startGame();



    }


    public void startGame(){
        newApple();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        if(running) {
            g.setColor(new Color(255, 255, 255, 10));

            // Draw vertical lines
            for (int i = 0; i <= SCREEN_WIDTH / UNIT_SIZE; i++) {
                int x = i * UNIT_SIZE;
                g.drawLine(x, 0, x, SCREEN_HEIGHT);
            }

            // Draw horizontal lines
            for (int i = 0; i <= SCREEN_HEIGHT / UNIT_SIZE; i++) {
                int y = i * UNIT_SIZE;
                g.drawLine(0, y, SCREEN_WIDTH, y);
            }

            //Draw the apple
            g.setColor(new Color(160, 0, 20));
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            //draw the snake
            for (int i = 0; i < bodyPartes; i++) {
                if (i == 0) {
                    g.setColor(new Color(50, 10, 200));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(0, 51, 128));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(new Color(71, 0, 179));
            g.setFont(new Font("INK Free",Font.BOLD,40));
            FontMetrics metrics = getFontMetrics(g.getFont());//to but the text in the middel
            g.drawString("Score: "+applesEaten,(SCREEN_HEIGHT - (metrics.stringWidth("Score: "+applesEaten))/2)-30,g.getFont().getSize());

        }else{
            gameOver(g);
        }

    }

    public void newApple(){

        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;

    }

    public void move(){

        for(int i = bodyPartes; i>0 ; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch(direction){
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] +UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }

    }

    public void checkApple(){

        if((x[0] == appleX) && (y[0] == appleY)){
            bodyPartes++;
            applesEaten++;
            newApple();
        }

    }

    public void checkCollisions(){
        //checks if head collides with body
        for(int i = bodyPartes; i>0 ; i--){
            if((x[0] == x[i]) && (y[0] == y[i])){
                running = false;
            }
        }
        //checks if head touches left border
        if(x[0] < 0){
            running = false;
        }
        //checks if head touches right border
        if(x[0] > SCREEN_WIDTH-UNIT_SIZE){
            running = false;
        }
        //checks if head touches top border
        if(y[0] < 0){
            running = false;
        }
        //checks if head touches buttom border
        if(y[0] > SCREEN_HEIGHT-UNIT_SIZE){
            running = false;
        }
        if(!running){
            timer.stop();
        }
    }

    public void gameOver(Graphics g){
        //score
        g.setColor(new Color(71, 0, 179));
        g.setFont(new Font("INK Free",Font.BOLD,40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());//to but the text in the middel
        g.drawString("Score: "+applesEaten,(SCREEN_HEIGHT - (metrics1.stringWidth("Score: "+applesEaten))/2)-30,g.getFont().getSize());
        //GameOver text
        g.setColor(new Color(71, 0, 179));
        g.setFont(new Font("INK Free",Font.BOLD,70));
        FontMetrics metrics2 = getFontMetrics(g.getFont());//to but the text in the middel
        g.drawString("Game Over !",SCREEN_HEIGHT - (metrics2.stringWidth("Game Over !"))/2,SCREEN_HEIGHT/2);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }


    public class MyKeyAdapet extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT :
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT :
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP :
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN :
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;
            }
        }
    }



}
