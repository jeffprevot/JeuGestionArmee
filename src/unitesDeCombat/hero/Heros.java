/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitesDeCombat.hero;

import unitesDeCombat.joueur.Joueur;
import unitesDeCombat.armee.Armee;
import unitesDeCombat.armee.Soldat;
import java.io.Serializable;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author jeff
 */
public class Heros implements Serializable {

    private final Joueur joueur;
    private String nom;
    private Armee armee;
    private Armee armeeMiseEnJeu;

    private int ratioPC;
    private int ratioPT;
    private int ratioCM;
    private int ratioXP;
    private int ratioCredits;
    private int ratioPertes;
    private int ratioPreCombat;
    private int ratioGeographie;
    private int ratioMeteo;
    private int ratioNecromancie;

    public Double tarifPC = 1d;
    public Double tarifPT = 1d;
    public Double tarifCM = 1d;
    public Double tarifXP = 1d;
    public Double tarifCredits = 1d;
    public Double tarifGeographie = 1d;
    public Double tarifMeteo = 1d;
    public Double tarifPreCombat = 1d;
    public Double tarifPertes = 1d;
    public Double tarifNecromancie = 1d;

    public Heros(String nom, Joueur joueur) {
        this.joueur = joueur;
        this.nom = nom;
        this.armee = new Armee();
        this.armeeMiseEnJeu = new Armee();
        
        initRatios();
    }

    public Heros() {
        this.joueur = null;
        this.nom = null;

        initRatios();
    }
    
    private void initRatios(){
        this.ratioPC = 1;
        this.ratioPT = 1;
        this.ratioCM = 1;
        this.ratioXP = 1;
        this.ratioCredits = 1;
        this.ratioPertes = 1;
        this.ratioPreCombat = 1;
        this.ratioGeographie = 1;
        this.ratioMeteo = 1;
        this.ratioNecromancie = 1;
    }

