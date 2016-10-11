/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.combat;

import unitesDeCombat.armee.Armee;
import unitesDeCombat.armee.Soldat;
import com.ezware.oxbow.swingbits.table.filter.TableRowFilterSupport;
import environement.Handicap;
import java.awt.Color;
import unitesDeCombat.hero.Heros;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import utils.Utils;

/**
 *
 * @author jeff
 */
public final class PanelScore extends javax.swing.JPanel implements MouseListener {

    private final Heros heros;
    private final Score score;
    private final Armee armee;

    private Double handicapCombat;
    private Double handicapTir;
    private Double handicapTotal;
    
    private final int numSoldatIndex = 0;
    private final int raceIndex = 1;
    private final int classeIndex = 2;
    private final int niveauIndex = 3;
    private final int pcIndex = 4;
    private final int ptIndex = 5;
    private final int vitesseIndex = 6;
    private final int ptotalIndex = 7;
    
     private final DefaultTableModel model;
    
    /**
     * Creates new form NewJPanel
     * @param heros
     * @param score
     * @param handicap
     */
    public PanelScore(Heros heros, Score score, Handicap handicap) {
        initComponents();
        
        this.score = score;
        this.heros = heros;
        
        if (heros.getJoueur().isJouable()) {
//            this.setBackground(Color.red);
            this.setBorder(new LineBorder(Color.red));
        }
        
        calculHandicapsValues(handicap);
        
        armee = heros.getArmeeMiseEnJeu();
        
        model = (DefaultTableModel) table.getModel();
        TableRowFilterSupport.forTable(table).searchable(true).apply();
        
        setPanelValues();
        
        // au clic, affiche un dialog affichant l'armée mise en jeu
//        addMouseListener(this);
        // BUG : la table du vaincu sera vide, 
            // car les soldats de l'armee mise en jeu ont été supprimé entre temps
    }
    
    private void calculHandicapsValues(Handicap handicap) {
        handicapCombat = handicap.getHandicapCombat();
        handicapTir = handicap.getHandicapTir();
        handicapTotal = handicap.getHandicapTotal();
    }
    
    public void setPanelValues() {
        
        labelNom.setText(heros.getNom());
        labelNbSoldats.setText(String.valueOf(armee.size()));
        
//        Double v4 = armee.getPuissanceCAC();
//        Double v5 = armee.getPuissanceTir();
//        Double v6 = armee.getPuissanceTotale();
//        
//        Double v1 = (Double)(armee.getPuissanceCAC() * handicapCombat);
//        Double v2 = (Double)(armee.getPuissanceTir() * handicapTir);
//        Double v3 = (Double)(armee.getPuissanceTotale() * handicapTotal);
                
                
        labelPC.setText(((Utils.round(armee.getPuissanceCAC() * handicapCombat))).toString());
        labelPT.setText(((Utils.round(armee.getPuissanceTir() * handicapTir))).toString());
        labelPTT.setText(((Utils.round(armee.getPuissanceTotale() * handicapTotal))).toString());
        labelV.setText(Utils.round(armee.getCapaciteDeplacement()).toString());
        labelValor.setText(Utils.round(armee.valoriser()).toString());
        labelPertesSoldats.setText(score.getPerteSoldats().toString());
        labelGainsCredits.setText(Utils.round(score.getGainsCredit()).toString());
        labelGainsXP.setText(Utils.round(score.getGainsXP()).toString());
    }
    
    public void setDialogValues() {
        
        labelNomDialog.setText(heros.getNom());
        labelPCDialog.setText(((Utils.round(armee.getPuissanceCAC() * handicapCombat))).toString());
        labelPTDialog.setText(((Utils.round(armee.getPuissanceTir() * handicapTir))).toString());
        labelPTTDialog.setText(((Utils.round(armee.getPuissanceTotale() * handicapTotal))).toString());
        labelVDialog.setText(Utils.round(armee.getCapaciteDeplacement()).toString());
        labelValorDialog.setText(Utils.round(armee.valoriser()).toString());
        labelPertesSoldatsDialog.setText(score.getPerteSoldats().toString());
        labelGainsCreditsDialog.setText(Utils.round(score.getGainsCredit()).toString());
        labelGainsXpDialog.setText(Utils.round(score.getGainsXP()).toString());
        
        fillDialogtable();
    }
    
