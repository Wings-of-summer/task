package com.asya.gotovskaya.trainingtask.entity;

/**
 * @author asya
 */
public class Employe {
    private int id;
    private String lastName;
    private String name;
    private String middleName;
    private String post;

    public Employe(int i, String l, String n, String m, String p){
        id = i;
        lastName = l;
        name = n;
        middleName = m;
        post = p;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getPost() {
        return post;
    }
}
