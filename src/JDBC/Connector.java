package JDBC;

import Tabs.Products.BookModel;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Connector implements Closeable {
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 3306;
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DATABASE = "db";
    public static final String TABLE = "ksiazki";
    public static final String[] TABLE_COLUMNS = { "id", "tytul", "autor", "rok_wydania" };

    private Connection connection;

    public Connector() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Could not load the mysql driver.");
            throw new RuntimeException(e);
        }

        connect();
        selectDB();
        createTables();
    }

    private void connect() {
        String url = "jdbc:mysql://%s:%d/".formatted(HOSTNAME, PORT);
        try {
            connection = DriverManager.getConnection(url, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("Could not connect to the mysql server.");
            throw new RuntimeException(e);
        }
    }

    private boolean selectDB() {
        try {
            if(connection == null || connection.isClosed()) return false;

            Statement stmt = connection.createStatement();
            String sql = "CREATE DATABASE IF NOT EXISTS %s;".formatted(DATABASE);
            stmt.executeUpdate(sql);

            stmt = connection.createStatement();
            sql = "use %s;".formatted(DATABASE);
            stmt.executeUpdate(sql);

            return true;
        } catch (Exception e) {
            System.out.println("Could not select the DB.");
            throw new RuntimeException(e);
        }
    }

    private void createTables() {
        try {
            if (connection == null || connection.isClosed()) return;

            Statement stmt = connection.createStatement();
            String sql = ("CREATE TABLE IF NOT EXISTS %s(" +
                    "%s INT AUTO_INCREMENT PRIMARY KEY," +
                    "%s VARCHAR(40) NOT NULL," +
                    "%s VARCHAR(40) NOT NULL," +
                    "%s INT NOT NULL);").formatted(TABLE, TABLE_COLUMNS[0], TABLE_COLUMNS[1], TABLE_COLUMNS[2], TABLE_COLUMNS[3]);
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Could not create the table.");
            throw new RuntimeException(e);
        }
    }

    public String[] getTableColumns() {
        return TABLE_COLUMNS;
    }

    public List<BookModel> getBooks() {
        try {
            if (connection == null || connection.isClosed()) return null;

            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM %s;".formatted(TABLE);
            ResultSet rs = stmt.executeQuery(sql);

            List<BookModel> out = new ArrayList<>();
            while (rs.next()) out.add(new BookModel(
                rs.getInt(TABLE_COLUMNS[0]),
                rs.getString(TABLE_COLUMNS[1]),
                rs.getString(TABLE_COLUMNS[2]),
                rs.getInt(TABLE_COLUMNS[3])
            ));

            return out;
        } catch (Exception e) {
            System.out.println("Could not get the books from the table.");
            throw new RuntimeException(e);
        }
    }

    public void deleteBook(int id) {
        try {
            if (connection == null || connection.isClosed()) return;

            Statement stmt = connection.createStatement();
            String sql = "DELETE FROM %s WHERE id = %d;".formatted(TABLE, id);
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Could not delete the record in the table.");
            throw new RuntimeException(e);
        }
    }

    public boolean updateBook(BookModel b) {
        try {
            if (connection == null || connection.isClosed()) return false;

            String sql = ("UPDATE %s SET " +
                    "%s = ?, " +
                    "%s = ?, " +
                    "%s = ? WHERE id = ?;").formatted(TABLE, TABLE_COLUMNS[1], TABLE_COLUMNS[2], TABLE_COLUMNS[3]);
            var stmt = connection.prepareStatement(sql);
            stmt.setString(1, b.getTitle());
            stmt.setString(2, b.getAuthor());
            stmt.setInt(3, b.getYearOfIssue());
            stmt.setInt(4, b.getId());
            int r = stmt.executeUpdate();


            return r >= 1;
        } catch (Exception e) {
            System.out.println("Could not update the record in the table.");
            throw new RuntimeException(e);
        }
    }

    public boolean insertBook(BookModel bm) {
        try {
            if (connection == null || connection.isClosed()) return false;

            String sql = ("INSERT INTO %s (%s, %s, %s) VALUES(?, ?, ?);").formatted(TABLE, TABLE_COLUMNS[1], TABLE_COLUMNS[2], TABLE_COLUMNS[3]);
            var stmt = connection.prepareStatement(sql);
            stmt.setString(1, bm.getTitle());
            stmt.setString(2, bm.getAuthor());
            stmt.setInt(3, bm.getYearOfIssue());
            int r = stmt.executeUpdate();

            return r >= 1;
        } catch (Exception e) {
            System.out.println("Could not insert the record into the table.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            if(connection == null || connection.isClosed()) return;

            connection.close();
        } catch (Exception e) {
            System.out.println("Could not close the mysql connector.");
            throw new RuntimeException(e);
        }
    }
}
