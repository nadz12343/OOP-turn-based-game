import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
public class SaveLoadGame{

    public static void saveState(double ninjaHealth, double ninjaAttackPower, double enemyHealth, double enemyAttackPower){
        File saveData;
        try {        
            saveData = new File("Saves/save1.txt");
            if (!saveData.createNewFile()){
               BufferedWriter writer = new BufferedWriter(new FileWriter(saveData));
        
               writer.write(Double.toString(ninjaHealth));
               writer.newLine();
               
               writer.write(Double.toString(ninjaAttackPower));
               writer.newLine();
             
               writer.write(Double.toString(enemyHealth));
               writer.newLine();
               
               writer.write(Double.toString(enemyAttackPower));
               
               writer.close();
            }               
                else
                System.out.println("file already exist");
        } catch(Exception e) {        
            e.getMessage();
        }     
    }

    public static void loadState(Ninja n, Character c) {
        File saveData;
         try {        
            saveData = new File("Saves/save1.txt");
            if (!saveData.createNewFile()){
               BufferedReader reader = new BufferedReader(new FileReader(saveData));
        
               n.setHealth(Double.parseDouble(reader.readLine()));               
               n.setAttackPower(Double.parseDouble(reader.readLine()));
               
               c.setHealth(Double.parseDouble(reader.readLine()));               
               c.setAttackPower(Double.parseDouble(reader.readLine()));
               
               reader.close();
            }                  
        } catch(Exception e) {        
            e.getMessage();
        }     
       }

}