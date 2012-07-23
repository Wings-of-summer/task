package com.asya.gotovskaya.trainingtask.entity;

/**
 * @author asya
 */
public class Project {
    private int id;
    private String name;
    private String abbreviation;
    private String description;

    public Project(int i, String n, String a, String d){
        id = i;
        name = n;
        abbreviation = a;
        description = d;
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
