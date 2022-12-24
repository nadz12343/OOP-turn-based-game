import java.util.*;
public class CatFighter extends Character {

    // the damage done by using fireball
     private double fireball;
     
    public CatFighter(double health, double attackPower, double x, double y, int cols) {
        super(health, attackPower, x, y, cols);
        fireball = 25;
        addAnimation("res/textures/cat_ani/", 0);
        addAnimation("res/textures/cat_ani/r", 1);
    }

    public void update(){
        incrementSpriteFrame();
     //   movement(1, getDirection());
    }
    
    public void incrementSpriteFrame(){
        setFrame(getFrame() + 0.2);
        if(getFrame() > 3) {
            //frame will equal to 0
            setFrame(0);           
        }
    }    
    
    public void attack(Character c){ 
        double newAttackPower = (getAttackPower() * attackMultiplier());
        setMessage("the catfighter attack is increased to " + newAttackPower + " for temporary");
        setFireball(getFireball() + 1);
        c.setHealth(c.getHealth() - newAttackPower);
    }
    
        
    public double attackMultiplier() {
        return Math.random();
    }
    
    public double getFireball(){
        return fireball;
    }
    
    public void setFireball(double damage){
        fireball = damage;
    }
    
    public void fireballAttack(Character c){
       c.setHealth(c.getHealth() - fireball);
    }    
}