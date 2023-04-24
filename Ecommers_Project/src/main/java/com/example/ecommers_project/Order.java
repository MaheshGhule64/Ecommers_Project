package com.example.ecommers_project;

import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Order {

    static boolean placeOrder(Customer customer, Product product){
        String groupOrderId = "SELECT max (group_order_id)+1 id from orders";
        try{
            DBConnection dbConnection = new DBConnection();
            ResultSet rs = dbConnection.getQueryTable(groupOrderId);
            if(rs.next()){
                String placeOrder = "insert into orders(group_order_id, customer_id, product_id) " +
                        "values("+ rs.getInt("id")+", " + customer.getId() + ","+ product.getId()+")";
                return dbConnection.updateDatabase(placeOrder)!=0;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    static int placeMultipleOrder(Customer customer, ObservableList<Product> products){
        String groupOrderId = "SELECT max(group_prder_id)+1  id FROM orders";
        try{
            DBConnection dbConnection = new DBConnection();
            ResultSet rs = dbConnection.getQueryTable(groupOrderId);
            int count = 0;

            if(rs.next()){
                for(Product product : products){
                    String placeOrder = "INSERT INTO orders(group_order_id, customer_id, product_id)" +
                            "values("+ rs.getInt("id") +", "+ customer.getId()+", "+ product.getId()+")";

                    count += dbConnection.updateDatabase(placeOrder);
                }
                return  count;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  0;
    }
}
