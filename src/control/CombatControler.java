/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import unitesDeCombat.armee.Armee;
import unitesDeCombat.armee.Soldat;
import environement.Handicap;
import unitesDeCombat.hero.Heros;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.JOptionPane;
import ui.combat.Score;
import ui.combat.UIDemanderComposition;
import ui.combat.UIScores;

/**
 *
 * @author jeff
 */
public class CombatControler {

    private List<Heros> herosList;
    private List<Heros> herosRestants; // utilisée pendant la selection des soldats à mettre en jeu lors d'un combat

    private final EnvironementControler environementControler;
    private final Map<Heros, Score> scores;

    private UIDemanderComposition uiDemanderComposition;
    private final UIScores uIScores;

    private Map<Heros, Handicap> handicaps;

    public CombatControler(EnvironementControler environementControler) {
        this.environementControler = environementControler;
        scores = new HashMap<>();
        uIScores = new UIScores();
    }

    public void confronter(List<Heros> combatHerosList) {

        System.out.println("Préparation du combat");

        this.herosList = combatHerosList;
        herosRestants = new ArrayList(herosList);

        handicaps = environementControler.generateHandicaps(herosList);

        preCombat();

        if (herosList.size() > 1) {
            demanderComposition(herosList.get(0));
        }
    }

    private void preCombat() {

        System.out.println("Pré-Combat");

        for (int i = 0; i < herosList.size(); i++) {
            Heros heros1 = herosList.get(i);
            double ratioPreCombat = heros1.getRatioPreCombat();

            for (int j = 0; j < herosList.size(); j++) {
                Heros heros2 = herosList.get(j);

                if (heros1 != heros2) {
                    int taille = heros2.getTailleArmee();
                    int garde = (int) (taille / ratioPreCombat);
                    int nombre = taille - (int) (taille / ratioPreCombat);

                    System.out.println(heros1.getNom() + "(" + ratioPreCombat + " --> " + nombre + ") attaque " + heros2.getNom()); // DEBUG
                    heros2.supprimerSoldatByNb(nombre, null);

                    if ((heros1.getJoueur().isJouable() || heros2.getJoueur().isJouable()) && nombre >= 1) {
                        JOptionPane.showMessageDialog(null,
                                heros1.getNom() + " lance une boule de feu et élimine"
                                + nombre + " soldats de l'armée de " + heros2.getNom());
                    }

                    if (heros2.getArmee().size() == 0) {
                        herosList.remove(heros2);
                        JOptionPane.showMessageDialog(
                                null,
                                "La boule de feu a décimé la ridicule armée de " + heros2.getNom()
                                + ", qui a préféré en finir avec la vie plutôt que de porter dans honte.\n"
                                + "Prend exemple mécréant ! C'était un guerrier noble ! "
                                + "Avec une armée ridicule, certes, mais noble.");
                    }

                }
            }
        }

    }

    public void demanderComposition(Heros heros) {

        Armee compositionChoisie = null;

        if (heros != null) {

            System.out.print("Composition pour " + heros); // DEBUG

            if (heros.getJoueur().isJouable()) {

                Handicap handicapEnvironemental = handicaps.get(heros);
                uiDemanderComposition = new UIDemanderComposition(this, heros, handicapEnvironemental);
                uiDemanderComposition.setModal(true);
                uiDemanderComposition.setGeographie(getGeographie());
                uiDemanderComposition.setVisible(true);

            } else {

                Armee armee = heros.getArmee();
                compositionChoisie = new Armee();

                int quantite = 0;

                // tire un nb aleatoire pour le nb de soldats mis en jeu
                do {
                    quantite = new Random().nextInt(heros.getTailleArmee() + 1);
                    System.out.print(quantite + ",");
                } while (quantite == 0);

                System.out.println("quantite=" + quantite); // DEBUG

                // choix aleatoire des soldats depuis l'armée du heros1
                for (int i = 0; i < quantite; i++) {
                    Soldat s;
                    do {
                        int num = new Random().nextInt(heros.getTailleArmee());
                        System.out.print(num + ", ");
                        List<Soldat> list = armee.getArmee();
                        s = list.get(num);
                    } while (s == null || compositionChoisie.contains(s));

                    compositionChoisie.ajouter(s);
                }

                heros.setArmeeMiseEnJeu(compositionChoisie);

                System.out.println();
                System.out.println("tirage du heros suivant:");
                Heros suivant = nextHeros(heros);
                demanderComposition(suivant);

            }
        } else {

            System.out.println();
            combattre();
        }
    }

    public Heros nextHeros(Heros heros) {

        herosRestants.remove(heros);

        if (!herosRestants.isEmpty()) {

            return herosRestants.get(0);
        }

        return null;
    }

