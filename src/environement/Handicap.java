/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package environement;

import utils.Utils;

/**
 *
 * @author jeff
 */
public class Handicap {

    private final Double handicapCombat;
    private final Double handicapTir;
    private final Double handicapTotal;
    private final Double geographieCombat;
    private final Double geographieTir;
    private final Double geographieTotal;

    /**
     * Handicap build with Default Values
     */
    public Handicap() {
        this.handicapCombat = 1d;
        this.handicapTir = 1d;
        this.handicapTotal = 1d;
        this.geographieTir = 1d;
        this.geographieCombat = 1d;
        this.geographieTotal = 1d;
    }
    
    
    public Handicap(boolean isCombatable, Double geographieTir, Double meteoCombat, Double meteoTir) {
        
        this.handicapCombat = isCombatable ? meteoCombat : 0d;
        this.handicapTir = geographieTir * meteoTir;
        this.handicapTotal = handicapCombat + handicapTir;
        this.geographieTir = geographieTir;
        this.geographieCombat = isCombatable ? 1d : 0d;
        this.geographieTotal = geographieTir + geographieCombat;
        
    }

    public Double getHandicapCombat() {
        return handicapCombat;
    }

    public Double getHandicapTir() {
        return handicapTir;
    }

    public Double getHandicapTotal() {
        return handicapTotal;
    }

    public Double getGeographieCombat() {
        return geographieCombat;
    }

    public Double getGeographieTir() {
        return geographieTir;
    }

    public Double getGeographieTotal() {
        return geographieTotal;
    }

}
