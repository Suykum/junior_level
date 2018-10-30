package ru.job4j.controltask;

import java.util.List;

public class Store {
    public Info diff(List<User> previous, List<User> current) {
        Info userStats = new Info(previous, current);
        userStats.statistics();
        return userStats;
    }

    static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.setId(id);
            this.setName(name);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object object) {
            User user = (User) object;
            return this.id == user.id;
        }
        public int hashCode() {
            return id;
        }
    }
}
