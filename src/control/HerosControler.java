/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import ui.gestionNiveau.UIHeros;
import unitesDeCombat.hero.Heros;
import unitesDeCombat.joueur.Joueur;

/**
 *
 * @author jeff
 */
public class HerosControler {

    private UIHeros uiHeros;
    private final MainControler mainControler;
    private Joueur joueur;
    private Heros heros;

    public HerosControler(MainControler mainControler) {

        this.mainControler = mainControler;
    }

    public void createHeros(Joueur joueur) {

        if (joueur.isJouable()) {

            this.joueur = joueur;
            int nbHerosExistants = joueur.getHerosList().size();
            Heros heros = new Heros(joueur.getNom() + "_Heros_" + nbHerosExistants + 1, joueur);
            this.heros = heros;

            this.uiHeros = new UIHeros(this);
            uiHeros.afficherHeros(heros);
        }
    }

    public void valider() {

        this.heros.setNom(uiHeros.getTextFieldNomHerosValue());
        this.heros.setRatioPC(uiHeros.getSliderPCValue());
        this.heros.setRatioPT(uiHeros.getSliderPTValue());
        this.heros.setRatioCM(uiHeros.getSliderCMValue());
        this.heros.setRatioXP(uiHeros.getSliderXPValue());
        this.heros.setRatioCredits(uiHeros.getSliderCreditsValue());
        this.heros.setRatioPreCombat(uiHeros.getSliderPreCombatValue());
        this.heros.setRatioPertes(uiHeros.getSliderPertesValue());
        this.heros.setRatioGeographie(uiHeros.getSliderGeographieValue());
        this.heros.setRatioMeteo(uiHeros.getSliderMeteoValue());
        this.heros.setRatioNecromancie(uiHeros.getSliderNecromancieValue());

        joueur.ajouterHeros(heros);
        
        System.out.println(heros.toString()); // DEBUG
        // DEBUG: choisir comportement
        joueur.setXP(uiHeros.getXp());
//        joueur.setXP(0d);

        mainControler.placeHerosEnnemis();
    }

    public Heros randomHeros(Joueur joueur) {

        int nbHerosExistants = joueur.getHerosList().size();
        Heros heros = new Heros(joueur.getNom() + "_heros" + nbHerosExistants + 1, joueur);

        heros.setRatioPC(1);
        heros.setRatioPT(1);
        heros.setRatioCM(1);
        heros.setRatioXP(1);
        heros.setRatioCredits(1);
        heros.setRatioGeographie(1);
        heros.setRatioMeteo(1);
        heros.setRatioPertes(1);
        heros.setRatioPreCombat(1);

        return heros;
    }

}
