package sist.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import sist.com.vo.CompanyVO;

public class CompanySearchDAO {
	private Connection conn;
	private PreparedStatement ps;

	public void getConnection() {
		try {
			Context init = new InitialContext();
			Context c = (Context) init.lookup("java://comp//env");
			DataSource ds = (DataSource) c.lookup("jdbc/oracle");
			conn = ds.getConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void disConnection() {
		try {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
		}
	}

	public List<CompanyVO> companySearchListData(String name) {
		List<CompanyVO> list = new ArrayList<CompanyVO>();
		try {
			getConnection();
			String sql = "SELECT c_name, c_logo, c_industry, c_address "
					+ "FROM (SELECT c_name, c_logo, c_industry, rownum as num "
					+ "FROM (SELECT c_name, c_logo, c_industry " + "FROM company_1 " + "ORDER BY c_visits DESC)) "
					+ "WHERE c_name LIKE '%||?||%";

			ps = conn.prepareStatement(sql);
			ps.setString(1, name);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CompanyVO vo = new CompanyVO();
				vo.setC_name(rs.getString(1));
				vo.setC_logo(rs.getString(2));
				vo.setC_industry(rs.getString(3));
				String address = rs.getString(4);
				vo.setC_address(address);
				list.add(vo);
			}
			rs.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return list;
	}
}
