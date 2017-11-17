/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.mfi.swing.tuto.impl.model;

import fr.mfi.swing.tuto.model.TriBoxModel;
import fr.mfi.swing.tuto.model.TriBoxModelEvent;
import fr.mfi.swing.tuto.model.TriBoxModelListener;
import fr.mfi.swing.tuto.model.Trilean;
import java.util.Objects;
import javax.swing.event.EventListenerList;

/**
 * Abstract base for implement a {@link TriBoxModel}, it supports all event
 * handling code.
 *
 * @author fguerry
 */
public abstract class AbstractTriBoxModel implements TriBoxModel {

    private final EventListenerList listeners;

    public AbstractTriBoxModel() {
        this.listeners = new EventListenerList();
    }

    @Override
    public void addTriBoxModelListener(TriBoxModelListener l) {
        listeners.add(TriBoxModelListener.class, l);
    }

    @Override
    public void removeTriBoxModelListener(TriBoxModelListener l) {
        listeners.add(TriBoxModelListener.class, l);
    }

    public void fireValueChanged(Trilean oldValue, Trilean newValue) {
        if (!Objects.equals(oldValue, newValue)) {
            TriBoxModelEvent e = null;
            final TriBoxModelListener[] triBoxListeners = listeners.getListeners(TriBoxModelListener.class);
            for (TriBoxModelListener listener : triBoxListeners) {
                if (e == null) {
                    e = new TriBoxModelEvent(this, oldValue, newValue);
                }
                listener.valueChanged(e);
            }
        }
    }

    public void fireLabelChanged(Trilean forValue, String newLabel) {
        TriBoxModelEvent e = null;
        final TriBoxModelListener[] triBoxListeners = listeners.getListeners(TriBoxModelListener.class);
        for (TriBoxModelListener listener : triBoxListeners) {
            if (e == null) {
                e = new TriBoxModelEvent(this, forValue, newLabel);
            }
            listener.labelChanged(e);
        }
    }

}
