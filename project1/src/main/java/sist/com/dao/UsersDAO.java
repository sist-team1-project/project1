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
            String sql = "SELECT COUNT(*) FROM users_1 "
                    + "WHERE u_id=?";
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
    public int loginIdcheck(String id)
    {
        int count=0;
        try
        {
            conn=dbcp.getConnection();
            String sql="SELECT COUNT(*) FROM users_1 "
                    +"WHERE u_id=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,id);
            ResultSet rs=ps.executeQuery();
            rs.next();
            count=rs.getInt(1);
            rs.close();
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            dbcp.disConnection(conn, ps);
        }
        return count;
    }
    
    public void loginJoin(UsersVO vo)
    {
        try
        {
            conn=dbcp.getConnection();
            String sql="INSERT INTO users_1 VALUES(?,?,?,?,?,?,?,?,?,?,'n')";
            ps.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getU_id());
            ps.setString(2, vo.getU_password());
            ps.setString(3, vo.getU_name());
            ps.setString(4, vo.getU_profile());
            ps.setDate(5, vo.getU_birthday());
            ps.setString(6, vo.getU_gender());
            ps.setString(7, vo.getU_email());
            ps.setString(8, vo.getU_address());
            ps.setString(9, vo.getU_question());
            ps.setString(10, vo.getU_answer());
            ps.executeUpdate();
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            dbcp.disConnection(conn, ps);
        }
    }

    public UsersVO userDetail(String id) {
        UsersVO vo = new UsersVO();
        try {
            conn = dbcp.getConnection();
            String sql = "SELECT * "
                    + "FROM users_1 "
                    + "WHERE u_id=?";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            
            ResultSet rs = ps.executeQuery();
            rs.next();
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
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
        return vo;
    }
}