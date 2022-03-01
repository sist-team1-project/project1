package sist.com.dao;

import java.sql.*;
import java.util.*;

import sist.com.vo.*;

public class CompanyDAO {

    private Connection conn;
    private PreparedStatement ps;
    private DBCPConnection dbcp = new DBCPConnection();

    // 메인 - 베스트 기업
    public List<CompanyVO> bestCompanyList() {
        List<CompanyVO> list = new ArrayList<CompanyVO>();
        try {
            conn = dbcp.getConnection();
            String sql = "SELECT c_id, c_logo, c_name FROM ("
                    + "SELECT c_id, c_logo, c_name, rownum as num "
                    + "FROM (SELECT c_id, c_logo, c_name "
                    + "FROM company_1 "
                    + "ORDER BY c_visits DESC)) "
                    + "WHERE num BETWEEN 1 AND 9";

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
    
    // 메인 - 대기업 리스트
    public List<CompanyVO> bigCompanyList() {
        List<CompanyVO> list = new ArrayList<CompanyVO>();
        try {
            conn = dbcp.getConnection();
            String sql = "SELECT c_id, c_logo, c_name FROM ("
                    + "SELECT c_id, c_logo, c_name, rownum as num "
                    + "FROM (SELECT c_id, c_logo, c_name "
                    + "FROM company_1 "
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
    
    // 기업 - 기업 상세정보
    public CompanyVO companyDetail(int id) {
        CompanyVO vo = new CompanyVO();
        try {
            conn = dbcp.getConnection();
            
            String sql = "UPDATE company_1 "
                    + "SET c_visits=c_visits+1 "
                    + "WHERE c_id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,  id);
            
            ps.executeUpdate();
            
            sql = "SELECT * "
                    + "FROM company_1 "
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
            conn = dbcp.getConnection();
            String sql = "SELECT c_id, c_name,c_logo,c_industry,c_address "
                    + "FROM (SELECT c_id, c_name,c_logo,c_industry,c_address,rownum as num "
                    + "FROM (SELECT c_id, c_name,c_logo,c_industry,c_address "
                    + "FROM company_1 "
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