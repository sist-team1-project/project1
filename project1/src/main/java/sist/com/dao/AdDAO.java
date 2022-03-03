package sist.com.dao;

import java.sql.*;
import java.util.*;

import sist.com.vo.*;

public class AdDAO {

	private Connection conn;
	private PreparedStatement ps;
	private DBCPConnection dbcp = new DBCPConnection();

	// 메인 - Best 공고
	public List<AdVO> bestAdList() {
		List<AdVO> list = new ArrayList<AdVO>();
		try {
			conn = dbcp.getConnection();
			String sql = "SELECT ad_id,ad_title,ad_end,ad_we,ad_education,ad_workplace,c_id "
					+ "FROM (SELECT ad_id,ad_title,ad_end,ad_we,ad_education,ad_workplace,c_id,rownum as num "
					+ "FROM (SELECT ad_id,ad_title,ad_end,ad_we,ad_education,ad_workplace,c_id FROM  ad_1 WHERE SYSDATE-1 < TO_DATE(ad_end) OR ad_end IS NULL ORDER BY ad_visits DESC)) "
					+ "WHERE num BETWEEN 1 AND 12";
			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AdVO vo = new AdVO();
				vo.setAd_id(rs.getInt(1));
				vo.setAd_title(rs.getString(2));

				String ad_end = rs.getString(3);
				if (ad_end == null) {
					ad_end = "채용시까지";
				}
				vo.setAd_end(ad_end);

				String we = rs.getString(4);
				if (we.startsWith("경력")) {
					we = we.substring(0, we.indexOf(" ("));
				}
				vo.setAd_we(we);

				vo.setAd_education(rs.getString(5));

				String addr = rs.getString(6);
				addr = addr.substring(0, addr.indexOf(" ", addr.indexOf(" ") + 1));
				vo.setAd_workplace(addr);
				vo.setC_id(rs.getInt(7));
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

	// 메인 - 마감 임박 공고
	public List<AdVO> adEndList() {
		List<AdVO> list = new ArrayList<AdVO>();
		try {
			conn = dbcp.getConnection();
			String sql = "SELECT ad_id,ad_title,ad_end,ad_we,ad_education,ad_workplace,c_id "
					+ "FROM (SELECT ad_id,ad_title,ad_end,ad_we,ad_education,ad_workplace,c_id,rownum as num "
					+ "FROM (SELECT ad_id,ad_title,ad_end,ad_we,ad_education,ad_workplace,c_id FROM  ad_1 WHERE SYSDATE-1 < TO_DATE(ad_end) ORDER BY ad_end ASC)) "
					+ "WHERE num BETWEEN 1 AND 6";
			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AdVO vo = new AdVO();
				vo.setAd_id(rs.getInt(1));
				vo.setAd_title(rs.getString(2));

				String ad_end = rs.getString(3);
				if (ad_end == null) {
					ad_end = "채용시까지";
				}
				vo.setAd_end(ad_end);

				String we = rs.getString(4);
				if (we.startsWith("경력")) {
					we = we.substring(0, we.indexOf(" ("));
				}
				vo.setAd_we(we);

				vo.setAd_education(rs.getString(5));

				String addr = rs.getString(6);
				addr = addr.substring(0, addr.indexOf(" ", addr.indexOf(" ") + 1));
				vo.setAd_workplace(addr);
				vo.setC_id(rs.getInt(7));
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

	// 공고 - 공고 상세정보
	public AdVO adDetail(int id) {
		AdVO vo = new AdVO();
		try {
			conn = dbcp.getConnection();
			String sql = "SELECT * " + "FROM ad_1 " + "WHERE ad_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setAd_id(rs.getInt(1));
			vo.setC_id(rs.getInt(2));
			vo.setAd_title(rs.getString(3));
			vo.setAd_content(rs.getString(4));
			vo.setAd_we(rs.getString(5));
			vo.setAd_education(rs.getString(6));
			vo.setAd_qualification(rs.getString(7));
			vo.setAd_language(rs.getString(8));
			vo.setAd_major(rs.getString(9));
			vo.setAd_wage(rs.getString(10));
			vo.setAd_workhours(rs.getString(11));
			vo.setAd_worktype(rs.getString(12));
			vo.setAd_workplace(rs.getString(13));

			String ad_end = rs.getString(14);
			if (ad_end == null) {
				ad_end = "채용시까지";
			}
			vo.setAd_end(ad_end);

			vo.setAd_end(ad_end);
			vo.setAd_visits(rs.getInt(15));

			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			dbcp.disConnection(conn, ps);
		}
		return vo;
	}

	// 기업 - 진행중인 채용 공고
	public List<AdVO> companyAdList(int id) {
		List<AdVO> list = new ArrayList<AdVO>();
		try {
			conn = dbcp.getConnection();
			String sql = "SELECT ad_id,c_id,ad_title,ad_end " + "FROM ad_1 " + "WHERE c_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AdVO vo = new AdVO();
				vo.setAd_id(rs.getInt(1));
				vo.setC_id(rs.getInt(2));
				vo.setAd_title(rs.getString(3));

				String ad_end = rs.getString(4);
				if (ad_end == null) {
					ad_end = "채용시까지";
				}
				vo.setAd_end(ad_end);

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

	// 채용정보 상세검색
	public List<AdVO> adSearchList(String search) {
		List<AdVO> list = new ArrayList<AdVO>();
		try {
			conn = dbcp.getConnection();
			String sql = "SELECT * " + "FROM ad_1 "
					+ "WHERE ad_title LIKE '%'||?||'%' OR ad_workplace LIKE '%'||?||'%' OR ad_content OR '%'||?||'%'";
			ps = conn.prepareStatement(sql);
			ps.setString(1, search);
			ps.setString(2, search);
			ps.setString(3, search);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AdVO vo = new AdVO();
				vo.setAd_id(rs.getInt(1));
				vo.setC_id(rs.getInt(2));
				vo.setAd_title(rs.getString(3));
				vo.setAd_content(rs.getString(4));
				vo.setAd_we(rs.getString(5));
				vo.setAd_education(rs.getString(6));
				vo.setAd_qualification(rs.getString(7));
				vo.setAd_language(rs.getString(8));
				vo.setAd_major(rs.getString(9));
				vo.setAd_wage(rs.getString(10));
				vo.setAd_workhours(rs.getString(11));
				vo.setAd_worktype(rs.getString(12));
				vo.setAd_workplace(rs.getString(13));
				String ad_end = rs.getString(14);
				if (ad_end == null) {
					ad_end = "채용시까지";
				}
				vo.setAd_end(ad_end);
				vo.setAd_visits(rs.getInt(15));

				rs.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			dbcp.disConnection(conn, ps);
		}
		return list;
	}

}