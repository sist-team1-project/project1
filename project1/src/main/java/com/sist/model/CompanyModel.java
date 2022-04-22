package com.sist.model;

import com.sist.controller.*;
import java.util.*;
import javax.servlet.http.*;

import com.sist.dao.*;
import com.sist.vo.*;

public class CompanyModel {

	@RequestMapping("company/company.do")
	public String company_page(HttpServletRequest request, HttpServletResponse response) {
		String cid = request.getParameter("cid");
		String page = request.getParameter("page");
		if (page == null)
			page = "1";

		int curPage = Integer.parseInt(page);

		CompanyDAO cdao = new CompanyDAO();
		cdao.updateCompanyVisits(Integer.parseInt(cid)); // 조회수 증가
		CompanyVO company = cdao.companyDetail(Integer.parseInt(cid)); // 기업 정보

		AdDAO a = new AdDAO();
		List<AdVO> adlist = a.companyAdList(Integer.parseInt(cid)); // 진행중인 공고

		ReviewDAO dao = new ReviewDAO();
		List<ReviewVO> review = dao.reviewList(Integer.parseInt(cid), Integer.parseInt(page)); // 리뷰 목록

		int totalPage = dao.reviewTotalPage(Integer.parseInt(cid));

		// 페이지
		final int BLOCK = 5;
		int startPage = ((curPage - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((curPage - 1) / BLOCK * BLOCK) + BLOCK;
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		
		request.setAttribute("curPage", curPage);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("cid", cid);
		
		request.setAttribute("company", company);
		request.setAttribute("adlist", adlist);
		request.setAttribute("review", review);

		request.setAttribute("main_jsp", "../company/company.jsp");

		return "../main/main.jsp";
	}
}