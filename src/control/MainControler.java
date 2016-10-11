/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import unitesDeCombat.armee.Armee;
import unitesDeCombat.armee.Propriete;
import unitesDeCombat.armee.Soldat;
import carte.Carte;
import carte.Cellule;
import carte.CelluleType;
import environement.geographie.Geographie;
import environement.meteo.Meteo;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import unitesDeCombat.joueur.Joueur;
import unitesDeCombat.hero.Heros;
import sauvegarde.Sauvegarde;
import ui.BlinkingMessage;
import ui.FramePrincipale;
import ui.carte.CartePanel;
import utils.SoldatBuilderArgumentException;

/**
 *
 * @author jeff
 */
public class MainControler {

    private final String mainSettingsPath = "config/mainSettings.properties";
    private final String proprietesPath = "config/proprietes";
    private final String meteoPath = "config/environements/meteos";
    private final String geographiePath = "config/environements/geographies";
    private final String cellulesPath = "config/carte/cellulesTypes";
    private final String cartesPath = "config/carte/cartes";
    private final String imagesPath = "config/images";
    private final String nomsPath = "config/noms";

    private Map<String, Integer> mainSettings;
    private List<Joueur> joueurList;
    private List<Carte> cartesList;
    private List<CelluleType> celluleTypesList;
    private Map<String, BufferedImage> imagesMap;
    private Map<String, List<Propriete>> proprietesMap;
    private Map<String, List<String>> nomsMap;
    private List<Meteo> meteoList;
    private List<Geographie> geographieList;

    private EnvironementControler environementControler;
    private CarteControler carteControler;
    private CombatControler combatControler;
    private NiveauControler niveauControler;
    private SoldatControler soldatControler;
    private HerosControler herosControler;

    private FramePrincipale framePrincipale;
    private boolean partieEnCours;

    public MainControler() {
        load();
    }

    public Map<String, Integer> getMainSettings() {
        return mainSettings;
    }

    public List<Joueur> getJoueurList() {
        return joueurList;
    }

    public void setJoueurList(List<Joueur> list) {
        this.joueurList = list;
    }

    public Joueur getJoueurHumain() {
        for (Joueur joueur : joueurList) {
            if (joueur.isJouable()) {
                return joueur;
            }
        }
        return null;
    }

    public CarteControler getCarteControler() {
        return this.carteControler;
    }

    public List<Carte> getCartesList() {
        return cartesList;
    }

    public Carte getCarte() {
        return carteControler.getCarte();
    }

    public Map<String, BufferedImage> getImagesMap() {
        return this.imagesMap;
    }

    public Map<String, List<Propriete>> getProprietesMap() {
        return this.proprietesMap;
    }

    public List<Meteo> getMeteoList() {
        return meteoList;
    }

    public List<Geographie> getGeographieList() {
        return geographieList;
    }

    public List<CelluleType> getCelluleTypesList() {
        return celluleTypesList;
    }

    public boolean isPartieEnCours() {
        return partieEnCours;
    }

    public void setPartieEnCours(boolean newPartieEnCours) {
        this.partieEnCours = newPartieEnCours;
        framePrincipale.setPartieEnCours(newPartieEnCours);
    }

    public void menuGeneral() {
        framePrincipale = new FramePrincipale(this);
    }

    public void creerPartie() {

        System.out.println("\n\nNOUVELLE PARTIE\n\n");

        createControlers();

        this.joueurList = new ArrayList<>();
        this.partieEnCours = false;

        framePrincipale.setCartePanel(generateCarte());

        setPartieEnCours(true);
        refreshInfosPanel();

        carteControler.affichePositionsHeros(); // DEBUG
    }

    public void placeHerosEnnemis() {
        carteControler.placeHerosEnnemies();

        generateRandomArmees(mainSettings.get("nbInitSoldatsParHeros"));

        carteControler.runPartie();

    }

    private void load() {

        System.out.print("Chargement des données...");
        mainSettings = LoadControler.loadmainSettings(mainSettingsPath);
        nomsMap = LoadControler.loadNoms(nomsPath);
        imagesMap = LoadControler.loadImages(imagesPath);
        cartesList = LoadControler.loadCartes(cartesPath);
        celluleTypesList = LoadControler.loadCelluleTypes(cellulesPath);
        meteoList = LoadControler.loadMeteo(meteoPath);
        geographieList = LoadControler.loadGeographie(geographiePath);
        proprietesMap = Propriete.sorProprieteByType(LoadControler.loadProprietes(proprietesPath));
        System.out.println(" OK");
//        environementControler = new EnvironementControler(meteoList, geographieList);
//        combatControler = new CombatControler(environementControler);
//        carteControler = new CarteControler(this);
//        niveauControler = new NiveauControler();
//        this.herosControler = new HerosControler(this);
    }

    private void createControlers() {
        System.out.print("Initialisation des controlers...");
        environementControler = new EnvironementControler(meteoList, geographieList);
        combatControler = new CombatControler(environementControler);
        carteControler = new CarteControler(this);
        niveauControler = new NiveauControler();
        this.herosControler = new HerosControler(this);
        System.out.println(" OK");
    }

    private void refreshInfosPanel() {
        framePrincipale.refreshInfosPanel();
    }

    public JPanel generateCarte() {

        carteControler.selectCarte(null);
        carteControler.setImagesMap(imagesMap);
        this.joueurList = carteControler.getJoueurList();

        return carteControler.creerFrame();
    }

