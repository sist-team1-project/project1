package sist.com.dao;

import java.sql.*;
import java.util.*;

import sist.com.vo.*;

public class UsersDAO {

    private Connection conn;
    private PreparedStatement ps;
    private DBCPConnection dbcp = new DBCPConnection();

    public UsersVO isLogin(String id, String pwd) {
        UsersVO result = null;
        try {
            conn = dbcp.getConnection();
            String sql = "SELECT * " + "FROM users_1 " + "WHERE u_id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            boolean isExist = rs.next();

            rs.close();
            if (isExist) {
                String password = rs.getString(2);

                if (password.equals(pwd)) {
                    UsersVO usersVo = new UsersVO();
                    usersVo.setU_id(rs.getString(1));
                    usersVo.setU_name(rs.getString(3));
                    usersVo.setU_profile(rs.getString(4));
                    usersVo.setU_birthday(rs.getDate(5));
                    usersVo.setU_gender(rs.getString(6));
                    usersVo.setU_email(rs.getString(7));
                    usersVo.setU_address(rs.getString(8));
                    usersVo.setU_question(rs.getString(9));
                    usersVo.setU_answer(rs.getString(10));
                    result = usersVo;
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