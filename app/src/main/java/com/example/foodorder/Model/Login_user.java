package com.example.foodorder.Model;

public class Login_user {
    String Number;
    String Name;
    String Password;
    String isStaff;

    public String getIsStaff() {
        return isStaff;
    }

    public void setIsStaff(String isStaff) {
        this.isStaff = isStaff;
    }

    public Login_user() {

    }

    public Login_user(String number, String name, String password, String isstaff) {
        Number = number;
        Name = name;
        Password = password;
        isStaff=isstaff;
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