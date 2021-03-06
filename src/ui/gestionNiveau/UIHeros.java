/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gestionNiveau;

import control.HerosControler;
import control.listeners.QuitterGestionHeros;
import control.listeners.SliderChangeListener;
import javax.swing.JOptionPane;
import unitesDeCombat.hero.Heros;

/**
 *
 * @author jeff
 */
public class UIHeros extends javax.swing.JFrame {

    private final HerosControler herosControler;
    private boolean alreadyOpened;

    private SliderChangeListener sclPC;
    private SliderChangeListener sclPT;
    private SliderChangeListener sclCM;
    private SliderChangeListener sclXP;
    private SliderChangeListener sclCredits;
    private SliderChangeListener sclGeographie;
    private SliderChangeListener sclMeteo;
    private SliderChangeListener sclPreCombat;
    private SliderChangeListener sclPertes;
    private SliderChangeListener sclNecromancie;

    /**
     * Creates new form UIHeros
     *
     * @param herosControler
     */
    public UIHeros(HerosControler herosControler) {
        initComponents();
        this.herosControler = herosControler;
        this.alreadyOpened = false;

        this.addWindowListener(new QuitterGestionHeros(herosControler));

        SliderChangeListener sclPC = new SliderChangeListener(labelXP);
        SliderChangeListener sclPT = new SliderChangeListener(labelXP);
        SliderChangeListener sclCM = new SliderChangeListener(labelXP);
        SliderChangeListener sclXP = new SliderChangeListener(labelXP);
        SliderChangeListener sclCredits = new SliderChangeListener(labelXP);
        SliderChangeListener sclGeographie = new SliderChangeListener(labelXP);
        SliderChangeListener sclMeteo = new SliderChangeListener(labelXP);
        SliderChangeListener sclPreCombat = new SliderChangeListener(labelXP);
        SliderChangeListener sclPertes = new SliderChangeListener(labelXP);
        SliderChangeListener sclNecromancie = new SliderChangeListener(labelXP);
    }