    public String getGeographie() {

        return this.environementControler.getGeographie();
    }

    public String getMeteo() {
        return this.environementControler.getMeteo();
    }

    public double calculerScore(Heros heros, Handicap handicapEnvironemental) {

        Armee armee = heros.getArmeeMiseEnJeu(); // DEBUG
        return heros.getArmeeMiseEnJeu().getPuissanceTotale() * handicapEnvironemental.getHandicapTotal();
    }

    /**
     * Calcule le vainqueur, puis distribue les gains et élimine les soldats
     * morts au combat.
     *
     */
    private void combattre() {

        System.out.println("Combat");

        scores.clear();

        for (Heros heros : herosList) {

            Handicap handicapEnvironemental = handicaps.get(heros);

            double score = calculerScore(heros, handicapEnvironemental);

            scores.put(heros, new Score(score));
        }

        Double scoreGagnant = 0d;
        Heros gagnant = null;
        Double credits = 0d;

        // comparaison des scores, calcul du gagnant
        for (Map.Entry<Heros, Score> entry : scores.entrySet()) {

            Score score = entry.getValue();

            if (score.getScore() >= scoreGagnant) {

                scoreGagnant = score.getScore();
                gagnant = entry.getKey();
            }
        }

        // calcul du gain du vainqueur
        for (Map.Entry<Heros, Score> entry : scores.entrySet()) {

            Heros heros = entry.getKey();

            if (heros != gagnant) {

                credits += heros.getArmeeMiseEnJeu().valoriser() / 2;
            }
        }

        // distribution des gains et des pertes
        for (Map.Entry<Heros, Score> entry : scores.entrySet()) {

            Heros heros = entry.getKey();
            Score score = entry.getValue();

            int nbSoldat = 0;

            if (heros != gagnant) {

                List<Soldat> armeeMiseEnJeu = heros.getArmeeMiseEnJeu().getArmee();

                // suppression des soldats chez le vainqueur (pertes collaterales)
                // attribution des credits au vainqueur
                nbSoldat = (int) ((double) armeeMiseEnJeu.size() * (1 - ((scoreGagnant - score.getScore()) / scoreGagnant)));
                if (gagnant != null) {

                    Score scoreVainqueur = scores.get(gagnant);

                    scoreVainqueur.setPerteSoldats(nbSoldat);

                    // distribution des credits gagnés au vainqueur
                    gagnant.ajouterCredits(credits);
                    scoreVainqueur.setGainsCredit(credits);
                }

                //perte de la totalite des soldats vaincus mis en jeu
                score.setPerteSoldats(armeeMiseEnJeu.size());

                // attribution des credits au vaincus
                score.setGainsCredit(0d);
            }

            // attribution de l'expérience à tous les heros1
            score.setGainsXP(credits);
        }

        //affichage des statistiques du combat
        for (Heros elt : herosList) {

            if (elt.getJoueur().isJouable()) {

                uIScores.cleanScores();
                for (Map.Entry<Heros, Score> entry : scores.entrySet()) {
                    Heros heros = entry.getKey();
                    Score score = entry.getValue();
                    uIScores.afficher(heros, score, handicaps.get(heros));
                }
                uIScores.setGeoraphie(getGeographie());
                uIScores.setMeteo(getMeteo());
                String gagnantString = "";
                if (gagnant != null) {
                    gagnantString = gagnant.getNom();
                }
                uIScores.setVainqueur(gagnantString);
                uIScores.setVisible(true);
                break;
            }
        }

        // attribution des pertes/gains aux joueurs eux memes
        for (Map.Entry<Heros, Score> entry : scores.entrySet()) {

            Heros heros = entry.getKey();
            Score score = entry.getValue();

            if (heros != gagnant) {
                // suppression de l'ensemble de l'armee mise en jeu
                heros.supprimer(heros.getArmeeMiseEnJeu());
            } else {
                // suppression aléatoire de soldats, parmi l'armee mise en jeu
                heros.supprimerSoldatByNb(score.getPerteSoldats(), heros.getArmeeMiseEnJeu());
            }

            heros.ajouterCredits(score.getGainsCredit());
            heros.ajouterXP(score.getGainsXP());
        }

        String nom = "";
        if (gagnant != null) {
            nom = gagnant.getNom();
        }
        System.out.println("scores : gagnant = " + nom); // DEBUG
        for (Map.Entry<Heros, Score> entry : scores.entrySet()) {
            Heros heros = entry.getKey();
            Score score = entry.getValue();
            System.out.println(heros.getNom() + " : " + score.getScore());
        }
    }
}
