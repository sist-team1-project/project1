package sist.com.dao;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import sist.com.vo.*;

public class UsersDAO {
    private Connection conn;
    private PreparedStatement ps;
    private DBCPConnection dbcp = new DBCPConnection();

    public String isLogin(String id, String pwd) 
    {
        String result="";
        try
        {
            conn = dbcp.getConnection();
            String sql = "SELECT COUNT(*) FROM users_1 " +
                         "WHERE u_id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count=rs.getInt(1);
            rs.close();
            if (count==0)
            {
                result="NOID";
            }
            else
            {
                sql="SELECT u_password,u_name,u_profile,u_birthday,u_gender,u_email,u_address,u_question,u_answer "
                        +"WHERE u_id=?";
                ps=conn.prepareStatement(sql);
                ps.setString(1, id);
                rs=ps.executeQuery();
                rs.next();
                String u_password=rs.getString(1);
                String u_name=rs.getString(2);
                String u_profile=rs.getString(3);
                Date u_birthday=rs.getDate(4);
                String u_gender=rs.getString(5);
                String u_email=rs.getString(6);
                String u_address=rs.getString(7);
                String u_question=rs.getString(8);
                String u_answer=rs.getString(9);
                rs.close();
                
                if (u_password.equals(pwd)) 
                {
                    return u_name+"|"+u_profile+"|"+u_birthday+"|"+u_gender+"|"+u_email+"|"
                            +u_address+"|"+u_question+"|"+u_answer;
                }
                else
                {
                    result="NOPWD";
                }
            }
        } catch (Exception ex) 
        {
            ex.printStackTrace();
        } 
        finally 
        {
            dbcp.disConnection(conn, ps);
        }
        return result;
    }
}