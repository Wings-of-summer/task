package com.asya.gotovskaya.trainingtask.entity;

import java.util.Date;

/**
 * @author asya
 */
public class Task {
    private int id;
    private String name;
    private Project project;
    private int hours;
    private Date start;
    private Date finish;
    private Employee employee;
    private String state;

    public Task(int id, String name, Project project, int hours, Date start, Date finish, Employee employee, String state) {
        this.id = id;
        this.name = name;
        this.project = project;
        this.hours = hours;
        this.start = start;
        this.finish = finish;
        this.employee = employee;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Project getProject() {
        return project;
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

    public Employee getEmployee() {
        return employee;
    }

    public String getState() {
        return state;
    }
}
