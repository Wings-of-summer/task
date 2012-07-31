package com.asya.gotovskaya.trainingtask.entity;

/**
 * @author asya
 */
public class Project {
    private int id;
    private String name;
    private String abbreviation;
    private String description;

    public Project(int id, String name, String abbreviation, String description) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getDescription() {
        return description;
    }
}
