package com.sist.model;

import javax.servlet.http.*;

import com.sist.dao.*;

import com.sist.controller.*;

public class FavoriteModel {

	@RequestMapping("favorite/insert.do")
	public String favorite_insert(HttpServletRequest request, HttpServletResponse response) {
		String adid = request.getParameter("adid");
		
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("id");
				
		FavoriteDAO dao = new FavoriteDAO();
		int fid = dao.favInsert(uid, Integer.parseInt(adid));
		request.setAttribute("result", fid);
        return "../users/result.jsp";
	}

	@RequestMapping("favorite/delete.do")
	public void favorite_delete(HttpServletRequest request, HttpServletResponse response) {
		String fid = request.getParameter("fid");
		
		FavoriteDAO dao = new FavoriteDAO();
		dao.favDelete(Integer.parseInt(fid));
	}
}
