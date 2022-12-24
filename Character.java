import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
public abstract class Character {

    //health attribute of the creature
    private double health;
    private double maxHealth;

    private double attackPower; 
    // these are the position where the characters are going to displayed.
    private double x,y;
    
    private ArrayList<BufferedImage> ani;
    private int cols;
    private double currentFrame;

    private static String msg;
    public Character(double health, double attackPower, double x, double y,int cols ) {
        this.health = health;
        maxHealth = health;
  
        this.attackPower = attackPower;
        this.x = x;
        this.y = y;
        this.cols = cols;
        ani = new ArrayList<BufferedImage>();
        currentFrame = 0;
    }

    public void addAnimation(String path, int row){
        for(int col = 0; col < cols; col++){
          try {
          ani.add(ImageIO.read(Character.class.getResource(path + "0" + col + ".png")));       
        } catch (Exception e) {
            e.getMessage();
         }
       }
    }
           
    public void render(Graphics graphics, double spriteFrame){
          graphics.drawImage(ani.get((int) getFrame()), (int) x, (int) y, null);

    }
    
    public void render(Graphics graphics, double spriteFrame, int width, int height){
           graphics.drawImage(ani.get((int) getFrame()), (int) x, (int) y, width, height, null);
    }
    
    public double getHealth(){
        return health;
    }
   
    public void setHealth(double health){
        this.health = health;
    }
   
    public double getMaxHealth(){
        return maxHealth;
    }
     
    public double getAttackPower(){
        return attackPower;
    }
    
    public void setAttackPower(double amount){
       attackPower = amount;
    }        
    
    public void attack(Character c){ 
        c.setHealth(c.getHealth() - attackPower);
    }
       
    public abstract void update();
    
    public abstract void incrementSpriteFrame();
    
    public void setFrame(double newFrame) {
        currentFrame = newFrame; 
    }
         
    public double getFrame(){
        return currentFrame;
    }    
    
    public int getCols(){
        return cols;
    }
    
    public double getY(){
        return y;
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public double getX() {
        return x;
    }
    
    public void setY(double y){
        this.y = y;
    }
    
    public static void attackEnemy(Character c1, Character c2){
        c1.attack(c2);
    }
    
    public void setMessage(String message){
        msg = message;
    }
    
    public String getMessage(){
        return msg;
    }
    
    public static boolean isDead(Character c1) {
       if(c1.getHealth() <= 0) {
           return true;
        }
       return false; 
    }
    
}
