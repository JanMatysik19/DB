package Components.ToolBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CloseBtn extends JButton {
    private final Image closeImg = new ImageIcon("./res/exitCross.png").getImage();
    private final Image closeHoverImg = new ImageIcon("./res/exitCrossWhite.png").getImage();

    private boolean isMouseOver = false;


    public CloseBtn() {
        setPreferredSize(new Dimension(30, 25));
        setMaximumSize(new Dimension(30, 25));

        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        addMouseListener(new CloseBtnMouseOverHandler());
    }



    private class CloseBtnMouseOverHandler extends MouseAdapter {
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
            System.exit(0);
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        Image img;
        Color color;

        if(isMouseOver) {
            img = closeHoverImg;
            color = Color.RED;
        } else {
            img = closeImg;
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
