/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import carte.Carte;
import carte.Cellule;
import carte.CelluleType;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import unitesDeCombat.joueur.Joueur;
import unitesDeCombat.hero.Heros;
import java.util.Collections;
import javax.swing.border.Border;
import sauvegarde.Sauvegarde;
import ui.carte.CelluleCase;

/**
 *
 * @author jeff
 */
public class CarteControler extends MouseAdapter {

    private final MainControler mainControler;

    private Carte carte;
    private final List<CelluleType> celluleTypesList;
    private final List<Carte> cartesList;

    private List<Heros> herosList;
    private List<Joueur> joueurList;
    private Map<String, BufferedImage> imagesMap;
    private List<CelluleCase> buttons;

    private Map<Heros, Cellule> positionHeros;

    private final Random random;

    private Heros herosEnCours;
    private Heros dernierHerosAffiche;

    private boolean waitingForUser;

    public CarteControler(MainControler mainControler) {
        this.random = new Random();

        this.mainControler = mainControler;

        this.cartesList = mainControler.getCartesList();
        this.celluleTypesList = mainControler.getCelluleTypesList();

        this.imagesMap = mainControler.getImagesMap();
    }

    public void setImagesMap(Map<String, BufferedImage> imagesmap) {
        this.imagesMap = imagesmap;
    }

    public void selectCarte(Sauvegarde sauvegarde) {

        this.positionHeros = new HashMap<>();

        if (sauvegarde != null) {

            this.joueurList = sauvegarde.getJoueurList();
            this.carte = new Carte(sauvegarde.getCarte());
            carte.setControler(mainControler);
            findHeros();

        } else {

            this.joueurList = new ArrayList<>();
            herosList = new ArrayList<>();
            carte = randomCarte();
            carte.setControler(mainControler);
            generateCarte();
        }
    }

    public List<Joueur> getJoueurList() {
        return this.joueurList;
    }

    public Joueur getJoueurHumain() {
        return mainControler.getJoueurHumain();
    }

    public BufferedImage getDefaultImage() {
        return imagesMap.get("visibleDefaut");
    }

    public BufferedImage getVoidImage() {
        return imagesMap.get("horsChamps");
    }

    public void setButtons(List<CelluleCase> buttons) {
        this.buttons = buttons;
    }

    public Cellule get(int x, int y) {
        return this.carte.get(x, y);
    }

    public Cellule get(int index) {

        return this.carte.get(index);
    }

    private void generateCarte() {

        carte.build();

        Properties properties = carte.getProperties();

        Set<String> stringPropertyNames = properties.stringPropertyNames();

        for (String pptname : stringPropertyNames) {

            int nbCellule = 0;
            try {
                nbCellule = Integer.parseInt(properties.getProperty(pptname, "0"));

            } catch (NumberFormatException ex) {
                continue;
            }

            for (CelluleType type : celluleTypesList) {
                if (type.getNom().equals(pptname)) {
                    carte.add(type, nbCellule);
                    System.out.println(pptname + ":" + nbCellule);
                }
            }
        }

        placeHero();
    }

    private void placeHero() {

        // en debut de partie, chaque joueur dispose d'un unique heros.
        // on place donc un heros par taverne
        joueurList.add(new Joueur("TOI", true, mainControler));

    }

    public void placeHerosEnnemies() {

        int nbJoueur = getNbTaverne();

        for (int i = 1; i < nbJoueur; i++) {

            joueurList.add(new Joueur("Ordinateur" + i, false, mainControler));
        }

        for (Joueur joueur : joueurList) {
            herosList.addAll(joueur.getHerosList());
        }

        Collections.shuffle(herosList);

        for (Heros hero : herosList) {

            boolean isPlace = false;

            for (int numligne = 0; numligne < carte.getDy(); numligne++) {

                for (int numcol = 0; numcol < carte.getDx(); numcol++) {

                    Cellule cellule = carte.get(numcol, numligne);
                    CelluleType type = cellule.getType();

//                    if (!isPlace && type != null && type.getNom().equalsIgnoreCase("taverne") && cellule.isEmpty()) {
                    if (!isPlace && type != null && cellule.isTaverne() && cellule.isEmpty()) {
                        cellule.add(hero);
                        positionHeros.put(hero, cellule);
                        isPlace = true;
                    }
                }
            }
        }
    }

