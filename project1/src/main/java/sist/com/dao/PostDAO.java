package sist.com.dao;

import java.sql.*;
import java.util.*;

import sist.com.vo.*;

public class PostDAO {

    private Connection conn;
    private PreparedStatement ps;
    private DBCPConnection dbcp = new DBCPConnection();
    
    public List<PostVO> freeBoardListByVisits() {
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
}