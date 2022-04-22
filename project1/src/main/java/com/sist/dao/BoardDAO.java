package com.sist.dao;

import java.sql.*;
import java.util.*;

import com.sist.vo.*;

public class BoardDAO {

    private Connection conn;
    private PreparedStatement ps;
    private DBCPConnection dbcp = new DBCPConnection();

    // 메인 - 자유게시판 조회수 순
    public List<BoardVO> freeboardListByVisits() {
        List<BoardVO> list = new ArrayList<BoardVO>();
        try {
            conn = dbcp.getConnection(conn);
            String sql = "SELECT board_id,board_category,board_title "
                    + "FROM (SELECT board_id,board_category,board_title,rownum as num "
                    + "FROM (SELECT /*+ INDEX_DESC(board_1 board_id_pk_1)*/ board_id,board_category,board_title "
                    + "FROM board_1 "
                    + "WHERE SYSDATE-8 < board_date "
                    + "ORDER BY board_visits DESC)) "
                    + "WHERE num BETWEEN 1 AND 5";
            ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setBoard_id(rs.getInt(1));
                vo.setBoard_category(rs.getString(2));
                vo.setBoard_title(rs.getString(3));
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
    
    // 메인 - 자유게시판 댓글 순
    public List<BoardVO> freeboardListByReply() {
        List<BoardVO> list = new ArrayList<BoardVO>();
        try {
            conn = dbcp.getConnection(conn);
            String sql = "SELECT board_id,board_category,board_title "
                    + "FROM (SELECT board_id,board_category,board_title,rownum AS num "
                    + "FROM (SELECT b.board_id,b.board_category,b.board_title,NVL(count,0) AS count "
                    + "FROM (SELECT /*+ INDEX_DESC(board_1 board_id_pk_1)*/ board_id,board_category,board_title FROM board_1 WHERE SYSDATE-8 < board_date) b, "
                    + "(SELECT board_id,count(reply_id) AS count FROM reply_1 GROUP BY board_id) r "
                    + "WHERE b.board_id=r.board_id(+)) "
                    + "ORDER BY count DESC) "
                    + "WHERE num BETWEEN 1 AND 5";
            ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setBoard_id(rs.getInt(1));
                vo.setBoard_category(rs.getString(2));
                vo.setBoard_title(rs.getString(3));
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
    
    // 자유게시판 - 게시물 목록
    public List<BoardVO> freeboardList(int page, String topic, String search, int no) {
        List<BoardVO> list = new ArrayList<BoardVO>();
        try {
            conn = dbcp.getConnection(conn);

            String sql = "";
            sql = "SELECT board_id,board_category,board_title,u_id,board_date,board_visits "
                    + "FROM (SELECT board_id,board_category,board_title,u_id,board_date,board_visits,rownum as num "
                    + "FROM (SELECT /*+ INDEX_DESC(board_1 board_id_pk_1)*/board_id,board_category,board_title,u_id,board_date,board_visits "
                    + "FROM board_1 "
                    + "WHERE " + topic +" LIKE '%'||?||'%')) "
                    + "WHERE num BETWEEN ? AND ?";
            
            int rowSize = 10;
            int start = (rowSize * page) - (rowSize) + 1;
            int end = rowSize * page;
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, search);
            ps.setInt(2, start);
            ps.setInt(3, end);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setBoard_id(rs.getInt(1));
                vo.setBoard_category(rs.getString(2));
                vo.setBoard_title(rs.getString(3));
                vo.setU_id(rs.getString(4));
                vo.setBoard_date(rs.getDate(5));
                vo.setBoard_visits(rs.getInt(6));
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

    public List<BoardVO> freeboardMyList(int page, String uid, int no) {
        List<BoardVO> list = new ArrayList<BoardVO>();
        try {
            conn = dbcp.getConnection(conn);
            
            String sql = "";
            if (no == 1) { // 내 게시물
                sql = "SELECT board_id,board_category,board_title,u_id,board_date,board_visits "
                        + "FROM (SELECT board_id,board_category,board_title,u_id,board_date,board_visits,rownum as num "
                        + "FROM (SELECT /*+ INDEX_DESC(board_1 board_id_pk_1)*/board_id,board_category,board_title,u_id,board_date,board_visits "
                        + "FROM board_1 " + "WHERE u_id=?)) " + "WHERE num BETWEEN ? AND ?";
            } else if (no == 2) { // 내 댓글
                sql = "SELECT board_id,board_category,board_title,u_id,board_date,board_visits "
                        + "FROM (SELECT board_id,board_category,board_title,u_id,board_date,board_visits,rownum as num "
                        + "FROM (SELECT DISTINCT /*+ INDEX_DESC(board_1 board_id_pk_1)*/b.board_id,b.board_category,b.board_title,b.u_id,b.board_date,b.board_visits "
                        + "FROM board_1 b, reply_1 r " + "WHERE b.board_id=r.board_id AND r.u_id=?)) "
                        + "WHERE num BETWEEN ? AND ?";
                
            }
            
            int rowSize = 10;
            int start = (rowSize * page) - (rowSize) + 1;
            int end = rowSize * page;
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, uid);
            ps.setInt(2, start);
            ps.setInt(3, end);
            
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setBoard_id(rs.getInt(1));
                vo.setBoard_category(rs.getString(2));
                vo.setBoard_title(rs.getString(3));
                vo.setU_id(rs.getString(4));
                vo.setBoard_date(rs.getDate(5));
                vo.setBoard_visits(rs.getInt(6));
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

    // 자유게시판 - 페이징 - 총 페이지 수
    public int freeboardTotalPage(String id, int no, String topic, String search) {
        int total = 0;
        try {
            conn = dbcp.getConnection(conn);
            String sql = "";
            if (no == 1) { // 자유게시판
                sql = "SELECT CEIL(COUNT(*)/10.0) " + "FROM board_1 " + "WHERE " + topic +" LIKE '%'||?||'%'";
                ps = conn.prepareStatement(sql);
                ps.setString(1, search);
            } else if (no == 2) { // 내 게시물
                sql = "SELECT CEIL(COUNT(*)/10.0) " + "FROM board_1 " + "WHERE u_id=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, id);
            } else if (no == 3) { // 내 댓글
                sql = "SELECT CEIL(COUNT(*)/10.0) " + "FROM (SELECT DISTINCT b.board_id " + "FROM board_1 b, reply_1 r "
                        + "WHERE b.board_id=r.board_id AND r.u_id=?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, id);
            }
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

    // 자유게시판 - 게시물 정보 & 조회수 증가
    public BoardVO freeboardDetail(int id) {
        BoardVO vo = new BoardVO();

        try {
            conn = dbcp.getConnection(conn);

            // 조회수 증가
            String sql = "UPDATE board_1 "
                    + "SET board_visits=board_visits+1 "
                    + "WHERE board_id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate(); // commit

            // 게시물 정보
            sql = "SELECT * "
                + "FROM board_1 "
                + "WHERE board_id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            vo.setBoard_id(rs.getInt(1));
            vo.setU_id(rs.getString(2));
            vo.setBoard_category(rs.getString(3));
            vo.setBoard_title(rs.getString(4));
            vo.setBoard_content(rs.getString(5));
            vo.setBoard_visits(rs.getInt(6));
            vo.setBoard_date(rs.getDate(7));

            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
        return vo;
    }
    
    // 자유게시판 - 게시물 작성
    public void freeboardInsert(BoardVO vo) {
        try {
            conn = dbcp.getConnection(conn);

            String sql = "INSERT INTO board_1(board_id,u_id,board_category,board_title,board_content) "
                    + "VALUES(board_id_seq_1.NEXTVAL,?,?,?,?)";

            ps = conn.prepareStatement(sql);
            ps.setString(1, vo.getU_id());
            ps.setString(2, vo.getBoard_category());
            ps.setString(3, vo.getBoard_title());
            ps.setString(4, vo.getBoard_content());

            ps.executeUpdate(); // commit

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
    }
    
    // 자유게시판 - 수정 창 정보
    public BoardVO freeboardUpdateDetail(int id) {
        
        BoardVO vo = new BoardVO();

        try {
            conn = dbcp.getConnection(conn);
            
            String sql = "SELECT board_id,board_category,board_title,board_content "
                    + "FROM board_1 "
                    + "WHERE board_id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            vo.setBoard_id(rs.getInt(1));
            vo.setBoard_category(rs.getString(2));
            vo.setBoard_title(rs.getString(3));
            String content = rs.getString(4).replace("<br>", "\n");
            vo.setBoard_content(content);

            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
        return vo;
    }
    
    // 자유게시판 - 수정 / 삭제 유효성 검사
    public boolean checkUser(String uid, int bid) {
        Boolean check = false;
        try {
            conn = dbcp.getConnection(conn);
            
            String sql = "SELECT u_id FROM board_1 "
                    + "WHERE board_id=?";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, bid);
            
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
    
    // 자유게시판 - 게시물 수정
    public void freeboardUpdate(BoardVO vo) {
        
        try {
            conn = dbcp.getConnection(conn);
            
            String sql = "UPDATE board_1 SET board_category=?,board_title=?,board_content=? "
                + "WHERE board_id=?";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, vo.getBoard_category());
            ps.setString(2, vo.getBoard_title());
            ps.setString(3, vo.getBoard_content());
            ps.setInt(4, vo.getBoard_id());
            ps.executeUpdate();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
    }
    
    // 자유게시판 게시물 삭제
    public void freeboardDelete(int bid) {
        
        try {
            conn = dbcp.getConnection(conn);
            
            String sql = "DELETE FROM board_1 WHERE board_id=?";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, bid);
            ps.executeUpdate();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
    }
}