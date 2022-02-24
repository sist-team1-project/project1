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
                sql = "SELECT u_password,u_name " + "FROM users_1 " + "WHERE u_id=?";
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

    public UsersVO userDetail(int id) {
        UsersVO vo = new UsersVO();
        try {
            conn = dbcp.getConnection();
            String sql = "SELECT * " + "FROM users_1 " + "WHERE u_id=?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                vo.setU_id(rs.getString(1));
                vo.setU_password(rs.getString(2));
                vo.setU_name(rs.getString(3));
                vo.setU_profile(rs.getString(4));
                vo.setU_birthday(rs.getDate(5));
                vo.setU_gender(rs.getString(6));
                vo.setU_email(rs.getString(7));
                vo.setU_address(rs.getString(8));
                vo.setU_question(rs.getString(9));
                vo.setU_answer(rs.getString(10));
                rs.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
        return vo;
    }

}