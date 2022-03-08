package sist.com.model;

import sist.com.vo.*;

import java.util.*;

import javax.servlet.http.*;

import sist.com.controller.RequestMapping;
import sist.com.dao.*;

public class FavoriteModel {

	@RequestMapping("favorite/insert.do")
	public void favorite_insert(HttpServletRequest request, HttpServletResponse response) {
		String adid = request.getParameter("adid");

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("id");

		FavoriteVO vo = new FavoriteVO();
		vo.setU_id(uid);
		vo.setAd_id(Integer.parseInt(adid));

		FavoriteDAO dao = new FavoriteDAO();
		dao.favInsert(vo);
		
		//return "../main/main.do";

	}

	@RequestMapping("users/favorite.do")
	public String favorite_List(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("id");
		
		String u_id = request.getParameter(uid);

		// 공고 번호, 공고정보
		FavoriteDAO dao = new FavoriteDAO();
		FavoriteVO fvo;
		// 즐찾 공고번호 리스트
		List<Integer> flist = dao.favListData(u_id);
		
		// 공고번호들의 공고정보리스트, 회사정보리스트
		List<AdVO> adList = new ArrayList<AdVO>();
		List<String> cList = new ArrayList<String>();

		AdDAO a = new AdDAO();
		AdVO vo;
		CompanyDAO c = new CompanyDAO();
		
		for (int i : flist) {
			vo = a.adDetail(i);
			adList.add(vo);
			cList.add(c.companyName(vo.getC_id()));
			
		}
		
//		int count = 0;
//		if (uid == null)
//			count = 0;
//		else
//			count = dao.favCountData(vo);
		
//		request.setAttribute("count", count);

		request.setAttribute("adList", adList);
		request.setAttribute("c_name", cList);
		request.setAttribute("main_jsp", "../users/favorite_Edit.jsp");
		return "../main/main.jsp";

	}

	@RequestMapping("favorite/delete.do")
	public String favorite_delete(HttpServletRequest request, HttpServletResponse response) {
		String fid = request.getParameter("fid");
		FavoriteDAO dao = new FavoriteDAO();
		dao.favDelete(Integer.parseInt(fid));

		return "redirect:../user/favorite.do";
	}

}
