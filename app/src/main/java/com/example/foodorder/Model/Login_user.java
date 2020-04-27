package com.example.foodorder.Model;

public class Login_user {
    String Number;
    String Name;
    String Password;


    public Login_user() {

    }

    public Login_user(String number, String name, String password) {
        Number = number;
        Name = name;
        Password = password;
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
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}