/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.listeners;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author jeff
 */
public class SliderChangeListener implements ChangeListener {

    private final JLabel label;
    private Double coef;
    private int initValue;
    private int precValue;

    public SliderChangeListener(JLabel label) {
        this.label = label;
        this.precValue = initValue;
    }
    
    public void setInitValue(int initValue) {
        this.initValue = initValue;
    }
    
    public void setCoef(Double coef) {
        this.coef = coef;
    }

    @Override
    public void stateChanged(ChangeEvent e) {

        JSlider source = (JSlider) e.getSource();

        if (!source.getValueIsAdjusting()) {

            int newValue = source.getValue();
            Double XP = Double.parseDouble(label.getText());
            Double cout = 0d;

            if (newValue >= initValue) {

                if (newValue - precValue >= 0) {

                    for (int i = precValue; i < newValue; i++) {
                        cout += Math.pow(2, i);
                    }

                } else {

                    for (int i = precValue - 1; i >= newValue; i--) {
                        cout -= Math.pow(2, i);
                    }
                }

                Double newXP = XP - (cout*coef);

                this.label.setText(newXP.toString());
                this.precValue = newValue;

            } else {

                source.setValue(initValue);
                JOptionPane.showMessageDialog(null,
                        "Tu te rends compte que si tu baisses ses caractéristiques, ton héros pourrais devenir aussi inutile que toi ?\n"
                        + "Allez, sois gentil et arrête de faire n'importe quoi.");
            }
        }
    }

}
