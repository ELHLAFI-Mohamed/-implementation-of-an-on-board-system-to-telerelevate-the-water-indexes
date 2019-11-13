import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
 
public class Panneau extends JPanel {
  public void paintComponent(Graphics g){
    try {
      Image img = ImageIO.read(new File("eau.jpg"));
      g.drawImage(img, 0, 0, this);
      g.drawImage(img,1300, 0, this);
      g.drawImage(img, 650, 0, this);
      
      //Pour une image de fond
      //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    } catch (IOException e) {
      e.printStackTrace();
    }                
  }               
}