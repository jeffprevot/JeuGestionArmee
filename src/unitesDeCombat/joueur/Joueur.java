/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitesDeCombat.joueur;

import control.MainControler;
import unitesDeCombat.hero.Heros;
import unitesDeCombat.armee.Armee;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import utils.Utils;

/**
 *
 * @author jeff
 */
public class Joueur implements Serializable {

    private final MainControler mainControler;

    private final List<Heros> herosList;
    private boolean jouable;
    private final String nom;
    private double credits;
    private double XP;

    public Joueur(String nom, boolean jouable, MainControler mainControler) {

        this.mainControler = mainControler;

        this.nom = nom;
        this.jouable = jouable;
        this.credits = 0;
        this.herosList = new ArrayList<>();

        // en debut de partie, chaque joueur ne dispose que d'un unique heros
        if (!jouable) {
            herosList.add(mainControler.randomHeros(this));
        } else {
            mainControler.createHeros(this);
        }
    }

    public boolean isJouable() {
        return jouable;
    }

    public void setJouable(boolean jouable) {
        this.jouable = jouable;
    }

    public String getNom() {
        return this.nom;
    }

    public List<Heros> getHerosList() {
        return this.herosList;
    }
    
    public void ajouterHeros(Heros heros) {
        herosList.add(heros);
    }

    public boolean isEmpty() {
        for (Heros hero : herosList) {
            if (!hero.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public Double getPuissanceCAC() {
        Double nb = 0d;
        for (Heros heros : herosList) {
            nb += heros.getPuissanceCAC();
        }
        return nb;
    }

    public Double getPuissanceTir() {
        Double nb = 0d;
        for (Heros heros : herosList) {
            nb += heros.getPuissanceTir();
        }
        return nb;
    }

    public Double getPuissanceTotale() {
        Double nb = 0d;
        for (Heros heros : herosList) {
            nb += heros.getPuissanceTotale();
        }
        return nb;
    }

    public Double getCapaciteDeplacement() {
        Double nb = 0d;
        for (Heros heros : herosList) {
            nb += heros.getCapaciteDeplacement();
        }
        return nb;
    }

    public Double valoriser() {
        Double nb = 0d;
        for (Heros heros : herosList) {
            nb += heros.valoriser();
        }
        return nb;
    }

    public Double getNiveau() {
        Double niveau = 0d;
        for (Heros hero : herosList) {
            niveau += hero.getPuissanceTotale();
        }
        return niveau;
    }

    public Double getCredits() {
        return this.credits;
    }

    public Double getXP() {
        return Utils.round(this.XP);
    }

    public void setXP(Double value) {
        this.XP = value;
    }

    public void ajouterCredits(double credits) {
        this.credits += credits;
    }

    public void ajouterXP(double xp) {
        this.XP += xp;
    }

    public void enleverCredits(double credits) {
        this.credits -= credits;
    }

    public void enleverXP(double xp) {
        this.XP -= xp;
    }

    /**
     * DEBUG
     *
     * @param armee
     */
    public void setArmee(Armee armee) {
        this.herosList.get(0).setArmee(armee);
    }

}
