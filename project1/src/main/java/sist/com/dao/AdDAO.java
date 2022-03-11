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
					+ "FROM (SELECT ad_id,ad_title,ad_end,ad_we,ad_education,ad_workplace,c_id FROM ad_1 WHERE SYSDATE-1 < TO_DATE(ad_end) OR ad_end IS NULL ORDER BY ad_visits DESC)) "
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
	
	// 메인 - 최근 조회한 공고
	public List<AdVO> adCookieList(List<String> cookieList) {
		List<AdVO> list = new ArrayList<AdVO>();
		try {
			conn = dbcp.getConnection();
			String sql = "";
			sql += "SELECT ad_id,ad_title,ad_end,ad_workplace,c_id ";
			sql += "FROM ad_1 ";
			sql += "where ad_id in (";
				for (int i = 0; i < cookieList.size(); i++) {
					if (i != cookieList.size()-1) {
						sql += "'" + cookieList.get(i)+ "',";
					} else {
						sql += "'" + cookieList.get(i)+ "'";
					}
				} 
			sql += ")";
			sql += "order by case ad_id ";
			
			for(int i = 0; i < cookieList.size(); i++) {
					sql += "when " + cookieList.get(i) + " then " + (i+1);
			}
			sql += " else " + cookieList.size() + " end ";
			
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
				
				String addr = rs.getString(4);
				addr = addr.substring(0, addr.indexOf(" ", addr.indexOf(" ") + 1));
				vo.setAd_workplace(addr);
				vo.setC_id(rs.getInt(5));
				
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
			
			String sql = "UPDATE ad_1 "
			        + "SET ad_visits = ad_visits+1 "
			        + "WHERE ad_id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
			
			sql = "SELECT * "
			        + "FROM ad_1 "
			        + "WHERE ad_id = ?";
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
			String sql = "SELECT ad_id,c_id,ad_title,ad_end "
			        + "FROM ad_1 "
			        + "WHERE c_id=? AND (SYSDATE-1 < TO_DATE(ad_end) OR ad_end IS NULL)";
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
	
	// 공고 검색 - 검색 결과
    public List<AdVO> adSearchList(int page, String keyword, String address, String we, String edu, String size, String worktype, String qual, String lang, String date1, String date2) {
        List<AdVO> list = new ArrayList<AdVO>();
       try {
            conn = dbcp.getConnection();
            
            int rowSize = 10;
            int start = (rowSize * page) - (rowSize) + 1;
            int end = rowSize * page;
            
            String sql = "SELECT c_id,ad_id,ad_title,ad_we,ad_education,ad_workplace,ad_wage,ad_worktype,ad_end "
                    + "FROM (SELECT c_id,ad_id,ad_title,ad_we,ad_education,ad_workplace,ad_wage,ad_worktype,ad_end,rownum As num "
                    + "FROM (SELECT c_id,ad_id,ad_title,ad_we,ad_education,ad_workplace,ad_wage,ad_worktype,ad_end "
                    + "FROM (SELECT a.c_id,ad_id,ad_title,ad_we,ad_education,ad_workplace,ad_wage,ad_worktype,ad_qualification,ad_language,ad_end,c_size FROM ad_1 a, company_1 c "
                    + "WHERE a.c_id = c.c_id(+)) "
                    + "WHERE ad_title LIKE '%'||?||'%' "
                    + "AND ad_workplace LIKE '%'||?||'%' "
                    + "AND regexp_like(ad_we, ?) "
                    + "AND regexp_like(ad_education, ?) "
                    + "AND regexp_like(c_size, ?) "
                    + "AND regexp_like(ad_worktype, ?) ";
            if(qual.equals("")) {
                sql += "AND ad_qualification LIKE '%'||?||'%' ";
            } else {
                sql += "AND regexp_like(ad_qualification, ?) ";
            }
            if(lang.equals("")) {
                sql += "AND ad_language LIKE '%'||?||'%' ";
            } else {
                sql += "AND regexp_like(ad_language, ?) ";
            }
            if(date2.equals("")) {
                sql += "AND (TO_DATE(ad_end) >= TO_DATE(?) OR ad_end IS NULL) ";
            } else {
                sql += "AND (TO_DATE(ad_end) BETWEEN TO_DATE(?) AND TO_DATE(?) OR ad_end IS NULL) ";
            }
            sql += "ORDER BY ad_end ASC)) "
                + "WHERE num BETWEEN ? AND ?";
            
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, keyword);
            ps.setString(2, address);
            ps.setString(3, we);
            ps.setString(4, edu);
            ps.setString(5, size);
            ps.setString(6, worktype);
            ps.setString(7, qual);
            ps.setString(8, lang);
            if(date2.equals("")) {
                ps.setString(9, date1);
                ps.setInt(10, start);
                ps.setInt(11, end);
            } else {
                ps.setString(9, date1);
                ps.setString(10, date2);
                ps.setInt(11, start);
                ps.setInt(12, end);
            }
            
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AdVO avo = new AdVO();
                avo.setC_id(rs.getInt(1));
                avo.setAd_id(rs.getInt(2));
                avo.setAd_title(rs.getString(3));
                avo.setAd_we(rs.getString(4));
                avo.setAd_education(rs.getString(5));
                avo.setAd_workplace(rs.getString(6));
                avo.setAd_wage(rs.getString(7));
                avo.setAd_worktype(rs.getString(8));
                String ad_end = rs.getString(9);
                
                if (ad_end == null) {
                    ad_end = "채용시까지";
                }
                avo.setAd_end(ad_end);
                list.add(avo);
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
        return list;
    }
    
    // 공고 검색 - 검색 결과 총 페이지
    public int adSearchListTotalPage(String keyword, String address, String we, String edu, String size, String worktype, String qual, String lang, String date1, String date2) {
        int total = 0;
        try {
            conn = dbcp.getConnection();
            
            String sql = "SELECT CEIL(COUNT(*)/10.0) "
                    + "FROM (SELECT c_id,ad_id,ad_title,ad_we,ad_education,ad_workplace,ad_wage,ad_worktype,ad_end "
                    + "FROM (SELECT a.c_id,ad_id,ad_title,ad_we,ad_education,ad_workplace,ad_wage,ad_worktype,ad_qualification,ad_language,ad_end,c_size FROM ad_1 a, company_1 c "
                    + "WHERE a.c_id = c.c_id(+)) "
                    + "WHERE ad_title LIKE '%'||?||'%' "
                    + "AND ad_workplace LIKE '%'||?||'%' "
                    + "AND regexp_like(ad_we, ?)"
                    + "AND regexp_like(ad_education, ?) "
                    + "AND regexp_like(c_size, ?) "
                    + "AND regexp_like(ad_worktype, ?) ";
            if(qual.equals("")) {
                sql += "AND ad_qualification LIKE '%'||?||'%' ";
            } else {
                sql += "AND regexp_like(ad_qualification, ?) ";
            }
            if(lang.equals("")) {
                sql += "AND ad_language LIKE '%'||?||'%' ";
            } else {
                sql += "AND regexp_like(ad_language, ?) ";
            }
            if(date2.equals("")) {
                sql += "AND (TO_DATE(ad_end) >= TO_DATE(?) OR ad_end IS NULL))";
            } else {
                sql += "AND (TO_DATE(ad_end) BETWEEN TO_DATE(?) AND TO_DATE(?) OR ad_end IS NULL))";
            }
            
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, keyword);
            ps.setString(2, address);
            ps.setString(3, we);
            ps.setString(4, edu);
            ps.setString(5, size);
            ps.setString(6, worktype);
            ps.setString(7, qual);
            ps.setString(8, lang);
            if(date2.equals("")) {
                ps.setString(9, date1);
            } else {
                ps.setString(9, date1);
                ps.setString(10, date2);
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
    
    // 채용 달력 - 날짜별 공고
    public List<AdVO> adByDate(String date) {
        ArrayList<AdVO> list = new ArrayList<AdVO>();
        try {
            conn = dbcp.getConnection();
            
            String sql = "SELECT ad_id,ad_title "
                    + "FROM (SELECT ad_id,ad_title,rownum AS num "
                    + "FROM (SELECT ad_id,ad_title "
                    + "FROM ad_1 "
                    + "WHERE ad_end=to_date(?,'yyyy-mm-dd') "
                    + "ORDER BY ad_visits)) "
                    + "WHERE num BETWEEN 1 AND 5";
                        
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, date);
            
            ResultSet rs = ps.executeQuery();
            rs.next();
            while (rs.next()) {
                AdVO vo = new AdVO();
                vo.setAd_id(rs.getInt(1));
                vo.setAd_title(rs.getString(2));
                
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
}