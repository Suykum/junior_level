package ru.job4j.cinema;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DataBase {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static DataBase instance = new DataBase();
    private static final Logger LOGGER = Logger.getLogger(DataBase.class);

    private DataBase() {
        try (InputStream in = DataBase.class.getClassLoader().getResourceAsStream("app2.properties")) {
            Properties config = new Properties();
            config.load(in);
            SOURCE.setDriverClassName(config.getProperty("driver-class-name"));
            SOURCE.setUrl(config.getProperty("url"));
            SOURCE.setUsername(config.getProperty("username"));
            SOURCE.setPassword(config.getProperty("password"));
            SOURCE.setMinIdle(Integer.parseInt(config.getProperty("minIdle")));
            SOURCE.setMaxIdle(Integer.parseInt(config.getProperty("maxIdle")));
            SOURCE.setMaxOpenPreparedStatements(Integer.parseInt(config.getProperty("maxPrepareStatment")));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        createHallTable();
        createAccountTable();
        setHall();

    }

    public static DataBase getInstance() {
        return instance;
    }

    private void createAccountTable() {
        String sql = "CREATE TABLE IF NOT EXISTS accounts(custom_id serial primary key, custom_name varchar(60) not null , tel_number varchar(50) not null, seat_id int references hall);";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)
        ) {
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createHallTable() {
        String sql = "CREATE TABLE IF NOT EXISTS hall(id int primary key, hall_row int not null , hall_column int not null, price int not null, reserved boolean );";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)
        ) {
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setHall() {
        String sql = "SELECT COUNT(*) FROM hall";
        int tableRowNum = 0;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tableRowNum = rs.getInt("count");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        if (tableRowNum == 0) {
            openNewHall();
        }
    }

    private void openNewHall() {
        ArrayList<Hall> seats = new ArrayList<>();
        seats.add(new Hall(11, 1, 1, 300, false));
        seats.add(new Hall(12, 1, 2, 300, false));
        seats.add(new Hall(13, 1, 3, 300, false));

        seats.add(new Hall(21, 2, 1, 200, false));
        seats.add(new Hall(22, 2, 2, 200, false));
        seats.add(new Hall(23, 2, 3, 200, false));

        seats.add(new Hall(31, 3, 1, 100, false));
        seats.add(new Hall(32, 3, 2, 100, false));
        seats.add(new Hall(33, 3, 3, 100, false));

        String sql = "INSERT INTO hall(id, hall_row, hall_column, price, reserved) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = SOURCE.getConnection();
        PreparedStatement pst = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            for (Hall seat : seats) {
                pst.setInt(1, seat.getId());
                pst.setInt(2, seat.getRow());
                pst.setInt(3, seat.getColumn());
                pst.setInt(4, seat.getPrice());
                pst.setBoolean(5, seat.isReserved());
                pst.addBatch();
            }
            pst.executeBatch();
            connection.commit();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public List getReservedSeats() {
        List<Integer> reservedSeatList = new ArrayList();
        String sql = "SELECT id FROM hall WHERE reserved = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setBoolean(1, true);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                reservedSeatList.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return reservedSeatList;
    }

    public Hall getSeatById(int id) {
        Hall seat = null;
        String sql = "SELECT id, hall_row, hall_column, price, reserved FROM hall WHERE id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                seat = new Hall(rs.getInt("id"), rs.getInt("hall_row"), rs.getInt("hall_column"),
                        rs.getInt("price"), rs.getBoolean("reserved"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return seat;
    }

    public boolean makeRezervation(Account account, int id) {
        String seatRezerve = "UPDATE hall SET reserved = ? WHERE id = ?";
        String addAccount = "INSERT INTO accounts (custom_name, tel_number, seat_id) VALUES (?, ?, ?)";
        int addAccountResult = 0;
        Connection connection = null;
        PreparedStatement pstHall = null;
        PreparedStatement pstAccount = null;
        try {
            connection = SOURCE.getConnection();
            connection.setAutoCommit(false);
            pstHall = connection.prepareStatement(seatRezerve);
            pstHall.setBoolean(1, true);
            pstHall.setInt(2, id);
            pstHall.executeUpdate();

            pstAccount = connection.prepareStatement(addAccount);
            pstAccount.setString(1, account.getName());
            pstAccount.setString(2, account.getTelNumber());
            pstAccount.setInt(3, id);
            addAccountResult = pstAccount.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exc) {
                LOGGER.error(exc.getMessage(), exc);
            }

        } finally {
            try {
                if (pstHall != null) {
                    pstHall.close();
                }
                if (pstAccount != null) {
                    pstAccount.close();
                }
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException exc1) {
                LOGGER.error(exc1.getMessage(), exc1);
            }

        }
        return addAccountResult > 0;
    }

    public List getAllAccounts() {
        String sql = "SELECT * FROM accounts";
        List<Account> list = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt("custom_id"), rs.getString("custom_name"),
                        rs.getString("tel_number"), rs.getInt("seat_id")));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return list;
    }

    public void clearAllData() {
        String sql = "DELETE from accounts";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        resetSeats();
    }

   private void resetSeats() {
        String sql = "UPDATE hall SET reserved = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setBoolean(1, false);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
