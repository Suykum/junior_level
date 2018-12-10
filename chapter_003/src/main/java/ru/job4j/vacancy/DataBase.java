package ru.job4j.vacancy;

import org.apache.log4j.Logger;

import java.sql.*;

public class DataBase implements AutoCloseable {
    private static final Logger LOGGER = Logger.getLogger(DataBase.class);
    private Connection connect;
    private Date lastDateinDB = null;

    public DataBase() {
        connect = ConnectorDB.getConnect();
    }

    public void createDB() {
        String sql = "CREATE DATABASE vacancyDB;";
        try (PreparedStatement pstmt = connect.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS vacancies (id serial primary key, name varchar(150), body text, link varchar(250), v_date date)";
        try (PreparedStatement pstmt = connect.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public boolean insertVacancy(Vacancy vacancy) {
        String sql = "INSERT INTO vacancies(name, body, link, v_date) VALUES (?, ?, ?, ?)";
        boolean inserted = false;
        try (PreparedStatement pstm = connect.prepareStatement(sql)) {
            pstm.setString(1, vacancy.getName());
            pstm.setString(2, vacancy.getBody());
            pstm.setString(3, vacancy.getLink());
            pstm.setDate(4,  vacancy.getDate());
            pstm.executeUpdate();
            inserted = true;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
            return inserted;
    }

    public Date lastDateInDB() {
        String sql = "SELECT MAX(v_date) as date from vacancies";
        ResultSet resultSet = null;
        Date lastdate = null;
        try (PreparedStatement pstmt = connect.prepareStatement(sql)) {
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                lastdate = resultSet.getDate("date");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return lastdate;
    }

    @Override
    public void close() {
        if (connect != null) {
            try {
                connect.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

    }
}
