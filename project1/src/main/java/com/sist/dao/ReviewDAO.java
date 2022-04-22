package com.sist.dao;

import java.sql.*;
import java.util.*;

import com.sist.vo.*;

public class ReviewDAO {

    private Connection conn;
    private PreparedStatement ps;
    private DBCPConnection dbcp = new DBCPConnection();

    // 메인 - 베스트 기업의 리뷰
    public String bestCompanyReviewList(int id) {
        String review = "";
        try {
            conn = dbcp.getConnection(conn);
            String sql = "SELECT review_content "
                    + "FROM (SELECT review_content,rownum as num "
                    + "FROM (SELECT /*+ INDEX_DESC(review review_id_pk)*/ review_content "
                    + "FROM review "
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
    
    public List<ReviewVO> reviewList(int id, int page) {
        List<ReviewVO> list = new ArrayList<ReviewVO>();
        try {
            conn = dbcp.getConnection(conn);
            
            int rowSize = 5;
            int start = (rowSize * page) - (rowSize) + 1;
            int end = rowSize * page;
            
            String sql = "SELECT /*+ INDEX_DESC(review review_id_pk)*/ review_id,u_id,c_id,review_content,review_goodbad,review_job,review_date "
                    + "FROM (SELECT review_id,u_id,c_id,review_content,review_goodbad,review_job,review_date, rownum AS num from review where c_id = ?) "
                    + "WHERE num BETWEEN ? AND ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, start);
            ps.setInt(3, end);

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
            conn = dbcp.getConnection(conn);
            String sql = "SELECT count(*) "
                    + "FROM review "
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
    
    // 추가 - 면접 후기 입력
    public void reviewInsert(ReviewVO vo) {
    	
    	try {
    		conn = dbcp.getConnection(conn);
    		String sql="INSERT INTO review(review_id,u_id,c_id,review_content,review_goodbad,review_job) "
    				+"VALUES(review_id_seq.NEXTVAL,?,?,?,?,?)";
    		
    		ps = conn.prepareStatement(sql);
    		
    		ps.setString(1, vo.getU_id());
            ps.setInt(2, vo.getC_id());
            ps.setString(3, vo.getReview_content());
    		ps.setInt(4, vo.getReview_goodbad());
    		ps.setString(5, vo.getReview_job());
    		
    		
    		ps.executeUpdate();
    		
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		dbcp.disConnection(conn, ps);
    	}
    }
    

    // 기업정보 - 리뷰 수정 / 삭제 유효성 검사
    public boolean checkUser(String uid, int rid) {
        Boolean check = false;
        try {
            conn = dbcp.getConnection(conn);
            
            String sql = "SELECT u_id FROM review "
                    + "WHERE review_id=?";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, rid);
            
            ResultSet rs = ps.executeQuery();
            rs.next();
            
            String db_uid = rs.getString(1);
            
            rs.close();
            
            if (db_uid.equals(uid)) {
                check = true;
            } else {
                check = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
        return check;
    }
    
    // 추가 - 면접 후기 삭제
    public void reviewDelete(int id) {
        
        try {
            conn = dbcp.getConnection(conn);
            String sql="DELETE FROM review "
                    + "WHERE review_id=?";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
    }
    

    public void reviewUpdate(int id, int goodbad, String job, String content) {
        try {
            conn = dbcp.getConnection(conn);
            
            String sql = "UPDATE review "
                    + "SET review_goodbad=?, review_job=?, review_content=? "
                    + "WHERE review_id=?";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, goodbad);
            ps.setString(2, job);
            ps.setString(3, content);
            ps.setInt(4, id);
            
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
    }
    
    // 리뷰 - 페이징 - 총 페이지 수
    public int reviewTotalPage(int cid) {
        int total = 0;
        try {
            conn = dbcp.getConnection(conn);
            String sql = "SELECT CEIL(COUNT(*)/5.0) " 
            		+ "FROM review "
            		+ "where c_id =?";
                
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cid);
            
            ResultSet rs = ps.executeQuery();
            rs.next();
            total = rs.getInt(1);
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
        return total;
    }
}