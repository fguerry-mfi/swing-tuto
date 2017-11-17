/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.mfi.swing.tuto.view;

import fr.mfi.swing.tuto.controler.JTriBox;
import fr.mfi.swing.tuto.looknfeel.CustomLookAndFeel;
import fr.mfi.swing.tuto.model.TriBoxModelEvent;
import fr.mfi.swing.tuto.model.TriBoxModelListener;
import fr.mfi.swing.tuto.model.Trilean;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Small test class to demonstrate the use of JTriBox component.
 *
 * @author fguerry
 */
public class TestTriBox {

    public static void main(String... args) {
        CustomLookAndFeel.setup();

        final JFrame frame = new JFrame("Tri Box Demo");
        final JPanel panel = new JPanel(new BorderLayout());
        final JTriBox box = new JTriBox();
        box.addTriBoxModelListener(new TriBoxModelListener() {
            @Override
            public void valueChanged(TriBoxModelEvent e) {
                Logger.getLogger(TestTriBox.class.getName()).log(Level.INFO, "value selected : " + e.getSelectedValue());
            }

            @Override
            public void labelChanged(TriBoxModelEvent e) {
            }
        });
        panel.add(box, BorderLayout.CENTER);
        panel.add(new JButton(new AbstractAction("Set to none") {
            @Override
            public void actionPerformed(ActionEvent e) {
                box.setValue(Trilean.NONE);
            }
        }), BorderLayout.NORTH);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
