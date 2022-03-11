package sist.com.model;

import sist.com.controller.RequestMapping;
import java.util.*;
import javax.servlet.http.*;

import sist.com.vo.*;
import sist.com.dao.*;

public class AdModel {

	@RequestMapping("ad/ad.do")
	public String ad_page(HttpServletRequest request, HttpServletResponse response) {
		
		String adid = request.getParameter("adid");

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("id");

		AdDAO a = new AdDAO();
		AdVO ad = a.adDetail(Integer.parseInt(adid));
		
		CompanyDAO c = new CompanyDAO();
		CompanyVO company = c.companyDetail(ad.getC_id());
		
		BookDAO b = new BookDAO();
		List<List<BookVO>> booksList = new ArrayList<List<BookVO>>();
		List<String> qualList = new ArrayList<String>();

		String qualifications = ad.getAd_qualification();
		StringTokenizer st = new StringTokenizer(qualifications, ",");

		while (st.hasMoreTokens()) {
			String qualification = st.nextToken();
			qualList.add(qualification);
			List<BookVO> books = b.recommended_books(qualification);
			booksList.add(books);
		}

		int favorite = 0;
		FavoriteDAO fdao = new FavoriteDAO();
		FavoriteVO fvo = new FavoriteVO();
		
		favorite = fdao.favCount(uid, Integer.parseInt(adid));

		request.setAttribute("ad", ad);
		request.setAttribute("company", company);
		request.setAttribute("booksList", booksList);
		request.setAttribute("qualList", qualList);
		request.setAttribute("favorite", favorite);
		request.setAttribute("main_jsp", "../ad/ad.jsp");
		return "../main/main.jsp";
	}
}
