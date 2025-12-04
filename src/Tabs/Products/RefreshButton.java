package Tabs.Products;

import App.AppInitials;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RefreshButton extends JButton {
    private boolean isHover = false;

    public RefreshButton() {
        setText("Odświerz");

        setBorder(null);
        setBorderPainted(false);
        setFocusPainted(false);

        var size = new Dimension(100, 25);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                isHover = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                isHover = false;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }




    @Override
    protected void paintComponent(Graphics g) {
        var g2 = (Graphics2D) g.create();
        int r = 10;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(isHover ? AppInitials.FIFTH_COLOR : Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, r, r);

        g2.setColor(isHover ? Color.WHITE : AppInitials.FIFTH_COLOR);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, r, r);

        g2.setColor(isHover ? Color.WHITE : Color.BLACK);
        g2.setFont(AppInitials.MONTSERRAT_FONT(AppInitials.FONT_TYPE.PLAIN, 12f));
        var fm = g2.getFontMetrics();
        var ascent = fm.getAscent();
        var descent = fm.getDescent();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = ((getHeight() - (ascent + descent)) / 2 + ascent);
        g2.drawString(getText(), x, y);

        g2.dispose();
    }
}
