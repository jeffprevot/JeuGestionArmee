/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package environement.geographie;

/**
 *
 * @author jeff
 */
public class Geographie {

    private String nom;

    private Boolean isCombatable;
    private Double handicapJ1Tir;
    private Double handicapJ2Tir;

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

    public Boolean isIsCombatable() {
        return isCombatable;
    }

    public void setIsCombatable(boolean isCombatable) {
        this.isCombatable = isCombatable;
    }

    public Double getHandicapJ1Tir() {
        return handicapJ1Tir;
    }

    public void setHandicapJ1Tir(Double handicapJ1Tir) {
        this.handicapJ1Tir = handicapJ1Tir;
    }

    public Double getHandicapJ2Tir() {
        return handicapJ2Tir;
    }

    public void setHandicapJ2Tir(Double handicapJ2Tir) {
        this.handicapJ2Tir = handicapJ2Tir;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Geographie{" + "nom=" + nom + ", isCombatable=" + isCombatable + ", handicapJ1Tir=" + handicapJ1Tir + ", handicapJ2Tir=" + handicapJ2Tir + '}';
    }

}
