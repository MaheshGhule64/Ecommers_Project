package com.example.ecommers_project;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    public Customer customerLogin(String userName, String password){

        DBConnection dbConnection = new DBConnection();
        MessageDigest md = null;
        //ResultSet rs = conn.getQueryTable(loginQuery);

        try{
            md = MessageDigest.getInstance("SHA-512");
//            if(rs.next()){
//                return (new Customer(rs.getInt("id"), rs.getString("name"),
//                        rs.getString("email"), rs.getString("mobile")));
//            }
        }
            catch (NoSuchAlgorithmException e){
                throw new RuntimeException(e);
            }
        String hashedPassword = new String(md.digest(password.getBytes(StandardCharsets.UTF_8)));
        ResultSet rs = dbConnection.getQueryTable("SELECT * FROM customer where email = '"+userName+"' AND password = '"+hashedPassword+"'");
        try{
            if(rs.next()){
                return (new Customer(rs.getInt("id"), rs.getString("name"),
                          rs.getString("email"), rs.getString("mobile")));
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
            return null;
        }
//
//    public static void main(String[] args) {
//        Login login = new Login();
//        Customer customer = login.customerLogin("mahesh@gmail.com", "mahesh123");
//        System.out.println("Welcome :" + customer.getName());
//
//    }
    }

