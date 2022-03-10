package sist.com.model;

import sist.com.vo.*;

import java.util.*;

import javax.servlet.http.*;

import sist.com.controller.RequestMapping;
import sist.com.dao.*;

public class FavoriteModel {

	@RequestMapping("favorite/insert.do")
	public String favorite_insert(HttpServletRequest request, HttpServletResponse response) {
		String adid = request.getParameter("adid");
		String cid = request.getParameter("cid");

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("id");

		FavoriteVO vo = new FavoriteVO();
		vo.setU_id(uid);
		vo.setAd_id(Integer.parseInt(adid));

		FavoriteDAO dao = new FavoriteDAO();
		dao.favInsert(vo);

		return "redirect: ../ad/ad.do?cid=" + cid + "&adid=" + adid;
	}

	@RequestMapping("users/favorite.do")
	public String favorite_List(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("id");

		// 공고 번호, 공고정보
		FavoriteDAO dao = new FavoriteDAO();
		
		// 즐찾 공고번호 리스트
		List<Integer> flist = dao.favListData(uid);

		// 공고번호들의 공고정보리스트, 회사정보리스트
		List<AdVO> adList = new ArrayList<AdVO>();
		List<String> cList = new ArrayList<String>();

		AdDAO a = new AdDAO();
		AdVO vo;
		CompanyDAO c = new CompanyDAO();

		List<Integer> fid_list = new ArrayList<Integer>();
		
		// 공고번호 for문
		for (int i : flist) {
			vo = a.adDetail(i);
			adList.add(vo);
			cList.add(c.companyName(vo.getC_id()));
			fid_list.add(dao.favData(uid, i));
		}


		request.setAttribute("adList", adList);
		request.setAttribute("c_name", cList);
		request.setAttribute("fid_list", fid_list);
		request.setAttribute("main_jsp", "../users/favorite_Edit.jsp");
		return "../main/main.jsp";

	}

	@RequestMapping("favorite/delete.do")
	public String favorite_delete(HttpServletRequest request, HttpServletResponse response) {
		// 마이페이지 - 즐겨찾기관리 - 삭제
		
		String fid = request.getParameter("fid");
		FavoriteDAO dao = new FavoriteDAO();
		dao.favDelete(Integer.parseInt(fid));

		return "redirect: ../users/favorite.do";
	}

	@RequestMapping("ad_favorite/delete.do")
	public String adfav_delete(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("id");		
		String adid = request.getParameter("adid");
		
		FavoriteDAO dao = new FavoriteDAO();
		dao.ad_favDelete(uid, Integer.parseInt(adid));
		
		return "redirect: ../ad.ad.do?adid=" + adid;
	}
	
}
