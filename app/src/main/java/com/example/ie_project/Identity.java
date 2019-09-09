package com.example.ie_project;

public class Identity
{
    private int user_Id;
    private String name;
    private String email;
    private String password;
    private int security_question_id;
    private String security_question_answer;

    public Identity(int user_Id, String name, String email, String password, int security_question_id, String security_question_answer) {
        this.user_Id = user_Id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.security_question_id = security_question_id;
        this.security_question_answer = security_question_answer;
    }

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSecurity_question_id() {
        return security_question_id;
    }

    public void setSecurity_question_id(int security_question_id) {
        this.security_question_id = security_question_id;
    }

    public String getSecurity_question_answer() {
        return security_question_answer;
    }

    public void setSecurity_question_answer(String security_question_answer) {
        this.security_question_answer = security_question_answer;
    }
}
