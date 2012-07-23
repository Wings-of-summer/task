package com.asya.gotovskaya.trainingtask.entity;

import java.util.Date;

/**
 * @author asya
 */
public class Task {
    private int id;
    private String name;
    private int hours;
    private Date start;
    private Date finish;
    private String state;

    public Task (int i, String n, int h, Date s, Date f, String st){
        id = i;
        name = n;
        hours = h;
        start = s;
        finish = f;
        state = st;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHours() {
        return hours;
    }

    public Date getStart() {
        return start;
    }

    public Date getFinish() {
        return finish;
    }

    public String getState() {
        return state;
    }
}
