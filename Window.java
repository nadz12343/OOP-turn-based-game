import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Window implements ActionListener{
    
    private JFrame frame;
    private JFrame mainMenuFrame;
    private Canvas canvas;
    
    private String title;
    
    Ninja n;
    Character c;
    private int width;
    private int height;
    
    JButton attackButton = new JButton("Attack");
    JButton healButton = new JButton("Heal");
    JButton saveButton;
    JButton loadButton;    

    
    JLabel playerHealth;
    JLabel playerAttackPower;
    JLabel enemyHealth;
    JLabel enemyAttackPower;

    JLabel commentary;
    JLabel playerMsg;
    JLabel enemyMsg;
    public Window(String title, int width, int height, Ninja n, Character c){
        this.title = title;
        this.width = width;
        this.height = height;
      
        saveButton = new JButton("Save");
        loadButton = new JButton("Load Save");
        
        attackButton.setBounds(0, 285, width, 20);
        healButton.setBounds(0, 305, width, 20);
        saveButton.setBounds(0, 265, width, 20);  
        loadButton.setBounds(0, 245, width, 20);  


        
        this.n = n;
        this.c = c;

        attackButton.addActionListener(this);
        healButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        initialiseLabels();
              
    }
    
    
    public void initialiseLabels(){
        playerHealth = new JLabel("Ninja Health: " + n.getHealth());
        playerAttackPower = new JLabel("Ninja attack power: " + n.getAttackPower());
        
        enemyHealth = new JLabel("Enemy Health: " + c.getHealth());
        enemyAttackPower = new JLabel("Enemy attack power: " + c.getAttackPower());

        playerMsg = new JLabel("Ninja Message");
        enemyMsg =  new JLabel("Enemy Message");       
        
        playerMsg.setBounds(5, 165, width/2 - 10, 20);
        playerMsg.setOpaque(true);
        playerMsg.setBackground(Color.white);
        
        enemyMsg.setBounds(width/2 + 10, 165, 470, 20);
        enemyMsg.setOpaque(true);
        enemyMsg.setBackground(Color.white);        
        
        playerHealth.setBounds(5, 20, width/2 - 10, 20);
        playerHealth.setOpaque(true);
        playerHealth.setBackground(Color.white);
        
        playerAttackPower.setBounds(5, 20 + 30, width/2 - 10, 20);
        playerAttackPower.setOpaque(true);
        playerAttackPower.setBackground(Color.white);
              
        enemyHealth.setBounds(width/2 + 10, 20, 470, 20);
        enemyHealth.setOpaque(true);
        enemyHealth.setBackground(Color.white);
        
        enemyAttackPower.setBounds(width/2 + 10, 20 + 30, 470, 20);
                       
        commentary = new JLabel("This is where the battle commentary occurs!");
        commentary.setBounds(0, 200, width, 40);

        commentary.setOpaque(true);
        commentary.setBackground(Color.white);
    }
    
    public void updateLabels() {
        playerHealth.setText("Ninja Health: " + n.getHealth());
        playerAttackPower.setText("Ninja attack power: " + n.getAttackPower());
        
        enemyHealth.setText("Enemy Health: " + c.getHealth());
        enemyAttackPower.setText("Enemy attack power: " + c.getAttackPower());
    }
    
    public void createMainMenu() {
           // creating a new instance of JFrame
        mainMenuFrame = new JFrame(title);
       // setting the dimension of the frame
        mainMenuFrame.setSize(width, height);
       // displays the window on the screen
        mainMenuFrame.setVisible(true);
       // prevents changing the size of the frame
        mainMenuFrame.setResizable(false);
       //shows the frame at the centre of the screen as opposed from top left
        mainMenuFrame.setLocationRelativeTo(null);
       //makes sure the program is terminated once closed
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.add(saveButton);
        
    }
    
    public void createWindow(){
        // creating a new instance of JFrame
        frame = new JFrame(title);
        // setting the dimension of the frame
        frame.setSize(width, height);
        // displays the window on the screen
        frame.setVisible(true);
        // prevents changing the size of the frame
        frame.setResizable(false);
        //shows the frame at the centre of the screen as opposed from top left
        frame.setLocationRelativeTo(null);
        //makes sure the program is terminated once closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        frame.add(attackButton);
        frame.add(healButton);
        frame.add(saveButton);
        frame.add(loadButton);
        
        frame.add(playerHealth);
        frame.add(playerAttackPower);
        frame.add(enemyHealth);
        frame.add(enemyAttackPower);
        frame.add(commentary);
        frame.add(playerMsg); 
        frame.add(enemyMsg);
               
        // canvas displays and draw images on the window(the frame)
        canvas = new Canvas();
         
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        // displays canvas on the frame
        frame.add(canvas);
        frame.pack();
    }    
    
    
    public void actionPerformed(ActionEvent e){ 
        
        if (e.getSource() == attackButton) {
         Character.attackEnemy(n, c);
         playerMsg.setText(n.getMessage());

         if(c instanceof Robot){
            commentary.setText("damage is dealt to the robot and its health is now, " + c.getHealth());
 
            if(Math.random() > 0.5)
            ((Robot)c).laserBeam(n);  
            else
            Character.attackEnemy(c, n);            
            enemyMsg.setText(c.getMessage());
         }
         updateLabels();
                       
         if (Character.isDead(n)){
            commentary.setText("Game over");
            return;
         }
        
         if (Character.isDead(c)){
            commentary.setText("Level Complete!");           
         }
        }
    
        if (e.getSource() == healButton) {
         n.heal();
         playerMsg.setText(n.getMessage());
         updateLabels();
        }
     
       if(e.getSource() == saveButton) {
         SaveLoadGame.saveState(n.getHealth(), n.getAttackPower(), c.getHealth(), c.getAttackPower());
         commentary.setText("You have saved the game!");           
        }
        
       if (e.getSource() == loadButton) {
           SaveLoadGame.loadState(n, c);
           updateLabels();
           commentary.setText("Save State Has Been Loaded!");           

       }
       
    }
    
    public Canvas getCanvas(){
        return canvas;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public void setCanvas(Canvas canvas){
        this.canvas = canvas;
    }
    
    public JButton getbutton(){
        return attackButton;
    }
    
    public JFrame getFrame(){
        return frame;
    }
    
 
}