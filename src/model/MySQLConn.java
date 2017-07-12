/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLConn {

    private Connection conn;

    public MySQLConn() {
        setConnection();
    }

    private void setConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLConn.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConn.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public Statement connect() {
        try {
            Statement statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            return statement;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConn.class.getName()).log(Level.SEVERE,
                    null, ex);
            return null;
        }
    }

    public PreparedStatement connect(String query) {
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            return statement;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConn.class.getName()).log(Level.SEVERE,
                    null, ex);
            return null;
        }
    }
}
