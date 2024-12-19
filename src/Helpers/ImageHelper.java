package Helpers;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageHelper {
    public static ImageIcon convertIconColor(String IconPath,int width,int height){
        try {
            // Load icon
            BufferedImage icon = ImageIO.read(new File(IconPath));
            // Change color to white using RescaleOp
            RescaleOp rescaleOp = new RescaleOp(1.0f, 255, null);  // Change color to white
            BufferedImage whiteCarImage = rescaleOp.filter(icon, null);
            ImageIcon carIcon = new ImageIcon(whiteCarImage.getScaledInstance(width,height,Image.SCALE_SMOOTH));  // Resize image
            return carIcon;
        } catch (Exception e) {
            return null;
        }
    }
}
