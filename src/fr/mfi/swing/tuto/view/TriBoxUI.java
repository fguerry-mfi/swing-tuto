/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.mfi.swing.tuto.view;

import fr.mfi.swing.tuto.controler.JTriBox;
import fr.mfi.swing.tuto.model.Trilean;
import javax.swing.plaf.ComponentUI;

/**
 * This class define the API of the UI (view) for a {@link JTriBox} component.
 *
 * @author fguerry
 */
public abstract class TriBoxUI extends ComponentUI {

    /**
     * This method allow to set the current hover zone value when the mouse move
     * overthe component.
     *
     * @param value
     */
    public abstract void setHover(Trilean value);
}
