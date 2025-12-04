package Tabs.Products;

import App.AppInitials;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

import static App.App.connector;

public class ProductsTable extends JTable {
    public ProductsTable(ProductsTableModel model) {
        super(model);

        setOpaque(false);
        setBackground(AppInitials.TRANSPARENT_COLOR);
        setBorder(null);
        setForeground(Color.WHITE);
        for(int i = 0; i < getColumnCount(); i++) getColumnModel().getColumn(i).setCellEditor(new FieldEditor());

        setShowGrid(false);
        setRowHeight(25);

        setSelectionBackground(AppInitials.EIGHT_COLOR);
        setSelectionForeground(Color.WHITE);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setColumnSelectionAllowed(false);

        setFocusable(false);


        setFont(AppInitials.ROBOTO_FONT(AppInitials.FONT_TYPE.PLAIN, 12f));
        setForeground(Color.WHITE);


        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                var lbl = new JLabel(String.valueOf(value));

                lbl.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                lbl.setBackground(AppInitials.TENTH_COLOR);
                lbl.setFont(AppInitials.MONTSERRAT_FONT(AppInitials.FONT_TYPE.PLAIN, 12f));
                lbl.setForeground(Color.WHITE);
                lbl.setPreferredSize(new Dimension(-1, 20));

                lbl.setOpaque(true);
                return lbl;
            }
        });
    }


    @Override
    protected void paintBorder(Graphics g) { }
}
