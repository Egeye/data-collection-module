package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcDemo {
    public static void main(String[]args){
        String url="jdbc://localhost//3306/dacollect";//localhost:3306/dacollect
        String user="root";
        String password="root";
        try {
            Class.forName("jdbc.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection(url,user,password);
            if(conn != null){
                System.out.println("连接成功");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
