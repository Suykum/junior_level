package ru.job4j.servlet2;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;



public class DbStore implements Store {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static DbStore instance = new DbStore();
    private static final Logger LOGGER = Logger.getLogger(DbStore.class);


    public DbStore() {
        try (InputStream in = DbStore.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            SOURCE.setDriverClassName(config.getProperty("driver-class-name"));
            SOURCE.setUrl(config.getProperty("url"));
            SOURCE.setUsername(config.getProperty("username"));
            SOURCE.setPassword(config.getProperty("password"));
            SOURCE.setMinIdle(Integer.parseInt(config.getProperty("minIdle")));
            SOURCE.setMaxIdle(Integer.parseInt(config.getProperty("maxIdle")));
            SOURCE.setMaxOpenPreparedStatements(Integer.parseInt(config.getProperty("maxPrepareStatment")));
            createTable();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    public static DbStore getInstance() {
        return instance;
    }

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users(id UUID default uuid_generate_v4() primary key, name varchar(50),"
                + " login varchar(50), email varchar(50), password varchar(50), create_d date default now(), role varchar(30))";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)
        ) {
            pst.execute();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public boolean add(User user) {
        int addResult = 0;
        String sql = "INSERT INTO users (name, login, email, password, role) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)
        ) {
            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getPassword());
            pst.setString(5, String.valueOf(user.getRole()));
            addResult = pst.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return addResult > 0;
    }

    @Override
    public boolean update(UUID id, User user) {
        int updateResult = 0;
        String sql = "UPDATE users SET name = ?, login = ?, email = ?, password = ?, role = ? WHERE id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)
        ) {
            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getPassword());
            pst.setString(5, String.valueOf(user.getRole()));
            pst.setObject(6, id);
            updateResult = pst.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return updateResult > 0;
    }

    @Override
    public boolean delete(UUID id) {
        int deleteResult = 0;
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)
        ) {
            pst.setObject(1, id);
            deleteResult = pst.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return deleteResult > 0;
    }

        @Override
    public List findAll() {
        String sql = "SELECT * FROM users";
        List<User> list = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)
        ) {
           ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new User(UUID.fromString(rs.getString("id")), rs.getString("name"), rs.getString("login"),
                        rs.getString("email"), rs.getString("password"), rs.getDate("create_d"),
                        Role.Roles.valueOf(rs.getString("role"))));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public User findById(UUID id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)
        ) {
            pst.setObject(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                user = new User(UUID.fromString(rs.getString("id")), rs.getString("name"), rs.getString("login"),
                        rs.getString("email"), rs.getString("password"), rs.getDate("create_d"), Role.Roles.valueOf(rs.getString("role")));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }
}
