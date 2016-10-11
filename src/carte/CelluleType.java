/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carte;

import java.io.Serializable;

/**
 *
 * @author jeff
 */
public class CelluleType implements Serializable {

    private String nom;

    public String getNom() {
        return nom;
    }

    public void setType(String type) {
        this.nom = type;
    }

    @Override
    public String toString() {
        return "Cellule{" + "nom=" + nom + '}';
    }

}
