/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import unitesDeCombat.armee.Propriete;
import carte.Carte;
import carte.CelluleType;
import com.csvreader.CsvReader;
import environement.geographie.Geographie;
import environement.meteo.Meteo;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.apache.commons.io.FilenameUtils;
import propertiesFiles.PropertiesReader;

/**
 *
 * @author jeff
 */
public class LoadControler {

    public static Set<File> listFilesForFolder(final File folder) {

        Set<File> set = new HashSet<>();
        
//        JOptionPane.showMessageDialog(null, "Chargement :\n" + folder); // DEBUG

        try {

            for (final File fileEntry : folder.listFiles()) {
                if (fileEntry.isDirectory()) {
                    listFilesForFolder(fileEntry);
                } else {
                    set.add(fileEntry);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }

        return set;
    }
    
    
    public static Map<String, Integer> loadmainSettings(String filePath) {
        
        Map<String, Integer> list = new HashMap<>();
        
        File file = new File(userPath(filePath));
            
            try {
                PropertiesReader ppr = new PropertiesReader(file.getCanonicalPath());
                Properties properties = ppr.readProperties();

                try {
                    Integer nbInitSoldatsParHeros = Integer.parseInt(properties.getProperty("nbInitSoldatsParHeros"));
                    Integer xpInit = Integer.parseInt(properties.getProperty("xpInit"));
                    Integer popNiveau10 = Integer.parseInt(properties.getProperty("popNiveau10"));
                    
                    list.put("nbInitSoldatsParHeros", nbInitSoldatsParHeros);
                    list.put("xpInit", xpInit);
                    list.put("popNiveau10", popNiveau10);
                    
                    System.out.println(list.toString()); //DEBUG

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.out.println("##### Erreur lors de la lecture des paramètres généraux #####");
                    JOptionPane.showMessageDialog(null, e);
                }

            } catch (IOException ex) {
                Logger.getLogger(LoadControler.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex);
            }
            
        
        
        return list;
    }
    

    public static List<Meteo> loadMeteo(String filePath) {

        List<Meteo> list = new ArrayList<>();

        File folder = new File(userPath(filePath));

        Set<File> fileSet = listFilesForFolder(folder);

        for (File file : fileSet) {

            try {
                PropertiesReader ppr = new PropertiesReader(file.getCanonicalPath());
                Properties properties = ppr.readProperties();

                try {
                    Meteo element = new Meteo();
                    element.setNom(properties.getProperty("nom"));
                    element.setImage(properties.getProperty("image"));
                    element.setHandicapCombat(Double.parseDouble(properties.getProperty("handicapCombat")));
                    element.setHandicapTir(Double.parseDouble(properties.getProperty("handicapTir")));

                    list.add(element);
                    System.out.println(element.toString()); //DEBUG

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.out.println("##### Erreur lors de la création d'une meteo #####");
                    JOptionPane.showMessageDialog(null, e);
                }

            } catch (IOException ex) {
                Logger.getLogger(LoadControler.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex);
            }

        }

        return list;
    }

    public static List<Geographie> loadGeographie(String filePath) {

        List<Geographie> list = new ArrayList<>();

        File folder = new File(userPath(filePath));

        Set<File> fileSet = listFilesForFolder(folder);

        for (File file : fileSet) {

            try {
                PropertiesReader ppr = new PropertiesReader(file.getCanonicalPath());
                Properties properties = ppr.readProperties();

                try {
                    Geographie element = new Geographie();
                    element.setNom(properties.getProperty("nom"));
                    element.setImage(properties.getProperty("image"));
                    element.setIsCombatable(Boolean.parseBoolean(properties.getProperty("isCombatable")));
                    element.setHandicapJ1Tir(Double.parseDouble(properties.getProperty("handicapJ1Tir")));
                    element.setHandicapJ2Tir(Double.parseDouble(properties.getProperty("handicapJ2Tir")));

                    list.add(element);
                    System.out.println(element.toString()); //DEBUG

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.out.println("##### Erreur lors de la création d'une geographie #####");
                    JOptionPane.showMessageDialog(null, e);
                }

            } catch (IOException ex) {
                Logger.getLogger(LoadControler.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex);
            }

        }

        return list;
    }

    public static HashMap<String, Propriete> loadProprietes(String filePath) {

        File folder = new File(userPath(filePath));
        Set<File> fileSet = listFilesForFolder(folder);

        HashMap<String, Propriete> proprieteMap = new HashMap<>();

        for (File file : fileSet) {

            try {
                PropertiesReader ppr = new PropertiesReader(file.getCanonicalPath());
                Properties properties = ppr.readProperties();

                Propriete element = new Propriete();

                try {
                    String nom = new String(properties.getProperty("nom").getBytes("ISO-8859-1"), "UTF-8");
                    element.setType(properties.getProperty("type"));
                    element.setNom(nom);
                    element.setImage(properties.getProperty("image"));
                    element.setCapaciteCombat(Double.parseDouble(properties.getProperty("capaciteCombat")));
                    element.setCapaciteTir(Double.parseDouble(properties.getProperty("capaciteTir")));
                    element.setCapaciteDeplacement(Double.parseDouble(properties.getProperty("capaciteDeplacement")));
                    String description = new String(properties.getProperty("description").getBytes("ISO-8859-1"), "UTF-8");
                    element.setDescription(description);

                    proprieteMap.put(nom.toLowerCase(), element);
                    System.out.println(element.toString()); //DEBUG

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.out.println("##### Erreur lors de la création d'une propriete #####");
                    JOptionPane.showMessageDialog(null, e);
                }
            } catch (IOException ex) {
                Logger.getLogger(LoadControler.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex);
            }

        }

        return proprieteMap;

    }

    public static List<CelluleType> loadCelluleTypes(String filePath) {

        File folder = new File(userPath(filePath));
        Set<File> fileSet = listFilesForFolder(folder);

        List<CelluleType> list = new ArrayList<>();

        for (File file : fileSet) {

            try {
                PropertiesReader ppr = new PropertiesReader(file.getCanonicalPath());
                Properties properties = ppr.readProperties();

                CelluleType celluleType = new CelluleType();

                try {
                    celluleType.setType(properties.getProperty("nom"));

                    System.out.println(celluleType.toString()); //DEBUG
                    list.add(celluleType);

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.out.println("##### Erreur lors de la création de la carte #####");
                    JOptionPane.showMessageDialog(null, e);
                }
            } catch (IOException ex) {
                Logger.getLogger(LoadControler.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex);
            }

        }

        return list;
    }

    public static List<Carte> loadCartes(String filePath) {

        File folder = new File(userPath(filePath));
        Set<File> fileSet = listFilesForFolder(folder);

        List<Carte> list = new ArrayList<>();

        for (File file : fileSet) {

            try {
                PropertiesReader ppr = new PropertiesReader(file.getCanonicalPath());
                Properties properties = ppr.readProperties();

                Carte carte = new Carte();

                try {
                    carte.setNom(properties.getProperty("nom"));
                    carte.setDx(Integer.parseInt(properties.getProperty("dx")));
                    carte.setDy(Integer.parseInt(properties.getProperty("dy")));

                    Properties ppts = new Properties();
                    ppts.putAll(properties);
                    ppts.remove("nom");
                    ppts.remove("dx");
                    ppts.remove("dy");
                    carte.setProperties(ppts);

                    System.out.println(carte.toString()); //DEBUG
                    list.add(carte);

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.out.println("##### Erreur lors de la création de la carte #####");
                    JOptionPane.showMessageDialog(null, e);
                }
            } catch (IOException ex) {
                Logger.getLogger(LoadControler.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex);
            }

        }

        return list;
    }

    public static Map<String, BufferedImage> loadImages(String filePath) {

        Map<String, BufferedImage> map = new HashMap<>();

        File folder = new File(userPath(filePath));
        Set<File> fileSet = listFilesForFolder(folder);

        for (File file : fileSet) {

            BufferedImage img = null;
            try {

                img = ImageIO.read(file);
                System.out.println(file.getName());

            } catch (IOException ex) {
                Logger.getLogger(LoadControler.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex);
            }

            map.put(FilenameUtils.removeExtension(file.getName()), img);
        }

        return map;
    }

    public static Map<String, List<String>> loadNoms(String filePath) {

        Map<String, List<String>> map = new HashMap<>();

        File folder = new File(userPath(filePath));
        Set<File> fileSet = listFilesForFolder(folder);

        for (File file : fileSet) {

            try {

                CsvReader reader = new CsvReader(file.getCanonicalPath());

                reader.readHeaders();
                String[] headersTab = reader.getHeaders();

                for (String header : headersTab) {
                    map.put(header, new ArrayList<String>());
                }

                while (reader.readRecord()) {

                    for (String header : headersTab) {

                        String value = reader.get(header);

                        if (!value.isEmpty()) {
                            map.get(header).add(value);
                        }
                    }
                }
                reader.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e);
            }
        }

        // DEBUG
        for (String key : map.keySet()) {

            System.out.print(key + " : ");

            for (String value : map.get(key)) {
                System.out.print(value + " ");
            }
            System.out.println();
        }

        return map;
    }

    public static String loadChangelog(String filePath) {

        BufferedReader br = null;
        String output = "";

        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader(userPath(filePath)));

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
                output += sCurrentLine + "\n";
            }
            System.out.println("\n\n\n");

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex);
            }
        }

        return output;
    }

    private static String userPath(String filePath) {
        
        return System.getProperty("user.dir") + "/" + filePath;
    }

}
