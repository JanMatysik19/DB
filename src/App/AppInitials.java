package App;

import java.awt.*;
import java.io.File;

public class AppInitials {
    public static final String APP_TITLE = "Księgozbiór";


    public static final int WINDOW_WIDTH = 850;
    public static final int WINDOW_HEIGHT = 500;
    public static final int WINDOW_RADIUS = 25;


    public static final Color TRANSPARENT_COLOR = new Color(0, 0, 0, 0);

    public static final Color FIRST_COLOR = Color.decode("#001e2b");
    public static final Color SECOND_COLOR = Color.decode("#0a2432");
    public static final Color THIRD_COLOR = Color.decode("#1c2d38");
    public static final Color FOURTH_COLOR = Color.decode("#3d4f58");
    public static final Color FIFTH_COLOR = Color.decode("#889397");
    public static final Color SIXTH_COLOR = Color.decode("#00684a");
    public static final Color SEVENTH_COLOR = Color.decode("#00ed64");
    public static final Color EIGHT_COLOR = Color.decode("#023430");
    public static final Color NINETH_COLOR = Color.decode("#ED6300");
    public static final Color TENTH_COLOR = Color.decode("#00A847");


    public enum FONT_TYPE {
        PLAIN,
        BOLD,
        ITALIC
    }

    public static Font MONTSERRAT_FONT(FONT_TYPE type, float size) {
        int t = translateFont(type);

        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File("./res/Montserrat-SemiBold.ttf")).deriveFont(t, size);
        } catch (Exception _) {
            System.exit(1);
            return null;
        }
    }

    public static Font ROBOTO_FONT(FONT_TYPE type, float size) {
        int t = translateFont(type);

        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File("./res/Roboto-Regular.ttf")).deriveFont(t, size);
        } catch (Exception _) {
            System.exit(1);
            return null;
        }
    }

    private static int translateFont(FONT_TYPE type) {
        return switch (type) {
            case BOLD -> Font.BOLD;
            case ITALIC -> Font.ITALIC;
            default -> Font.PLAIN;
        };
    }
}
