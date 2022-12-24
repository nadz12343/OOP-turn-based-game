public class Robot extends Character {

    
    private int laserBeamDamage;
    
    //increases the attack of the robot by a certain percentage
    private double strengthenAmount;
 //(double health, int speed, double attackPower, double x, double y, int rows, int cols, int minX, int maxX) {
        
   public Robot(double health, double attackPower, double x, double y, int cols) {
       super(health, attackPower, x, y, cols);
       laserBeamDamage = 20;
       strengthenAmount = 0.15;
       
      // addAnimation("res/textures/robotfree/png/", 0);
      addAnimation("res/textures/robotfree/png/reverse/", 1);
   }
    
   public void update() {
       incrementSpriteFrame();
    }      
  
   public void incrementSpriteFrame() {
         setFrame(getFrame() + 0.3);
         if(getFrame() > getCols()) {
            //frame will equal to 0
            setFrame(0);           
         }
   }
   
      public void attack(Character c){
       // the attack of the robot takes its health into account too
       double healthMultiplier = 0.07 * getHealth();
       //decreases the damage of the laserbeam each time the robot attacks
       laserBeamDamage -= 2;   
       setMessage("My laserBeam damage is decreased");
       c.setHealth(c.getHealth() - (getAttackPower() + healthMultiplier));
   }
    
   
   public void strengthenAttack(){
       setAttackPower(strengthenAmount);
   } 
   
   public void setStrengthenAmount(){
       strengthenAmount += 0.025;
   }
    
   public double getStrengthenAmount(){
       return strengthenAmount;
   }
   
   public void laserBeam(Character c){
       setMessage("I have used laser beam attack");
       c.setHealth(c.getHealth() - laserBeamDamage);       
   }
       
    
    }