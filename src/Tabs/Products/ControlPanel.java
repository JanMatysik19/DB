package Tabs.Products;

import App.AppInitials;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static App.App.connector;

public class ControlPanel extends JPanel {
    private ProductsTableModel model;
    private JTable table;

    private ControlTextField titleTxf;
    private ControlTextField authorTxf;
    private ControlTextField yearOfIssueTxf;

    public ControlPanel(ProductsTableModel model, JTable table) {
        this.model = model;
        this.table = table;

        setOpaque(false);

        var titleLbl = new ControlLabel("Tytuł");
        titleTxf = new ControlTextField(150);

        var authorLbl = new ControlLabel("Autor");
        authorTxf = new ControlTextField(100);

        var yearOfIssueLbl = new ControlLabel("Rok wydania");
        yearOfIssueTxf = new ControlTextField(80);

        var addBtn = new AddButton();
        addBtn.addActionListener(this::handleAddBook_click);

        var refreshBtn = new RefreshButton();
        refreshBtn.addActionListener((e) -> model.set(connector.getBooks()));

        var deleteBtn = new DeleteButton();
        deleteBtn.addActionListener(this::handleDeleteBook_click);

        var content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new GridBagLayout());
        var c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;

        c.insets = new Insets(0, 0, 10, 15);
        c.gridx = 0;
        c.gridy = 0;
        content.add(titleLbl, c);
        c.gridx = 1;
        c.gridy = 0;
        content.add(authorLbl, c);
        c.gridx = 2;
        c.gridy = 0;
        content.add(yearOfIssueLbl, c);
        c.insets = new Insets(0, 0, 15, 15);
        c.gridx = 0;
        c.gridy = 1;
        content.add(titleTxf, c);
        c.gridx = 1;
        c.gridy = 1;
        content.add(authorTxf, c);
        c.gridx = 2;
        c.gridy = 1;
        content.add(yearOfIssueTxf, c);
        c.gridx = 3;
        c.gridy = 1;
        content.add(addBtn, c);

        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(0, 15, 15, 0);
        c.weightx = 1;
        c.gridx = 4;
        c.gridy = 1;
        content.add(refreshBtn, c);
        c.weightx = 0;
        c.gridx = 5;
        c.gridy = 1;
        content.add(deleteBtn, c);


        setLayout(new BorderLayout());
        add(content, BorderLayout.CENTER);
    }



    private static class ControlLabel extends JLabel {
        public ControlLabel(String text) {
            setText(text);
            setFont(AppInitials.ROBOTO_FONT(AppInitials.FONT_TYPE.PLAIN, 12f));
            setForeground(Color.WHITE);
        }
    }


    private static class ControlTextField extends JTextField {
        public ControlTextField(int width) {
            setOpaque(false);
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(AppInitials.FIFTH_COLOR, 1, true),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));

            var size = new Dimension(width, 30);
            setPreferredSize(size);

            setForeground(Color.WHITE);
        }
    }



    private void handleAddBook_click(ActionEvent e) {
        StringBuilder errors = new StringBuilder();

        String title = titleTxf.getText().trim();
        String author = authorTxf.getText().trim();
        int yearOfIssue = -1;

        if(title.isEmpty()) errors.append("- Tytuł musi zawierać miniumum 1 znak.\n");

        if(author.isEmpty()) errors.append("- Autor musi zawierać miniumum 1 znak.\n");

        try {
            yearOfIssue = Integer.parseInt(yearOfIssueTxf.getText());
        } catch (Exception _) {
            errors.append("- Rok wydania musi być liczbą całkowitą (np. 2025).\n");
        }

        if(!errors.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Popełniono następujące błędy:\n" + errors, "Dodawanie nowej książki", JOptionPane.ERROR_MESSAGE);
            return;
        }

        var b = new BookModel(0, title, author, yearOfIssue);
        connector.insertBook(b); // adding the book to the db;
        titleTxf.setText("");
        authorTxf.setText("");
        yearOfIssueTxf.setText("");
        JOptionPane.showMessageDialog(null, "Pomyślnie dodany nową książkę: " + title, "Dodawanie nowej książki", JOptionPane.INFORMATION_MESSAGE);
        model.set(connector.getBooks());
    }


    private void handleDeleteBook_click(ActionEvent e) {
        int index = table.getSelectedRow();
        if(index >= 0) {
            var b = model.remove(index);
            connector.deleteBook(b.getId());
            JOptionPane.showMessageDialog(null, "Pomyślnie usunięto książkę: " + b.getTitle(), "Usuwanie książki", JOptionPane.INFORMATION_MESSAGE);
            model.set(connector.getBooks());
        } else {
            JOptionPane.showMessageDialog(null, "Nie zaznaczono poprawnie żadnej książki.", "Usuwanie książki", JOptionPane.ERROR_MESSAGE);
        }
    }
}
