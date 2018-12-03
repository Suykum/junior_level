package ru.job4j.tracker;

public class Item {
    private String id;
    private String name;
    private String desc;
    private long create;
    private String[] comments;

    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
    public Item(String name, String desc, long create) {
        this.name = name;
        this.desc = desc;
        this.create = create;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getCreated() {
        return create;
    }

    public void setCreated(long created) {
        this.create = create;
    }

    public String[] getComments() {
        return comments;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
    }
    public String toString() {
        return ("ID = " + this.getId() + ", name = " + this.getName() + ", description = " + this.getDesc());
    }
}
