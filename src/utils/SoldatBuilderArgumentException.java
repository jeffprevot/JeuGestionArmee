/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author jeff
 */
public class SoldatBuilderArgumentException extends Exception {
    
    public SoldatBuilderArgumentException() {
        
        super("Erreur lors de la création d'un soldat.\n"
                + "Vérifier les Propriétés données");
        
    }
    
}
