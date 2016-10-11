/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.EventObject;
import javax.swing.DefaultCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import ui.gestionNiveau.UIGestionNiveauxSoldats;

public class SpinnerEditor extends DefaultCellEditor {

    private JSpinner spinner;
    private final JSpinner.DefaultEditor editor;
    private JTextField textField;
    private boolean valueSet;
    
    private final UIGestionNiveauxSoldats uIGestionNiveau;
    
//    private int oldValue;

    // Initializes the spinner.
    public SpinnerEditor(final UIGestionNiveauxSoldats uIGestionNiveau) {
        super(new JTextField());
                
        this.uIGestionNiveau = uIGestionNiveau;
        
        // default value, lower bound, upper bound, increment by
        SpinnerModel sm = new SpinnerNumberModel(1, 1, 10, 1);
        spinner = new JSpinner(sm);
        
        editor = ((JSpinner.DefaultEditor) spinner.getEditor());
        textField = editor.getTextField();
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
//                System.err.println("Got focus");
                
                ((SpinnerNumberModel)spinner.getModel()).setMaximum(maxValue());
                
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (valueSet) {
                            textField.setCaretPosition(1);
                        }
                    }
                });
            }

            @Override
            public void focusLost(FocusEvent fe) {
                ((SpinnerNumberModel)spinner.getModel()).setMaximum(maxValue());
                uIGestionNiveau.refesh();
            }
        });
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                stopCellEditing();
            }
        });
        
        spinner.addChangeListener(new ChangeListener() {

                @Override
                public void stateChanged(ChangeEvent e) {
                    System.out.print(((JSpinner)e.getSource()).getValue() + " ");
//                    uIGestionNiveau.refesh();
                }
            });
    }
    
    private int maxValue() {
        return (int) (Math.log(uIGestionNiveau.getDisplayedXPValue()) / Math.log(2));
    }

    // Prepares the spinner component and returns it.
    @Override
    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column
    ) {
        if (!valueSet) {
            spinner.setValue(value);
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                textField.requestFocus();
            }
        });
        return spinner;
    }

    @Override
    public boolean isCellEditable(EventObject eo) {
        valueSet = false;
        return true;
    }

    // Returns the spinners current value.
    @Override
    public Object getCellEditorValue() {
        return spinner.getValue();
    }

    @Override
    public boolean stopCellEditing() {
        
        try {
            editor.commitEdit();
            spinner.commitEdit();
        } catch (java.text.ParseException e) {
//            JOptionPane.showMessageDialog(null,
//                    "Invalid value, discarding.");
        }
        return super.stopCellEditing();
    }
}
