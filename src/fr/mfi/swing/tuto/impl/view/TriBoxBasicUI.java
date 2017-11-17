/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.mfi.swing.tuto.impl.view;

import fr.mfi.swing.tuto.controler.JTriBox;
import fr.mfi.swing.tuto.model.TriBoxModel;
import fr.mfi.swing.tuto.model.Trilean;
import fr.mfi.swing.tuto.view.TriBoxUI;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;

/**
 *
 * @author fguerry
 */
public class TriBoxBasicUI extends TriBoxUI {

    private static final double HEIGHT_RATIO = Math.sqrt(3);
    private static final double RADIUS_RATIO = 1 / (2 + HEIGHT_RATIO);
    private JTriBox triBox;
    private TriBoxModel model;
    private TriBoxListener listener;
    private Trilean hover;

    public static ComponentUI createUI(JComponent c) {
        Logger.getLogger(TriBoxBasicUI.class.getName()).log(Level.INFO, "TriBoxBasicUI createUI");
        return new TriBoxBasicUI();
    }

    public TriBoxBasicUI() {
        Logger.getLogger(TriBoxBasicUI.class.getName()).log(Level.INFO, "TriBoxBasicUI new instance");
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        triBox = (JTriBox) c;
        model = triBox.getModel();
        installDefaults();
        installListeners();
    }

    private void installDefaults() {
        LookAndFeel.installColorsAndFont(triBox, JTriBox.UI_CLASS_PREFIX + ".background", JTriBox.UI_CLASS_PREFIX + ".foreground", JTriBox.UI_CLASS_PREFIX + ".font");
        Color ballColor = triBox.getBallColor();
        if (ballColor == null || ballColor instanceof UIResource) {
            triBox.setBallColor(UIManager.getColor(JTriBox.UI_CLASS_PREFIX + ".ballColor"));
        }
    }

    private void installListeners() {
        listener = createListener();
        triBox.addMouseListener(listener);
        triBox.addMouseMotionListener(listener);
        triBox.getModel().addTriBoxModelListener(listener);
    }

    @Override
    public void uninstallUI(JComponent c) {
        uninstallListeners();
        uninstallDefault();
        model = null;
        triBox = null;
        super.uninstallUI(c);
    }

    private void uninstallDefault() {
    }

    private void uninstallListeners() {
        triBox.getModel().removeTriBoxModelListener(listener);
        triBox.removeMouseMotionListener(listener);
        triBox.removeMouseListener(listener);
        listener = null;

    }

    @Override
    public Dimension getMinimumSize(JComponent c) {
        return getPreferredSize(c);
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        final FontMetrics fm = c.getFontMetrics(c.getFont());
        final Rectangle2D labelNoneBounds = fm.getStringBounds(model.getLabelFor(Trilean.NONE), c.getGraphics());
        final Rectangle2D labelOnBounds = fm.getStringBounds(model.getLabelFor(Trilean.ON), c.getGraphics());
        final Rectangle2D labelOffBounds = fm.getStringBounds(model.getLabelFor(Trilean.OFF), c.getGraphics());
        final double bottomLine = labelOnBounds.getWidth() + labelOffBounds.getWidth() + 10;
        final double largestLine = Math.max(labelNoneBounds.getWidth(), bottomLine);
        return new Dimension((int) Math.max(largestLine, fm.getHeight() * 5), fm.getHeight() * 5);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        final Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        final FontMetrics fm = g2.getFontMetrics();
        final int triBoxHeight = Math.max(1, c.getHeight() - 2 * fm.getHeight());
        final int triBoxWidth = (int) (triBox.getWidth() * 0.9);
        final double ballRadius = Math.min(triBoxHeight, triBoxWidth) * RADIUS_RATIO;
        final double ballHeightGap = HEIGHT_RATIO * ballRadius;
        g2.translate(c.getWidth() * 0.5, 0);
        {
            final String labelNone = model.getLabelFor(Trilean.NONE);
            float labelNoneWidth = (float) fm.getStringBounds(labelNone, g2).getWidth();
            g2.drawString(labelNone, -labelNoneWidth * 0.5f, fm.getAscent());
        }
        final Ellipse2D.Double ball = new Ellipse2D.Double(1.5 - ballRadius, 1.5 - ballRadius, 2 * ballRadius - 3, 2 * ballRadius - 3);
        final Path2D.Double path = new Path2D.Double();
        path.moveTo(0, 0);
        path.lineTo(ballRadius, ballHeightGap);
        path.lineTo(-ballRadius, ballHeightGap);
        path.closePath();
        final Shape outline = new BasicStroke((float) ballRadius * 2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND).createStrokedShape(path);
        g2.translate(0, fm.getHeight() + ballRadius);
        g2.setColor(triBox.getBallColor());
        g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.draw(outline);
        g2.setColor(triBox.getBackground());
        g2.fill(outline);
        paintBall(g2, ball, Trilean.NONE);
        g2.translate(-ballRadius, ballHeightGap);
        paintBall(g2, ball, Trilean.ON);
        g2.translate(ballRadius * 2, 0);
        paintBall(g2, ball, Trilean.OFF);
        g2.translate(-ballRadius, ballRadius);
        g2.setColor(triBox.getForeground());
        {
            final String labelOn = model.getLabelFor(Trilean.ON);
            final double labelOnWidth = fm.getStringBounds(labelOn, g2).getWidth();
            final double lineEnd = Math.min(-5, 0.5 * labelOnWidth - ballRadius);
            float labelX = (float) (lineEnd - labelOnWidth);
            g2.drawString(labelOn, labelX, fm.getAscent());
        }
        {
            final String labelOff = model.getLabelFor(Trilean.OFF);
            double labelOffWidth = fm.getStringBounds(labelOff, g2).getWidth();
            float labelX = (float) Math.max(5, -labelOffWidth * 0.5f + ballRadius);
            g2.drawString(labelOff, labelX, fm.getAscent());
        }
        g2.dispose();
    }

    private void paintBall(Graphics2D g2, Ellipse2D ball, Trilean mode) {
        if (model.getValue() == mode) {
            g2.setColor(triBox.getBallColor());
            g2.fill(ball);
        }
        if (hover == mode) {
            g2.setColor(new Color(0x66666666, true));
            g2.fill(ball);
        }

    }

    private TriBoxListener createListener() {
        return new TriBoxListener(triBox, this);
    }

    @Override
    public void setHover(Trilean hover) {
        this.hover = hover;
        triBox.repaint();
    }

}
