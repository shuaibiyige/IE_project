package com.example.ie_project;

public class Activity
{
    private String date;
    private String name;
    private int id;
    private int ts;

    public Activity(String date, String name, int id, int ts)
    {
        this.date = date;
        this.name = name;
        this.id = id;
        this.ts = ts;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTs() {
        return ts;
    }

    public void setTs(int ts) {
        this.ts = ts;
    }
}
