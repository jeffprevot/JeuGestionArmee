/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carte;

import control.CarteControler;
import control.MainControler;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import javax.swing.JPanel;
import ui.carte.CartePanel;

/**
 *
 * @author jeff
 */
public class Carte {

    private CarteControler carteControler;
    private MainControler mainControler;

    private Properties propertiesList;

    private CartePanel panel;

    private List<List<Cellule>> liste;
    private int dx, dy;
    private String nom;

    private transient final Random random = new Random();

    public Carte() {
    }
    
    public Carte(List<List<Cellule>> liste) {
        this.liste = liste;
        this.dy = liste.size();
        this.dx = liste.get(0).size();
    }
    
    public CartePanel getCartePanel() {
        return this.panel;
    }

    public void setControler(MainControler mainControler) {
        this.mainControler = mainControler;
        this.carteControler = mainControler.getCarteControler();
    }

    public void build() {

        liste = new ArrayList<>();
        int index = 0;

        for (int numligne = 0; numligne < dy; numligne++) {

            List<Cellule> ligne = new ArrayList<>();

            for (int numcol = 0; numcol < dx; numcol++) {

                ligne.add(new Cellule(mainControler, index));
                index++;
            }

            liste.add(ligne);
        }
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String type) {
        this.nom = type;
    }

    public void setListe(List<List<Cellule>> carte) {
        this.liste = carte;
    }
    
    public List<List<Cellule>> getListe() {
        return this.liste;
    }

    public Cellule get(int x, int y) {
        return liste.get(y).get(x);
    }

    public Cellule get(int index) {

        int col = index % dx;
        int row = index / dx;

        return this.get(col, row);
    }

    public void setProperties(Properties propertiesList) {
        this.propertiesList = propertiesList;
    }

    public Properties getProperties() {
        return propertiesList;
    }

    @Override
    public String toString() {

        String string = "null";
        if (propertiesList != null) {
            string = propertiesList.toString();
        }
        return "Carte{" + "propertiesList=" + string + ", carte=" + liste + ", dx=" + dx + ", dy=" + dy + ", nom=" + nom + '}';

    }

    public void add(CelluleType celluleType, int nbCellule) {

        for (int i = 0; i < nbCellule; i++) {

            CelluleType type = null;
            Cellule cellule = null;
            do {
                int x = random.nextInt(dx);
                int y = random.nextInt(dy);

                cellule = get(x, y);
                type = cellule.getType();

            } while (type != null);

            cellule.setType(celluleType);
        }
    }

    public String afficherTexte() {

        String output = "";

        for (List<Cellule> ligne : liste) {

            for (Cellule cell : ligne) {

                output += cell.getType() + ", ";

            }

            output += "\n";
        }

        return output;
    }

    public JPanel creerFrame(Map<String, BufferedImage> iconsMap) {
        panel = new CartePanel(carteControler, iconsMap);        
        return panel;
    }
}
