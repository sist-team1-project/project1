package sist.com.dao;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import sist.com.vo.*;

public class FavoriteDAO {

	private Connection conn;
	private PreparedStatement ps;
	private DBCPConnection dbcp = new DBCPConnection();

	public void favInsert(FavoriteVO vo) {

		try {
			conn = dbcp.getConnection();

			String sql = "INSERT INTO favorite_1 VALUES(favorite_id_seq_1.NEXTVAL, ?, ?)";
			ps = conn.prepareStatement(sql);

			ps.setString(1, vo.getU_id());
			ps.setInt(2, vo.getAd_id());

			ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			dbcp.disConnection(conn, ps);
		}

	}

	public int favData(String uid, int adid) {
		int fid = 0;

		try {
			conn = dbcp.getConnection();

			String sql = "SELECT fav_id FROM favorite_1 WHERE u_id = ? AND ad_id = ?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, uid);
			ps.setInt(2, adid);
			
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			fid = rs.getInt(1);

			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbcp.disConnection(conn, ps);
		}

		return fid;
	}

	public List<Integer> favListData(String uid) {
		List<Integer> list = new ArrayList<Integer>();

		try {
			conn = dbcp.getConnection();

			String sql = "SELECT ad_id " + "FROM favorite_1	" + "WHERE u_id = ? " + "ORDER BY fav_id";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(rs.getInt(1));
			}

			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			dbcp.disConnection(conn, ps);
		}
		return list;
	}

	public int favCount(String uid, int adid) {
		int count = 0;

		try {
			conn = dbcp.getConnection();
			String sql = "SELECT COUNT(*) FROM favorite_1 WHERE u_id = ? " + "AND ad_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setInt(2, adid);
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

	public void favDelete(int fid) {
		try {
			conn = dbcp.getConnection();

			String sql = "DELETE FROM favorite_1 WHERE " + "fav_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fid);

			ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			dbcp.disConnection(conn, ps);
		}
	}
	

    public int favCount2(String uid, int adid) {
        int fid = 0;
        try {
            conn = dbcp.getConnection();
            String sql = "SELECT COUNT(*) FROM favorite_1 WHERE u_id = ? " + "AND ad_id = ?";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, uid);
            ps.setInt(2, adid);
            
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            
            
            
            if(count == 0) {
                fid = 0;
            } else {
                conn = dbcp.getConnection();
                sql = "SELECT fav_id FROM favorite_1 WHERE u_id = ? " + "AND ad_id = ?";
                
                ps = conn.prepareStatement(sql);
                ps.setString(1, uid);
                ps.setInt(2, adid);
                
                rs = ps.executeQuery();
                rs.next();
                fid = rs.getInt(1);
                rs.close();
            }
            

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
        return fid;
    }
}
