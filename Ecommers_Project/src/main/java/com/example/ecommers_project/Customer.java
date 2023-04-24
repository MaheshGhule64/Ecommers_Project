package com.example.ecommers_project;

public class Customer {

     int id;
     String name, email, mobile, password;

    public Customer(int id, String name, String email, String mobile) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;

    }

    public Customer(String name, String email, String mobile, String password) {

        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }
}