    public void afficherHeros(Heros heros) {

        this.textFieldNomHeros.setText(heros.getNom());
        this.labelXP.setText(heros.getJoueur().getXP().toString());

        int ratioPC = heros.getRatioPC();
        int ratioPT = heros.getRatioPT();
        int ratioCM = heros.getRatioCM();
        int ratioXP = heros.getRatioXP();
        int ratioCredits = heros.getRatioCredits();
        int ratioGeographie = heros.getRatioGeographie();
        int ratioMeteo = heros.getRatioMeteo();
        int ratioPreCombat = heros.getRatioPreCombat();
        int ratioPertes = heros.getRatioPertes();
        int ratioNecromancie = heros.getRatioNecromancie();

        this.sliderPC.setValue(ratioPC);
        this.sliderPT.setValue(ratioPT);
        this.sliderCM.setValue(ratioCM);
        this.sliderXP.setValue(ratioXP);
        this.sliderCredits.setValue(ratioCredits);
        this.sliderGeographie.setValue(ratioGeographie);
        this.sliderMeteo.setValue(ratioMeteo);
        this.sliderPreCombat.setValue(ratioPreCombat);
        this.sliderPertes.setValue(ratioPertes);
        this.sliderNecromancie.setValue(ratioNecromancie);

        sclPC.setCoef(heros.tarifPC);
        sclPT.setCoef(heros.tarifPT);
        sclCM.setCoef(heros.tarifCM);
        sclXP.setCoef(heros.tarifXP);
        sclCredits.setCoef(heros.tarifCredits);
        sclGeographie.setCoef(heros.tarifGeographie);
        sclMeteo.setCoef(heros.tarifMeteo);
        sclPreCombat.setCoef(heros.tarifPreCombat);
        sclPertes.setCoef(heros.tarifPertes);
        sclNecromancie.setCoef(heros.tarifNecromancie);

        sclPC.setInitValue(1);
        sclPT.setInitValue(1);
        sclCM.setInitValue(1);
        sclXP.setInitValue(1);
        sclCredits.setInitValue(1);
        sclGeographie.setInitValue(1);
        sclMeteo.setInitValue(1);
        sclPreCombat.setInitValue(1);
        sclPertes.setInitValue(1);
        sclNecromancie.setInitValue(1);

        this.sliderPC.addChangeListener(sclPC);
        this.sliderPT.addChangeListener(sclPT);
        this.sliderCM.addChangeListener(sclCM);
        this.sliderXP.addChangeListener(sclXP);
        this.sliderCredits.addChangeListener(sclCredits);
        this.sliderGeographie.addChangeListener(sclGeographie);
        this.sliderMeteo.addChangeListener(sclMeteo);
        this.sliderPreCombat.addChangeListener(sclPreCombat);
        this.sliderPertes.addChangeListener(sclPertes);
        this.sliderNecromancie.addChangeListener(sclNecromancie);

        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelXP = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        sliderPreCombat = new javax.swing.JSlider();
        sliderGeographie = new javax.swing.JSlider();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        sliderMeteo = new javax.swing.JSlider();
        jLabel16 = new javax.swing.JLabel();
        sliderPertes = new javax.swing.JSlider();
        sliderNecromancie = new javax.swing.JSlider();
        jLabel17 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        sliderPC = new javax.swing.JSlider();
        sliderPT = new javax.swing.JSlider();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        sliderCM = new javax.swing.JSlider();
        jLabel13 = new javax.swing.JLabel();
        sliderXP = new javax.swing.JSlider();
        jLabel14 = new javax.swing.JLabel();
        sliderCredits = new javax.swing.JSlider();
        boutonValider = new javax.swing.JButton();
        textFieldNomHeros = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Héros : ");

        jLabel4.setText("XP disponible : ");

        labelXP.setText("jLabel5");

        jLabel9.setText("Boule de Feu");

        sliderPreCombat.setMajorTickSpacing(1);
        sliderPreCombat.setMaximum(10);
        sliderPreCombat.setMinimum(1);
        sliderPreCombat.setMinorTickSpacing(1);
        sliderPreCombat.setPaintTicks(true);

        sliderGeographie.setMajorTickSpacing(1);
        sliderGeographie.setMaximum(10);
        sliderGeographie.setMinimum(1);
        sliderGeographie.setMinorTickSpacing(1);
        sliderGeographie.setPaintTicks(true);

        jLabel11.setText("Avantage Géographique :");

        jLabel15.setText("Avantage Météo :");

        sliderMeteo.setMajorTickSpacing(1);
        sliderMeteo.setMaximum(10);
        sliderMeteo.setMinimum(1);
        sliderMeteo.setMinorTickSpacing(1);
        sliderMeteo.setPaintTicks(true);

        jLabel16.setText("Limitations des Pertes :");

        sliderPertes.setMajorTickSpacing(1);
        sliderPertes.setMaximum(10);
        sliderPertes.setMinimum(1);
        sliderPertes.setMinorTickSpacing(1);
        sliderPertes.setPaintTicks(true);

        sliderNecromancie.setMajorTickSpacing(1);
        sliderNecromancie.setMaximum(10);
        sliderNecromancie.setMinimum(1);
        sliderNecromancie.setMinorTickSpacing(1);
        sliderNecromancie.setPaintTicks(true);
        sliderNecromancie.setEnabled(false);

        jLabel17.setText("Surprise à venir");
        jLabel17.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(sliderPreCombat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(sliderGeographie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sliderMeteo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sliderPertes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sliderNecromancie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(sliderPreCombat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel11))
                    .addComponent(sliderGeographie, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel15))
                    .addComponent(sliderMeteo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel16))
                    .addComponent(sliderPertes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sliderNecromancie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setText("Amélioration PC :");

        sliderPC.setMaximum(10);
        sliderPC.setMinimum(1);
        sliderPC.setMinorTickSpacing(1);
        sliderPC.setPaintTicks(true);
        sliderPC.setSnapToTicks(true);
        sliderPC.setToolTipText("");

        sliderPT.setMajorTickSpacing(1);
        sliderPT.setMaximum(10);
        sliderPT.setMinimum(1);
        sliderPT.setMinorTickSpacing(1);
        sliderPT.setPaintTicks(true);

        jLabel10.setText("Amélioration PT :");

        jLabel12.setText("Amélioration CM :");

        sliderCM.setMajorTickSpacing(1);
        sliderCM.setMaximum(10);
        sliderCM.setMinimum(1);
        sliderCM.setMinorTickSpacing(1);
        sliderCM.setPaintTicks(true);

        jLabel13.setText("Amélioration XP :");

        sliderXP.setMajorTickSpacing(1);
        sliderXP.setMaximum(10);
        sliderXP.setMinimum(1);
        sliderXP.setMinorTickSpacing(1);
        sliderXP.setPaintTicks(true);

        jLabel14.setText("Amélioration Crédits :");

        sliderCredits.setMajorTickSpacing(1);
        sliderCredits.setMaximum(10);
        sliderCredits.setMinimum(1);
        sliderCredits.setMinorTickSpacing(1);
        sliderCredits.setPaintTicks(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sliderPC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sliderPT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sliderCM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sliderXP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(sliderCredits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(sliderPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel10))
                    .addComponent(sliderPT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel12))
                    .addComponent(sliderCM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel13))
                    .addComponent(sliderXP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel14))
                    .addComponent(sliderCredits, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        boutonValider.setText("Valider");
        boutonValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonValiderActionPerformed(evt);
            }
        });

        textFieldNomHeros.setText("jTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelXP)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(boutonValider, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldNomHeros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textFieldNomHeros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(labelXP))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(boutonValider, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void boutonValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boutonValiderActionPerformed

        int pc = sliderPC.getValue();
        int pt = sliderPT.getValue();
        int cm = sliderCM.getValue();
        int xp = sliderXP.getValue();
        int cr = sliderCredits.getValue();
        int co = sliderPreCombat.getValue();
        int pe = sliderPertes.getValue();
        int ge = sliderGeographie.getValue();
        int me = sliderMeteo.getValue();
        int ne = sliderNecromancie.getValue();

        if (pc == 1 && pt == 1 && cm == 1 && xp == 1 && cr == 1 && co == 1 && pe == 1 && pe == 1 && ge == 1 && me == 1 && ne == 1) {
            int response = JOptionPane.showConfirmDialog(
                    null,
                    "J'y crois pas ! Je lui tend la main, l'aide à se faire ridiculiser le moins possible, et il m'ignore ?\n"
                    + "Ben il va être servi !",
                    "Craint dégun",
                    JOptionPane.YES_NO_OPTION);

            if (response == 0) {
                valider();
            }
        } else {

            sclPC.setInitValue(pc);
            sclPT.setInitValue(pt);
            sclCM.setInitValue(cm);
            sclXP.setInitValue(xp);
            sclCredits.setInitValue(cr);
            sclGeographie.setInitValue(ge);
            sclMeteo.setInitValue(me);
            sclPreCombat.setInitValue(co);
            sclPertes.setInitValue(pe);
            sclNecromancie.setInitValue(ne);

            valider();
        }
    }//GEN-LAST:event_boutonValiderActionPerformed

    private void valider() {

        this.setVisible(false);
        this.alreadyOpened = true;
        this.herosControler.valider();
    }

    public boolean isAlreadyOpened() {
        return this.alreadyOpened;
    }

    public Double getXp() {
        return Double.parseDouble(this.labelXP.getText());
    }

    public String getTextFieldNomHerosValue() {
        return this.textFieldNomHeros.getText();
    }

    public int getSliderCMValue() {
        return sliderCM.getValue();
    }

    public int getSliderCreditsValue() {
        return sliderCredits.getValue();
    }

    public int getSliderGeographieValue() {
        return sliderGeographie.getValue();
    }

    public int getSliderMeteoValue() {
        return sliderMeteo.getValue();
    }

    public int getSliderNecromancieValue() {
        return sliderNecromancie.getValue();
    }

    public int getSliderPCValue() {
        return sliderPC.getValue();
    }

    public int getSliderPTValue() {
        return sliderPT.getValue();
    }

    public int getSliderPertesValue() {
        return sliderPertes.getValue();
    }

    public int getSliderPreCombatValue() {
        return sliderPreCombat.getValue();
    }

    public int getSliderXPValue() {
        return sliderXP.getValue();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boutonValider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelXP;
    private javax.swing.JSlider sliderCM;
    private javax.swing.JSlider sliderCredits;
    private javax.swing.JSlider sliderGeographie;
    private javax.swing.JSlider sliderMeteo;
    private javax.swing.JSlider sliderNecromancie;
    private javax.swing.JSlider sliderPC;
    private javax.swing.JSlider sliderPT;
    private javax.swing.JSlider sliderPertes;
    private javax.swing.JSlider sliderPreCombat;
    private javax.swing.JSlider sliderXP;
    private javax.swing.JTextField textFieldNomHeros;
    // End of variables declaration//GEN-END:variables
}
