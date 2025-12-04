package App;

import Components.ToolBar.ToolBar;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Body extends JPanel {

    public Body(JFrame appFrame) {
        setLayout(new BorderLayout());
        setOpaque(false);
        getInsets();

        var lbl = new JLabel("Jan Matysik 4TP 2025/2026");
        lbl.setFont(AppInitials.ROBOTO_FONT(AppInitials.FONT_TYPE.BOLD, 11f));
        lbl.setForeground(Color.WHITE);
        lbl.setOpaque(false);
        lbl.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
        lbl.setHorizontalAlignment(JLabel.CENTER);

        add(new ToolBar(appFrame), BorderLayout.NORTH);
        add(new Main(), BorderLayout.CENTER);
        add(lbl, BorderLayout.SOUTH);
    }


    @Override
    protected void paintComponent(Graphics g) {
        int w = getWidth(),     h = getHeight();
        var g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setClip(new RoundRectangle2D.Float(0, 0, w, h, AppInitials.WINDOW_RADIUS, AppInitials.WINDOW_RADIUS));

        g2.setPaint(AppInitials.THIRD_COLOR);
        g2.fillRect(0, 0, w, h);

        g2.setColor(AppInitials.FIFTH_COLOR);
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(0, 0, w - 1, h - 1, AppInitials.WINDOW_RADIUS, AppInitials.WINDOW_RADIUS);

        g2.dispose();
    }


    @Override
    protected void paintChildren(Graphics g) {
        int w = getWidth(),     h = getHeight();
        var g2 = (Graphics2D) g.create();

        g2.setClip(new RoundRectangle2D.Float(1, 1, w - 2, h - 2, AppInitials.WINDOW_RADIUS, AppInitials.WINDOW_RADIUS));
        super.paintChildren(g2);

        g2.dispose();
    }
}
