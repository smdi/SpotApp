package com.smile.spotapp.model;

public class Signup_details {
    String name,email,phno,pinno;

    public Signup_details() {
    }

    public Signup_details(String name, String email, String phno, String pinno) {
        this.name = name;
        this.email = email;
        this.phno = phno;
        this.pinno = pinno;
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

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getPinno() {
        return pinno;
    }

    public void setPinno(String pinno) {
        this.pinno = pinno;
    }
}
