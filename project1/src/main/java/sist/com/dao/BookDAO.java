package sist.com.dao;

import java.sql.*;
import java.util.*;

import sist.com.vo.*;

public class BookDAO {
    
    private Connection conn;
    private PreparedStatement ps;
    private DBCPConnection dbcp = new DBCPConnection();
    
    // 공고 추천 수험서
    public List<BookVO> recommended_books(String qualification) {
        List<BookVO> list = new ArrayList<BookVO>();

        try {
            conn = dbcp.getConnection();
            String sql = "SELECT book_id, book_title, book_img, book_sold, book_link "
                    + "FROM (SELECT book_id, book_title, book_img, book_sold, book_link, rownum as num "
                    + "FROM (SELECT book_id, book_title, book_img, book_sold, book_link "
                    + "FROM textbook_1 "
                    + "WHERE book_title LIKE '%'||?||'%' "
                    + "ORDER BY book_sold DESC)) "
                    + "WHERE num BETWEEN 1 and 6";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, qualification);

            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                BookVO vo = new BookVO();
                vo.setBook_id(rs.getInt(1));
                vo.setBook_title(rs.getString(2));
                vo.setBook_img(rs.getString(3));
                vo.setBook_sold(rs.getInt(4));
                vo.setBook_link(rs.getString(5));
                list.add(vo);
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }

        return list;
    }
}
