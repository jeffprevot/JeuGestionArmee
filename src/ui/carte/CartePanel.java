/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.carte;

import carte.CelluleType;
import control.CarteControler;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author jeff
 */
public class CartePanel extends JPanel {

    private List<CelluleCase> buttons;

    private transient final CarteControler carteControler;
    private transient final Map<String, BufferedImage> imagesMap;

    private final int nbRow;
    private final int nbColumn;

    public CartePanel(CarteControler carteControler, Map<String, BufferedImage> imagesMap) {

        this.carteControler = carteControler;
        this.imagesMap = imagesMap;

        this.nbRow = carteControler.getCarte().getDy();
        this.nbColumn = carteControler.getCarte().getDx();

        initUI();
    }

    private void initUI() {

        buttons = new ArrayList();

        this.setBorder(new LineBorder(Color.BLACK));
        this.setLayout(new GridLayout(nbRow, nbColumn, 0, 0));

        BufferedImage defaultImage = carteControler.getDefaultImage();
        BufferedImage voidImage = carteControler.getVoidImage();

        BufferedImage image;
        for (int i = 0; i < nbRow; i++) { //ligne

            for (int j = 0; j < nbColumn; j++) { // colonnes

                CelluleType cellType = carteControler.get(j, i).getType();

                if (cellType == null) {
                        image = defaultImage;
                } else {
                    image = imagesMap.get(cellType.getNom());
                }

                CelluleCase button = new CelluleCase(carteControler, image, voidImage);
                buttons.add(button);
                add(button);
            }
        }

        carteControler.setButtons(buttons);
    }
}
