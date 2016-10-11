/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitesDeCombat.armee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jeff
 */
public class Propriete implements Cloneable, Serializable {

    private String type;
    private String nom;

    private Double capaciteDeplacement;
    private Double capaciteTir;
    private Double capaciteCombat;
    
    private String description;
    
    private String image;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getImage() {
        return this.image;
    }
    
    public void setImage(String imagePath) {
        this.image = imagePath;
    }

    public Double getCapaciteDeplacement() {
        return capaciteDeplacement;
    }

    public void setCapaciteDeplacement(Double capaciteDeplacement) {
        this.capaciteDeplacement = capaciteDeplacement;
    }

    public Double getCapaciteTir() {
        return capaciteTir;
    }

    public void setCapaciteTir(Double capaciteTir) {
        this.capaciteTir = capaciteTir;
    }

    public Double getCapaciteCombat() {
        return capaciteCombat;
    }

    public void setCapaciteCombat(Double capaciteCombat) {
        this.capaciteCombat = capaciteCombat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Propriete{" + "type=" + type + ", nom=" + nom + ", capaciteDeplacement=" + capaciteDeplacement + ", capaciteTir=" + capaciteTir + ", capaciteCombat=" + capaciteCombat + '}';
    }

    @Override
    public Propriete clone() throws CloneNotSupportedException {

        Object clone = null;

        try {
            clone = super.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return (Propriete) clone;
    }

    public static Map<String, List<Propriete>> sorProprieteByType(Map<String, Propriete> unSortedMap) {

        Map<String, List<Propriete>> propByType = new HashMap<>();

        for (String nom : unSortedMap.keySet()) {

            Propriete ppt = unSortedMap.get(nom);

            String type = ppt.getType();

            if (propByType.containsKey(type)) {

                propByType.get(type).add(ppt);

            } else {

                List<Propriete> list = new ArrayList<>();
                list.add(ppt);
                propByType.put(type.toLowerCase(), list);

            }

        }

        return propByType;
    }
}
