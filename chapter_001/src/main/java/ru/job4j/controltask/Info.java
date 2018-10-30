package ru.job4j.controltask;

import java.util.List;

public class Info {
    private List<Store.User> previous;
    private List<Store.User> current;
    private int addCount;
    private int deleteCount;
    private int editCount;

    public Info(List<Store.User> previous, List<Store.User> current) {
        this.previous = previous;
        this.current = current;
    }

    public void statistics() {
        for (Store.User uP : previous) {
            if (!current.contains(uP)) {
                    deleteCount++;
            }
        }
        for (Store.User uC : current) {
            if (!previous.contains(uC)) {
                addCount++;
            }
        }
        for (Store.User uP : previous) {
            for (Store.User uC : current) {
                if (uP.equals(uC) && !(uP.getName().equals(uC.getName()))) {
                    editCount++;
                }
            }
        }
    }

    public int getAddCount() {
        return addCount;
    }

    public int getEditCount() {
        return editCount;
    }

    public int getDeleteCount() {
        return deleteCount;
    }
}
