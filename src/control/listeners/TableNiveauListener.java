/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.listeners;

import unitesDeCombat.armee.Armee;
import unitesDeCombat.armee.Soldat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import ui.gestionNiveau.UIGestionNiveauxSoldats;

/**
 *
 * @author jeff
 */
public class TableNiveauListener implements TableModelListener {

    private final UIGestionNiveauxSoldats uiGestionNiveau;
    private final JTable table;
    private final Armee armee;
    private final int columnIndex;

    private final DefaultTableModel model;

    private final List<Integer> niveauxPrecedents;
    private final List<Boolean> changedTab;

    public TableNiveauListener(UIGestionNiveauxSoldats uiGestionNiveau, Armee armee, int columnIndex) {

        this.uiGestionNiveau = uiGestionNiveau;
        this.table = uiGestionNiveau.getTable();
        this.armee = armee;
        this.columnIndex = columnIndex;

        model = (DefaultTableModel) table.getModel();

        niveauxPrecedents = new ArrayList<>();
        changedTab = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            niveauxPrecedents.add((Integer) model.getValueAt(i, columnIndex));
            changedTab.add(Boolean.FALSE);
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {

        int rowIndex = table.getSelectedRow();

        if (rowIndex > -1) {

            if (!changedTab.get(rowIndex)) {
                
                Integer niveauDemande = (int) model.getValueAt(rowIndex, columnIndex);

                double cout = 0d;
                int nivPrec = niveauxPrecedents.get(rowIndex);

                if (niveauDemande != nivPrec) {

                    if (niveauDemande > nivPrec) {
                        for (int i = nivPrec; i < niveauDemande; i++) {
                            cout += Math.pow(2, i);
                        }
                    } else {
                        for (int i = niveauDemande; i < nivPrec; i++) {
                            cout += -Math.pow(2, i);
                        }
                    }

                    double displayedXP = uiGestionNiveau.getDisplayedXPValue();

                    uiGestionNiveau.setDisplayedXPValue(displayedXP - cout);
                    
                    Soldat soldat = armee.getArmee().get(rowIndex);
                    soldat.setNiveau(niveauDemande);

                    changedTab.set(rowIndex, Boolean.TRUE);
                    niveauxPrecedents.set(rowIndex, niveauDemande);
                }
            }
        }
    }
}