    public Joueur getJoueur() {
        return this.joueur;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public Armee getArmee() {
        return this.armee;
    }

    public Armee getArmeeMiseEnJeu() {
        return this.armeeMiseEnJeu;
    }

    public void setArmeeMiseEnJeu(Armee armee) {
        this.armeeMiseEnJeu = armee;
    }

    public boolean isEmpty() {
        return this.getArmee().getArmee().isEmpty();
    }

    public int getTailleArmee() {
        return armee.size();
    }

    public Double getXP() {
        Double experience = 0d;

        for (Soldat soldat : armee.getArmee()) {
            experience += soldat.getNiveau();
        }

        return experience * ratioXP;
    }

    public void setRatioPC(int ratioPC) {
        this.ratioPC = ratioPC;
    }

    public void setRatioPT(int ratioPT) {
        this.ratioPT = ratioPT;
    }

    public void setRatioCM(int ratioCM) {
        this.ratioCM = ratioCM;
    }

    public void setRatioXP(int ratioXP) {
        this.ratioXP = ratioXP;
    }

    public void setRatioCredits(int ratioCredits) {
        this.ratioCredits = ratioCredits;
    }

    public void setRatioPertes(int ratioPertes) {
        this.ratioPertes = ratioPertes;
    }

    public void setRatioPreCombat(int ratioPreCombat) {
        this.ratioPreCombat = ratioPreCombat;
    }

    public void setRatioGeographie(int ratioGeographie) {
        this.ratioGeographie = ratioGeographie;
    }

    public void setRatioMeteo(int ratioMeteo) {
        this.ratioMeteo = ratioMeteo;
    }

    public void setRatioNecromancie(int ratioNecromancie) {
        this.ratioNecromancie = ratioNecromancie;
    }

    public void ajouterCredits(double credits) {
        this.joueur.ajouterCredits(credits * ratioCredits);
    }

    public void enleverCredits(double credits) {
        this.joueur.enleverCredits(credits);
    }

    public void ajouterXP(double xp) {
        this.joueur.ajouterXP(xp * ratioXP);
    }

    public void enleverXP(double xp) {
        this.joueur.enleverXP(xp);
    }

    public int getRatioPreCombat() {
        return this.ratioPreCombat;
    }

    public int getRatioGeographie() {
        return this.ratioGeographie;
    }

    public int getRatioMeteo() {
        return this.ratioMeteo;
    }

    public int getRatioCM() {
        return this.ratioCM;
    }

    public int getRatioXP() {
        return this.ratioXP;
    }

    public int getRatioPertes() {
        return this.ratioPertes;
    }

    public int getRatioCredits() {
        return this.ratioCredits;
    }

    public int getRatioNecromancie() {
        return this.ratioNecromancie;
    }

    public void supprimer(Armee armeeMiseEnJeu) {

        int nb = (int) (armeeMiseEnJeu.size() / ratioPertes);

//        this.armeeMiseEnJeu.supprimerSoldatByNb(nb);

        this.armee.supprimer(this.armeeMiseEnJeu.supprimerSoldatByNb(nb));
    }

    public void supprimerSoldatByNb(int nombre, Armee armeeMiseEnJeu) {

        int nb = (int) (nombre / ratioPertes);

        System.out.println("Limitation de la perte de soldat : ratio=" + ratioPertes); // DEBUG

        if (ratioPertes > 1) {
            JOptionPane.showMessageDialog(null,
                    "Le pouvoir de guérison de " + this.getNom()
                    + " a permis de limiter les pertes.\n"
                    + "Il ne perd que " + nombre + " soldat(s)");
        }

        System.out.println(this.getNom() + " : Suppression de " + nombre + " soldats"); // DEBUG

        if (armeeMiseEnJeu == null) {
            this.armee.supprimerSoldatByNb(nb);
        } else {
            List<Soldat> pertesCollaterales = armeeMiseEnJeu.supprimerSoldatByNb(nombre);
            this.armee.supprimer(pertesCollaterales);
        }
    }

    public Soldat getSoldat(int numero) {
        return this.armee.getSoldat(numero);
    }

    public int getRatioPC() {
        return this.ratioPC;
    }

    public int getRatioPT() {
        return this.ratioPT;
    }

    public Double getPuissanceCAC() {
        return armee.getPuissanceCAC() * ratioPC;
    }

    public Double getPuissanceTir() {
        return armee.getPuissanceTir() * ratioPT;
    }

    public Double getPuissanceTotale() {
        return armee.getPuissanceTotale();
    }

    public Double getCapaciteDeplacement() {

        return 4d; // DEBUG !!!!!!!!!
//            return armee.getCapaciteDeplacement() * ratioCM;
    }

    public Double valoriser() {
        return this.armee.valoriser();
    }

//    @Override
//    public String toString() {
//        return "nom=" + nom + "\n"
//                + "\texperience=" + getXP() + "\n"
//                + "\tarmee= " + armee.toString();
//    }

    @Override
    public String toString() {
        return "Heros{" + "joueur=" + joueur.getNom() + ", nom=" + nom + " \narmee=" + armee.toString() + "\nratioPC=" + ratioPC + ", ratioPT=" + ratioPT + ", ratioCM=" + ratioCM + ", ratioXP=" + ratioXP + ", ratioCredits=" + ratioCredits + ", ratioPertes=" + ratioPertes + ", ratioPreCombat=" + ratioPreCombat + ", ratioGeographie=" + ratioGeographie + ", ratioMeteo=" + ratioMeteo + ", ratioNecromancie=" + ratioNecromancie + ", tarifPreCombat=" + tarifPreCombat + ", tarifPertes=" + tarifPertes + ", tarifNecromancie=" + tarifNecromancie + '}';
    }
    
    

    /**
     * DEBUG
     *
     * @param armee
     */
    public void setArmee(Armee armee) {
        this.armee = armee;
    }

}
