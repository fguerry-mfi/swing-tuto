/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.mfi.swing.tuto.controler;

import fr.mfi.swing.tuto.impl.model.DefaultTriBoxModel;
import fr.mfi.swing.tuto.model.TriBoxModel;
import fr.mfi.swing.tuto.model.TriBoxModelListener;
import fr.mfi.swing.tuto.model.Trilean;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.UIManager;

/**
 *
 * @author fguerry
 */
public class JTriBox extends JComponent {

    public static final String UI_CLASS_PREFIX = "TriBox";
    public static final String UI_CLASS_UI = UI_CLASS_PREFIX + "UI";
    public static final String MODEL_PROPERTY = "model";
    public static final String BALL_COLOR_PROPERTY = "ballColor";

    private TriBoxModel model;
    private Color ballColor;

    /**
     * This method gives the key to the UIManager to find the UI in the
     * Look'n'Feel. We must find the same key in the Look'n'feel, associated
     * with the class name of the wanted UI class.
     *
     * @return
     */
    @Override
    public String getUIClassID() {
        Logger.getLogger(JTriBox.class.getName()).log(Level.INFO, "JTriBox inform about UIClassId");
        return UI_CLASS_UI;
    }

    public JTriBox() {
        Logger.getLogger(JTriBox.class.getName()).log(Level.INFO, "JTriBox new instance");
        setModel(new DefaultTriBoxModel());
        updateUI();
        Logger.getLogger(JTriBox.class.getName()).log(Level.INFO, "JTriBox UI is initialized");
    }

    @Override
    public void updateUI() {
        // ask UIManager to obtain the UI for this component, and set it
        setUI(UIManager.getUI(this));
    }

    public TriBoxModel getModel() {
        return model;
    }

    public void setModel(TriBoxModel newValue) {
        final TriBoxModel oldValue = this.model;
        this.model = newValue;
        firePropertyChange(MODEL_PROPERTY, oldValue, newValue);
    }

    public Trilean getValue() {
        return model.getValue();
    }

    public void setValue(Trilean newValue) {
        model.setValue(newValue);
    }

    public String getLabelFor(Trilean value) {
        return model.getLabelFor(value);
    }

    public void setLabelFor(Trilean forValue, String label) {
        model.setLabelFor(forValue, label);
    }

    public void addTriBoxModelListener(TriBoxModelListener l) {
        model.addTriBoxModelListener(l);
    }

    public void removeTriBoxModelListener(TriBoxModelListener l) {
        model.removeTriBoxModelListener(l);
    }

    public Color getBallColor() {
        return ballColor;
    }

    public void setBallColor(Color newValue) {
        final Color oldValue = this.ballColor;
        this.ballColor = newValue;
        firePropertyChange(BALL_COLOR_PROPERTY, oldValue, newValue);
    }

}