    private void fillDialogtable() {

        model.setRowCount(armee.size());

        int index = 0;
        for (Soldat soldat : armee) {

            model.setValueAt(soldat.getNumero(), index, numSoldatIndex);
            model.setValueAt(soldat.getRace().getNom(), index, raceIndex);
            model.setValueAt(soldat.getClasse().getNom(), index, classeIndex);
            model.setValueAt(soldat.getNiveau(), index, niveauIndex);
            model.setValueAt(soldat.getPuissanceCombat() * handicapCombat, index, pcIndex);
            model.setValueAt(soldat.getPuissanceTir() * handicapTir, index, ptIndex);
            model.setValueAt(soldat.getCapaciteDeplacement(), index, vitesseIndex);
            model.setValueAt(soldat.getPuissance() * handicapTotal, index, ptotalIndex);

            index++;
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        detailDialog = new javax.swing.JDialog();
        labelVDialog = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        labelPTDialog = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        labelValorDialog = new javax.swing.JLabel();
        labelPTTDialog = new javax.swing.JLabel();
        labelPCDialog = new javax.swing.JLabel();
        labelNomDialog = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        labelPertesSoldatsDialog = new javax.swing.JLabel();
        labelGainsCreditsDialog = new javax.swing.JLabel();
        labelGainsXpDialog = new javax.swing.JLabel();
        labelPTT = new javax.swing.JLabel();
        labelV = new javax.swing.JLabel();
        labelPT = new javax.swing.JLabel();
        labelPC = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelNom = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labelValor = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        labelPertesSoldats = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        labelGainsCredits = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        labelGainsXP = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelNbSoldats = new javax.swing.JLabel();

        detailDialog.setTitle("Détails de l'armée mise en jeu");
        detailDialog.setResizable(false);

        labelVDialog.setText("jLabel9");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Numéro", "Race", "Classe", "Niveau", "PC", "PT", "Vitesse", "P. Tot."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        jLabel8.setText("Puissance Totale :");

        jLabel9.setText("Vitesse :");

        labelPTDialog.setText("jLabel8");

        jLabel10.setText("Puissance Tir :");

        jLabel11.setText("Valorisation :");

        labelValorDialog.setText("jLabel12");

        labelPTTDialog.setText("jLabel10");

        labelPCDialog.setText("jLabel7");

        labelNomDialog.setText("jLabel1");

        jLabel12.setText("Puissance CàC :");

        jLabel16.setText("Pertes (soldats) :");

        jLabel18.setText("Gains (crédits) :");

        jLabel19.setText("Gains (exp.) :");

        labelPertesSoldatsDialog.setText("jLabel20");

        labelGainsCreditsDialog.setText("jLabel21");

        labelGainsXpDialog.setText("jLabel22");

        javax.swing.GroupLayout detailDialogLayout = new javax.swing.GroupLayout(detailDialog.getContentPane());
        detailDialog.getContentPane().setLayout(detailDialogLayout);
        detailDialogLayout.setHorizontalGroup(
            detailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailDialogLayout.createSequentialGroup()
                .addGroup(detailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(detailDialogLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelNomDialog)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(detailDialogLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(detailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel8)
                            .addComponent(jLabel12)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(detailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelGainsXpDialog)
                            .addComponent(labelGainsCreditsDialog)
                            .addComponent(labelPertesSoldatsDialog)
                            .addComponent(labelPTDialog)
                            .addComponent(labelPCDialog)
                            .addComponent(labelVDialog)
                            .addComponent(labelPTTDialog)
                            .addComponent(labelValorDialog))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        detailDialogLayout.setVerticalGroup(
            detailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(detailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(detailDialogLayout.createSequentialGroup()
                        .addComponent(labelNomDialog)
                        .addGap(18, 18, 18)
                        .addGroup(detailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(labelPCDialog))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(detailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(labelPTDialog))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(detailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(labelVDialog))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(detailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(labelPTTDialog))
                        .addGap(18, 18, 18)
                        .addGroup(detailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(labelValorDialog))
                        .addGap(133, 133, 133)
                        .addGroup(detailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(labelPertesSoldatsDialog))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(detailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(labelGainsCreditsDialog))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(detailDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(labelGainsXpDialog))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelPTT.setText("jLabel10");

        labelV.setText("jLabel9");

        labelPT.setText("jLabel8");

        labelPC.setText("jLabel7");

        jLabel2.setText("Puissance CàC :");

        labelNom.setText("jLabel1");

        jLabel4.setText("Vitesse :");

        jLabel3.setText("Puissance Tir :");

        jLabel5.setText("Puissance Totale :");

        jLabel1.setText("Valorisation :");

        labelValor.setText("jLabel12");

        jLabel13.setText("Pertes (soldats) :");

        labelPertesSoldats.setText("jLabel14");

        jLabel15.setText("Gains (crédits) :");

        labelGainsCredits.setText("jLabel16");

        jLabel17.setText("Gains (exp.) :");

        labelGainsXP.setText("jLabel18");

        jLabel6.setText("Nombre de soldats :");

        labelNbSoldats.setText("jLabel7");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelV)
                            .addComponent(labelPC)
                            .addComponent(labelPT)
                            .addComponent(labelPTT)
                            .addComponent(labelValor)
                            .addComponent(labelPertesSoldats)
                            .addComponent(labelGainsCredits)
                            .addComponent(labelNbSoldats)
                            .addComponent(labelGainsXP)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(labelNom)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNom)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(labelNbSoldats))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(labelPC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labelPT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(labelV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(labelPTT))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelValor))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(labelPertesSoldats))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(labelGainsCredits))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(labelGainsXP))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog detailDialog;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelGainsCredits;
    private javax.swing.JLabel labelGainsCreditsDialog;
    private javax.swing.JLabel labelGainsXP;
    private javax.swing.JLabel labelGainsXpDialog;
    private javax.swing.JLabel labelNbSoldats;
    private javax.swing.JLabel labelNom;
    private javax.swing.JLabel labelNomDialog;
    private javax.swing.JLabel labelPC;
    private javax.swing.JLabel labelPCDialog;
    private javax.swing.JLabel labelPT;
    private javax.swing.JLabel labelPTDialog;
    private javax.swing.JLabel labelPTT;
    private javax.swing.JLabel labelPTTDialog;
    private javax.swing.JLabel labelPertesSoldats;
    private javax.swing.JLabel labelPertesSoldatsDialog;
    private javax.swing.JLabel labelV;
    private javax.swing.JLabel labelVDialog;
    private javax.swing.JLabel labelValor;
    private javax.swing.JLabel labelValorDialog;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {

        System.out.println("mouseClicked !!!!");
        setDialogValues();
        detailDialog.pack();
        detailDialog.setVisible(true);
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }


}
