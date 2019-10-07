package com.example.ie_project;

public class HistoryList
{
    private String date;
    private String name;
    private float stars;
    private int color;

    public HistoryList(String date, String name, float stars, int color)
    {
        this.date = date;
        this.name = name;
        this.stars = stars;
        this.color = color;
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

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
