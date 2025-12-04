package Tabs.Products;

import App.AppInitials;
import Components.ScrollPanel.ScrollPanel;

import javax.swing.*;
import java.awt.*;

public class ProductsTab extends JPanel {
    private final ProductsTableModel model;
    private final ControlPanel controlPanel;
    public static final String TITLE = "Przegląd Książek";

    public ProductsTab() {
        setLayout(new BorderLayout());
        setBackground(AppInitials.FIRST_COLOR);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        model = new ProductsTableModel();
        var table = new ProductsTable(model);

        controlPanel = new ControlPanel(model, table);

        add(controlPanel, BorderLayout.NORTH);
        var scroll = new ScrollPanel(table, 25, AppInitials.TRANSPARENT_COLOR, AppInitials.TRANSPARENT_COLOR, AppInitials.FOURTH_COLOR, AppInitials.FIFTH_COLOR);
        add(scroll, BorderLayout.CENTER);
    }

    public ProductsTableModel getModel() {
        return model;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }
}
