/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import carte.Cellule;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import unitesDeCombat.joueur.Joueur;
import sauvegarde.ConnectionFichiers;
import sauvegarde.Sauvegarde;

/**
 *
 * @author jeff
 */
public class SauvegardeControler {

    public static void ecrire(List<Joueur> joueurList, List<List<Cellule>> carte) {

        String nomFichier = JOptionPane.showInputDialog(null, "Tape le nom de ta sauvegarde");

        if ((nomFichier != null) && (nomFichier.length() > 0)) {

            ConnectionFichiers fichier = new ConnectionFichiers(nomFichier + ".data");
            fichier.ecrire(new Sauvegarde(carte, joueurList));
        }

    }

    public static Sauvegarde lire() {

        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(null);
        File file = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }

        Sauvegarde sauvegarde= null;
        if (file != null) {
            ConnectionFichiers fichier = new ConnectionFichiers(file);

            sauvegarde = (Sauvegarde) fichier.lire();
        }
        return sauvegarde;
    }
}
