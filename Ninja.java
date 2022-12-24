import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
public class Ninja extends Character{

      //the amount the ninja can increase its health by
      private int increaseHealth;

      public Ninja(double health, double attackPower, double x, double y, int cols) {
       super(health, attackPower, x, y, cols);
       increaseHealth = 50;

       addAnimation("res/textures/n/a/Run-", 0);

       //addAnimation("res/textures/ninja/reverse/", 1);
      }

      public void update() {
          incrementSpriteFrame();

      }
      
      public void incrementSpriteFrame() {
         setFrame(getFrame() + 0.215);
         if(getFrame() > getCols()) {
            //frame will equal to 0
            setFrame(0);           
         }
      }
   
      public void attack(Character c){ 
        super.attack(c);
        setMessage("Damage dealt");
      }
       
      public void heal() {  
           setHealth(getHealth() + increaseHealth);
           if(getHealth() > getMaxHealth()){
               setHealth(getMaxHealth());              
           }
           setMessage("i healed myself up");
      }   
      
  
    public int getIncreaseHealth() {
        return increaseHealth;
    }
    
    //decrease the amount health is restored during each turn of the fight 
    public void setIncreaseHealth(){
        increaseHealth -= 5;
    }
  
}
