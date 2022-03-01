package sist.com.dao;

import java.sql.*;
import java.util.*;

import sist.com.vo.*;

public class BoardDAO {

    private Connection conn;
    private PreparedStatement ps;
    private DBCPConnection dbcp = new DBCPConnection();
    
    // 메인 - 자유게시판 조회수 순
    public List<BoardVO> freeboardListByVisits() {
        List<BoardVO> list = new ArrayList<BoardVO>();
        try {
            conn = dbcp.getConnection();
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
    
    // 자유게시판 - 게시물 목록
    public List<BoardVO> freeboardList(int page) {
        List<BoardVO> list = new ArrayList<BoardVO>();
        try {
            conn = dbcp.getConnection();
            String sql = "SELECT board_id,board_category,board_title,u_id,board_date,board_visits "
                    + "FROM (SELECT board_id,board_category,board_title,u_id,board_date,board_visits,rownum as num "
                    + "FROM (SELECT /*+ INDEX_DESC(board_1 board_id_pk_1)*/board_id,board_category,board_title,u_id,board_date,board_visits "
                    + "FROM board_1)) "
                    + "WHERE num BETWEEN ? AND ?";
            
            int rowSize = 10;
            int start = (rowSize * page) - (rowSize) + 1;
            int end = rowSize * page;
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1,  start);
            ps.setInt(2,  end);
            
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
    public int freeboardTotalPage() {
        int total = 0;
        try {
            conn = dbcp.getConnection();
            String sql = "SELECT CEIL(COUNT(*)/12.0) "
                    + "FROM board_1";
            ps = conn.prepareStatement(sql);
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
            conn = dbcp.getConnection();
            
            // 조회수 증가
            String sql = "UPDATE board_1 "
                    + "SET board_visits=board_visits+1 "
                    + "WHERE board_id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,  id);
            
            ps.executeUpdate(); // commit
            
            // 게시물 정보
            sql = "SELECT * "
                    + "FROM board_1 "
                    + "WHERE board_id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,  id);
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
            conn = dbcp.getConnection();
            
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
}