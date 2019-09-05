package com.example.ie_project;

public class User
{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String name;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private int age;
}
