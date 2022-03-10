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
