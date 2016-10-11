/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitesDeCombat.armee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Utils;

/**
 *
 * @author jeff
 */
public class Armee implements Iterable<Soldat>, Serializable, Cloneable {

    private List<Soldat> armee;

    public Armee() {
        this.armee = new ArrayList<>();
    }
    
    @Override
     public Iterator<Soldat> iterator() {       

        Iterator<Soldat> iprof = armee.iterator();

        return iprof;
    }
     
     public void setArmee(Armee armee) {
         this.armee = armee.getArmee();
     }

    public List<Soldat> getArmee() {
        return armee;
    }

    public int size() {
        return this.getArmee().size();
    }

    public boolean contains(Soldat soldat) {
        return this.armee.contains(soldat);
    }

    public void ajouter(Soldat soldat) {
        this.armee.add(soldat);
    }

    public void ajouter(List<Soldat> armeeMiseEnJeu) {
        this.armee.addAll(armeeMiseEnJeu);
    }

    public void supprimer(Soldat soldat) {
        this.armee.remove(soldat);
    }

    public void supprimer(List<Soldat> armeeMiseEnJeu) {
        for (Soldat soldat : armeeMiseEnJeu) {
            supprimer(soldat);
        }
    }

    /**
     * Supprimer aléatoirement nombre de soldat de l'armée.
     *
     * @param nombre
     * @return 
     */
    public List<Soldat> supprimerSoldatByNb(int nombre) {

        List<Soldat> list = new ArrayList<>();
        
        if (nombre >= 0) {
            
            for (int i = 0; i < nombre && armee.size() > 0; i++) {
                
                int index = new Random().nextInt(armee.size());
                Soldat soldat = armee.get(index);
                list.add(soldat);
                supprimer(soldat);
            }
            
        } else {
            throw new IllegalArgumentException("Impossible de supprimer un nombre négatif de soldats");
        }
        
        return list;
    }

    public Soldat getSoldat(int numero) {
        for (Soldat soldat : armee) {
            if (soldat.getNumero() == numero) {
                return soldat;
            }
        }
        return null;
    }

    public Double getCapaciteDeplacement() {

        Double deplacement = Double.MAX_VALUE;

        for (Soldat elt : armee) {

            double value = elt.getCapaciteDeplacement();

            if (deplacement > value) {
                deplacement = value;
            }
        }

        if (deplacement == Double.MAX_VALUE) {
            return 0d;
        }
        return deplacement;
    }

    public Double getPuissanceCAC() {

        Double points = 0d;

        for (Soldat soldat : armee) {
            points += soldat.getPuissanceCombat();
        }

        return points;
    }

    public Double getPuissanceTir() {

        Double points = 0d;

        for (Soldat soldat : armee) {
            points += soldat.getPuissanceTir();
        }

        return points;
    }

    public Double getPuissanceTotale() {
        Double points = 0d;

        for (Soldat soldat : armee) {
            points += soldat.getPuissance();
        }

        return points;
    }

    public Double valoriser() {

        Double val = 0d;

        for (Soldat soldat : armee) {
            val += soldat.valoriser();
        }
        return val;
    }
    
    @Override
    public String toString() {

        String s = armee.size() + " soldats :\n";

        for (Soldat soldat : armee) {
            s += "\t" + soldat.toString() + "\n";
        }

        return s;
    }
    
    @Override
    public Armee clone() throws CloneNotSupportedException {
        
        Armee armee = null;
        try {
            armee = (Armee) super.clone();
            
            
            
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Armee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return armee;
    } 

}
