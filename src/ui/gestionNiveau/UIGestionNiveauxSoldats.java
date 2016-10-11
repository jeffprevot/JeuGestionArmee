/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gestionNiveau;

import unitesDeCombat.armee.Armee;
import unitesDeCombat.armee.Soldat;
import com.ezware.oxbow.swingbits.table.filter.TableRowFilterSupport;
import control.NiveauControler;
import control.listeners.TableNiveauListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import unitesDeCombat.hero.Heros;
import utils.SpinnerEditor;
import utils.Utils;

/**
 *
 * @author jeff
 */
public class UIGestionNiveauxSoldats extends javax.swing.JFrame {

    private final NiveauControler niveauControler;
    
    public static final int nomSoldatIndex = 0;
    public static final int raceIndex = 1;
    public static final int classeIndex = 2;
    public static final int niveauIndex = 3;
    public static final int pcIndex = 4;
    public static final int ptIndex = 5;
    public static final int cmIndex = 6;
    public static final int ptotalIndex = 7;

    private final DefaultTableModel model;

    private Heros heros;
    private Armee armee;

    private Double displayedXPValue;

    /**
     * Creates new form UICombat
     *
     * @param niveauControler
     */
    public UIGestionNiveauxSoldats(NiveauControler niveauControler) {
        
        this.niveauControler = niveauControler;
        
        initComponents();

        model = (DefaultTableModel) table.getModel();

        TableColumn col = table.getColumnModel().getColumn(3);
        col.setCellEditor(new SpinnerEditor(this));

        TableRowFilterSupport.forTable(table).searchable(true).apply();

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                buttonValiderActionPerformed(null);
            }
        });
    }
    
    public DefaultTableModel getModel() {
        return this.model;
    }

    public JTable getTable() {
        return table;
    }

    public Double getDisplayedXPValue() {
        return this.displayedXPValue;
    }

    public void setDisplayedXPValue(Double value) {
        this.heros.getJoueur().setXP(value);
    }

//    public void setHero(Heros hero) {
//
//        this.hero = hero;
//        try {
//            this.armee = hero.getArmee().clone();
//        } catch (CloneNotSupportedException ex) {
//            Logger.getLogger(UIGestionNiveauxSoldats.class.getName()).log(Level.SEVERE, null, ex);
//            this.armee = null;
//        }
//
//        fillTable(this.armee);
//
////        niveauxInitiaux = lireNiveaux();
//
//        for (TableModelListener tml : model.getTableModelListeners()) {
//            model.removeTableModelListener(tml);
//        }
//        
//        model.addTableModelListener(new TableNiveauListener(this, armee, niveauIndex));
//    }

    public void afficher(Heros heros) {
        
        this.heros = heros;
        try {
            this.armee = heros.getArmee().clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(UIGestionNiveauxSoldats.class.getName()).log(Level.SEVERE, null, ex);
            this.armee = null;
        }

        fillTable(this.armee);

//        niveauxInitiaux = lireNiveaux();

        for (TableModelListener tml : model.getTableModelListeners()) {
            model.removeTableModelListener(tml);
        }
        
        model.addTableModelListener(new TableNiveauListener(this, armee, niveauIndex));

        labelNomHeros.setText(heros.getNom());
        displayedXPValue = heros.getJoueur().getXP();

        updateLabels(this.armee);

        this.setVisible(true);
    }

    public void refesh() {
        afficher(this.heros);
    }

    public void fillTable(Armee armee) {

        model.setRowCount(armee.size());

        int index = 0;
        for (Soldat soldat : armee) {

            model.setValueAt(soldat.getNom(), index, nomSoldatIndex);
            model.setValueAt(soldat.getRace().getNom(), index, raceIndex);
            model.setValueAt(soldat.getClasse().getNom(), index, classeIndex);
            model.setValueAt(soldat.getNiveau(), index, niveauIndex);
            model.setValueAt(Utils.round(soldat.getPuissanceCombat()), index, pcIndex);
            model.setValueAt(Utils.round(soldat.getPuissanceTir()), index, ptIndex);
            model.setValueAt(Utils.round(soldat.getCapaciteDeplacement()), index, cmIndex);
            model.setValueAt(Utils.round(soldat.getPuissance()), index, ptotalIndex);

            index++;
        }
    }

    public void updateLabels(Armee armee) {

        this.labelPC.setText(Utils.round(armee.getPuissanceCAC()).toString());
        this.labelPT.setText(Utils.round(armee.getPuissanceTir()).toString());
        this.labelCM.setText(Utils.round(armee.getCapaciteDeplacement()).toString());
        this.labelValorisation.setText(Utils.round(armee.valoriser()).toString());
        this.labelEffectif.setText(String.valueOf(armee.size()));

        this.labelXP.setText(displayedXPValue.toString());
    }