    private void findHeros() {

        int x = carte.getDx();
        int y = carte.getDy();

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {

                Cellule cell = carte.get(j, i);
                if (!cell.isEmpty()) {

                    List<Heros> list = cell.getList();
                    for (Heros perso : list) {

                        positionHeros.put(perso, cell);
                    }
                }
            }
        }
    }

    private Carte randomCarte() {

        return cartesList.get(random.nextInt(cartesList.size()));
    }

    public Carte getCarte() {
        return this.carte;
    }

    private int getNbTaverne() {
        return Integer.parseInt(carte.getProperties().getProperty("taverne"));
    }

    public JPanel creerFrame() {
        return carte.creerFrame(imagesMap);
    }

    public void runPartie() {

        Collections.shuffle(herosList);

        herosEnCours = herosList.get(0);

        waitingForUser = false;

        tourDeJeu(herosEnCours);
    }

    private void tourDeJeu(Heros herosEnCours) {

        if (herosEnCours.getJoueur().isJouable()) { // heros controle par un humain

            waitingForUser = true;
            displayDistanceMax(herosEnCours);
            dernierHerosAffiche = herosEnCours;

        } else { // heros controle par l'ordinateur

            boolean jouablePresent = false;
            for (Heros heros : herosList) {
                if (heros.getJoueur().isJouable()) {
                    jouablePresent = true;
                }
            }

            if (jouablePresent && !waitingForUser) {

                Cellule destination = deplacerAleatoire(herosEnCours);

                affichePositionsHeros(); // DEBUG

                destination.action();

                tourDeJeu(nextHeros());
            }
        }
    }

    private Heros nextHeros() {

        int indexEnCours = herosList.indexOf(herosEnCours);

        if (indexEnCours + 1 < herosList.size()) {

            this.herosEnCours = herosList.get(indexEnCours + 1);
            return herosList.get(indexEnCours + 1);

        } else {

            this.herosEnCours = herosList.get(0);
            return herosList.get(0);
        }

    }

    private void displayDistanceMax(Heros heros) {

        if (heros != null) {
            for (CelluleCase button : buttons) {
                int didx = buttons.indexOf(button);

                Border border = null;
                if (isReachable(heros, didx)) {

                    button.setEnabled(true);

                    Cellule cell = carte.get(didx);

                    if (!cell.isEmpty()) {
                        Color color = null;
                        if (!cell.contains(heros)) {
                            color = Color.red;
                        } else {
                            color = Color.green;
                        }
                        border = new LineBorder(color);
                    }
                } else {
                    button.setEnabled(false);
                }
                button.setBorder(border);
            }

            mainControler.refreshFramePrincipale();
        }
    }

    private boolean isReachable(Heros heros, int finalIndex) {

        int carteWidth = carte.getDx();

        int initIndex = 0;
        Cellule cell = positionHeros.get(heros);
        if (cell != null) {
            initIndex = cell.getIndex();
        } else {
            throw new NullPointerException();
        }

        double distMax = heros.getCapaciteDeplacement();
        double diffMax = heros.getCapaciteDeplacement() * carteWidth;

        boolean multiple = (Math.abs(initIndex - finalIndex) % carteWidth) == 0;
        boolean procheV = diffMax >= Math.abs(initIndex - finalIndex);

        if (multiple && procheV) {
            return true;
        }

        boolean procheH = distMax >= Math.abs(initIndex - finalIndex);
        int l1 = initIndex / carteWidth;
        int l2 = finalIndex / carteWidth;
        boolean mmligne = (l1) == (l2);

        if (procheH && mmligne) {
            return true;
        }

        for (int i = 0; i < distMax; i++) {
            for (int j = 0; j < distMax; j++) {
                boolean NW = ((initIndex - i) - j * carteWidth) == finalIndex;
                boolean NE = ((initIndex + i) - j * carteWidth) == finalIndex;
                boolean SE = ((initIndex + i) + j * carteWidth) == finalIndex;
                boolean SW = ((initIndex - i) + j * carteWidth) == finalIndex;
                int prevLigne = (l2) - j;
                int nextLigne = (l2) + j;
                boolean mmligneUp = l1 == prevLigne;
                boolean mmligneDown = l1 == nextLigne;

                if ((NW || NE || SW || SE) && (mmligneUp || mmligneDown)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Change la position du heros, puis rafraichie l'affichage
     *
     * @param heros
     * @param didx
     */
    private void deplacer(Heros heros, int didx) {

        if (isReachable(heros, didx)) {

            effectuerDeplacement(heros, didx);
        }
    }

    /**
     * Change la position du heros pour une destination aléatoire dans son
     * champs d'action
     *
     * @param heros
     */
    private Cellule deplacerAleatoire(Heros heros) {

        List<Integer> list = new ArrayList<>();
        for (CelluleCase button : buttons) {

            int didx = buttons.indexOf(button);

            if (isReachable(heros, didx)) {
                list.add(didx);
            }
        }

        // avoid standstill
        int heroPosition = positionHeros.get(heros).getIndex();
        for (Integer index : list) {
            if (index == heroPosition) {
                list.remove(index);
                break;
            }
        }

        int idest = list.get(new Random().nextInt(list.size()));

        return effectuerDeplacement(heros, idest);
    }

    private Cellule effectuerDeplacement(Heros heros, int indexDestination) {
        Cellule source = positionHeros.get(heros);
        Cellule destination = carte.get(indexDestination);
        source.remove(heros);
        destination.add(heros);
        positionHeros.put(heros, destination);
        return destination;
    }

    public void supprimer(Joueur joueur) {
        joueurList.remove(joueur);
    }

    public void supprimer(Heros hero) {
        positionHeros.get(hero).getList().remove(hero);
        positionHeros.remove(hero);
        herosList.remove(hero);
    }

    /**
     * La méthode correspond en fait à un tour de jeu. 1- L'utilisateur clique :
     * commande un déplacement, et l'action correspondant à la cellule ciblée 2-
     * Ensuite un déplacement et son action correspondante sont commandés pour
     * chacun des autres joueurs (non jouables),
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        int caseIndex = buttons.indexOf(e.getSource());

        System.out.println("cell " + caseIndex + " clicked"); // DEBUG
        Cellule clickedCell = this.get(caseIndex);

        // empeche les deplacements sur sa propre case
        if (positionHeros.get(herosEnCours).getIndex() != caseIndex) {

            deplacer(herosEnCours, caseIndex);
            clickedCell.action();

            affichePositionsHeros(); // DEBUG

            waitingForUser = false;

            tourDeJeu(nextHeros());

//            // deplacement des autres heros dans un ordre aléatoire !
//            List<Heros> shuffledHerosList = new ArrayList<>(herosList);
//            shuffledHerosList.remove(premierHerosHumain);
//            Collections.shuffle(shuffledHerosList);
//
//            // classical for loop avoid concurrentModificationExcetion during iteration in enhanced for loop
//            for (int i = 0; i < shuffledHerosList.size(); i++) {
//                Heros heros = shuffledHerosList.get(i);
//                if (heros.getJoueur().isJouable()) { // le heros est controlé par un humain
//
////                    deplacer(heros, caseIndex);
////                    clickedCell.action();
////                    affichePositionsHeros(); // DEBUG
//                } else { // le heros n'est pas humain
//                    Cellule destination = deplacer(heros);
//                    destination.action();
//                }
//            }
//
//            affichePositionsHeros(); // DEBUG
        }
    }

    /**
     * DEBUG !!! Affiche en console, la liste des joueurs ainsi que l'index de
     * leur position respective
     */
    public void affichePositionsHeros() {

        for (Map.Entry<Heros, Cellule> entry : positionHeros.entrySet()) {
            System.out.println(entry.getKey().getNom() + ":" + entry.getValue().getIndex());
        }
    }

}
