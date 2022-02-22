package sist.com.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import sist.com.vo.*;

public class UsersDAO {
    private Connection conn;
    private PreparedStatement ps;
    private DBCPConnection dbcp = new DBCPConnection();

    public String isLogin(String id, String pwd) {
        String result = "";
        try {
            conn = dbcp.getConnection();
            String sql = "SELECT COUNT(*) FROM users_1 " + "WHERE u_id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            if (count == 0) {
                return "FAIL";
            } else {
                sql = "SELECT u_password,u_name "
                        + "FROM users_1 "
                        + "WHERE u_id=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, id);
                rs = ps.executeQuery();
                rs.next();
                String password = rs.getString(1);
                String name = rs.getString(2);
                rs.close();

                if (password.equals(pwd)) {
                    return name;
                } else {
                    result = "FAIL";
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
        return result;
    }
}