package com.example.ecommers_project;
import java.sql.*;

public class DBConnection {

    private final String dbUrl = "jdbc:mysql://localhost:3306/ecommerce";
    private final String username = "root";
    private final String password = "12345";

     Statement getStatement(){
        try{
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            Statement statement = connection.createStatement();
            return statement;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getQueryTable(String query){
        try{

            Statement statement = getStatement();
            return statement.executeQuery(query);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int updateDatabase(String query){
        try{
            Statement statement = getStatement();
            return statement.executeUpdate(query);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {

        DBConnection conn = new DBConnection();
        ResultSet rs = conn.getQueryTable("SELECT * FROM customer");

        if(rs!=null){
            System.out.print("Connection Successful");
        }
        else{
            System.out.print("Connection Failed");
        }
    }
}
