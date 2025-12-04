package App;

import JDBC.Connector;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App extends JFrame {
    public static Connector connector = new Connector();

    public App() {
        var content = new Body(this);
        setContentPane(content);

        setUndecorated(true);
        setBackground(AppInitials.TRANSPARENT_COLOR);

        setBounds(200, 200, AppInitials.WINDOW_WIDTH, AppInitials.WINDOW_HEIGHT);
        setResizable(false);

        setTitle(AppInitials.APP_TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                connector.close();
            }
        });
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }
}
