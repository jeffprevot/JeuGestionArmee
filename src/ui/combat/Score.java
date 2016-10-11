/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.combat;

/**
 *
 * @author jeff
 */
public class Score {

    private final Double score;
    private Integer perteSoldats;
    private Double gainsCredit;
    private Double gainsXP;

    public Score(Double score) {
        this.score = score;
    }

    public void setPerteSoldats(Integer perteSoldats) {
        this.perteSoldats = perteSoldats;
    }

    public void setGainsCredit(Double gainsCredit) {
        this.gainsCredit = gainsCredit;
    }

    public void setGainsXP(Double gainsXP) {
        this.gainsXP = gainsXP;
    }

    public Double getScore() {
        return score;
    }

    public Integer getPerteSoldats() {
        return perteSoldats;
    }

    public Double getGainsCredit() {
        return gainsCredit;
    }

    public Double getGainsXP() {
        return gainsXP;
    }

}
