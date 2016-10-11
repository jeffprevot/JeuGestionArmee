/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carte;

import control.MainControler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import unitesDeCombat.hero.Heros;
import unitesDeCombat.joueur.Joueur;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author jeff
 */
public class Cellule implements Serializable {

    private CelluleType type;
    private final Integer index;
    private final List<Heros> herosList;

    private final MainControler mainControler;

    public Cellule(MainControler mainControler, int index) {
        this.mainControler = mainControler;
        herosList = new ArrayList<>();
        this.index = index;
    }

    public void setType(CelluleType type) {
        this.type = type;
    }

    public CelluleType getType() {
        return type;
    }

    public int getIndex() {
        return this.index;
    }

    public void add(Heros perso) {
        this.herosList.add(perso);
    }

    public void remove(Heros perso) {
        this.herosList.remove(perso);
    }

    public boolean isEmpty() {
        return herosList.isEmpty();
    }

    public boolean contains(Heros persoPrincipal) {
        return herosList.contains(persoPrincipal);
    }

    public List<Heros> getList() {
        return herosList;
    }

    @Override
    public String toString() {

        return "Cellule{" + "index=" + this.getIndex() + "type=" + type + ", herosList=" + herosList.size() + 1 + '}';
    }

    public void action() {

        List<Heros> actionHerosList = getList();
        System.out.println("population=" + actionHerosList.size()); // DEBUG

        if (actionHerosList.size() > 1 && !isTaverne()) { // la cellule est déjà occupée, mais n'est pas une taverne

            // verifie si (au moins) 2 heros ont le même joueur
            // si tous les joueurs sont différents, alors combat
            // sinon, rien
            Set<Joueur> actionJoueurSet = new HashSet<>();
            for (Heros heros : actionHerosList) {
                actionJoueurSet.add(heros.getJoueur());
            }
            if (actionHerosList.size() == actionJoueurSet.size()) {
                mainControler.confronter(this);
            }

        } else { // la cellule n'est pas encore occupée

            // la cellule correspond à une taverne
            if (isTaverne()) { 
                mainControler.gererNiveau(actionHerosList.get(0));
            
            } else {
                int popNiveau10 = mainControler.getMainSettings().get("popNiveau10");
                if (new Random().nextInt(popNiveau10) ==  1) {
                    JOptionPane.showMessageDialog(null, "Là, par exemple, tu aurais pu gagner un soldat niveau 10...\n"
                            + "Mais comme je suis radin, tu l'as dans le cul");
                }
            }
        }

    }
    
    public boolean isTaverne() {
        return this.type != null && this.type.getNom().equalsIgnoreCase("taverne");
    }

}
