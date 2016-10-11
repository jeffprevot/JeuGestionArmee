/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sauvegarde;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author jeff
 * @param <TypeStructure>
 */
public class ConnectionFichiers<TypeStructure> {

    private final String nomPhysique;
    private String message = "";

    public ConnectionFichiers(String nomPhysique) {
        this.nomPhysique = nomPhysique;
    }
    
    public ConnectionFichiers(File file) {
        this.nomPhysique = file.getAbsolutePath();
    }

    public String getNomPhysique() {
        return nomPhysique;
    }

    @Override
    public String toString() {
        return getNomPhysique();
    }

    public TypeStructure lire() {

        TypeStructure tab = null;
        message = "Chargement du fichier " + nomPhysique + " :\n";

        try {
            FileInputStream fis = new FileInputStream(nomPhysique);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tab = (TypeStructure) ois.readObject();

            message += "Fichier chargé\n";

        } catch (FileNotFoundException ex) {
            message += "Fichier Inexistant\n";
            JOptionPane.showMessageDialog(null, message);
        } catch (IOException ex) {
            message += "PROBLEME DE LECTURE\n";
            JOptionPane.showMessageDialog(null, message);
        } catch (ClassNotFoundException ex) {
            message += "PROBLEME DE LECTURE : Fichier non conforme\n";
            JOptionPane.showMessageDialog(null, message);
        }
        finally {

            System.out.println(message);
        }

        return tab;
    }

    public void ecrire(TypeStructure tab) {

        message = "Sauvegarde du fichier " + nomPhysique + " :\n";

        try {
            FileOutputStream fos = new FileOutputStream(nomPhysique);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject((TypeStructure) tab);

            message += "Sauvegarde effectuée avec succès\n";

        } catch (FileNotFoundException ex) {
            message += "PROBLEME D'ECRITURE : Fichier Inexistant\n";
            JOptionPane.showMessageDialog(null, message);

        } catch (IOException ex) {
            message += "PROBLEME D'ECRITURE (objet non serializable)\n";
            JOptionPane.showMessageDialog(null, message);
        } 
        finally {

            System.out.println(message);
        }
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

}
