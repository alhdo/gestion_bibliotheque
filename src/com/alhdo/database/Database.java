package com.alhdo.database;

import java.sql.*;

/*
 * Created by Castro Alhdo on 4/26/16.
 * File created af 10:26 PM
 * _____________________________________
 * < Don't copy my code without my chmod >
 * ------------------------------------
 * \   ^__^
 * \  (oo)\_______
 *    (__)\       )\/\
 *         ||----w |
 *         ||     ||
 */
public class Database {
    Statement statement;
    ResultSet resultSet;
    Connection c;
    public Database(){
        if(c==null){
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test?user=monty&password=greatsqldb");
                c=conn;
                // Do something with the Connection


            } catch (SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }

    }
}
