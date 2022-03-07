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

	public List<FavoriteVO> favListData(String uid) {
		List<FavoriteVO> list = new ArrayList<FavoriteVO>();

		try {
			conn = dbcp.getConnection();

			String sql = "SELECT /*+ INDEX_DESC(favorite_1 fav_id_pk)*/ fav_id, u_id, ad_id, "
					+ "(SELECT ad_title FROM ad_1 WHERE	ad_id = favorite_1.ad_id) as ad_title, "
					+ "(SELECT c_logo FROM company_1 WHERE c_id = favorite_1.ad_id) as c_logo " + "FROM favorite_1	"
					+ "WHERE u_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				FavoriteVO vo = new FavoriteVO();
				vo.setFav_id(rs.getInt(1));
				vo.setU_id(rs.getString(2));
				vo.setAd_id(rs.getInt(3));
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

	public int favCountData(FavoriteVO vo) {
		int count = 0;

		try {
			conn = dbcp.getConnection();
			String sql = "SELECT COUNT(*) FROM favorite_1 WHERE u_id = ? " + "AND ad_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getU_id());
			ps.setInt(2, vo.getAd_id());
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

}
