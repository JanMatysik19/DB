package Tabs;

import App.AppInitials;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

public class TabPanelUI extends BasicTabbedPaneUI {
    private final JTabbedPane pane;

    public TabPanelUI(JTabbedPane pane) {
        this.pane = pane;
    }

    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        var g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if(isSelected) {
            g2.setColor(AppInitials.FIRST_COLOR);
            g2.fillRect(x, y, w, h);
        } else if(tabIndex != 0) {
            g2.setColor(AppInitials.FOURTH_COLOR);
            g2.setStroke(new BasicStroke(1));
            g2.drawLine(x, y, x, y + h);
        }

        g2.setColor(AppInitials.FOURTH_COLOR);
        g2.setStroke(new BasicStroke(1));
        g2.drawLine(x + w - 1, y, x + w - 1, y + h);

        g2.dispose();
    }

    @Override
    protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) { }

    @Override
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) { }

    @Override
    protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
        return 30;
    }

    @Override
    protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
        return 150;
    }

    @Override
    protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
        var g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if(isSelected) g2.setColor(Color.WHITE);
        else g2.setColor(AppInitials.FIFTH_COLOR);

        g2.setFont(AppInitials.MONTSERRAT_FONT(AppInitials.FONT_TYPE.PLAIN, 12f));
        var fm = g2.getFontMetrics();
        var ascent = fm.getAscent();
        var descent = fm.getDescent();
        int x = textRect.x + (textRect.width - fm.stringWidth(title)) / 2;
        int y = (textRect.y + (textRect.height - (ascent + descent)) / 2 + ascent);
        g2.drawString(title, x, y);

        g2.dispose();
    }

    @Override
    protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) { }

    @Override
    protected Insets getContentBorderInsets(int tabPlacement) {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    protected Insets getTabInsets(int tabPlacement, int tabIndex) {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    protected Insets getTabAreaInsets(int tabPlacement) {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
        var g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = pane.getWidth(),    h = 30;

        g2.setColor(AppInitials.FOURTH_COLOR);
        g2.setStroke(new BasicStroke(1));
        g2.drawLine(0, h - 1, w, h - 1);

        g2.dispose();
        super.paintTabArea(g, tabPlacement, selectedIndex);
    }
}
