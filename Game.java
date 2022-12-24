import java.util.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
public class Game{    
    
        CatFighter catfighter;
        Ninja ninja;
        Robot robot;
        Level level;
        Window win ;
                
       private BufferStrategy bufferStrategy;
        //draws the image
        private Graphics graphics;
        //stores the background image
        private BufferedImage background;
    public void initialise(){
      
        catfighter = new CatFighter(65, 10, 200, 200, 3);
        robot = new Robot(50, 10, 400, 80, 8);
        ninja = new Ninja(70, 10, 0, 80, 6);
        
        win = new Window("Adventure", 475, 325, ninja, robot);
    
        win.createWindow();
        
        //CatFighter(double health, int speed, double attackPower, double x, double y, int rows, int cols, int minX, int maxX) {
        try {
          background = ImageIO.read(Game.class.getResource("res/textures/freetileset/png/BG/BG.png")); 
        } catch (Exception e){
          e.getMessage(); 
        }
  
        level = new Level();
        // win.getbutton().addActionListener(win);
        
    }
    
    
    public void updateGame(){
        ninja.update();
        catfighter.update();
        robot.update();
    }
        
    
    public void render(){
        bufferStrategy = win.getCanvas().getBufferStrategy();
        if(bufferStrategy == null) {
            win.getCanvas().createBufferStrategy(3); 
            return;
        }
        //(Graphics graphics, double spriteFrame, int width, int height){
            
            
        graphics = bufferStrategy.getDrawGraphics();   
        graphics.clearRect(0, 0, win.getWidth(), win.getHeight());      
        graphics.drawImage(background, 0, 0, null);
         //level.render(graphics);
        catfighter.render(graphics, (int)catfighter.getFrame());      
        robot.render(graphics, (int)robot.getFrame(), 75, 75);
        ninja.render(graphics, (int)ninja.getFrame(), 75, 75);
        bufferStrategy.show();
        graphics.dispose();
    }
    
    
    public void runGame(){
     int fps = 60;
     double timePerUpdate = 1000000000/fps;
     double delta = 0;
     long lastTime = System.nanoTime();
     long now;
    
     while(true){
        now = System.nanoTime();
        delta += (now-lastTime)/timePerUpdate;
        lastTime = now;
        if(delta >=1){
            updateGame();
            render();
            delta --;
        }
     }
    
    }   
  }