/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.mfi.swing.tuto.model;

/**
 * A model to express a user selected {@link Trilean} value.
 *
 * @author fguerry
 */
public interface TriBoxModel {

    /**
     * Return the current selected value.
     *
     * @return
     */
    Trilean getValue();

    /**
     * Sets the current selected value to the given value.
     *
     * @param newValue
     */
    void setValue(Trilean newValue);

    /**
     * Return a label that represent the display text of the given value.
     *
     * @param value
     * @return
     */
    String getLabelFor(Trilean value);

    /**
     * Sets the label for the given value, to the given String.
     *
     * @param forValue
     * @param label
     */
    void setLabelFor(Trilean forValue, String label);

    /**
     * Register the given listener to be informed about changes in this model.
     *
     * @param l
     */
    void addTriBoxModelListener(TriBoxModelListener l);

    /**
     * Unregister the given listener to stop being informed about changes in
     * this model.
     *
     * @param l
     */
    void removeTriBoxModelListener(TriBoxModelListener l);
}
