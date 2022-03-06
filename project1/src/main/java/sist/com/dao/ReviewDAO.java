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
                    + "FROM (SELECT /*+ INDEX_DESC(review_1 review_id_pk_1)*/ review_content "
                    + "FROM review_1 "
                    + "WHERE c_id=?)) "
                    + "WHERE num=1";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
                
            ResultSet rs = ps.executeQuery();
            
            try {
                rs.next();
                review = rs.getString(1);
            } catch (Exception ex) {
                review = "유저들의 리뷰를 기다리고 있습니다.";
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
        return review;
    }
    
    public List<ReviewVO> reviewList(int id) {
        List<ReviewVO> list = new ArrayList<ReviewVO>();
        try {
            conn = dbcp.getConnection();
            String sql = "SELECT /*+ INDEX_DESC(review_1 review_id_pk_1)*/ review_id,u_id,c_id,review_content,review_goodbad,review_job,review_date "
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
    
    // 검색 - 회사 리뷰 개수
    public int countReview(int id) {
        int count = 0;
        try {
            conn = dbcp.getConnection();
            String sql = "SELECT count(*) "
                    + "FROM review_1 "
                    + "WHERE c_id=?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            count = rs.getInt(1);
            
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
        return count;
    }
    
    // 추가 - 면접 후기 제출
    public void reviewInsert(ReviewVO vo) {
    	
    	try {
    		conn = dbcp.getConnection();
    		String sql="INSERT INTO review_1 VALUES (review_id,u_id,c_id,review_job,review_date) "
    				+"VALUES(review_id_seq_nextval,?,?,?)";
    		
    		ps = conn.prepareStatement(sql);
    		
    		ps.setInt(1, vo.getReview_goodbad());
    		ps.setString(2, vo.getReview_job());
    		ps.setString(3, vo.getReview_content());
    		
    		ps.executeUpdate();
    		
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		dbcp.disConnection(conn, ps);
    	}
    }
}