/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sauvegarde;

import carte.Cellule;
import java.io.Serializable;
import java.util.List;
import unitesDeCombat.joueur.Joueur;

/**
 *
 * @author jeff
 */
public class Sauvegarde implements Serializable {

    private final List<List<Cellule>> carte;
    private final List<Joueur> joueurList;

    public Sauvegarde(List<List<Cellule>> carte, List<Joueur> joueurList) {
        this.carte = carte;
        this.joueurList = joueurList;
    }

    public List<List<Cellule>> getCarte() {
        return carte;
    }

    public List<Joueur> getJoueurList() {
        return joueurList;
    }

    @Override
    public String toString() {
        return "Sauvegarde{" + "carte=" + carte + ", joueurList=" + joueurList + '}';
    }

}
