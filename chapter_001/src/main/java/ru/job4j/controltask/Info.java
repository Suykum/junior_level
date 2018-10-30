package ru.job4j.controltask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Info {
    private List<Store.User> previous;
    private List<Store.User> current;
    private Map<Integer, String> previousMap;
    private Map<Integer, String> currentMap;
    private int addCount;
    private int deleteCount;
    private int editCount;

    public Info(List<Store.User> previous, List<Store.User> current) {
        this.previous = previous;
        this.current = current;
        this.previousMap = toMap(previous);
        this.currentMap = toMap(current);
    }

    private Map<Integer, String> toMap(List<Store.User> list) {
        return list.stream().collect(Collectors.toMap(n -> n.getId(), n -> n.getName()));
    }

    /*public void statistics() {
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
    }*/

    public void statistics() {
        addCount = (int) current.stream().filter(n -> !previousMap.containsKey(n.getId())).count();
        deleteCount = (int) previous.stream().filter(n -> !currentMap.containsKey(n.getId())).count();
        editCount = (int) current.stream().filter(n -> previousMap.containsKey(n.getId()) && !previousMap.containsValue(n.getName())).count();
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
