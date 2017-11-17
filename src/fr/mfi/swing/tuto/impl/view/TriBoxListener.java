/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.mfi.swing.tuto.impl.view;

import fr.mfi.swing.tuto.controler.JTriBox;
import fr.mfi.swing.tuto.model.TriBoxModelEvent;
import fr.mfi.swing.tuto.model.TriBoxModelListener;
import fr.mfi.swing.tuto.model.Trilean;
import fr.mfi.swing.tuto.view.TriBoxUI;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author fguerry
 */
class TriBoxListener extends MouseAdapter implements TriBoxModelListener {

    private JTriBox triBox;
    private TriBoxUI ui;

    public TriBoxListener(JTriBox triBox, TriBoxUI ui) {
        this.triBox = triBox;
        this.ui = ui;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 1) {
            final Trilean zone = getZone(e.getPoint());
            triBox.getModel().setValue(zone);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        final Trilean zone = getZone(e.getPoint());
        ui.setHover(zone);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ui.setHover(null);
    }

    private Trilean getZone(Point p) {
        if (p.getY() < triBox.getHeight() / 2) {
            return Trilean.NONE;
        } else if (p.getX() < triBox.getWidth() / 2) {
            return Trilean.ON;
        } else {
            return Trilean.OFF;
        }
    }

    @Override
    public void valueChanged(TriBoxModelEvent e) {
        triBox.repaint();
    }

    @Override
    public void labelChanged(TriBoxModelEvent e) {
        triBox.revalidate();
        triBox.repaint();
    }

}
