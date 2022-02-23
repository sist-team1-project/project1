package sist.com.dao;

import java.sql.*;
import java.util.*;

import sist.com.vo.*;

public class ReviewDAO {

    private Connection conn;
    private PreparedStatement ps;
    private DBCPConnection dbcp = new DBCPConnection();

    // 메인 - 베스트 기업의 리뷰
    public String bestCompanyReviewList(int id) {
        String review = "";
        try {
            conn = dbcp.getConnection();
            String sql = "SELECT review_content "
                    + "FROM (SELECT review_content,rownum as num "
                    + "FROM (SELECT review_content "
                    + "FROM review_1 "
                    + "WHERE c_id=? "
                    + "ORDER BY review_date DESC)) "
                    + "WHERE num=1";
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
            dbcp.disConnection(conn, ps);
        }
        return review;
    }
    
    public List<ReviewVO> getReviewDetail(int id) {
        List<ReviewVO> list = new ArrayList<ReviewVO>();
        try {
            conn = dbcp.getConnection();
            String sql = "SELECT review_id,u_id,c_id,review_content,review_goodbad,review_job,review_date "
                    + "FROM review_1 "
                    + "WHERE c_id=?";

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
            dbcp.disConnection(conn, ps);
        }
        return list;
    }
}