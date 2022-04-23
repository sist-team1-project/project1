package com.sist.dao;

import java.sql.*;
import java.util.*;

import com.sist.vo.*;

public class CompanyDAO {

    private Connection conn;
    private PreparedStatement ps;
    private DBCPConnection dbcp = new DBCPConnection();

    // 메인 - 베스트 기업
    public List<Map<String,Object>> bestCompanyList() {
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        try {
            conn = dbcp.getConnection(conn);
            String sql = "SELECT c_id,c_logo,c_name,review_content "
                    + "FROM (SELECT c_id,c_logo,c_name,review_content,rownum AS num2 "
                    + "FROM (SELECT c_id,c_logo,c_name,review_content "
                    + "FROM (SELECT c_id,c_logo,c_name,review_content,c_visits,ROW_NUMBER() OVER(PARTITION BY c_id ORDER BY review_id DESC) AS num "
                    + "FROM (SELECT c.c_id,c.c_logo,c.c_name,c.c_visits,r.review_id,r.review_content "
                    + "FROM (SELECT c_id,c_logo,c_name,c_visits FROM company) c, (SELECT c_id,review_id,review_content FROM review) r "
                    + "WHERE c.c_id=r.c_id(+))) "
                    + "WHERE num=1 "
                    + "ORDER BY c_visits DESC)) "
                    + "WHERE num2 BETWEEN 1 AND 9";

            ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map map = new HashMap();
                map.put("c_id",rs.getInt(1));
                map.put("c_logo",rs.getString(2));
                map.put("c_name",rs.getString(3));
                map.put("review_content",rs.getString(4));
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
    
    // 메인 - 대기업 리스트
    public List<CompanyVO> bigCompanyList() {
        List<CompanyVO> list = new ArrayList<CompanyVO>();
        try {
            conn = dbcp.getConnection(conn);
            String sql = "SELECT c_id, c_logo, c_name FROM ("
                    + "SELECT c_id, c_logo, c_name, rownum as num "
                    + "FROM (SELECT c_id, c_logo, c_name "
                    + "FROM company "
                    + "WHERE c_size='대기업' AND NOT c_logo='https://work.go.kr/images/common/none_imglogo.gif'"
                    + "ORDER BY c_visits DESC)) "
                    + "WHERE num BETWEEN 1 AND 12";

            ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CompanyVO vo = new CompanyVO();
                vo.setC_id(rs.getInt(1));
                vo.setC_logo(rs.getString(2));
                vo.setC_name(rs.getString(3));
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
    
    // 메인 - 공고 회사 이름 가져오기
    public String companyName(int id) {
        String name = "";
        try {
            conn = dbcp.getConnection(conn);
            
            String sql = "SELECT c_name "
                    + "FROM company "
                    + "WHERE c_id=?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            rs.next();
            name = rs.getString(1);
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
        return name;
    }
    
    public CompanyVO updateCompanyVisits(int id) {
        CompanyVO vo = new CompanyVO();
        try {
            conn = dbcp.getConnection(conn);
            
            String sql = "UPDATE company "
                    + "SET c_visits=c_visits+1 "
                    + "WHERE c_id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,  id);
            
            ps.executeUpdate();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
        return vo;
    }
    
    // 기업 - 기업 상세정보
    public CompanyVO companyDetail(int id) {
        CompanyVO vo = new CompanyVO();
        try {
            conn = dbcp.getConnection(conn);
                    
            String sql = "SELECT * "
                    + "FROM company "
                    + "WHERE c_id=?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            rs.next();
            vo.setC_id(rs.getInt(1));
            vo.setC_logo(rs.getString(2));
            vo.setC_name(rs.getString(3));
            
            String addr = rs.getString(4);
            addr = addr.substring(addr.indexOf(")") + 2);
            vo.setC_address(addr);
                
            vo.setC_industry(rs.getString(5));
            vo.setC_size(rs.getString(6));
            vo.setC_visits(rs.getInt(7));
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
        return vo;
    }
    
    public List<CompanyVO> companySearchList(String name) {
        List<CompanyVO> list = new ArrayList<CompanyVO>();
        try {
            conn = dbcp.getConnection(conn);
            String sql = "SELECT c_id, c_name,c_logo,c_industry,c_address "
                    + "FROM (SELECT c_id, c_name,c_logo,c_industry,c_address,rownum as num "
                    + "FROM (SELECT c_id, c_name,c_logo,c_industry,c_address "
                    + "FROM company "
                    + "ORDER BY c_visits DESC)) "
                    + "WHERE c_name LIKE '%'||?||'%'";

            ps = conn.prepareStatement(sql);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CompanyVO vo = new CompanyVO();
                vo.setC_id(rs.getInt(1));
                vo.setC_name(rs.getString(2));
                vo.setC_logo(rs.getString(3));
                vo.setC_industry(rs.getString(4));
                String addr = rs.getString(5);
                addr = addr.substring(addr.indexOf(")") + 2);
                addr = addr.substring(0,addr.indexOf(" ", addr.indexOf(" ")+1));
                vo.setC_address(addr);
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