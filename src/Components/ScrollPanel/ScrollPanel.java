package Components.ScrollPanel;

import App.AppInitials;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ScrollPanel extends JScrollPane {
    private int roundRadius;
    private Color bg;
    private Color border;

    public ScrollPanel(Component component, int roundRadius, Color bg, Color border, Color thumb, Color track) {
        super(component);
        this.roundRadius = roundRadius;
        this.bg = bg;
        this.border = border;

        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        getVerticalScrollBar().setUI(new ScrollBarUI(thumb, track));
        getVerticalScrollBar().setOpaque(false);
        getVerticalScrollBar().setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));


        setOpaque(false);
        getViewport().setOpaque(false);
        setBorder(null);
        setBackground(AppInitials.TRANSPARENT_COLOR);
        getViewport().setBackground(AppInitials.TRANSPARENT_COLOR);
    }


//    @Override
//    protected void paintComponent(Graphics g) {
//        int w = getWidth(),     h = getHeight();
//        int r = roundRadius;
//        var g2 = (Graphics2D) g.create();
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//        g2.setClip(new RoundRectangle2D.Float(0, 0, w, h, r, r));
//        g2.setColor(bg);
//        g2.fillRect(0, 0, w, h);
//
//        g2.setColor(border);
//        g2.setStroke(new BasicStroke(3));
//        g2.drawRoundRect(0, 0, w - 1, h - 1, r, r);
//
//        super.paintComponent(g);
//
//        g2.dispose();
//    }


    @Override
    protected void paintBorder(Graphics g) {
//        super.paintBorder(g);
    }
}
