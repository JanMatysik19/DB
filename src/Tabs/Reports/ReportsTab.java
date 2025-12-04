package Tabs.Reports;

import App.AppInitials;

import javax.swing.*;
import java.awt.*;

public class ReportsTab extends JPanel {
    public static final String TITLE = "Raporty";

    public ReportsTab() {
        setLayout(new BorderLayout());
        setBackground(AppInitials.FIRST_COLOR);


        var lbl = new JLabel("Miejsce na przyszłe wykresy/statystyki");
        lbl.setFont(AppInitials.MONTSERRAT_FONT(AppInitials.FONT_TYPE.PLAIN, 15f));
        lbl.setForeground(Color.WHITE);
        lbl.setHorizontalAlignment(JLabel.CENTER);
        add(lbl, BorderLayout.CENTER);
    }
}
