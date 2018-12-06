package ru.job4j.sqlite;

import java.sql.*;
import java.util.ArrayList;

public class StoreSQL implements AutoCloseable {

    private Config config;
    private Connection connection;

    public StoreSQL(Config config) {
        this.config = config;
    }

    private Connection connect() {
        // SQLite connection string
        String url = config.get("url");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void createNewDatabase(String fileName) {
        String url = "jdbc:sqlite:C:/sqlite/db/" + fileName;
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void generate(int n) {
        createNewTable();
        clearTable();
        insertValues(n);
    }

    private void createNewTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS entry (field integer);";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertValues(int n) {
        String sql = "INSERT INTO entry(field) VALUES(?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 1; i <= n; i++) {
                pstmt.setInt(1, i);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private void clearTable() {
        String sql = "DELETE FROM entry";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<Entry> selectAll() {
        ArrayList<Entry> allEntry = new ArrayList<>();
        String sql = "SELECT * FROM entry";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                allEntry.add(new Entry(rs.getInt("field")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allEntry;
    }


    @Override
    public void close() throws Exception {
        connection.close();
    }
}
