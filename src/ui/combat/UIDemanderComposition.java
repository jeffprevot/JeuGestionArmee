/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.combat;

import unitesDeCombat.armee.Armee;
import unitesDeCombat.armee.Soldat;
import com.ezware.oxbow.swingbits.table.filter.TableRowFilterSupport;
import control.CombatControler;
import control.listeners.FuiteActionListener;
import environement.Handicap;
import javax.swing.JOptionPane;
import unitesDeCombat.hero.Heros;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import utils.Utils;

/**
 *
 * @author jeff
 */
public class UIDemanderComposition extends javax.swing.JDialog {

    private final int checkedIndex = 0;
    private final int nomSoldatIndex = 1;
    private final int raceIndex = 2;
    private final int classeIndex = 3;
    private final int niveauIndex = 4;
    private final int pcIndex = 5;
    private final int ptIndex = 6;
    private final int vitesseIndex = 7;
    private final int ptotalIndex = 8;

    private final DefaultTableModel model;

    private final Armee armee;
    private final Heros heros;
    private final Handicap handicap;
    private final CombatControler combatControler;

    /**
     * Creates new form UICombat
     *
     * @param heros
     * @param combatControler
     * @param handicap
     */
    public UIDemanderComposition(CombatControler combatControler, Heros heros, Handicap handicap) {
        initComponents();

        this.combatControler = combatControler;
        this.heros = heros;
        this.armee = heros.getArmee();
        this.handicap = handicap;

        model = (DefaultTableModel) table.getModel();

        labelNomHeros.setText(heros.getNom());
        labelSelectionPC.setText("");
        labelSelectionPT.setText("");
        labelSelectionValorisation.setText("");
        labelEffectif.setText("");
        labelPourcentageArmee.setText("");
        labelNbSoldats.setText("");

        fillTable();

        TableRowFilterSupport.forTable(table).searchable(true).apply();

        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                setCaracteristiquesSelection();
            }
        });

        this.addWindowListener(new FuiteActionListener(heros));
    }

    public void setGeographie(String geographie) {
        this.geographieLabel.setText(geographie);
    }

    private void fillTable() {

        model.setRowCount(armee.size());

        int index = 0;
        for (Soldat soldat : armee) {

            model.setValueAt(false, index, checkedIndex);
            model.setValueAt(soldat.getNom(), index, nomSoldatIndex);
            model.setValueAt(soldat.getRace().getNom(), index, raceIndex);
            model.setValueAt(soldat.getClasse().getNom(), index, classeIndex);
            model.setValueAt(soldat.getNiveau(), index, niveauIndex);

            model.setValueAt(Utils.round(soldat.getPuissanceCombat() * handicap.getGeographieCombat()), index, pcIndex);

            double pt = soldat.getPuissanceTir();
            double gpt = handicap.getGeographieTir();
            double ptrtgpt = pt * gpt;
            model.setValueAt(Utils.round(ptrtgpt), index, ptIndex);

            model.setValueAt(Utils.round(soldat.getCapaciteDeplacement()), index, vitesseIndex);
            model.setValueAt(Utils.round(soldat.getPuissance() * handicap.getGeographieTotal()), index, ptotalIndex);

            index++;
        }

    }

    private void setCaracteristiquesSelection() {

        Armee armee = getSelection();

        Double tt = armee.getPuissanceTir();
        Double ptDouble = tt * handicap.getGeographieTir();

        this.labelNbSoldats.setText(String.valueOf(armee.size()));
        this.labelSelectionPC.setText((Utils.round((armee.getPuissanceCAC() * handicap.getGeographieCombat()))).toString());
        this.labelSelectionPT.setText((Utils.round((armee.getPuissanceTir() * handicap.getGeographieTir()))).toString());
        this.labelSelectionValorisation.setText(Utils.round(armee.valoriser()).toString());
        this.labelEffectif.setText(String.valueOf(armee.size()));
        this.labelPourcentageArmee.setText(Utils.round(((double) armee.size() / (double) heros.getTailleArmee()) * 100).toString());

    }

    public Armee getSelection() {

        Armee armeeSelectionnee = new Armee();

        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, checkedIndex) != null && (boolean) model.getValueAt(i, checkedIndex) == true) {

                int numero = i;
                Soldat selec = armee.getArmee().get(numero);
                armeeSelectionnee.ajouter(selec);

            }
        }
        return armeeSelectionnee;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        geographieLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelSelectionPC = new javax.swing.JLabel();
        labelSelectionPT = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelSelectionValorisation = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelEffectif = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        labelPourcentageArmee = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        labelNbSoldats = new javax.swing.JLabel();
        buttonValider = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        labelNomHeros = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Choix des soldats");
        setLocationByPlatform(true);
        setModalExclusionType(null);
        setResizable(false);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "Nom", "Race", "Classe", "Niveau", "PC", "PT", "Vitesse", "P Tot."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(table);

        jLabel1.setText("Géographie :");

        geographieLabel.setText("jLabel2");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Caractéristiques de la sélection :");

        jLabel3.setText("PC :");

        jLabel4.setText("PT :");

        labelSelectionPC.setText("jLabel5");

        labelSelectionPT.setText("jLabel6");

        jLabel5.setText("Valorisation :");

        labelSelectionValorisation.setText("jLabel6");

        jLabel6.setText("Effectif :");

        labelEffectif.setText("jLabel7");

        jLabel7.setText("% / armée :");

        labelPourcentageArmee.setText("jLabel9");

        jLabel9.setText("Nombre de soldats :");

        labelNbSoldats.setText("jLabel10");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPourcentageArmee)
                    .addComponent(labelEffectif)
                    .addComponent(labelSelectionPC)
                    .addComponent(labelSelectionPT)
                    .addComponent(labelSelectionValorisation)
                    .addComponent(labelNbSoldats))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(labelNbSoldats))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labelSelectionPC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(labelSelectionPT))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(labelSelectionValorisation))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(labelEffectif))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(labelPourcentageArmee))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonValider.setText("Combattre");
        buttonValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonValiderActionPerformed(evt);
            }
        });

        jLabel8.setText("Héros :");

        labelNomHeros.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelNomHeros.setText("jLabel9");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(geographieLabel))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNomHeros))
                    .addComponent(buttonValider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(labelNomHeros))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(geographieLabel))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonValider, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonValiderActionPerformed

        Armee armee = getSelection();
        if (armee.size() > 0) {
            
            heros.setArmeeMiseEnJeu(getSelection());
            this.setVisible(false);
            Heros suivant = combatControler.nextHeros(heros);
            combatControler.demanderComposition(suivant);
        
        } else {
            JOptionPane.showMessageDialog(null, "Tu serais pas en train d'essayer de fuir en fourbe, quand même ?\n"
                    + "Choisis des soldats ou casse toi !");
        }
    }//GEN-LAST:event_buttonValiderActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonValider;
    private javax.swing.JLabel geographieLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelEffectif;
    private javax.swing.JLabel labelNbSoldats;
    private javax.swing.JLabel labelNomHeros;
    private javax.swing.JLabel labelPourcentageArmee;
    private javax.swing.JLabel labelSelectionPC;
    private javax.swing.JLabel labelSelectionPT;
    private javax.swing.JLabel labelSelectionValorisation;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

}
