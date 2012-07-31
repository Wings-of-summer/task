package com.asya.gotovskaya.trainingtask.entity;

/**
 * @author asya
 */
public class Employee {

    private int id;
    private String lastName;
    private String name;
    private String middleName;
    private String post;

    public Employee(int id, String lastName, String name, String middleName, String post) {
        this.id = id;
        this.lastName = lastName;
        this.name = name;
        this.middleName = middleName;
        this.post = post;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", post='" + post + '\'' +
                '}';
    }
}
