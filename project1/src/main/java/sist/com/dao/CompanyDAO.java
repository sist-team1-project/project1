package sist.com.dao;

import java.sql.*;
import java.util.*;

import sist.com.vo.*;

public class CompanyDAO {

    private Connection conn;
    private PreparedStatement ps;
    private DBCPConnection dbcp = new DBCPConnection();

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
    
    public List<CompanyVO> getBigCompanyList() {
        List<CompanyVO> list = new ArrayList<CompanyVO>();
        try {
            conn = dbcp.getConnection();
            String sql = "SELECT c_id, c_logo, c_name FROM ("
                    + "SELECT c_id, c_logo, c_name, rownum as num "
                    + "FROM (SELECT c_id, c_logo, c_name "
                    + "FROM company_1 "
                    + "WHERE c_size='대기업' AND NOT c_logo= 'https://work.go.kr/images/common/none_imglogo.gif'"
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

    public CompanyVO getCompanyDetail(int id) {
        CompanyVO vo = new CompanyVO();
        try {
            conn = dbcp.getConnection();
            String sql = "SELECT c_id,c_logo,c_name,c_address,c_industry,c_size,c_visits FROM company_1 WHERE c_id=?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                vo.setC_id(rs.getInt(1));
                vo.setC_logo(rs.getString(2));
                vo.setC_name(rs.getString(3));
                vo.setC_address(rs.getString(4));
                vo.setC_industry(rs.getString(5));
                vo.setC_size(rs.getString(6));
                vo.setC_visits(rs.getInt(7));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbcp.disConnection(conn, ps);
        }
        return vo;
    }
}