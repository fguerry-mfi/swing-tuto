/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.mfi.swing.tuto.impl.model;

import fr.mfi.swing.tuto.model.Trilean;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Default model for a TriBox.
 *
 * @author fguerry
 */
public class DefaultTriBoxModel extends AbstractTriBoxModel {

    private Trilean value;
    private Map<Trilean, String> labels;

    public DefaultTriBoxModel(String onLabel, String offLabel, String noneLabel) {
        Logger.getLogger(DefaultTriBoxModel.class.getName()).log(Level.INFO, "DefaultTriBoxModel new instance");
        value = Trilean.OFF;
        labels = new EnumMap<>(Trilean.class);
        labels.put(Trilean.ON, onLabel);
        labels.put(Trilean.OFF, offLabel);
        labels.put(Trilean.NONE, noneLabel);
    }

    public DefaultTriBoxModel() {
        this("On", "Off", "None");
    }

    @Override
    public Trilean getValue() {
        return value;
    }

    @Override
    public void setValue(Trilean newValue) {
        final Trilean oldValue = this.value;
        this.value = newValue;
        fireValueChanged(oldValue, newValue);
    }

    @Override
    public String getLabelFor(Trilean value) {
        return labels.get(value);
    }

    @Override
    public void setLabelFor(Trilean forValue, String label) {
        labels.put(forValue, label);
        fireLabelChanged(forValue, label);
    }

}
