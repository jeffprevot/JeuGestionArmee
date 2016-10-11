/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.listeners;

import control.HerosControler;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import ui.gestionNiveau.UIHeros;

/**
 *
 * @author jeff
 */
public class QuitterGestionHeros extends WindowAdapter {

    private final HerosControler herosControler;

    public QuitterGestionHeros(HerosControler herosControler) {
        this.herosControler = herosControler;
    }

    @Override
    public void windowClosing(WindowEvent e) {

        UIHeros frame = (UIHeros) e.getSource();

        if (!frame.isAlreadyOpened()) {
            int response = JOptionPane.showConfirmDialog(
                    e.getWindow(),
                    "Je te fais cadeau d'assez XP pour que tu puisses te créer un Héros à peu près potable,"
                    + "et tu refuses ?! Tu me craches à la gueule ?!",
                    "\"J'irai cracher sur vos tombes\"",
                    JOptionPane.YES_NO_OPTION);

            if (response == 0) {

                JOptionPane.showMessageDialog(null,
                        "C'est bien tu annonces l'ambiance de la partie.\n"
                        + "Méfies toi, j'ai bonne mémoire...",
                        "Ambiance...",
                        JOptionPane.ERROR_MESSAGE);

                // ici la vengeance de l'ordinateur vexé
                frame.setVisible(false);
                this.herosControler.valider();
            } else {
                frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            }
        }
    }
    
    public void afficherMessage() {
        
    }
}
