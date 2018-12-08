package ru.job4j.sqlite;

import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;

public class StoreSQL implements AutoCloseable {
    private static final Logger LOGGER = Logger.getLogger(StoreSQL.class);
    private Config config;
    private Connection connection;

    public StoreSQL(Config config) {
        this.config = config;
        connection = connect();
    }

    private Connection connect() {
        // SQLite connection string
        String url = config.get("url");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return conn;
    }

    public void createNewDatabase() {
        try  {
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
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
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // create a new table
            pstmt.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void insertValues(int n) {
        String sql = "INSERT INTO entry(field) VALUES(?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            for (int i = 1; i <= n; i++) {
                pstmt.setInt(1, i);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    private void clearTable() {
        String sql = "DELETE FROM entry";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    public ArrayList<Entry> selectAll() {
        ArrayList<Entry> allEntry = new ArrayList<>();
        String sql = "SELECT * FROM entry";
        try (PreparedStatement pstmt  = connection.prepareStatement(sql);
             ResultSet rs    = pstmt.executeQuery()) {
            // loop through the result set
            while (rs.next()) {
                allEntry.add(new Entry(rs.getInt("field")));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return allEntry;
    }

    public void dataInsertingTime(int numberOfInsertingElements) {
        long startTime = System.currentTimeMillis();
        generate(numberOfInsertingElements);
        long endTime = System.currentTimeMillis();
        long finalTime = endTime - startTime;
        System.out.println(finalTime + " Milliseconds used for inserting " + numberOfInsertingElements + " elements");
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
