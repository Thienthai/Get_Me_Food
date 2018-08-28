package com.example.jarvis.sendmefood.Model;

public class User {
    private String Name;
    private String Password;
    private String Number;

    public User() {

    }

    public User(String name, String psswd,String number) {
        this.Name = name;
        this.Password = psswd;
        this.Number = number;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}
