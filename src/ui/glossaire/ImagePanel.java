/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.glossaire;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author jeff
 */
public class ImagePanel extends JPanel {

    private BufferedImage myPicture = null;

    public ImagePanel() {
    }

    public ImagePanel(String imagePath) throws IOException {

        myPicture = ImageIO.read(new File(imagePath));
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (myPicture != null) {
            g.drawImage(myPicture, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }
    
    public void setImage(String imagePath) throws IOException {
        myPicture = ImageIO.read(new File(imagePath));
    }

}
