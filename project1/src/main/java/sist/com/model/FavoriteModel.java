package sist.com.model;

import sist.com.vo.*;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sist.com.controller.RequestMapping;
import sist.com.dao.*;

public class FavoriteModel {

	@RequestMapping("favorite/insert.do")
	public String favorite_insert(HttpServletRequest request, HttpServletResponse response) {
		String cid = request.getParameter("cid");
		String fid = request.getParameter("fid");
		String uid = request.getParameter("uid");
		String adid = request.getParameter("adid");

		FavoriteVO vo = new FavoriteVO();
		vo.setFav_id(Integer.parseInt(fid));
		vo.setU_id(uid);
		vo.setAd_id(Integer.parseInt(adid));

		FavoriteDAO dao = new FavoriteDAO();
		dao.favInsert(vo);

		return "redirect:../ad/ad.do?cid=" + cid + "&adid=" + adid;
	}

	@RequestMapping("favorite/favorite.do")
	public String favorite_List(HttpServletRequest request, HttpServletResponse response) {

		String uid = request.getParameter("uid");

		FavoriteDAO dao = new FavoriteDAO();
		List<FavoriteVO> flist = dao.favListData(uid);
		
/*		for (FavoriteVO vo : flist) {
			String poster = vo.getPoster();
			poster = poster.substring(0, poster.indexOf("^"));
*/
		
		request.setAttribute("favorite", flist);
		return "";

	}
	
	@RequestMapping("favorite/delete.do")
	public String favorite_delete(HttpServletRequest request, HttpServletResponse response) {
		String fid = request.getParameter("fid");
		FavoriteDAO dao = new FavoriteDAO();
		dao.favDelete(Integer.parseInt(fid));
		
		return "redirect:../";
	}
	
}
















