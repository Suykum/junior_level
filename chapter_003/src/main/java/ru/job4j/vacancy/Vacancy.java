package ru.job4j.vacancy;

import java.sql.Date;

public class Vacancy {
    private int id;
    private String name;
    private String body;
    private String link;
    private Date date;

    public Vacancy(String name, String body, String link, Date date) {
        this.setName(name);
        this.setBody(body);
        this.setLink(link);
        this.setDate(date);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
