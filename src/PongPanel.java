import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class PongPanel extends JPanel implements Runnable{

    static final int SCREEN_WIDTH = 820;
    static final int SCREEN_HEIGHT = (int)(SCREEN_WIDTH * 0.55);
    static final Dimension SCREEN_SIZE = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;

    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    PongPaddle paddle1;
    PongPaddle paddle2;
    PongBall ball;
    PongScore score;


    PongPanel() {
        newPaddles();
        newBall();
        score = new PongScore(SCREEN_WIDTH, SCREEN_HEIGHT);

        this.setBounds(33, 100, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.requestFocusInWindow(); // Ajoutez cette ligne pour demander le focus

        gameThread = new Thread(this);
        gameThread.start();
    }


    public void newBall(){
        random = new Random();
        ball = new PongBall((SCREEN_WIDTH/2) - (BALL_DIAMETER/2), random.nextInt(SCREEN_HEIGHT-BALL_DIAMETER), BALL_DIAMETER,BALL_DIAMETER);
    }

    public void newPaddles(){
        paddle1 = new PongPaddle(0,(SCREEN_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2 = new PongPaddle(SCREEN_WIDTH-PADDLE_WIDTH,(SCREEN_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
    }

    @Override
    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());

        draw(graphics);

        g.drawImage(image, 0, 0, this);
    }


    public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    public void move(){

        paddle1.move();
        paddle2.move();
        ball.move();

    }

    public void checkCollision(){

        //bounce ball off top & buttom window edges
         if(ball.y <= 0)
             ball.setYDirection(-ball.yVelocity);
         if(ball.y >+ (SCREEN_HEIGHT-BALL_DIAMETER))
             ball.setYDirection(-ball.yVelocity);

         //bounces ball off paddles
        if(ball.intersects(paddle1)){
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;//optional for more difficulty
            if(ball.yVelocity > 0 )
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(ball.intersects(paddle2)){
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;//optional for more difficulty
            if(ball.yVelocity > 0 )
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        //stops paddles at window edges
        if(paddle1.y <=0)
            paddle1.y = 0;
        if(paddle1.y >= (SCREEN_HEIGHT-PADDLE_HEIGHT))
            paddle1.y = SCREEN_HEIGHT-PADDLE_HEIGHT;

        if(paddle2.y <=0)
            paddle2.y = 0;
        if(paddle2.y >= (SCREEN_HEIGHT-PADDLE_HEIGHT))
            paddle2.y = SCREEN_HEIGHT-PADDLE_HEIGHT;

        //give a payer 1 point and creates new paddles & ball
        if(ball.x <= 0){
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("player 2: "+score.player2);
        }
        if(ball.x >= SCREEN_WIDTH-BALL_DIAMETER){
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("player 1: "+score.player1);
        }

    }

    public void run() {

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;//nanoseconds
        double delta = 0 ;
        while(true){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                move();
                checkCollision();
                repaint();
                delta --;
            }
        }

    }

    public class AL extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            paddle1.KeyPressed(e);
            paddle2.KeyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            paddle1.KeyReleased(e);
            paddle2.KeyReleased(e);
        }
    }

}