//    private List<Integer> lireNiveaux() {
//
//        List<Integer> niveauxInitiaux = new ArrayList<>();
//
//        for (int i = 0; i < model.getRowCount(); i++) {
//            niveauxInitiaux.add((int) model.getValueAt(i, niveauIndex));
//        }
//
//        return niveauxInitiaux;
//    }

//    private boolean correctValues() {
//
//        boolean wasCorrect = true;
//
//        for (int rowIndex = 0; rowIndex < model.getRowCount(); rowIndex++) {
//
//            int value = (int) model.getValueAt(rowIndex, niveauIndex);
//            int valueInit = niveauxInitiaux.get(rowIndex);
//
//            if (value < valueInit) {
//
//                int cout = 0;
//                for (int j = value; j < valueInit; j++) {
//                    cout += Math.pow(2, j);
//                }
//                displayedXPValue -= cout;
//
//                Soldat soldat = armee.getArmee().get(rowIndex);
//                soldat.setNiveau(valueInit);
//
//                fillTable();
//                updateLabels();
//
//                wasCorrect = false;
//            }
//
//        }
//
//        return wasCorrect;
//    }

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
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelPC = new javax.swing.JLabel();
        labelPT = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelValorisation = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labelCM = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelEffectif = new javax.swing.JLabel();
        buttonValider = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        labelNomHeros = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        labelXP = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Amélioration des soldats");
        setLocationByPlatform(true);
        setModalExclusionType(null);
        setResizable(false);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nom", "Race", "Classe", "Niveau", "PC", "PT", "Vitesse", "P Tot."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false, false
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

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("Caractéristiques de l'armée :");

        jLabel3.setText("PC :");

        jLabel4.setText("PT :");

        labelPC.setText("jLabel5");

        labelPT.setText("jLabel6");

        jLabel5.setText("Valorisation :");

        labelValorisation.setText("jLabel6");

        jLabel1.setText("CM :");

        labelCM.setText("jLabel6");

        jLabel6.setText("Effectif :");

        labelEffectif.setText("jLabel");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelEffectif)
                    .addComponent(labelCM)
                    .addComponent(labelPC)
                    .addComponent(labelPT)
                    .addComponent(labelValorisation))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(labelEffectif))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labelPC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(labelPT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelCM))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(labelValorisation))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonValider.setText("Valider");
        buttonValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonValiderActionPerformed(evt);
            }
        });

        jLabel8.setText("Héros :");

        labelNomHeros.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelNomHeros.setText("jLabel9");

        jLabel10.setText("XP disponible :");

        labelXP.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelXP.setText("jLabel11");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addGap(76, 76, 76)
                            .addComponent(labelNomHeros))
                        .addComponent(buttonValider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(27, 27, 27)
                        .addComponent(labelXP)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
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
                            .addComponent(jLabel10)
                            .addComponent(labelXP))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonValider, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonValiderActionPerformed

        if (niveauControler.correctValues()) {

            heros.getArmee().setArmee(armee);
            heros.getJoueur().setXP(displayedXPValue);

            this.setVisible(false);
            
        } else {

            JOptionPane.showMessageDialog(this,
                    "Tu as essayé de faire baisser le niveau d'un des soldats pour grapiller quelques piécettes ?!"
                    + " Tu en es réduit à ca ?\n"
                    + "C'était bien tenté, mais je t'ai grillé. Tu me ferais presque pitié.\n"
                    + "Mais je suis magnanime, je t'ai corrigé..."
            );
        }
    }//GEN-LAST:event_buttonValiderActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonValider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCM;
    private javax.swing.JLabel labelEffectif;
    private javax.swing.JLabel labelNomHeros;
    private javax.swing.JLabel labelPC;
    private javax.swing.JLabel labelPT;
    private javax.swing.JLabel labelValorisation;
    private javax.swing.JLabel labelXP;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

}
