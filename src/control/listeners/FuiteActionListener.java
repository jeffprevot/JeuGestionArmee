/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.listeners;

import unitesDeCombat.hero.Heros;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author jeff
 */
public class FuiteActionListener extends WindowAdapter {

    private final Heros heros;

    public FuiteActionListener(Heros heros) {
        this.heros = heros;
    }

    @Override
    public void windowClosing(WindowEvent e) {

        JDialog frame = (JDialog) e.getSource();

        int response = JOptionPane.showConfirmDialog(
                e.getWindow(),
                "La fuite entraine la perte de 10% de ton armée.\n"
                + "Confirme la fuite, lâche !",
                "Fuite",
                JOptionPane.YES_NO_OPTION);

        if (response == 0) {

            int nb = heros.getTailleArmee() / 10;
            if (nb == 0) {
                nb = 1;
            }
            heros.supprimerSoldatByNb(nb, null);

            frame.dispose();

        }
    }

}
