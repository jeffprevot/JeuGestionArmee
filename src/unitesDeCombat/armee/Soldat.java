/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitesDeCombat.armee;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import unitesDeCombat.hero.Heros;
import utils.SoldatBuilderArgumentException;
import utils.Utils;

/**
 *
 * @author jeff
 */
public class Soldat implements Serializable {

    private int numero;
    private String nom;
    private Propriete race;
    private Propriete classe;
    private int niveau;

    private Heros heros;

    public Soldat(List<Propriete> list, String nom) throws SoldatBuilderArgumentException {

        this.heros = new Heros();

        if (!checkDuplicateUsingSet(list)) {

            for (Propriete ppt : list) {

                String type = ppt.getType();

                if (type.equalsIgnoreCase("race")) {

                    this.race = ppt;

                } else if (type.equalsIgnoreCase("classe")) {

                    this.classe = ppt;

                } else {

                    throw new SoldatBuilderArgumentException();
                }
            }
            this.nom = nom;
            this.niveau = 1;

        } else {
            throw new SoldatBuilderArgumentException();
        }
    }

    public void setHeros(Heros heros) {
        this.heros = heros;
    }

    /**
     * detect duplicate in array by comparing size of List and Set. since Set
     * doesn't contain duplicate, size must be less for an array which contains
     * duplicates
     *
     * @param list
     * @return
     */
    public static boolean checkDuplicateUsingSet(List<Propriete> list) {

        Set<String> inputSet = new HashSet();

        for (Propriete ppt : list) {

            inputSet.add(ppt.getType());
        }

        return inputSet.size() < list.size();
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return this.numero;
    }

    public String getNom() {
        return this.nom;
    }

    public Propriete getRace() {
        return race;
    }

    public void setRace(Propriete race) {
        this.race = race;
    }

    public Propriete getClasse() {
        return classe;
    }

    public void setClasse(Propriete classe) {
        this.classe = classe;
    }

    public int getNiveau() {
        return this.niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public void augmenterNiveau() {
        this.niveau++;
    }

    public Double getPointsCombat() {
        return race.getCapaciteCombat() * classe.getCapaciteCombat() * niveau;
    }

    public Double getPointsTir() {
        return race.getCapaciteTir() * classe.getCapaciteTir() * niveau;
    }

    public Double getCapaciteDeplacement() {
        return race.getCapaciteDeplacement() * classe.getCapaciteDeplacement() * niveau * heros.getRatioCM();
    }

    public Double getPuissanceCombat() {
        return getPointsCombat() * getCapaciteDeplacement() * this.heros.getRatioPC();
    }

    public Double getPuissanceTir() {
        return getPointsTir() * getCapaciteDeplacement() * this.heros.getRatioPT();
    }

    public Double getPuissance() {
        return getPuissanceCombat() + getPuissanceTir();
    }

    public Double valoriser() {
        return getPuissance() / 10;
    }

    @Override
    public String toString() {
        return "Soldat{" + "numero:" + numero + ", race=" + race.getNom() + ", classe=" + classe.getNom() + ", niveau=" + niveau + ", puissance=" + Utils.round(getPuissance()) + '}';
    }

}
