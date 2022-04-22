package com.sist.dao;

import java.sql.*;

public class DBCPConnection {
    
    private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";

    public DBCPConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception ex) {
        }
    }

    public Connection getConnection(Connection conn) {
        try {
            conn = DriverManager.getConnection(URL, "hr", "happy");
        } catch (Exception ex) {
        }
        return conn;
    }

    public void disConnection(Connection conn, PreparedStatement ps) {
        try {
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        } catch (Exception ex) {
        }
    }
}