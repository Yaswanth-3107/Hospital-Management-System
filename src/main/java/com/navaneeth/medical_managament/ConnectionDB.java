package com.navaneeth.medical_managament;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionDB {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "Root@176";
    private static final String URL = "jdbc:mysql://localhost:3306/doctorsystem";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Properties connectionProperties = new Properties();
            connectionProperties.put("user", USERNAME);
            connectionProperties.put("password", PASSWORD);
            con = DriverManager.getConnection(URL, connectionProperties);
        } catch (Exception e) {
            System.out.println("there was a problem in getting connection" + e.getMessage());
            e.printStackTrace();
        }
        return con;
    }

}
