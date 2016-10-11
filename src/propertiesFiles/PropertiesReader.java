/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertiesFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author jeff
 */
public class PropertiesReader {

    private final String filePath;

    public PropertiesReader(String filePath) {
        this.filePath = filePath;
    }

    public Properties readProperties() {

        Properties properties = null;
        File file = new File(filePath);
        FileInputStream propertiesFile = null;
        try {
            propertiesFile = new FileInputStream(file.getAbsolutePath());
            properties = new Properties();
            properties.load(propertiesFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e);
        } finally {
            if (propertiesFile != null) {
                try {
                    propertiesFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }

        return properties;
    }

    public String readProperty(String propertyName) {

        Properties properties = readProperties();

        if (properties != null) {
            String propertyValue = (String) properties.get(propertyName);
            return propertyValue;

        }
        return null;
    }
}
