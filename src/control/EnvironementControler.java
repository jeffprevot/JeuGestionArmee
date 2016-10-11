/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import environement.geographie.Geographie;
import environement.Handicap;
import environement.meteo.Meteo;
import unitesDeCombat.hero.Heros;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author jeff
 */
public final class EnvironementControler {

    private final List<Meteo> meteoList;
    private final List<Geographie> geographieList;
    private Meteo meteo;
    private Geographie geographie;

    private final Random random;

    public EnvironementControler(List<Meteo> meteoList, List<Geographie> geographieList) {
        this.random = new Random();

        this.meteoList = meteoList;
        this.geographieList = geographieList;
    }

    private Meteo generateMeteo() {
        int size = meteoList.size();
        int item = random.nextInt(size);
        return meteoList.get(item);
    }

    private Geographie generateGeographie() {
        int size = geographieList.size();
        int item = random.nextInt(size);
        return geographieList.get(item);
    }

    public Map<Heros, Handicap> generateHandicaps(List<Heros> herosList) {

        this.meteo = generateMeteo();
        this.geographie = generateGeographie();

        Map<Heros, Handicap> map = new HashMap<>();
        List<Heros> list = herosList;
        Collections.shuffle(list);

        for (int i = 0; i < list.size(); i++) {

            Heros heros = list.get(i);
            double handiGeo = 0;
            if (i % 2 == 0) {

                handiGeo =  geographie.getHandicapJ2Tir();

            } else {

                handiGeo = geographie.getHandicapJ1Tir();
            }

            // multiplication points handicap par pondérations induites par specificités heros
            Handicap handicap = new Handicap(
                    geographie.isIsCombatable(),
                    handiGeo * heros.getRatioGeographie(),
                    meteo.getHandicapCombat() * heros.getRatioMeteo(),
                    meteo.getHandicapTir() * heros.getRatioMeteo());
            map.put(heros, handicap);
        }
        return map;
    }

    public String getMeteo() {
        return this.meteo.getNom();
    }

    public String getGeographie() {
        return this.geographie.getNom();
    }

}
