package Tabs.Products;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import java.awt.*;

import static App.App.connector;

public class FieldEditor extends AbstractCellEditor implements TableCellEditor {
    private final JTextField field = new JTextField();

    @Override
    public Object getCellEditorValue() {
        return field.getText();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        var v = String.valueOf(value);
        field.setText(v);
        return field;
    }
}
