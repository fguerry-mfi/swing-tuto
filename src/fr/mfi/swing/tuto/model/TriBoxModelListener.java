/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.mfi.swing.tuto.model;

import java.util.EventListener;

/**
 * Listenening API to be informed about modifications in a {@link TriBoxModel}.
 *
 * @author fguerry
 */
public interface TriBoxModelListener extends EventListener {

    /**
     * *
     * Inform about a change of selected value in the listened model. The given
     * event must contain the old and current selected values.
     *
     * @param e
     */
    void valueChanged(TriBoxModelEvent e);

    /**
     * Inform about a change of label in the listened model. The given event
     * must contain the value for which label is changed, and the new label.
     *
     * @param e
     */
    void labelChanged(TriBoxModelEvent e);
}
