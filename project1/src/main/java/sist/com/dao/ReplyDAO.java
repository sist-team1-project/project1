package sist.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sist.com.vo.*;

public class ReplyDAO {

    private Connection conn;
    private PreparedStatement ps;
    private DBCPConnection dbcp = new DBCPConnection();
    
    // 게시물 - 댓글 목록
    public List<ReplyVO> replyList(int id) {
        
        List<ReplyVO> list = new ArrayList<ReplyVO>();
        try {
            conn = dbcp.getConnection();
            String sql = "SELECT reply_id,board_id,u_id,reply_content,reply_date "
                    + "FROM (SELECT reply_id,board_id,u_id,reply_content,reply_date,rownum as num "
                    + "FROM (SELECT /*+ INDEX_DESC(reply_1 reply_id_pk_1)*/reply_id,board_id,u_id,reply_content,reply_date "
                    + "FROM reply_1 "
                    + "WHERE board_id=?))";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ReplyVO vo = new ReplyVO();
                vo.setReply_id(rs.getInt(1));
                vo.setBoard_id(rs.getInt(2));
                vo.setU_id(rs.getString(3));
                vo.setReply_content(rs.getString(4));
                vo.setReply_date(rs.getDate(5));
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
    
    // 자유게시판 - 댓글 수
    public int replyCount(int id) {
        int count = 0;
        try {
            conn = dbcp.getConnection();
            String sql = "SELECT COUNT(*) "
                    + "FROM reply_1 "
                    + "WHERE board_id=?";
            

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
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
    
    // 게시물 - 댓글 달기
    public void replyInsert(ReplyVO vo) {
        try {
            conn = dbcp.getConnection();
            
            String sql = "INSERT INTO reply_1(reply_id,board_id,u_id,reply_content) "
                    + "VALUES(reply_id_seq_1.NEXTVAL,?,?,?)";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, vo.getBoard_id());
            ps.setString(2, vo.getU_id());
            ps.setString(3, vo.getReply_content());
            
            ps.executeUpdate(); // commit

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
    }
    
    // 게시물 - 댓글 수정 / 삭제 유효성 검사
    public boolean checkUser(String uid, int rid) {
        Boolean check = false;
        try {
            conn = dbcp.getConnection();
            
            String sql = "SELECT u_id FROM reply_1 "
                    + "WHERE reply_id=?";
            
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
    
    // 게시물 - 댓글 삭제
    public void replyDelete(int id) {
        try {
            conn = dbcp.getConnection();
            
            String sql = "DELETE FROM reply_1 "
                    + "WHERE reply_id=?";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            ps.executeUpdate(); // commit

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
    }
    
    // 게시물 - 댓글 수정
    public void replyUpdate(int id, String content) {
        try {
            conn = dbcp.getConnection();
            
            String sql = "UPDATE reply_1 "
                    + "SET reply_content=? "
                    + "WHERE reply_id=?";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, content);
            ps.setInt(2, id);
            
            ps.executeUpdate(); // commit

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
    }
}