/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.mfi.swing.tuto.looknfeel;

import fr.mfi.swing.tuto.controler.JTriBox;
import fr.mfi.swing.tuto.impl.view.TriBoxBasicUI;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author fguerry
 */
public class CustomLookAndFeel extends NimbusLookAndFeel {

    @Override
    public UIDefaults getDefaults() {
        final UIDefaults defaults = super.getDefaults();

        defaults.put(JTriBox.UI_CLASS_UI, TriBoxBasicUI.class.getName());
        defaults.put(JTriBox.UI_CLASS_PREFIX + ".background", new ColorUIResource(0xFFFFFF));
        defaults.put(JTriBox.UI_CLASS_PREFIX + ".foreground", new ColorUIResource(0x666666));
        defaults.put(JTriBox.UI_CLASS_PREFIX + ".ballColor", new ColorUIResource(0xFF6666));
        defaults.put(JTriBox.UI_CLASS_PREFIX + ".font", new FontUIResource("dialog", Font.ITALIC, 16));

        return defaults;
    }

    public static void setup() {
        try {
            UIManager.setLookAndFeel(new CustomLookAndFeel());
        } catch (Exception e) {
            Logger.getLogger(CustomLookAndFeel.class.getName()).log(Level.WARNING, "unable to setup LnF", e);
        }
    }
}
