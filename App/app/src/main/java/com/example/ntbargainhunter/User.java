package com.example.ntbargainhunter;

public class User {
    private String email;
    private String name;

    User() {

    }

    User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public void updateName(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    public void updateEmail(String email) {
        this.email = email;
    }
}
