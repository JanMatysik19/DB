package App;

import JDBC.Connector;
import Tabs.Products.ProductsTab;
import Tabs.Reports.ReportsTab;
import Tabs.TabPanelUI;

import javax.swing.*;
import java.awt.*;

public class Main extends JTabbedPane {
    public Main() {
        var productsTab = new ProductsTab();
        addTab(ProductsTab.TITLE, productsTab);

        setTabPlacement(TOP);
        setTabLayoutPolicy(SCROLL_TAB_LAYOUT);

        setUI(new TabPanelUI(this));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, AppInitials.FOURTH_COLOR));

//        try(Connector connector = new Connector()) {
        var connector = App.connector;
//        productsTab.getModel().set(connector.getBooks());
//        productsTab.getControlPanel().setBookDeleter((b) -> connector.deleteBook(b.getId()));
//        productsTab.getControlPanel().setBookInserter(connector::insertBook);
//        productsTab.getControlPanel().setBookRefresher(() -> productsTab.getModel().set(connector.getBooks()));
//        }
    }
}
