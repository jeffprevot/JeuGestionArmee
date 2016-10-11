/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import ui.glossaire.GlossairePanel;
import unitesDeCombat.armee.Propriete;
import control.LoadControler;
import control.MainControler;
import control.listeners.QuitActionListener;
import control.listeners.DisplayMessageActionListener;
import environement.geographie.Geographie;
import environement.meteo.Meteo;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import unitesDeCombat.joueur.Joueur;
import utils.Utils;

/**
 *
 * @author jeff
 */
public final class FramePrincipale extends javax.swing.JFrame {

    private final String aboutText = "Version : beta.1\n"
            + "Realease date : 2016-06-08\n"
            + "\nCredits :\n"
            + "Game conception : Vincent L.\n"
            + "Development : Jeff P. (jeff.prevot@zoho.com)";

    private final MainControler mainControler;

    private final Dimension cartePanelDimension;

    /**
     * Creates new form Frameprincipale
     *
     * @param mainControler
     */
    public FramePrincipale(MainControler mainControler) {

        this.mainControler = mainControler;
        initComponents();
        this.addWindowListener(new QuitActionListener(mainControler, true));

        setPartieEnCours(false);
        menuItemQuitter.setToolTipText("ah mais tu as vraiment une vie en dehors du jeu ?!");
        menuItemAbout.setToolTipText("Pour les propositions d'embauches par exemple...");
        menuItemAbout.addActionListener(new DisplayMessageActionListener(aboutText));

        menuItemChangelog.addActionListener(new DisplayMessageActionListener(LoadControler.loadChangelog("config/changelog.txt")));

        Map<String, List<Object>> map = new HashMap<>();
        for (String key : mainControler.getProprietesMap().keySet()) {
            map.put(key, new ArrayList<>());
            for (Propriete ppt : mainControler.getProprietesMap().get(key)) {
                map.get(key).add(ppt);
            }
        }
        map.put("meteo", new ArrayList<>());
        for (Meteo meteo : mainControler.getMeteoList()) {
            map.get("meteo").add(meteo);
        }
        map.put("geographie", new ArrayList<>());
        for (Geographie geographie : mainControler.getGeographieList()) {
            map.get("geographie").add(geographie);
        }
        JTabbedPane glossairePanel = new GlossairePanel(map);

        dialogGlossaire.setLayout(new BorderLayout());
        dialogGlossaire.add(glossairePanel, BorderLayout.CENTER);
        dialogGlossaire.pack();
        menuItemGlossaire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogGlossaire.setVisible(true);
            }
        });

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);

        cartePanelDimension = cartePanel.getSize();
        infosPanel.setVisible(false);
    }

    public void setPartieEnCours(boolean partieEnCours) {

        menuItemSauvegarder.setEnabled(partieEnCours);
        if (!partieEnCours) {
            menuItemSauvegarder.setToolTipText(
                    "Et si tu commençais une partie avant de vouloir la sauvegarder ? Bouffon");
        } else {
            menuItemSauvegarder.setToolTipText(null);
        }
    }

    public void setCartePanel(JPanel panel) {
        cartePanel.removeAll();

        if (panel != null) {
            cartePanel.setLayout(new BorderLayout());
            cartePanel.add(panel);
        }
    }

    /**
     * @return the Dimension of the mainPanel minus the scrollBars
     */
    public Dimension getPanelUtilSize() {
        return cartePanelDimension;
    }

    public void refreshCarte() {
        this.repaint();
    }

    public void refreshInfosPanel() {

        Joueur joueur = mainControler.getJoueurHumain();

        String cr = "", nom = "", pc = "", pt = "", ptt = "", val = "", xp = "", cm = "", ni = "";
        if (joueur != null) {
            
        cr = Utils.round(joueur.getCredits()).toString();
        nom = joueur.getNom();
        pc = Utils.round(joueur.getPuissanceCAC()).toString();
        pt = Utils.round(joueur.getPuissanceTir()).toString();
        cm = Utils.round(joueur.getCapaciteDeplacement()).toString();
        ptt = Utils.round(joueur.getPuissanceTotale()).toString();
        val = Utils.round(joueur.valoriser()).toString();
        xp = Utils.round(joueur.getXP()).toString();
        ni = joueur.getNiveau().toString();
        
        }
        
        labelCredits.setText(cr);
        labelNom.setText(nom);
        labelPC.setText(pc);
        labelPT.setText(pt);
        labelVitesse.setText(cm);
        labelTotal.setText(ptt);
        labelValor.setText(val);
        labelXP.setText(xp);
        labelNiveau.setText(ni);
        
        infosPanel.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogGlossaire = new javax.swing.JDialog();
        mainPanel = new javax.swing.JPanel();
        cartePanel = new javax.swing.JPanel();
        infosPanel = new javax.swing.JPanel();
        labelNom = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        labelPC = new javax.swing.JLabel();
        labelPT = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        labelXP = new javax.swing.JLabel();
        labelCredits = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labelVitesse = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        labelNiveau = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelValor = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuPartie = new javax.swing.JMenu();
        menuItemNouvellePartie = new javax.swing.JMenuItem();
        menuItemChargerPartie = new javax.swing.JMenuItem();
        menuItemSauvegarder = new javax.swing.JMenuItem();
        menuItemQuitter = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();
        menuItemGlossaire = new javax.swing.JMenuItem();
        menuItemChangelog = new javax.swing.JMenuItem();
        menuItemAbout = new javax.swing.JMenuItem();

        javax.swing.GroupLayout dialogGlossaireLayout = new javax.swing.GroupLayout(dialogGlossaire.getContentPane());
        dialogGlossaire.getContentPane().setLayout(dialogGlossaireLayout);
        dialogGlossaireLayout.setHorizontalGroup(
            dialogGlossaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        dialogGlossaireLayout.setVerticalGroup(
            dialogGlossaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jeu");

        javax.swing.GroupLayout cartePanelLayout = new javax.swing.GroupLayout(cartePanel);
        cartePanel.setLayout(cartePanelLayout);
        cartePanelLayout.setHorizontalGroup(
            cartePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1217, Short.MAX_VALUE)
        );
        cartePanelLayout.setVerticalGroup(
            cartePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 364, Short.MAX_VALUE)
        );

        labelNom.setText("jLabel1");

        jLabel3.setText("PC :");

        jLabel4.setText("PT :");

        jLabel2.setText("Puissance Totale :");

        jLabel5.setText("Niveau :");

        jLabel7.setText("Crédits :");

        labelPC.setText("jLabel1");

        labelPT.setText("jLabel8");

        labelTotal.setText("jLabel9");

        labelXP.setText("jLabel10");

        labelCredits.setText("jLabel11");

        jLabel1.setText("CM :");

        labelVitesse.setText("jLabel8");

        jLabel8.setText("XP :");

        labelNiveau.setText("jLabel10");

        jLabel6.setText("Valorisation :");

        labelValor.setText("jLabel9");

        javax.swing.GroupLayout infosPanelLayout = new javax.swing.GroupLayout(infosPanel);
        infosPanel.setLayout(infosPanelLayout);
        infosPanelLayout.setHorizontalGroup(
            infosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infosPanelLayout.createSequentialGroup()
                .addContainerGap(225, Short.MAX_VALUE)
                .addGroup(infosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infosPanelLayout.createSequentialGroup()
                        .addGroup(infosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(11, 11, 11)
                        .addGroup(infosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelPC)
                            .addComponent(labelPT)))
                    .addGroup(infosPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelVitesse)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                .addGroup(infosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTotal)
                    .addComponent(labelValor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 219, Short.MAX_VALUE)
                .addGroup(infosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCredits)
                    .addComponent(labelNiveau)
                    .addComponent(labelXP))
                .addContainerGap(225, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infosPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelNom)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        infosPanelLayout.setVerticalGroup(
            infosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infosPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(infosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(infosPanelLayout.createSequentialGroup()
                        .addComponent(labelNom)
                        .addGap(18, 18, 18)
                        .addGroup(infosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(infosPanelLayout.createSequentialGroup()
                                .addComponent(labelCredits)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelXP)
                                .addGap(23, 23, 23))
                            .addGroup(infosPanelLayout.createSequentialGroup()
                                .addGroup(infosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infosPanelLayout.createSequentialGroup()
                                        .addComponent(labelPC)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelPT))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infosPanelLayout.createSequentialGroup()
                                        .addGroup(infosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2)
                                            .addComponent(labelTotal))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(infosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(infosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(labelNiveau)
                                        .addComponent(jLabel6)
                                        .addComponent(labelValor))
                                    .addGroup(infosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(labelVitesse))))))
                    .addGroup(infosPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(23, 23, 23)))
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cartePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(infosPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(cartePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(infosPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menuPartie.setText("Partie");

        menuItemNouvellePartie.setText("Nouvelle Partie");
        menuItemNouvellePartie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemNouvellePartieActionPerformed(evt);
            }
        });
        menuPartie.add(menuItemNouvellePartie);

        menuItemChargerPartie.setText("Charger Partie");
        menuItemChargerPartie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemChargerPartieActionPerformed(evt);
            }
        });
        menuPartie.add(menuItemChargerPartie);

        menuItemSauvegarder.setText("Sauvegarder");
        menuItemSauvegarder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSauvegarderActionPerformed(evt);
            }
        });
        menuPartie.add(menuItemSauvegarder);

        menuItemQuitter.setText("Quitter");
        menuItemQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemQuitterActionPerformed(evt);
            }
        });
        menuPartie.add(menuItemQuitter);

        menuBar.add(menuPartie);

        menuHelp.setText("Help");

        menuItemGlossaire.setText("Glossaire");
        menuHelp.add(menuItemGlossaire);

        menuItemChangelog.setText("Changelog");
        menuHelp.add(menuItemChangelog);

        menuItemAbout.setText("About");
        menuHelp.add(menuItemAbout);

        menuBar.add(menuHelp);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemNouvellePartieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemNouvellePartieActionPerformed

        if (mainControler.isPartieEnCours()) {
            mainControler.sauverPartie();
            mainControler.setPartieEnCours(false);
        }

        mainControler.creerPartie();
    }//GEN-LAST:event_menuItemNouvellePartieActionPerformed

    private void menuItemQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemQuitterActionPerformed
        if (mainControler.isPartieEnCours()) {
            mainControler.sauverPartie();
            mainControler.setPartieEnCours(false);
        }

        ActionListener quitAction = new QuitActionListener(mainControler, true);
        quitAction.actionPerformed(evt);
    }//GEN-LAST:event_menuItemQuitterActionPerformed

    private void menuItemChargerPartieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemChargerPartieActionPerformed
        mainControler.chargerPartie();
    }//GEN-LAST:event_menuItemChargerPartieActionPerformed

    private void menuItemSauvegarderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSauvegarderActionPerformed
        mainControler.sauverPartie();
    }//GEN-LAST:event_menuItemSauvegarderActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cartePanel;
    private javax.swing.JDialog dialogGlossaire;
    private javax.swing.JPanel infosPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel labelCredits;
    private javax.swing.JLabel labelNiveau;
    private javax.swing.JLabel labelNom;
    private javax.swing.JLabel labelPC;
    private javax.swing.JLabel labelPT;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JLabel labelValor;
    private javax.swing.JLabel labelVitesse;
    private javax.swing.JLabel labelXP;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenuItem menuItemAbout;
    private javax.swing.JMenuItem menuItemChangelog;
    private javax.swing.JMenuItem menuItemChargerPartie;
    private javax.swing.JMenuItem menuItemGlossaire;
    private javax.swing.JMenuItem menuItemNouvellePartie;
    private javax.swing.JMenuItem menuItemQuitter;
    private javax.swing.JMenuItem menuItemSauvegarder;
    private javax.swing.JMenu menuPartie;
    // End of variables declaration//GEN-END:variables
}
