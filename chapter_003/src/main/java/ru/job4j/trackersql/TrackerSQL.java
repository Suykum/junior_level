package ru.job4j.trackersql;

import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class TrackerSQL implements ITracker, AutoCloseable {
    private Connection connection;
    private PreparedStatement st = null;
    private ResultSet rs = null;


    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }
    public void createTable() {
        Statement st = null;
        if (init()) {
            try (TrackerSQL trackerSQL = new TrackerSQL()) {
                st = connection.createStatement();
                st.execute("CREATE TABLE IF NOT EXISTS items (id serial primary key, name varchar(20), description varchar(100))");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    @Override
    public Item add(Item item) {
        if (init()) {
            try (TrackerSQL trackerSQL = new TrackerSQL()) {
                st = connection.prepareStatement("INSERT INTO items(name, description) values (?, ?)");
                st.setString(1, item.getName());
                st.setString(2, item.getDesc());
                st.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        int rowsUpdated = 0;
        if (init()) {
            try (TrackerSQL trackerSQL = new TrackerSQL()) {
                st = connection.prepareStatement("UPDATE items SET name = ? , description = ? WHERE id = ?");
                st.setString(1, item.getName());
                st.setString(2, item.getDesc());
                st.setInt(3, Integer.parseInt(id));
                rowsUpdated = st.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rowsUpdated != 0;
    }

    @Override
    public boolean delete(String id) {
        int rowsDeleted = 0;
        if (init()) {
            try (TrackerSQL trackerSQL = new TrackerSQL()) {
                st = connection.prepareStatement("DELETE FROM items WHERE id = ?");
                st.setInt(1, Integer.parseInt(id));
                rowsDeleted = st.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rowsDeleted != 0;
    }

    @Override
    public ArrayList<Item> getAll() {
        ArrayList<Item> resultAll = new ArrayList<>();
        if (init()) {
            try (TrackerSQL trackerSQL = new TrackerSQL()) {
                st = connection.prepareStatement("SELECT * FROM items");
                rs = st.executeQuery();
                while (rs.next()) {
                    resultAll.add(new Item(String.valueOf(rs.getInt("id")), rs.getString("name"), rs.getString("description")));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultAll;
    }

    @Override
    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> resultByName = new ArrayList<>();
        if (init()) {
            try (TrackerSQL trackerSQL = new TrackerSQL()) {
                st = connection.prepareStatement("SELECT * FROM items WHERE name = ?");
                st.setString(1, key);
                rs = st.executeQuery();
                while (rs.next()) {
                    resultByName.add(new Item(String.valueOf(rs.getInt("id")), rs.getString("name"), rs.getString("description")));
                    //System.out.println(String.format("%d. %s, %s", rs.getInt("id"), rs.getString("name"), rs.getString("description")));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultByName;
    }

    @Override
    public Item findById(String id) {
        Item item = null;
        if (init()) {
            try (TrackerSQL trackerSQL = new TrackerSQL()) {
                PreparedStatement st = connection.prepareStatement("SELECT * FROM items WHERE id = ?");
                st.setInt(1, Integer.parseInt(id));
                rs = st.executeQuery();
                while (rs.next()) {
                    item = new Item(String.valueOf(rs.getInt("id")), rs.getString("name"), rs.getString("description"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return item;
    }

    @Override
    public void close() throws Exception {
        System.out.println("closing resources");
    }
}
