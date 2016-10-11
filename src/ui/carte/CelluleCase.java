/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.carte;

import control.CarteControler;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author jeff
 */
public class CelluleCase extends JPanel implements MouseListener {

    private final CarteControler carteControler;
    private final BufferedImage imageStock;
    private BufferedImage imageDisplayed;
    private final BufferedImage voidImage;

    public CelluleCase(CarteControler carteControler, BufferedImage image, BufferedImage voidImage) {

        this.imageStock = image;
        this.carteControler = carteControler;
        this.voidImage = voidImage;
        this.addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageDisplayed, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    @Override
    public void setEnabled(boolean enable) {
        super.setEnabled(enable);

        if (isEnabled()) {
            this.imageDisplayed = imageStock;
        } else {
            this.imageDisplayed = voidImage;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        carteControler.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
