/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.mfi.swing.tuto.model;

import java.util.EventObject;

/**
 * Event to hold information about changes in a {@link TriBoxModel}. Depending
 * on the type of event, an instance of this class may hold different sets of
 * informations :
 * <ul>
 * <li>for a value change, the old and selected value are filled</li>
 * <li>for a label change, the value for which the label is changed and the new
 * label are filled</li>
 * <ul>
 *
 * @author fguerry
 */
public class TriBoxModelEvent extends EventObject {

    private Trilean oldValue;
    private Trilean selectedValue;
    private String labelForValue;

    public TriBoxModelEvent(Object source, Trilean oldValue, Trilean newValue) {
        super(source);
        this.oldValue = oldValue;
        this.selectedValue = newValue;
    }

    public TriBoxModelEvent(Object source, Trilean forValue, String newLabel) {
        super(source);
        this.selectedValue = forValue;
        this.labelForValue = newLabel;
    }

    public Trilean getOldValue() {
        return oldValue;
    }

    public Trilean getSelectedValue() {
        return selectedValue;
    }

    public String getLabelForValue() {
        return labelForValue;
    }

}
