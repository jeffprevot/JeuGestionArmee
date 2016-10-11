/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.listeners;

import control.MainControler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author jeff
 */
public class QuitActionListener extends WindowAdapter implements ActionListener {

    private final MainControler mainControler;
    private final boolean confirmation;

    public QuitActionListener(MainControler mainControler, boolean confirmation) {
        this.mainControler = mainControler;
        this.confirmation = confirmation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        quitter();
    }

    @Override
    public void windowClosing(WindowEvent e) {

        quitter();
    }

    private void quitter() {

        if (confirmation) {

            int response = JOptionPane.showConfirmDialog(null,
                    "Tu t'en vas ? Dis le si je t'emmerde !\n"
                            + "Tu veux pas sauvegarder avant? Histoire de ne pas passer ta vie à jouer à un jeu qui t'insulte.\n"
                            + "Looser",
                    null,
                    JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                mainControler.sauverPartie();
            }
        }
        
        System.exit(0);

    }
}
