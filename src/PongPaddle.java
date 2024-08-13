import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class PongPaddle extends Rectangle{

    int id;
    int yVelocity;//how fast the paddle move
    int speed = 10;

    PongPaddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        this.id = id;

    }


    public void KeyPressed(KeyEvent e){

        switch(id){
            case 1:
                if(e.getKeyCode() == KeyEvent.VK_W){
                    setYDirection(-speed);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_S){
                    setYDirection(speed);
                    move();
                }
                break;

            case 2:
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    setYDirection(-speed);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    setYDirection(speed);
                    move();
                }
                break;
        }

    }

    public void KeyReleased(KeyEvent e){

        switch(id){
            case 1:
                if(e.getKeyCode() == KeyEvent.VK_W){
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_S){
                    setYDirection(0);
                    move();
                }
                break;

            case 2:
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    setYDirection(0);
                    move();
                }
                break;
        }

    }

    public void setYDirection(int yDirection){

        yVelocity = yDirection;

    }

    public void move(){

        y = y + yVelocity;
        
    }

    public void draw(Graphics g){

        if(id == 1)
            g.setColor(new Color(50, 10, 200));
        else
            g.setColor(new Color(160, 0, 20));
        g.fillRect(x,y,width,height);

    }

}
