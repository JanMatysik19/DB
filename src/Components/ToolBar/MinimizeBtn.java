package Components.ToolBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MinimizeBtn extends JButton {
    private final Image minimizeImg = new ImageIcon("./res/miniDash.png").getImage();
    private final Image minimizeHoverImg = new ImageIcon("./res/miniDashWhite.png").getImage();

    private boolean isMouseOver = false;
    private final JFrame appFrame;


    public MinimizeBtn(JFrame appFrame) {
        this.appFrame = appFrame;

        setPreferredSize(new Dimension(30, 25));
        setMaximumSize(new Dimension(30, 25));

        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        addMouseListener(new MinimizeBtnMouseOverHandler());
    }


    private class MinimizeBtnMouseOverHandler extends MouseAdapter {
        @Override
        public void mouseExited(MouseEvent e) {
            isMouseOver = false;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            isMouseOver = true;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            appFrame.setExtendedState(JFrame.ICONIFIED);
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        Image img;
        Color color;

        if(isMouseOver) {
            img = minimizeHoverImg;
            color = new Color(94, 94, 94, (int)(255 * 0.2));
        } else {
            img = minimizeImg;
            color = new Color(0, 0, 0, 0);
        }

        var g2 = (Graphics2D) g.create();

        int btnWidth = getWidth(),                          btnHeight = getHeight();
        int imgWidth = img.getWidth(this),          imgHeight = img.getHeight(this);

        int imgX = (btnWidth - imgWidth) / 2,   imgY = (btnHeight - imgHeight) / 2;

        g2.setColor(color);
        g2.fillRect(0, 0, btnWidth, btnHeight);

        g2.drawImage(img, imgX, imgY, this);

        g2.dispose();
    }
}
