/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package environement.meteo;

/**
 *
 * @author jeff
 */
public class Meteo {

    private String nom;

    private Double handicapCombat;
    private Double handicapTir;
    
    private String description;
    private String image;

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

    public Double getHandicapCombat() {
        return handicapCombat;
    }

    public void setHandicapCombat(Double handicapCombat) {
        this.handicapCombat = handicapCombat;
    }

    public Double getHandicapTir() {
        return handicapTir;
    }

    public void setHandicapTir(Double handicapTir) {
        this.handicapTir = handicapTir;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Meteo{" + "nom=" + nom + ", handicapCombat=" + handicapCombat + ", handicapTir=" + handicapTir + '}';
    }

}