    public void chargerPartie() {
        load();
        createControlers();

        Sauvegarde sauvegarde = SauvegardeControler.lire();
        this.carteControler = new CarteControler(this);
        this.carteControler.selectCarte(sauvegarde);
        this.partieEnCours = true;

        if (sauvegarde != null) {
            this.setJoueurList(sauvegarde.getJoueurList());
        } else {
            this.setJoueurList(new ArrayList<Joueur>());
        }
        
        framePrincipale.setCartePanel(new CartePanel(carteControler, imagesMap));
        refreshInfosPanel();
    }

    public void sauverPartie() {

        if (partieEnCours) {

            SauvegardeControler.ecrire(joueurList, carteControler.getCarte().getListe());
        } else {

            JOptionPane.showMessageDialog(framePrincipale,
                    "Ce petit message d'amour pour t'apprendre combien tu es débile de vouloir sauvegarder cette \"partie\".\n"
                    + "Donc, au lieu de t'écouter, je vais tranquillement me reposer pendant que la honte fera son boulot.\n"
                    + "Bisous");

            JOptionPane.showMessageDialog(null, "Et que je ne t'y reprennes plus.");

            JOptionPane.showMessageDialog(null, "Non parce que sérieux, tu t'es pas dit un moment\n"
                    + "que c'était peut-être un peu con de sauvegarder une partie vide ?");

            JOptionPane.showMessageDialog(null, "Je ne sais pas si tu rends compte quand même...\n"
                    + "Tu allais me faire créer un fichier vide...\n"
                    + "Un fichier complètement inutile");

            JOptionPane.showMessageDialog(null, "ET TE CONNAISSANT, TU M'AURAIS FAIT UNE REMARQUE SUR MON POIDS !!!");

            JOptionPane.showMessageDialog(null, "Tu sais quoi ?! Je me casse !\n"
                    + "T'as qu'à faire signe quand tu seras de meilleure humeur.");
        }
    }

    public void confronter(Cellule cellule) {

        List<Heros> herosList = cellule.getList();

        combatControler.confronter(herosList);

        refreshInfosPanel();

        // using enhanced for loop to iterate while removing element throws ConcurrentModificationException
        for (int i = 0; i < joueurList.size(); i++) {
            Joueur joueur = joueurList.get(i);

            for (Heros hero : joueur.getHerosList()) {
                if (hero.isEmpty()) {
                    supprimerHero(hero);
                }
            }
            if (joueur.isEmpty()) {
                supprimerJoueur(joueur);
            }
        }
    }

    public void gererNiveau(Heros hero) {
        niveauControler.gererNiveau(hero);
    }

    public void supprimerJoueur(Joueur joueur) {

        boolean jouable = joueur.isJouable();

        suppression(joueur);

        if (jouable) {

            BlinkingMessage.createAndShowGUI(joueur.getNom() + "\nTu as perdu, tu n'as lamentablement pas été capable de survivre !\n"
                    + "Essais de ne pas te faire humilier la prochaine fois, ok ?");
//            JOptionPane.showMessageDialog(
//                    framePrincipale,
//                    joueur.getNom() + "Tu as perdu, tu n'as lamentablement pas été capable de survivre !\n"
//                    + "Essais de ne pas te faire humilier la prochaine fois, ok ?");

            partieEnCours = false;
            framePrincipale.setCartePanel(null);

        } else {

            if (joueurList.size() == 1 && joueurList.get(0).isJouable()) {

                BlinkingMessage.createAndShowGUI(joueurList.get(0).getNom() + "Tu as vaincus tous les mécréants de ce monde.\n"
                        + "Tu mérites le respect");

                partieEnCours = false;
                framePrincipale.setCartePanel(null);

            }

        }
    }

    public void supprimerHero(Heros hero) {

        boolean jouable = hero.getJoueur().isJouable();

        suppression(hero);

        if (jouable) {
            JOptionPane.showMessageDialog(
                    framePrincipale,
                    "Tu n'as pas été capable de protéger ton héro " + hero.getNom());
        }
    }

    private void suppression(Joueur joueur) {
        System.out.println("suppression du joueur" + joueur.getNom()); // DEBUG
        this.joueurList.remove(joueur);
        carteControler.supprimer(joueur);
    }

    private void suppression(Heros hero) {
        System.out.println("suppression du hero" + hero.getNom()); // DEBUG
        carteControler.supprimer(hero);
    }

    /**
     * Attribut aléatoirement une armée à chaque joueur
     *
     * @param nbSoldat
     */
    public void generateRandomArmees(int nbSoldat) {

        this.soldatControler = new SoldatControler(proprietesMap, nomsMap);

        for (Joueur joueur : joueurList) {

            for (Heros heros : joueur.getHerosList()) {

                joueur.setArmee(randomArmee(heros, nbSoldat));

            }
        }
    }

    public Armee randomArmee(Heros heros, int nombreSoldat) {

        Armee armee = new Armee();

        int index = nombreSoldat;
        while (index != 0) {

            try {

                Soldat soldat = soldatControler.createRandomSoldat();
                soldat.setNumero(armee.getArmee().size() + 1);
                soldat.setHeros(heros);
                armee.ajouter(soldat);

            } catch (SoldatBuilderArgumentException ex) {
                Logger.getLogger(MainControler.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            index--;
        }

        return armee;
    }

    public void createHeros(Joueur joueur) {

//        System.out.println("DEBUG : attribution de 300 XP à " + joueur.getNom()); // DEBUG
        joueur.ajouterXP(mainSettings.get("xpInit")); // DEBUG

        herosControler.createHeros(joueur);

    }

    public Heros randomHeros(Joueur joueur) {

        return herosControler.randomHeros(joueur);
    }

    public void refreshFramePrincipale() {
        this.framePrincipale.refreshCarte();
    }

}
