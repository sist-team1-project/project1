package com.sist.dao;

import java.sql.*;
import java.util.*;

public class FavoriteDAO {

	private Connection conn;
	private PreparedStatement ps;
	private DBCPConnection dbcp = new DBCPConnection();
	
	public int favInsert(String uid, int adid) {
	    
	    int fid = 0;
	    try {
            conn = dbcp.getConnection(conn);
            
            String sql = "INSERT INTO favorite VALUES(favorite_id_seq.NEXTVAL, ?, ?)";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, uid);
            ps.setInt(2, adid);
            ps.executeUpdate();
            
            sql = "SELECT fav_id FROM favorite WHERE u_id=? "
                    + "AND ad_id=?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, uid);
            ps.setInt(2, adid);

            ResultSet rs = ps.executeQuery();
            rs.next();
            fid = rs.getInt(1);
            rs.close();
            
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			dbcp.disConnection(conn, ps);
		}
	    return fid;
	}
	
    public void favDelete(int fid) {
        try {
            conn = dbcp.getConnection(conn);
            
            String sql = "DELETE FROM favorite "
                    + "WHERE fav_id=?";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, fid);
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
    }
    
    // 마이페이지 - 즐겨찾기 관리
	public List<Integer> favListData(String uid) {
		List<Integer> list = new ArrayList<Integer>();

		try {
			conn = dbcp.getConnection(conn);

			String sql = "SELECT /*+ INDEX_ASC(favorite favorite_id_pk)*/ fav_id "
			        + "FROM favorite "
			        + "WHERE u_id=?";
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
		int fid = 0;
		try {
			conn = dbcp.getConnection(conn);
			String sql = "SELECT COUNT(*) FROM favorite "
			        + "WHERE u_id=? "
			        + "AND ad_id=?";

			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setInt(2, adid);

			ResultSet rs = ps.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			rs.close();
			
			if (count == 0) {
				fid = 0;
			} else {
				sql = "SELECT fav_id FROM favorite WHERE u_id=? " + "AND ad_id=?";

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
