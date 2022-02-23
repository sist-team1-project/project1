package sist.com.dao;

import java.sql.*;
import java.util.*;

import sist.com.vo.*;

public class PostDAO {

    private Connection conn;
    private PreparedStatement ps;
    private DBCPConnection dbcp = new DBCPConnection();
    
    public List<PostVO> getFreeboardListByVisits() {
        List<PostVO> list = new ArrayList<PostVO>();
        try {
            conn = dbcp.getConnection();
            String sql = "SELECT post_id,post_category,post_title "
                    + "FROM (SELECT post_id,post_category,post_title,rownum as num "
                    + "FROM (SELECT post_id,post_category,post_title,post_content,post_visits,post_date "
                    + "FROM post_1 "
                    + "WHERE SYSDATE-8 < TO_DATE(post_date,'YYYY-MM-DD HH24:MI:SS')"
                    + "ORDER BY post_visits))"
                    + "WHERE num BETWEEN 1 AND 5";
            ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PostVO vo = new PostVO();
                vo.setPost_id(rs.getInt(1));
                vo.setPost_category(rs.getString(2));
                vo.setPost_title(rs.getString(3));
                list.add(vo);
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
        return list;
    }
    
    public List<PostVO> getFreeboardList(int page) {
        List<PostVO> list = new ArrayList<PostVO>();
        try {
            conn = dbcp.getConnection();
            String sql = "SELECT post_id,post_category,post_title,u_id,post_date,post_visits "
                    + "FROM (SELECT post_id,post_category,post_title,u_id,post_date,post_visits,rownum as num "
                    + "FROM (SELECT /*+ INDEX_DESC(post_1 post_id_pk_1)*/post_id,post_category,post_title,u_id,post_date,post_visits "
                    + "FROM post_1 "
                    + "WHERE board_id=1)) "
                    + "WHERE num BETWEEN ? AND ?";
            
            int rowSize=10;
            int start = (rowSize * page) - (rowSize) - 1;
            int end = rowSize * page;
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1,  start);
            ps.setInt(2,  end);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PostVO vo = new PostVO();
                vo.setPost_id(rs.getInt(1));
                vo.setPost_category(rs.getString(2));
                vo.setPost_title(rs.getString(3));
                vo.setU_id(rs.getString(4));
                vo.setPost_date(rs.getString(5));
                vo.setPost_visits(rs.getInt(6));
                list.add(vo);
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
        return list;
    }
}