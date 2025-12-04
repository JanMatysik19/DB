package Tabs.Products;

import JDBC.Connector;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static App.App.connector;

public class ProductsTableModel extends AbstractTableModel {
    private final List<BookModel> books;
    private final String[] COLUMN_NAMES = Connector.TABLE_COLUMNS;

    public ProductsTableModel(BookModel[] books) {
        this.books = new ArrayList<>(Arrays.asList(books));
    }
    public ProductsTableModel(List<BookModel> books) {
        this.books = books;
    }
    public ProductsTableModel() {
        this.books = connector.getBooks();
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1 || columnIndex == 2 || columnIndex == 3;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            var b = books.get(rowIndex);
            switch (columnIndex) {
                case 0 -> b.setId((int) aValue);
                case 1 -> b.setTitle((String) aValue);
                case 2 -> b.setAuthor((String) aValue);
                case 3 -> b.setYearOfIssue(Integer.parseInt((String) aValue));
            }
            connector.updateBook(b);
            set(connector.getBooks());
        } catch (Exception _) { }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            var b = books.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> b.getId();
                case 1 -> b.getTitle();
                case 2 -> b.getAuthor();
                case 3 -> b.getYearOfIssue();
                default -> new JLabel("-|-");
            };
        } catch (Exception _) { return new JLabel("-|-"); }
    }


    public BookModel remove(int index) {
        if(books.size() <= index) return null;

        var b = books.remove(index);
        fireTableRowsDeleted(index, index);
        return b;
    }

    public void set(List<BookModel> books) {
        if(books == null) return;

        this.books.clear();
        this.books.addAll(books);
        fireTableRowsInserted(0, books.size() - 1);
    }
}
