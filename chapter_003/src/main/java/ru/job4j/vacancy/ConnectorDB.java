package ru.job4j.vacancy;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectorDB {
    private static final Logger LOGGER = Logger.getLogger(ConnectorDB.class);
    private static Connection connection = null;
    public static Connection getConnect() {

        try (InputStream in = ConnectorDB.class.getClassLoader().getResourceAsStream("vacancy.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        }  catch (Exception e) {
        throw new IllegalStateException(e);
        }
        return connection;
    }

}
