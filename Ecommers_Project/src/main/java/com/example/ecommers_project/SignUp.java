package com.example.ecommers_project;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUp {
    public static boolean customerLogin(Customer customer){
        DBConnection dbConnection = new DBConnection();
        String query = "INSERT INTO customer(name, email, mobile, password) VALUES("
                         + "'" + customer.name + "'" +"," +
                         "'" + customer.email + "'" +"," +
                         "'" + customer.mobile + "'" + "," +
                         "'" + customer.password + "'" + ")";

            int rs = dbConnection.updateDatabase(query);
                return rs!=0;
    }
}
