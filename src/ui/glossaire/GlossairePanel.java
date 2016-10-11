package ui.glossaire;

import unitesDeCombat.armee.Propriete;
import environement.geographie.Geographie;
import environement.meteo.Meteo;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author jeff
 */
public class GlossairePanel extends JTabbedPane {

    private final Map<String, List<Object>> propMap;

    /**
     * Creates new form GlossairePanel
     *
     * @param propMap
     */
    public GlossairePanel(Map<String, List<Object>> propMap) {

        this.propMap = propMap;

        generateGlossaire();
    }

    private void generateGlossaire() {

        List<Object> list;
        List<Component> panelList;
        MyPanel panel = null;

        list = propMap.get("race");
        panelList = new ArrayList<>();
        for (Object ppt : list) {
            ProprietePanel pptPanel = new ProprietePanel((Propriete) ppt);
            panelList.add(pptPanel);
        }
        panel = new MyPanel(panelList);
        this.addTab("Races", panel);

        list = propMap.get("classe");
        panelList = new ArrayList<>();
        for (Object ppt : list) {
            ProprietePanel pptPanel = new ProprietePanel((Propriete) ppt);
            panelList.add(pptPanel);
        }
        panel = new MyPanel(panelList);
        this.addTab("Classes", panel);

        list = propMap.get("meteo");
        panelList = new ArrayList<>();
        for (Object ppt : list) {
            MeteoPanel pptPanel = new MeteoPanel((Meteo) ppt);
            panelList.add(pptPanel);
        }
        panel = new MyPanel(panelList);
        this.addTab("Météos", panel);

        list = propMap.get("geographie");
        panelList = new ArrayList<>();
        for (Object ppt : list) {
            GeographiePanel pptPanel = new GeographiePanel((Geographie) ppt);
            panelList.add(pptPanel);
        }
        panel = new MyPanel(panelList);
        this.addTab("Terrains", panel);

    }

    private class MyPanel extends JPanel {

        public MyPanel(List<Component> list) {
            super();
            this.setLayout(new GridLayout(2, 2));
            add(list);
        }

        private void add(List<Component> list) {

            for (Component component : list) {
                this.add(component);
            }

        }
    }
}
