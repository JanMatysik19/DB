package Components.ToolBar;

import App.AppInitials;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToolBar extends JPanel {
    private final JFrame appFrame;
    private final Point clickPoint = new Point();
    private final CloseBtn closeBtn;


    public ToolBar(JFrame appFrame) {
        this.appFrame = appFrame;

        setOpaque(false);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(-1, 25));

        addMouseListener(new BarClickHandler());
        addMouseMotionListener(new BarMouseMoveHandler());

        var westContent = new JPanel();
        westContent.setLayout(new BoxLayout(westContent, BoxLayout.X_AXIS));
        westContent.setOpaque(false);

        var appTitleLbl = new JLabel(AppInitials.APP_TITLE);
        appTitleLbl.setFont(AppInitials.MONTSERRAT_FONT(AppInitials.FONT_TYPE.PLAIN, 12f));
        appTitleLbl.setForeground(Color.WHITE);
        appTitleLbl.setVerticalAlignment(JLabel.CENTER);
        appTitleLbl.setBorder(BorderFactory.createEmptyBorder(0, 7, 0, 0));
        westContent.add(appTitleLbl);

        add(westContent, BorderLayout.WEST);

        var eastContent = new JPanel();
        eastContent.setLayout(new BoxLayout(eastContent, BoxLayout.X_AXIS));
        eastContent.setOpaque(false);

        var minimizeBtn = new MinimizeBtn(appFrame);
        eastContent.add(minimizeBtn);

        closeBtn = new CloseBtn();
        eastContent.add(closeBtn);

        add(eastContent, BorderLayout.EAST);
    }


    private class BarClickHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            clickPoint.setLocation(e.getPoint());
        }
    }

    private class BarMouseMoveHandler extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent e) {
            var currScreen = e.getLocationOnScreen();

            appFrame.setLocation((int) currScreen.getX() - clickPoint.x, (int) currScreen.getY() - clickPoint.y);
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        var g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(AppInitials.SECOND_COLOR);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.dispose();
    }
}
