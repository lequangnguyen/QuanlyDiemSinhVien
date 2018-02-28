/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lenovo g4070
 */
public class MyConnect {
    static final String className = "com.mysql.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost/quanlydiem?characterEncoding=utf8";
    static final String user = "root";
    static final String pass = "";

    public static Connection getConnect() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Connect succeed!");
            return conn;
        } catch (ClassNotFoundException ex) {
            throw new ClassNotFoundException();
        } catch (SQLException ex) {
            throw new SQLException();
        }
    }
    
    /**
     *
     * @param con
     * @param stm
     * @param rs
     */
    public static void closeAll(Connection conn,
            PreparedStatement stm,
            ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (stm != null) {
            try {
                stm.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
