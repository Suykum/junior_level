package ru.job4j.sqlite;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class Entries {
    @XmlElement(name = "entry")
    private ArrayList<Entry> list;
    public Entries() {
        super();
    }

    public boolean add(Entry entry) {
        return list.add(entry);
    }

    public void setEntries(ArrayList<Entry> entries) {
        this.list = entries;
    }
}
