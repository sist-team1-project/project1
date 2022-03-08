package sist.com.dao;

import java.sql.*;
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

    public int idCheck(String id) {
        
        int count = 0;
        try {
            conn = dbcp.getConnection();
            
            String sql = "SELECT COUNT(*) FROM users_1 "
                    + "WHERE u_id=?";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            
            ResultSet rs = ps.executeQuery();
            rs.next();
            count = rs.getInt(1);
            
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
        return count;
    }

    public void join(UsersVO vo) {
        
        try {
            conn = dbcp.getConnection();
            
            String sql = "INSERT INTO users_1 VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, vo.getU_id());
            ps.setString(2, vo.getU_password());
            ps.setString(3, vo.getU_name());
            ps.setString(4, vo.getU_birthday());
            ps.setString(5, vo.getU_gender());
            ps.setString(6, vo.getU_email());
            ps.setString(7, vo.getU_post());
            ps.setString(8, vo.getU_address1());
            ps.setString(9, vo.getU_address2());
            ps.setString(10, vo.getU_question());
            ps.setString(11, vo.getU_answer());
            ps.executeUpdate();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
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
            vo.setU_birthday(rs.getString(4));
            vo.setU_gender(rs.getString(5));
            vo.setU_email(rs.getString(6));
            vo.setU_post(rs.getString(7));
            vo.setU_address1(rs.getString(8));
            vo.setU_address2(rs.getString(9));
            vo.setU_question(rs.getString(10));
            vo.setU_answer(rs.getString(11));
            rs.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
        return vo;
    }

    public String idFind(String name, String email) {
        
        String result = "";
        try {
            conn = dbcp.getConnection();
            
            String sql = "SELECT COUNT(*) "
                    + "FROM users_1 "
                    + "WHERE u_name=? AND u_email=?";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            
            if (count == 0) {
                result = "no";
            } else {
                sql = "SELECT RPAD(SUBSTR(u_id,1,4),LENGTH(u_id),'*') "
                    + "FROM users_1 "
                    + "WHERE u_name=? AND u_email=?";
                
                ps = conn.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, email);
                
                rs = ps.executeQuery();
                rs.next();
                result = rs.getString(1);
                rs.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
        return result;
    }
    
    public String pwFind1(String name, String id) {
        
        String result = "";
        try {
            conn = dbcp.getConnection();
            
            String sql = "SELECT COUNT(*) "
                    + "FROM users_1 "
                    + "WHERE u_name=? AND u_id=?";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, id);
            
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            if (count == 0) {
                result = "no";
            } else {
                sql = "SELECT u_question "
                    + "FROM users_1 "
                    + "WHERE u_name=? AND u_id=?";
                
                ps = conn.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, id);
                
                rs = ps.executeQuery();
                
                rs.next();
                
                result = rs.getString(1);
                
                rs.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
        return result;
    }
    
    public String pwFind2(String id, String answer) {
        
        String result = "";
        try {
            conn = dbcp.getConnection();
            
            String sql = "SELECT COUNT(*) "
                    + "FROM users_1 "
                    + "WHERE u_id=? AND u_answer=?";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, answer);
            
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            
            if (count == 0) {
                result = "no";
            } else {
                result = "yes";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
        return result;
    }

    public void pwUpdate(String id, String password) {
        try {
            conn = dbcp.getConnection();
            
            String sql = "UPDATE users_1 "
                    + "SET u_password=? "
                    + "WHERE u_id=?";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, id);
            ps.executeUpdate();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
    }
    
    public UsersVO userUpdate(String id) {
        
        UsersVO vo = new UsersVO();
        try {
            conn = dbcp.getConnection();
            
            String sql = "SELECT u_id,u_name,u_gender,u_birthday,u_email,u_post,u_address1,"
                    + "NVL(u_address2,' ')"
                    + "FROM users_1 "
                    + "WHERE u_id=?";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            
            ResultSet rs = ps.executeQuery();
            rs.next();
            vo.setU_id(rs.getString(1));
            vo.setU_name(rs.getString(2));
            vo.setU_gender(rs.getString(3));
            vo.setU_birthday(rs.getString(4));
            vo.setU_email(rs.getString(5));
            vo.setU_post(rs.getString(6));
            vo.setU_address1(rs.getString(7));
            vo.setU_address2(rs.getString(8));
            rs.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
        return vo;
    }

    public String userDelete(String pwd, String id) {
        
        String result = "";
        try {
            conn = dbcp.getConnection();
            
            String sql = "SELECT u_password FROM users_1 "
                        + "WHERE u_id=?";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            
            ResultSet rs = ps.executeQuery();
            rs.next();
            String db_pwd = rs.getString(1);
            
            rs.close();

            if (db_pwd.equals(pwd)) {
                result = "yes";
                sql = "DELETE FROM users_1 "
                    + "WHERE u_id=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, id);
                ps.executeUpdate();
            } else {
                result = "no";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
        return result;
    }
}