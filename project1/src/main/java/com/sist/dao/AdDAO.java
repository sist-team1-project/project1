package com.sist.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import com.sist.vo.*;

public class AdDAO {

	private Connection conn;
	private PreparedStatement ps;
	private DBCPConnection dbcp = new DBCPConnection();

	// 메인 - Best 공고
	public List<Map<String,Object>> bestAdList() {
	    List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			conn = dbcp.getConnection(conn);
			
			String sql = "SELECT c_name,c_id,ad_id,ad_title,ad_end,ad_we,ad_education,ad_workplace "
			        + "FROM (SELECT c_name,c_id,ad_id,ad_title,ad_end,ad_we,ad_education,ad_workplace,rownum AS num "
			        + "FROM (SELECT c_name,c_id,ad_id,ad_title,ad_end,ad_we,ad_education,ad_workplace "
			        + "FROM (SELECT c.c_name,c.c_id,a.ad_id,a.ad_title,a.ad_end,a.ad_we,a.ad_education,a.ad_workplace,a.ad_visits FROM ad a, company c "
			        + "WHERE a.c_id = c.c_id(+) AND (ad_end>=TO_CHAR(SYSDATE,'YYYY-MM-DD') OR ad_end IS NULL)) "
			        + "ORDER BY ad_visits DESC)) "
			        + "WHERE num BETWEEN 1 AND 12";
			ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
            while (rs.next()) {
			    Map map = new HashMap();
			    
			    map.put("c_name", rs.getString(1));
			    map.put("c_id", rs.getInt(2));
			    map.put("ad_id", rs.getInt(3));
			    map.put("ad_title", rs.getString(4));
			    
				String ad_end = rs.getString(5);
				if (ad_end == null) {
					ad_end = "채용시까지";
				}
				map.put("ad_end", ad_end);
				
				String we = rs.getString(6);
				if (we.startsWith("경력")) {
					we = we.substring(0, we.indexOf(" ("));
				}
				map.put("ad_we", we);
				map.put("ad_education", rs.getString(7));
				
				String addr = rs.getString(8);
				addr = addr.substring(0, addr.indexOf(" ", addr.indexOf(" ") + 1));
				map.put("ad_workplace", addr);
				
				list.add(map);
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
	public List<Map<String,Object>> adEndList() {
	    List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        
		try {
			conn = dbcp.getConnection(conn);
			String sql = "SELECT c_name,c_id,ad_id,ad_title,ad_end,ad_we,ad_education,ad_workplace "
                    + "FROM (SELECT c_name,c_id,ad_id,ad_title,ad_end,ad_we,ad_education,ad_workplace,rownum AS num "
                    + "FROM (SELECT c_name,c_id,ad_id,ad_title,ad_end,ad_we,ad_education,ad_workplace "
                    + "FROM (SELECT c.c_name,c.c_id,a.ad_id,a.ad_title,a.ad_end,a.ad_we,a.ad_education,a.ad_workplace,a.ad_visits FROM ad a, company c "
                    + "WHERE a.c_id = c.c_id(+) AND (ad_end>=TO_CHAR(SYSDATE,'YYYY-MM-DD'))) "
                    + "ORDER BY ad_end ASC)) "
                    + "WHERE num BETWEEN 1 AND 6";
			
			ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			    Map map = new HashMap();
			    
			    map.put("c_name", rs.getString(1));
                map.put("c_id", rs.getInt(2));
                map.put("ad_id", rs.getInt(3));
                map.put("ad_title", rs.getString(4));
                
                String ad_end = rs.getString(5);
                if (ad_end == null) {
                    ad_end = "채용시까지";
                }
                map.put("ad_end", ad_end);
                
                String we = rs.getString(6);
                if (we.startsWith("경력")) {
                    we = we.substring(0, we.indexOf(" ("));
                }
                map.put("ad_we", we);
                map.put("ad_education", rs.getString(7));
                
                String addr = rs.getString(8);
                addr = addr.substring(0, addr.indexOf(" ", addr.indexOf(" ") + 1));
                map.put("ad_workplace", addr);
                
                list.add(map);
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
			conn = dbcp.getConnection(conn);
			String sql = "";
			sql += "SELECT ad_id,ad_title,ad_end,ad_workplace,c_id ";
			sql += "FROM ad ";
			sql += "where ad_id in (";
				for (int i = 0; i < cookieList.size(); i++) {
					if (i != cookieList.size()-1) {
					    sql += "'" + cookieList.get(i)+ "',";
					} else {
						sql += "'" + cookieList.get(i)+ "'";
					}
				} 
			sql += ") ";
			sql += "order by case ad_id";
			
			for(int i = 0; i < cookieList.size(); i++) {
			    if(!cookieList.get(i).equals("")) {
			        sql += " when " + cookieList.get(i) + " then " + (i+1);
			    }
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
			conn = dbcp.getConnection(conn);
			
			String sql = "UPDATE ad "
			        + "SET ad_visits = ad_visits+1 "
			        + "WHERE ad_id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
			
			sql = "SELECT * "
			        + "FROM ad "
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
			conn = dbcp.getConnection(conn);
			String sql = "SELECT ad_id,c_id,ad_title,ad_end "
			        + "FROM ad "
			        + "WHERE c_id=? AND (ad_end>=TO_CHAR(SYSDATE,'YYYY-MM-DD') OR ad_end IS NULL)";
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
    public List<Map<String,Object>> adSearchList(int page, String keyword, String address, String we, String edu, String size, String worktype, String qual, String lang, String date1, String date2) {
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
       try {
            conn = dbcp.getConnection(conn);
            
            int rowSize = 10;
            int start = (rowSize * page) - (rowSize) + 1;
            int end = rowSize * page;
            
            String sql = "SELECT c_name,ad_id,ad_title,ad_we,ad_education,ad_workplace,ad_wage,ad_worktype,ad_end "
                    + "FROM (SELECT c_name,ad_id,ad_title,ad_we,ad_education,ad_workplace,ad_wage,ad_worktype,ad_end,rownum As num "
                    + "FROM (SELECT c_name,ad_id,ad_title,ad_we,ad_education,ad_workplace,ad_wage,ad_worktype,ad_end "
                    + "FROM (SELECT c.c_name,ad_id,ad_title,ad_we,ad_education,ad_workplace,ad_wage,ad_worktype,ad_qualification,ad_language,ad_end,c_size FROM ad a, company c "
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
                sql += "AND (ad_end >= ? OR ad_end IS NULL) ";
            } else {
                sql += "AND (ad_end BETWEEN ? AND ? OR ad_end IS NULL) ";
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
                Map map = new HashMap();
                map.put("c_name", rs.getString(1));
                map.put("ad_id", rs.getInt(2));
                map.put("ad_title", rs.getString(3));
                map.put("ad_we", rs.getString(4));
                map.put("ad_education", rs.getString(5));
                map.put("ad_workplace", rs.getString(6));
                map.put("ad_wage", rs.getString(7));
                map.put("ad_worktype", rs.getString(8));
                                
                String ad_end = rs.getString(9);
                
                if (ad_end == null) {
                    ad_end = "채용시까지";
                }
                map.put("ad_end",ad_end);
                
                list.add(map);
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
            conn = dbcp.getConnection(conn);
            
            String sql = "SELECT CEIL(COUNT(*)/10.0) "
                    + "FROM (SELECT c_id,ad_id,ad_title,ad_we,ad_education,ad_workplace,ad_wage,ad_worktype,ad_end "
                    + "FROM (SELECT a.c_id,ad_id,ad_title,ad_we,ad_education,ad_workplace,ad_wage,ad_worktype,ad_qualification,ad_language,ad_end,c_size FROM ad a, company c "
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
                sql += "AND (ad_end >= ? OR ad_end IS NULL))";
            } else {
                sql += "AND (ad_end BETWEEN ? AND ? OR ad_end IS NULL))";
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
            conn = dbcp.getConnection(conn);
            String sql = "SELECT ad_id,ad_title,ad_end "
                    + "FROM (SELECT ad_id,ad_title,ad_end,ROW_NUMBER() OVER(PARTITION BY ad_end ORDER BY ad_visits DESC) as num "
                    + "FROM ad "
                    + "WHERE ad_end LIKE ?||'%') "
                    + "WHERE num BETWEEN 1 AND 5";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            
            ResultSet rs = ps.executeQuery();
            rs.next();
            while (rs.next()) {
                AdVO vo = new AdVO();
                vo.setAd_id(rs.getInt(1));
                vo.setAd_title(rs.getString(2));
                vo.setAd_end(rs.getString(3));
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