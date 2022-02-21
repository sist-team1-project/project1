package sist.com.dao;

import java.sql.*;
import java.util.*;
import javax.sql.*; // DataSource
import javax.naming.*; // Context 

import sist.com.vo.*;

public class ReviewDAO {

    private Connection conn;
    private PreparedStatement ps;

    public void getConnection() {
        try {
            Context init = new InitialContext();
            Context c = (Context) init.lookup("java://comp//env");
            DataSource ds = (DataSource) c.lookup("jdbc/oracle");
            conn = ds.getConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void disConnection() {
        try {
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        } catch (Exception ex) {
        }
    }

    public String bestCompanyReviewList(int id) {
        String review = "";
        try {
            getConnection();
            String sql = "SELECT review_content FROM ("
                    + "SELECT c_id, review_content "
                    + "FROM (SELECT ROW_NUMBER() OVER(PARTITION BY c_id ORDER BY r.review_date DESC) as rnum, c_id, review_content, review_date FROM  review_1 r) "
                    + "WHERE rnum=1) "
                    + "WHERE c_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
                
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                review = rs.getString(1);
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            disConnection();
        }
        return review;
    }
    
    public List<ReviewVO> getReviewDetail(int id) {
        List<ReviewVO> list = new ArrayList<ReviewVO>();
        try {
            getConnection();
            String sql = "SELECT review_id,u_id,c_id,review_content,review_goodbad,review_job,review_date FROM review_1 WHERE c_id=?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ReviewVO vo = new ReviewVO();
                vo.setReview_id(rs.getInt(1));
                vo.setU_id(rs.getString(2));
                vo.setC_id(rs.getInt(3));
                vo.setReview_content(rs.getString(4));
                vo.setReview_goodbad(rs.getInt(5));
                vo.setReview_job(rs.getString(6));
                vo.setReview_date(rs.getString(7));
                list.add(vo);
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            disConnection();
        }
        return list;
    }
}