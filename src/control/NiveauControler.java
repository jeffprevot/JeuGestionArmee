/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import unitesDeCombat.hero.Heros;
import ui.gestionNiveau.UIGestionNiveauxSoldats;
import static ui.gestionNiveau.UIGestionNiveauxSoldats.niveauIndex;
import unitesDeCombat.armee.Armee;
import unitesDeCombat.armee.Soldat;

/**
 *
 * @author jeff
 */
public class NiveauControler {

    private final UIGestionNiveauxSoldats uiGestionNiveau;

    private Heros heros;

    private DefaultTableModel model;
    private List<Integer> niveauxInitiaux;

    public NiveauControler() {
        uiGestionNiveau = new UIGestionNiveauxSoldats(this);

    }

    public void gererNiveau(Heros heros) {

        if (heros.getJoueur().isJouable()) {

            this.model = uiGestionNiveau.getModel();

            this.heros = heros;
//            uiGestionNiveau.setHero(heros);

            

            uiGestionNiveau.afficher(heros);
            niveauxInitiaux = lireNiveaux();

        }
    }

    private List<Integer> lireNiveaux() {

        List<Integer> niveauxInitiaux = new ArrayList<>();

        for (int i = 0; i < model.getRowCount(); i++) {
            niveauxInitiaux.add((int) model.getValueAt(i, niveauIndex));
        }

        return niveauxInitiaux;
    }

    public boolean correctValues() {

        for (int rowIndex = 0; rowIndex < model.getRowCount(); rowIndex++) {

            int value = (int) model.getValueAt(rowIndex, niveauIndex);
            int valueInit = niveauxInitiaux.get(rowIndex);

            if (value < valueInit) {

                int cout = 0;
                for (int j = value; j < valueInit; j++) {
                    cout += Math.pow(2, j);
                }
                uiGestionNiveau.setDisplayedXPValue(uiGestionNiveau.getDisplayedXPValue() - cout);

                Armee armee = this.heros.getArmee();
                Soldat soldat = armee.getArmee().get(rowIndex);
                soldat.setNiveau(valueInit);

                uiGestionNiveau.fillTable(armee);
                uiGestionNiveau.updateLabels(armee);

                return false;
            }
        }

        return true;
    }

}
